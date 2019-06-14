// Runner.java
// Written by Jacob Krucinski on 6/14/19
// This class runs the simple text UI for code creation

// Import necessary packages
import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Find necessary program info
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter some text: ");
		String ans = reader.nextLine();
		
		System.out.println(ans.replaceAll("\\s", ""));
	}
}