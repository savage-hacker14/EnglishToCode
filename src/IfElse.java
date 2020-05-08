// IfElse.java
// This class encapsulates data for creating if/else expressions in a program

import java.util.ArrayList;

public class IfElse extends Command {
	// Instance variables
	// Use paramStr inherited from Command class for storing logical condition
	private ArrayList<Command> cmdsIfTrue;
	private ArrayList<Command> cmdsIfFalse;
	private int numNested;
	
	/**
	 * Default constructor for IfElse object
	 */
	public IfElse() {
		super();
		setCommand("If");
		setIndentLevel(0);
		numNested = 0;
	}
	
	/** 
	 * Main constructor for IfElse object
	 * @param lan - Language for the IfElse object
	 * @param logic - The logical expression for the if statement
	 * @param cmdsTrueToSet - The list of Command objects to run if the logic expression evaluates to true
	 * @param cmdsFalseToSet - The list of Command objects to run if the logic expression evaluates to false
	 * @param numNestedToSet - The nested number for the IfElse expression
	 */
	public IfElse(String lan, String logic, ArrayList<Command> cmdsTrueToSet, ArrayList<Command> cmdsFalseToSet, int numNestedToSet) {
		setCommand("If");
		setParameters(logic);
		setLanguage(lan);
		
		if (lan.equals("java")) {
			setIndentLevel(2 + numNested);
		}
		else if (lan.equals("c++")) {
			setIndentLevel(1 + numNested);
		}
		else {
			// Python
			setIndentLevel(numNested);
		}
		
		cmdsIfTrue = cmdsTrueToSet;
		cmdsIfFalse = cmdsFalseToSet;
		numNested = numNestedToSet;
	}
	
	/**
	 * Return the ArrayList of Command objects for to run when the logical expression evaluates to true
	 * @return ArrayList of Command objects
	 */
	public ArrayList<Command> getIfTrueCommands() {
		return cmdsIfTrue;
	}
	
	/**
	 * Return the ArrayList of Command objects for to run when the logical expression evaluates to false
	 * @return ArrayList of Command objects
	 */
	public ArrayList<Command> getIfFalseCommands() {
		return cmdsIfFalse;
	}
}
