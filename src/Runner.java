// Runner.java
// Written by Jacob Krucinski on 6/13/19
// This class runs the simple text UI for code creation

// Import necessary packages
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Find necessary program info
		Scanner reader = new Scanner(System.in);
		System.out.print("What is the name of your program?: ");
		String name = reader.next();
		System.out.print("What language do you want your program coded in (java/c++/python)?: ");
		String lang = reader.next();
		
		// Have user create their program
		System.out.println();
		System.out.println("Create your program here: Press enter after each line and type EXIT when done");
		do {
			String rawLine = reader.next();		// Obtain raw input from user
			Command cmd = Interpretor.interpret(rawLine);
			cmd.setLanguage(lang);
		} while (reader.next() != "EXIT");
	}

}
