// Runner.java
// Written by Jacob Krucinski on 6/13/19
// This class runs the simple text UI for code creation

// Import necessary packages
import java.io.IOException;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Find necessary program info
		Scanner reader = new Scanner(System.in);
		System.out.print("What is the name of your program?: ");
		String name = reader.nextLine();
		System.out.print("What language do you want your program coded in (java/c++/python)?: ");
		String lang = reader.nextLine();
		
		// Have user create their program
		System.out.println();
		System.out.println("Create your program here: Press enter after each line and type EXIT when done");
		Program p = new Program();
		
		String rawLine = "";
		while (!rawLine.equals("EXIT")) {
			rawLine = reader.nextLine();		// Obtain raw input from user
			
			if (rawLine.equals("EXIT")) {
				break;
			}
			
			// Create all necessary objects
			Command cmd = Interpretor.interpret(rawLine);
			cmd.setLanguage(lang);
			
			if (!cmd.getCommand().equals("ForLoop")) {
				Interpretor.createLineOfCode(cmd);
			}
			else {
				//System.out.println("for loop detected");
				cmd = Interpretor.interpretForLoop(rawLine, lang, 0);
				Interpretor.createLinesOfCodeForLoop((ForLoop) cmd);
			}
			
			System.out.println(cmd.toString());
			p.addCommand(cmd);
		}
	
		// After program object has acquired all data from user, BUILD THE PROGRAM
		FileCreator writer = new FileCreator(name, lang, p);
		try {
			writer.createCodeFile();
			System.out.println("Success: Code file created!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Failed to generate code file!");
		}
	}

}
