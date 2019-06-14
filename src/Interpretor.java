// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is responsible for creating Command objects from user input

public class Interpretor {
	// List of commands for string-based code creation
	private static final String[] cmds = {"display", "var", "arr", "list", "mat"};
	
	public static Command interpret(String input) {
		// Find input string with all spaces removed
		String inputNWS = input.replaceAll("\\s", "");
		
		// Find name of command
		String name = "";
		for (String cmd : cmds) {
			if (inputNWS.indexOf(cmd) != -1) {
				name = cmd;
			}
		}		
		
		// Find parameters of command
		String params = "";
		int start = inputNWS.indexOf("=");
		String paramStr = inputNWS.substring(start + 1);
		
		// Find type (only for var, list)
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
		
		
		
		return new Command(name, type, params);
	}
	
	public static String createLineOfCode(Command c) {
		String output = "";
		String lang = c.getLanguage();
		
		if (lang == "java") {
			
		}
		else if (lang == "c++") {
			
		}
		else {	// Language is Python
			
		}
		
		return output;
	}
}
