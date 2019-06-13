// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is the base for any command in Java, c++, or Python

// Necessary imports
import java.util.ArrayList;

public class Command {
	// List of commands for string-based code creation
	private final String[] cmds = {"display", "var"};
	
	// Instance variables name
	private String name;
	private ArrayList<Object> parameters;
	private String lang;
	
	public String createLineOfCode() {
		String output = "";
		
		switch(lang) {
			case "java":
				break;
			case "c++":
				break;
			case "python":
				break;
			
		}
	}
	
	public void setLanguage(String str) {
		lang = str;
	}
	
	public void interpret(String str) {
		// This method analyzes the string inputted by the user and creates the proper Command object
		
	}
}
