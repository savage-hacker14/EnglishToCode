// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is the base for any command in Java, c++, or Python

public class Command {
	// Instance variables name
	private String command;			// Command name: var, list, arr, etc.
	private String name;			// Variable name (if applicable)
	private String type;			// Type (if applicable)
	private String params;			// Parameters (i.e a = 5, 5 is parameter)
	private String lang;			// Programming language the code should be written for 
	private String lineOfCode;		// The actual line of code that will be written to the code file
	private int indentLevel; 		// Determines how indented the command should be
	
	/** 
	 * Default Command object constructor
	 * It initializes all instance variables of Command object with blank strings
	 */
	public Command() {
		command = "";
		name = "";
		type = "";
		params = "";
		lang = "";
		lineOfCode = "";
		setIndentLevel(2);				// Java default
	}
	
	/**
	 * Command object constructor
	 * @param commandToSet - Command used (i.e var, arr, list, etc.)
	 * @param nameToSet - Name of the variable (if used)
	 * @param typeToSet - Data type of the var, arr, etc. (i.e int, double, String, etc.)
	 * @param parametersToSet - Parameters (i.e the string to be displayed, the values that need to be assigned to the variable, array, etc.
	 */
	public Command(String commandToSet, String nameToSet, String typeToSet, String parametersToSet) {
		command = commandToSet;
		name = nameToSet;
		type = typeToSet;
		params = parametersToSet;
		setIndentLevel(2);				// Java default
	}
	
	/**
	 * Set the language of the command
	 * @param str - java, c++, or python
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
	 * Set the command instance variable of Command 
	 * i.e mat, var, display, ForLoop, etc.
	 * @param str - String (see above)
	 */
	public void setCommand(String str) {
		command = str;
	}
	
	/**
	 * Get the line of code for the command (in the proper language) 
	 * Used for code generation
	 * @return lineOfCode
	 */
	public String getLineOfCode() {	
		return lineOfCode;
	}
	
	/**
	 * Set the line of code for the command (in the proper language) 
	 * Used for code generation
	 * @return lineOfCode
	 */
	public void setLineOfCode(String lineOfCodeToSet) {
		lineOfCode = lineOfCodeToSet;
	}
	
	/**
	 * Get the name of the command (in the proper language) 
	 * Used for code generation
	 * @return lineOfCode
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the type instance variable of the Command object
	 * @param str - Type (i.e double, int, etc.)
	 */
	public void setType(String str) {
		type = str;
	}
	
	/**
	 * Sets the indentLevel instance variable
	 * @param i - int to set the indentLevel to
	 */
	public void setIndentLevel(int i) {
		indentLevel = i;
	}
	
	/**
	 * Returns the indentLevel instance variable
	 * @return indentLevel - The number of tabs to insert before the line of code of the Command object
	 */
	public int getIndentLevel() {
		return indentLevel;
	}
	
	/**
	 * This method converts the indentLevel instance variable into a string with the proper number of tabs
	 * @return A String with indentLevel number of tabs
	 */
	public String indent() {
		String output = "";
		
		if (indentLevel == 0) {
			return output;
		}
		else {
			// Indent does not equal 0 (1 or above)
			for (int i = 1; i <= indentLevel; i++) {
				output += "\t";
			}
		}
		
		return output;
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
		output += "# of Indents:\t" + indentLevel + "\n";
		output += "Line of code:\n" + lineOfCode + "\n";
		
		return output;
	}
}
