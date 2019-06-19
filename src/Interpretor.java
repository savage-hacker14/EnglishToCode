// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is responsible for creating Command objects from user input

// Import packages
import java.util.ArrayList;


public class Interpretor {
	// List of commands for string-based code creation
	private static final String[] cmds = {"display", "var", "arr", "list", "mat"};
	
	/**
	 * This method takes the user code string and extracts its command, name, parameters, and type
	 * @param input
	 * @return Command object with the extracted data
	 */
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
				cmdIdx = inputNWS.indexOf(cmd);		// cmd location in string
				cmdLen = cmd.length();				// length of cmd
			}
		}		
		
		// Find name and parameters of command
		String paramStr = "";
		String name = "";
		int eq = inputNWS.indexOf("=");
		
		// If line of code isn't an expression
		if (eq != -1) {
			name = inputNWS.substring(cmdIdx + cmdLen, eq);	
		}
		
		if (!(command.equals("mat") || command.contentEquals("arr"))) {
			// Command is not "mat" or "arr"
			paramStr = inputNWS.substring(eq + 1); 		// Get string after the equal sign
			
			if (command.equals("display")) {
				// Special instructions for display command
				if (input.indexOf("\"") != -1) {		// Special case to check for "s
					int idx = input.indexOf("\"");
					paramStr = input.substring(idx);
				}
				else {
					paramStr = input.substring(cmdLen + 1);		// displaying a variable (i.e display a)
				}
			}
		}
		else {
			// Special matrix and array interpretation
			int brack = input.indexOf("[");
			int end = input.indexOf("]");
			String values = input.substring(brack + 1, end);		// Get the string of values between the brackets
			
			if (command.contentEquals("mat")) {						// Interpret values string as arr or mat, depending on command
				paramStr = interpretMat(values);
			}
			else {
				paramStr = interpretArr(values);
			}
		}
		
		// Find type (only for var, list, arr. and mat)
		String type = "";
		
		if (!command.equals("display")) {
			// If the command is not "display"
			if (!(command.equals("mat") || command.contentEquals("arr"))) {
				// If the command is not "mat" or "arr"
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
					
					// HOWEVER THIS MAY BE CONFUSED WITH JUST REGULAR EXPRESSIONS
					// So add this
					if (command.equals("")) {
						type = "";
					}
				}
			}
			else {
				// Command is "mat" or "arr"
				if (command.equals("mat")) {
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
				else {
					// "arr" command
					if (Character.isDigit(paramStr.charAt(1)) && paramStr.indexOf(".") == -1) {
						// Integers only
						type = "int[]";
					}
					else if (Character.isDigit(paramStr.charAt(1)) && paramStr.indexOf(".") != -1) {
						// There is a double somewhere in the arr definition
						type = "double[]";
					}
					else {
						// The array is a array of strings
						type = "String[]";
					}
				}
			}
		}
		
		// Then return the created Command object
		return new Command(command, name, type, paramStr);
	}
	
	
	public static void createLineOfCode(Command c) {
		// Grab important variables from command c
		String code = "";
		String lang = c.getLanguage();
		
		
		if (lang.equals("java")) {
			// Java line creation
			if (!c.getCommand().equals("display")) {
				// If command is not "display"
				if (!c.getType().equals("")) {
					// If no type exists in the command
					code = c.getType() + " " + c.getName() + " = " + c.getParameters() + ";";
				}
				else {
					// Command has a type
					if (c.getCommand().equals("") && c.getName().equals("") && c.getType().equals("")) {
						// Regular expression (command, name, and type is blank)
						code = c.getParameters() + ";";
					}
					else {
						// Regular line of code: type, var name, equals, and parameters (content)
						code = c.getType() + c.getName() + " = " + c.getParameters() + ";";
					}
				}
			}
			else {
				// Display command
				code = "System.out.println(" + c.getParameters() + ");";
			}
		}
		else if (lang.equals("c++")) {
			// C++ line creation
			if (!c.getCommand().equals("display")) {
				// If command is not "display"
				if (!c.getType().equals("")) {
					// If no type exists in the command
					code = c.getType() + " " + c.getName() + " = " + c.getParameters() + ";";
				}
				else {
					// Command has a type
					if (c.getCommand().equals("") && c.getName().equals("") && c.getType().equals("")) {
						// Regular expression (command, name, and type is blank)
						code = c.getParameters() + ";";
					}
					else {
						// Regular line of code: type, var name, equals, and parameters (content)
						code = c.getType() + c.getName() + " = " + c.getParameters() + ";";
					}
				}
			}
			else {
				// Display command
				code = "cout << " + c.getParameters() + " << endl;";
			}
		}
		else {	// Language is Python
			if (!c.getCommand().equals("display")) {
				// Command is not "display"
				code = c.getName() + " = " + c.getParameters();
			}
			else {
				// Display command
				code = "print(" + c.getParameters() + ")";
			}
		}
		
		c.setLineOfCode(code);
	}
	
	/**
	 * Special helper method for parsing a string of values into a 2D array (matrix)
	 * NOTE: ONLY WORKS FOR JAVA AND C++
	 * @param vals
	 * @return Explicit definition of a 2D array (matrix) 
	 */
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
		
		// Extract the values from each row
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
				
				s = s.substring(spaceIdx + 1);		// Update string (remove value just added to output)
			}
			
			output += "},{";
		}
		
		output += "}}";		// Finish brackets for the explicit mat definition
		
		// This code adds and extra ",{}" at the end of the string, REMOVE IT
		output = output.replace(",{}", "");
		
		return output;
	}
	
	/**
	 * Special helper method for parsing a string of values into an array
	 * NOTE; ONLY WORKS FOR JAVA AND C++
	 * @param vals
	 * @return Explicit definition of an array
	 */
	private static String interpretArr(String vals) {
		String output = "{";
		
		// Extract each value from the string
		while (vals.length() > 0) {
			int spaceIdx = vals.indexOf(" ");
			
			if (spaceIdx != -1) {
				output += vals.substring(0, spaceIdx) + ",";
			}
			else {
				output += vals;		// Prevents outOfBounds error and doesn't add , after last value
				break;
			}
			
			vals = vals.substring(spaceIdx + 1); // Update string (remove value just added to output)
		}
		
		output += "}";
		
		return output;
	}
}
