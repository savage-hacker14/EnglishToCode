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
	
	public Function() {
		commands = new ArrayList<Command>();
		name = "Function";
		params = new String[1];
		privacyLevel = "public";
		returnType = "String";
		staticOrNot = true;
		returnVar = "";
	}
	
	public Function(ArrayList<Command> cmds, String nameToSet, String[] paramsToSet, String returnVarToSet) {
		commands = cmds;
		name = nameToSet;
		params = paramsToSet;
		returnVar = returnVarToSet;
	}
	
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
