// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is the base for any command in Java, c++, or Python

public class Command {
	// Instance variables name
	private String command;		// Command name: var, list, arr, etc.
	private String name;			// Variable name (if applicable)
	private String type;			// Type (if applicable)
	private String params;			// Parameters (i.e a = 5, 5 is parameter)
	private String lang;			// Programming language the code should be written for 
	private String lineOfCode;		// The actual line of code that will be written to the code file
	
	/**
	 * Command object constructor
	 * @param Command used (i.e var, arr, list, etc.)
	 * @param Name of the variable (if used)
	 * @param Data type of the var, arr, etc. (i.e int, double, String, etc.)
	 * @param Parameters (i.e the string to be displayed, the values that need to be assigned to the variable, array, etc.
	 */
	public Command(String commandToSet, String nameToSet, String typeToSet, String parametersToSet) {
		command = commandToSet;
		name = nameToSet;
		type = typeToSet;
		params = parametersToSet;
	}
	
	/**
	 * Set the language of the command
	 * @param string
	 */
	public void setLanguage(String str) {
		lang = str;
	}
	
	/**
	 * Get the command of the Command object 
	 * Used for code generation
	 * @return command
	 */
	public String getCommand() {
		return command;
	}
	
	/**
	 * Get the line of code for the command (in the proper language) 
	 * Used for code generation
	 * @return lineCfCode
	 */
	public String getLineOfCode() {	
		return lineOfCode;
	}
	
	/**
	 * Set the line of code for the command (in the proper language) 
	 * Used for code generation
	 * @return lineCfCode
	 */
	public void setLineOfCode(String lineOfCodeToSet) {
		lineOfCode = lineOfCodeToSet;
	}
	
	/**
	 * Get the name of the command (in the proper language) 
	 * Used for code generation
	 * @return lineCfCode
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the name of the command (in the proper language) 
	 * Used for code generation
	 * @return lineCfCode
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Get the parameters of the command (in the proper language) 
	 * Used for code generation
	 * @return lineCfCode
	 */
	public String getParameters() {
		return params;
	}

	/**
	 * Get the language of the command
	 * Used for code generation
	 * @return lineCfCode
	 */
	public String getLanguage() {
		return lang;
	}
	
	/**
	 * To string method, useful for debugging and testing
	 * @return Values of all instance variables
	 */
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
