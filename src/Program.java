// Program.java
// Written by Jacob Krucinski on 6/13/19
// This object class contains all the data on the program created by the user

import java.util.ArrayList;

public class Program {
	// Instance variables
	private ArrayList<Command> commands;
	
	public Program(ArrayList<Command> cmdArray) {
		commands = cmdArray;
	}
	
	public void addCommand(Command c) {
		commands.add(c);
	}
	
	public Command getCommand(int i) {
		return commands.get(i);
	}
}
