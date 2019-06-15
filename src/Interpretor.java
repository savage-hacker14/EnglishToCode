// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is responsible for creating Command objects from user input

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
		paramStr = inputNWS.substring(start + 1);
		if (start != -1) {
			name = inputNWS.substring(cmdIdx + cmdLen, start);
		}
		
		// Find type (only for var, list, arr. and mat)
		String type = "";
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
		
		// Then return the created Command object
		return new Command(command, name, type, paramStr);
	}
	
	public static void createLineOfCode(Command c) {
		String code = "";
		String lang = c.getLanguage();
		
		if (lang == "java") {
			code = c.getType() + " " + c.getName() + " = " + c.getParameters() + ";";
		}
		else if (lang == "c++") {
			
		}
		else {	// Language is Python
			code = c.getName() + " = " + c.getParameters();
		}
		
		c.setLineOfCode(code);
	}
}
