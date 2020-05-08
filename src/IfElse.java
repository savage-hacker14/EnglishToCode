// IfElse.java
// This class encapsulates data for creating if/else expressions in a program

import java.util.ArrayList;

public class IfElse extends Command {
	// Instance variables
	// Use paramStr inherited from Command class for storing logical condition
	private ArrayList<Command> cmdsIfTrue;
	private ArrayList<Command> cmdsIfFalse;
	private int numNested;
	
	public IfElse() {
		super();
		setCommand("If");
		setIndentLevel(0);
		numNested = 0;
	}
	
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
	
	public ArrayList<Command> getIfTrueCommands() {
		return cmdsIfTrue;
	}
	
	public ArrayList<Command> getIfFalseCommands() {
		return cmdsIfFalse;
	}
}
