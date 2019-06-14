// Command.java
// Written by Jacob Krucinski on 6/13/19
// This object class is the base for any command in Java, c++, or Python

// Necessary imports
import java.util.ArrayList;

public class Command {
	// Instance variables name
	private String command;
	private String name;
	private String type;
	private String params;
	private String lang;
	private String lineOfCode;
	
	public Command(String commandToSet, String nameToSet, String typeToSet, String parametersToSet) {
		command = commandToSet;
		name = nameToSet;
		type = typeToSet;
		params = parametersToSet;
	}
	
	public void setLanguage(String str) {
		lang = str;
	}
	
	public void createLineOfCode() {
		lineOfCode = Interpretor.createLineOfCode(this);
	}
	
	public String getLineOfCode() {	
		return lineOfCode;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getParameters() {
		return params;
	}
	
	public String getLanguage() {
		return lang;
	}
}
