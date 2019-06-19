// Program.java
// Written by Jacob Krucinski on 6/13/19
// This object class contains all the data on the program created by the user

import java.util.ArrayList;

public class Program {
	// Instance variables
	private ArrayList<Command> commands = new ArrayList<Command>();
	
	/**
	 * Create a program object with no data
	 */
	public Program() {}
	
	/**
	 * Create a program object with an ArrayList of Command objects
	 * @param cmdArray
	 */
	public Program(ArrayList<Command> cmdArray) {
		commands = cmdArray;
	}
	
	/**
	 * Add a command to the commands ArrayList
	 * @param c
	 */
	public void addCommand(Command c) {
		commands.add(c);
	}
	
	/**
	 * Get a given command based on its index in the ArrayList
	 * @param index (0 based)
	 * @return
	 */
	public Command getCommand(int i) {
		return commands.get(i);
	}
	
	/**
	 * Returns the following:
	 * @return length of commands ArrayLists
	 */
	public int numCommands() {
		return commands.size();
	}
}
