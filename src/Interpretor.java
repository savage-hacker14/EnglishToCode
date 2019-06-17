// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is responsible for creating Command objects from user input

// Import packages
import java.util.ArrayList;


public class Interpretor {
	// List of commands for string-based code creation
	private static final String[] cmds = {"display", "var", "arr", "list", "mat"};
	
	public static Command interpret(String input) {
		// Find input string with all spaces removed
		String inputNWS = input.replaceAll("\\s", "");
		
		// Find command
		int cmdIdx = 0;
		int cmdLen = 0;
		String command = "";
		for (String cmd : cmds) {
			if (inputNWS.indexOf(cmd) != -1) {
				command = cmd;
				cmdIdx = inputNWS.indexOf(cmd);
				cmdLen = cmd.length();
			}
		}		
		
		// Find name and parameters of command
		String paramStr = "";
		String name = "";
		int start = inputNWS.indexOf("=");
		
		if (start != -1) {
			name = inputNWS.substring(cmdIdx + cmdLen, start);
		}
		
		if (!command.equals("mat")) {
			paramStr = inputNWS.substring(start + 1);
			
			if (command.equals("display")) {
				// Special instructions for display command
				if (input.indexOf("\"") != -1) {
					int idx = input.indexOf("\"");
					paramStr = input.substring(idx);
				}
				else {
					paramStr = input.substring(cmdLen + 1);
				}
			}
		}
		else {
			// Special matrix interpretation
			start = input.indexOf("[");
			int end = input.indexOf("]");
			String values = input.substring(start + 1, end);
			
			paramStr = interpretMat(values);
		}
		
		// Find type (only for var, list, arr. and mat)
		String type = "";
		if (!command.equals("display")) {
			if (!command.equals("mat")) {
				if (Character.isDigit(paramStr.charAt(0)) && inputNWS.indexOf(".") == -1) {
					// The line contains an integer
					type = "int";
					
				}
				else if (Character.isDigit(paramStr.charAt(0)) && inputNWS.indexOf(".") != -1) {
					// The line contains a double or float
					type = "double";
				}
				else {
					// The line contains a string (i.e "ab") 
					type = "String";
				}
			}
			else {
				// Command is "mat"
				if (Character.isDigit(paramStr.charAt(2)) && paramStr.indexOf(".") == -1) {
					// Integers only
					type = "int[][]";
				}
				else if (Character.isDigit(paramStr.charAt(2)) && paramStr.indexOf(".") != -1) {
					// There is a double somewhere in the mat definition
					type = "double[][]";
				}
				else {
					// The mat is a mat of strings
					type = "String[][]";
				}
			}
		}
		
		// Then return the created Command object
		return new Command(command, name, type, paramStr);
	}
	
	public static void createLineOfCode(Command c) {
		String code = "";
		String lang = c.getLanguage();
		
		if (lang.equals("java")) {
			if (!c.getCommand().equals("display")) {
				code = c.getType() + " " + c.getName() + " = " + c.getParameters() + ";";
			}
			else {
				// Display command
				code = "System.out.println(" + c.getParameters() + ");";
			}
		}
		else if (lang.equals("c++")) {
			
		}
		else {	// Language is Python
			if (!c.getCommand().equals("display")) {
				code = c.getName() + " = " + c.getParameters();
			}
			else {
				// Display command
				code = "print(" + c.getParameters() + ")";
			}
		}
		
		c.setLineOfCode(code);
	}
	
	private static String interpretMat(String vals) {
		// This method takes a list of numbers and puts them into a mat
		// i.e [1 1;2 2] --> {{1,1},{2,2}} (for java at least)
		
		String output = "{{";
		
		// Parse vals string to build a string array of all the matrix arrays
		ArrayList<String> rows = new ArrayList<String>();
		while (vals.indexOf(";") != -1) {
			int i = vals.indexOf(";");
			rows.add(vals.substring(0, i));
			vals = vals.substring(i + 1);
			
			if (vals.indexOf(";") == -1) {
				rows.add(vals);
				break;
			}
		}
		
		for (int i = 0; i < rows.size(); i++) {
			String s = rows.get(i);
			while (s.length() > 0) {
				int spaceIdx = s.indexOf(" ");
				
				if (spaceIdx == -1) {
					// Last value in the row will always encounter this
					output += s;
					break;
				}
				
				String value = s.substring(0, spaceIdx);
				output += value + ",";
				
				s = s.substring(spaceIdx + 1);
			}
			
			output += "},{";
		}
		
		output += "}}";
		
		// This code adds and extra ",{}" at the end of the string, REMOVE IT
		output = output.replace(",{}", "");
		
		return output;
	}
}
