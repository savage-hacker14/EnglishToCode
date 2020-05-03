// Function.java
// This class represents an Object that stores the necessary information that would be needed
// the creation of a function/method

// Import necessary packages
import java.util.ArrayList;

public class Function {
	// Instance variables
	private ArrayList<Command> commands;
	private String name; 
	private String[] params;
	private String privacyLevel; 			// Public, Private, and Protected - Only applies to Java and C++
	private String returnType;				// Type of variable returned by the function (int, double, void) - Only applies to Java and C++
	private boolean staticOrNot;			// Is the method static or not - Only applies to Java and C++
	private String returnVar;				// The variable to be returned from the function (i.e return a --> a)
	
	/**
	 * Default constructor for a Function object.
	 * Work in progress.
	 */
	public Function() {
		commands = new ArrayList<Command>();
		name = "Function";
		params = new String[1];
		privacyLevel = "public";
		returnType = "String";
		staticOrNot = true;
		returnVar = "";
	}
	
	/**
	 * Minimal constructor for a Function object
	 * @param cmds - Commands to be included within the Function
	 * @param nameToSet - Name of the function
	 * @param paramsToSet - Parameters of the function
	 * @param returnVarToSet - The name of the variable to return from the function
	 */
	public Function(ArrayList<Command> cmds, String nameToSet, String[] paramsToSet, String returnVarToSet) {
		commands = cmds;
		name = nameToSet;
		params = paramsToSet;
		returnVar = returnVarToSet;
	}
	
	/**
	 * Full constructor for a Function object
	 * @param cmds - Commands to be included within the Function
	 * @param nameToSet - Name of the function
	 * @param paramsToSet - Parameters of the function
	 * @param privacyLevelToSet - Privacy level of the function (public, private, or protected - Only for Java and C++)
	 * @param returnTypeToSet - Data type of the return variable (int, double, boolean, etc.)
	 * @param staticFlag - 
	 * @param returnVarToSet
	 */
	public Function(ArrayList<Command> cmds, String nameToSet, String[] paramsToSet, String privacyLevelToSet, String returnTypeToSet, boolean staticFlag, String returnVarToSet) {
		commands = cmds;
		name = nameToSet;
		params = paramsToSet;
		privacyLevel = privacyLevelToSet;
		returnType = returnTypeToSet;
		staticOrNot = staticFlag;
		returnVar = returnVarToSet;
	}
}
