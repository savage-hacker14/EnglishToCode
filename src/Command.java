// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is the base for any command in Java, c++, or Python

// Necessary imports
import java.util.ArrayList;

public class Command {
	// Instance variables name
	private String name;
	private ArrayList<Object> parameters;
	private String lang;
	private String lineOfCode;
	
	public Command(String nameToSet, ArrayList<Object> parametersToSet, String lineOfCodeToSet) {
		name = nameToSet;
		parameters = parametersToSet;
		lineOfCode = lineOfCodeToSet;
	}
	
	public String createLineOfCode() {
		String output = "";
		
		switch(lang) {
			case "java":
				break;
			case "c++":
				break;
			case "python":
				break;
			
		}
		
		return output;
	}
	
	public void setLanguage(String str) {
		lang = str;
	}
	
	public String getLineOfCode() {
		return lineOfCode;
	}
}
