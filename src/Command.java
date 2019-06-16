// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is the base for any command in Java, c++, or Python

public class Command {
	// Instance variables name
	private String command = "";		// Command name: var, list, arr, etc.
	private String name = "";			// Variable name (if applicable)
	private String type = "";			// Type (if applicable)
	private String params = "";			// Parameters (i.e a = 5, 5 is parameter)
	private String lang = "";			// Programming language the code should be written for 
	private String lineOfCode = "";		// The actual line of code that will be written to the code file
	
	public Command(String commandToSet, String nameToSet, String typeToSet, String parametersToSet) {
		command = commandToSet;
		name = nameToSet;
		type = typeToSet;
		params = parametersToSet;
	}
	
	public void setLanguage(String str) {
		lang = str;
	}
	
	public String getLineOfCode() {	
		return lineOfCode;
	}
	
	public void setLineOfCode(String lineOfCodeToSet) {
		lineOfCode = lineOfCodeToSet;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getParameters() {
		return params;
	}
	
	public String getLanguage() {
		return lang;
	}
	
	public String toString() {
		String output = "";
		output += "Command Details:\n";
		output += "Command:\t" + command + "\n";
		output += "Name:\t\t" + name + "\n";
		output += "Type:\t\t" + type + "\n";
		output += "Parameters:\t" + params + "\n";
		output += "Language:\t" + lang + "\n";
		output += "Line of code:\t" + lineOfCode + "\n";
		
		return output;
	}
}
