// Runner.java
// Written by Jacob Krucinski on 6/14/19
// This class runs the simple text UI for code creation

// Import necessary packages
import java.io.IOException;
import java.util.Scanner;

public class Tester {
	// Key variable
	private static final boolean outputCode = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Testing input
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter some text: ");
		String userInput = reader.nextLine();	
		System.out.println(userInput.replaceAll("\\s", ""));
			
		// Testing interpreter
		System.out.println();
		Command c = Interpretor.interpret(userInput);
		String lang = "java";
		c.setLanguage(lang);
		Interpretor.createLineOfCode(c);
		System.out.println(c.toString());
		
		if (outputCode) {
			// Testing Program
			Program p = new Program();
			p.addCommand(c);
			
			// Testing FileCreator
			FileCreator fc = new FileCreator("Test", lang, p);
			try {
				fc.createCodeFile();
				System.out.println("Code created!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error: Failed to generate code!");
			}
		}
		
		reader.close();
	}
}