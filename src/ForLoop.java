// FoorLoop.java
// This object handles users trying to implement a for loop
// It also stores all the commands to be run in the for loop

import java.util.ArrayList;

public class ForLoop extends Command {
	// Instance variables
	private ArrayList<Command> commands = new ArrayList<Command>();
	private String loopVar;
	private int start;					// Modify later to support vars (change to String type)
	private int end; 
	private int increment;
	
	/**
	 * Default constructor for ForLoop object
	 * @param None
	 * @return default initialized ForLoop variable
	 */
	public ForLoop() {
		setCommand("ForLoop");
		commands = null;
		loopVar = "i"; 		// default var
		start = 0;
		end = 0;
		increment = 1;
	}
	
	public ForLoop(ArrayList<Command> cmds, String lan, String lVar, int s, int e, int i) {
		setCommand("ForLoop");
		commands = cmds;
		loopVar = lVar;
		start = s;
		end = e;
		increment = i;
		setLanguage(lan);
	}
	
	public ArrayList<Command> getCommands() {
		return commands;
	}
	
	public int getNumCommands() {
		return commands.size();
	}
	
	public String getLoopVar() {
		return loopVar;
	}
	
	public int getStart() {
		return start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public int getIncrement() {
		return increment;
	}
}
