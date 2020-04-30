// FoorLoop.java
// This object handles users trying to implement a for loop
// It also stores all the commands to be run in the for loop

import java.util.ArrayList;

public class ForLoop extends Command {
	// Instance variables
	private ArrayList<Command> commands = new ArrayList<Command>();
	private String loopVar;
	private String start;					// These are string types so that ints and doubles are supported
	private String end; 
	private String increment;
	private int numNested;					// Denotes which nested for loop a ForLoop object is. No nest is 0
	
	/**
	 * Default constructor for ForLoop object that initializes loop variable to the typical "i" and increment of one
	 * It also sets the other instance variables to default values of 0
	 */
	public ForLoop() {
		setCommand("ForLoop");
		commands = null;
		loopVar = "i"; 		// default var
		start = "0";
		end = "0";
		increment = "1";
		numNested = 0;
	}
	
	/**
	 * Main constructor for ForLoop object that takes in values for all instance variables
	 * @param cmds - The ArrayList of commands to be executed within the for loop
	 * @param lan - The language the ForLoop is to be written in
	 * @param lVar - The name of the looping variable (i.e "i")
	 * @param s - The start value of the looping variable 
	 * @param e - The end value of the looping variable (exclusive when looping)
	 * @param i - The value to increment the looping variable after each iteration of the for loop
	 */
	public ForLoop(int numNestedToSet, ArrayList<Command> cmds, String lan, String type, String lVar, String s, String e, String i) {
		setCommand("ForLoop");
		
		numNested = numNestedToSet;
		
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
		
		commands = cmds;
		loopVar = lVar;
		start = s;
		end = e;
		increment = i;
		setLanguage(lan);
		setType(type);
	}
	
	/**
	 * This method returns the commands instance variable
	 * @return ArrayList of commands to be executed within the for loop
	 */
	public ArrayList<Command> getCommands() {
		return commands;
	}
	
	/**
	 * This method returns the size of the commands instance variable
	 * @return The size of the commands ArrayList (the number of commands to be placed in the for loop)
	 */
	public int getNumCommands() {
		return commands.size();
	}
	
	/**
	 * This methods returns the loopvar instance variable
	 * @return The name of the looping variable
	 */
	public String getLoopVar() {
		return loopVar;
	}
	
	/**
	 * This method returns the start instance variable
	 * @return The number on which the looping variable starts at
	 */
	public String getStart() {
		return start;
	}
	
	/**
	 * This method returns the end instance variable
	 * @return The number on which the looping variable ends at (-1 due to exclusiveness)
	 */
	public String getEnd() {
		return end;
	}
	
	/**
	 * This methods return the increment variable
	 * @return The number at which the looping variable is incremented during each iteration of the for loop
	 */
	public String getIncrement() {
		return increment;
	}
}
