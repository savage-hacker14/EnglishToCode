// FileCreator.java
// Written by Jacob Krucinski on 6/13/19
// This file is responsible for creating the file with your code based on the Program Object

// Import required packages
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
	// Instance variables
	private String nameOfClass;
	private String lang;
	private Program userCode;
	
	public FileCreator(String nameToSet, String langToSet, Program pToSet) {
		nameOfClass = nameToSet;
		lang = langToSet;
		userCode = pToSet;
	}
	
	public void createCodeFile() throws IOException {
		switch (lang) {
			case "java":
				createJavaFile(userCode);
				break;
			case "c++":
				createCPPFile(userCode);
				break;
			case "python":
				createPythonFile(userCode);
				break;
		}
	}
	
	private void createJavaFile(Program p) throws IOException {
		// Create new file and writer object
		String filename = nameOfClass + ".java";
		BufferedWriter writer = new BufferedWriter(new FileWriter("ExportedCode/" + filename));
		
		// Create header and main methdd
		writer.write("public class " + nameOfClass + " {");
		writer.write("\n\n\t");
		writer.write("public static void main (String[] args) {");
		writer.write("\n");
		
		// Now add lines of code from Program object
		for (int i = 0; i < userCode.numCommands(); i++) {
			String currentIndent = "\t\t";
			writer.write(currentIndent + userCode.getCommand(i).getLineOfCode() + "\n");
		}
		
		// Finalize all brackets and EOF
		writer.write("\n\t");
		writer.write("}");
		writer.write("\n");
		writer.write("}");
		
		// Close writer buffer
		writer.close();
	}
	
	private void createCPPFile(Program p) {
		
	}
	
	private void createPythonFile(Program p) throws IOException {
		// Create new file and writer object
		String filename = nameOfClass + ".py";
		BufferedWriter writer = new BufferedWriter(new FileWriter("ExportedCode/" + filename));
		
		// Now add lines of code from Program object
		for (int i = 0; i < userCode.numCommands(); i++) {
			writer.write(userCode.getCommand(i).getLineOfCode() + "\n");
		}
		
		// Close writer buffer
		writer.close();
	}
}
