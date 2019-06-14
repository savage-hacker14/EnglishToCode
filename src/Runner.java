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
		do {
			String rawLine = reader.next();		// Obtain raw input from user
			
			// Create all necessary objects
			Command cmd = Interpretor.interpret(rawLine);
			cmd.setLanguage(lang);
			p.addCommand(cmd);
		} while (reader.next() != "EXIT");
		
		// After program object has acquired all data from user, BUILD THE PROGRAM
		FileCreator writer = new FileCreator(name, lang, p);
		try {
			writer.createCodeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Failed to generate code file!");
		}
	}

}
