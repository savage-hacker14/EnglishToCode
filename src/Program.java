// Program.java
// Written by Jacob Krucinski on 6/13/19
// This object class contains all the data on the program created by the user

import java.util.ArrayList;

public class Program {
	// Instance variables
	private ArrayList<Command> commands = new ArrayList<Command>();
	private ArrayList<Function> functions = new ArrayList<Function>();
	
	/**
	 * Create a program object with no data
	 */
	public Program() {}
	
	/**
	 * Create a program object with an ArrayList of Command objects
	 * @param cmdArray - ArrayList of Command objects within Program object
	 */
	public Program(ArrayList<Command> cmdArray) {
		commands = cmdArray;
	}
	
	/**
	 * Add a command to the commands ArrayList
	 * @param c - The Command to add
	 */
	public void addCommand(Command c) {
		commands.add(c);
	}
	
	/**
	 * Get a given command based on its index in the ArrayList
	 * @param i - index (0 based)
	 * @return
	 */
	public Command getCommand(int i) {
		return commands.get(i);
	}
	
	/**
	 * Returns the following:
	 * @return length of commands ArrayList
	 */
	public int numCommands() {
		return commands.size();
	}
}
