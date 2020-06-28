// Function.java
// This class represents an Object that stores the necessary information that would be needed
// the creation of a function/method

// Import necessary packages
import java.util.ArrayList;

public class Function extends Command {
	// Instance variables
	private ArrayList<Command> commands;	// Commands to be run inside of the function
	private String returnType;				// Type of variable returned by the function (int, double, void) - Only applies to Java and C++
	private String returnVar;				// The variable to be returned from the function (i.e return a --> a)
	
	/**
	 * Minimal constructor for a Function object.
	 */
	public Function() {
		super();
		super.setCommand("Function");
		
		commands = new ArrayList<Command>();
		returnType = "";
		returnVar = "";
	}
	
	/**
	 * Default constructor for a Function object
	 * @param langToSet - Language to set for the function
	 * @param cmds - Commands to be included within the Function
	 * @param nameToSet - Name of the function
	 * @param paramsToSet - Parameters of the function (as a string)
	 * @param returnVarToSet - The name of the variable to return from the function
	 */
	public Function(String langToSet, ArrayList<Command> cmds, String nameToSet, String paramsToSet, String returnVarToSet) {
		super.setCommand("Function");
		super.setLanguage(langToSet);
		commands = cmds;
		super.setName(nameToSet);
		super.setParameters(paramsToSet);
		returnVar = returnVarToSet;
	}
	
	/**
	 * Full constructor for a Function object
	 * @param langToSet - Language to set for the function
	 * @param nameToSet - Name of the function
	 * @param paramsToSet - Parameters of the function
	 * @param cmds - Commands to be included within the Function
	 * @param returnTypeToSet - Data type of the return variable (int, double, boolean, etc.)
	 * @param returnVarToSet - The name of the variable to return from the function
	 */
	public Function(String langToSet, ArrayList<Command> cmds, String nameToSet, String paramsToSet, String returnTypeToSet, String returnVarToSet) {
		super.setCommand("Function");
		super.setLanguage(langToSet);
		commands = cmds;
		super.setName(nameToSet);
		super.setParameters(paramsToSet);
		returnType = returnTypeToSet;
		returnVar = returnVarToSet;
	}
	
	public ArrayList<Command> getCommands() {
		return commands;
	}
	
	public String getReturnVar() {
		return returnVar;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public void setReturnType(String retType) {
		returnType = retType;
	}
	
	public String toString() {
		String output = "";
		
		output += "Function details:\n";
		output += "Name:\t\t" + super.getName() + "\n";
		output += "Language:\t" + super.getLanguage() + "\n";
		output += "Parameters:\t" + super.getParameters() + "\n";
		output += "Num commands:\t" + commands.size() + "\n";
		output += "Return var:\t" + returnVar + "\n";
		output += "Return type:\t" + returnType + "\n";
		output += "Code:\n" + super.getLineOfCode();
		
		return output;
	}
	
	public String[] convertParametersToArray() {
		String params = super.getParameters();
		
		int[] commaLocations = Interpretor.findAllCommas(params);
		String[] paramsArr = new String[commaLocations.length + 1];
		
		for (int i = 0; i < paramsArr.length; i++) {
			if (i == paramsArr.length - 1) {
				paramsArr[i] = params;
			}
			else {
				paramsArr[i] = params.substring(0, params.indexOf(","));
			}
			
			params = params.substring(params.indexOf(",") + 1);
		}
		
		return paramsArr;
	}
}
