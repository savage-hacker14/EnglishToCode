// Program.java
// Written by Jacob Krucinski on 6/13/19
// This object class contains all the data on the program created by the user

import java.util.ArrayList;

public class Program {
	// Instance variables
	private ArrayList<String> includes = new ArrayList<String>();
	private ArrayList<Command> commands = new ArrayList<Command>();
	private ArrayList<Function> functions = new ArrayList<Function>();
	
	/**
	 * Create a Program object with no data
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
	 * Create a Program object with an ArrayList of Command objects and an ArrayList of String include libraries
	 * @param cmdArray
	 * @param imports
	 */
	public Program(ArrayList<Command> cmdArray, ArrayList<String> imports) {
		commands = cmdArray;
		includes = imports;
	}
	
	/**
	 * Adds a string to the includes ArrayList
	 * @param inc - The string to include to the ArrayList
	 */
	public void addInclude(String inc) {
		includes.add(inc);
	}
	
	/**
	 * Get string at index i in the 
	 * @param i - Index of include in includes ArrayList
	 * @return A String with code to include a certain library
	 */
	public String getInclude(int i) {
		return includes.get(i);
	}
	
	/**
	 * Get the number of include libraries in the Program object
	 * @return length of includes ArrayList
	 */
	public int numIncludes() {
		return includes.size();
	}
	
	/**
	 * Add a command to the commands ArrayList
	 * @param c - The Command to add
	 */
	public void addCommand(Command c) {
		commands.add(c);
	}
	
	/**
	 * Get a given Command object based on its index in the ArrayList
	 * @param i - index (0 based)
	 * @return The Command object at index i
	 */
	public Command getCommand(int i) {
		return commands.get(i);
	}
	
	/**
	 * Add a Function object to the functions ArrayList in the Program object
	 * @param f - The Function object to add
	 */
	public void addFunction(Function f) {
		functions.add(f);
	}
	
	/**
	 * Get a given Function object based on its index in the ArrayList
	 * @param i - index (0 based)
	 * @return The Function object at index i
	 */
	public Function getFunction(int i) {
		return functions.get(i);
	}
	
	/**
	 * Get the number of Function objects in the Program object
	 * @return lengths of functions ArrayList
	 */
	public int numFunctions() {
		return functions.size();
	}
	
	/**
	 * Get the number of Command objects in the Program object
	 * @return length of commands ArrayList
	 */
	public int numCommands() {
		return commands.size();
	}
}
