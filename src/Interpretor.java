// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is responsible for creating Command objects from user input

public class Interpretor {
	// List of commands for string-based code creation
	private static final String[] cmds = {"display", "var", "arr", "list", "mat"};
	
	public static Command interpret(String input) {
		// Find name of command
		String name;
		for (String cmd : cmds) {
			if (input.indexOf(cmd) != -1) {
				name = cmd;
			}
		}
		
		// Find type (only for var, list)
		
		// Find parameters of command
		String params;
		int start = input.indexOf("=");
		if (input.charAt(start + 1) != ' ') {
			String paramStr = input.substring(start + 1);
		}
		else {
			
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
