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
				createJavaFile();
				break;
			case "c++":
				createCPPFile();
				break;
			case "python":
				createPythonFile();
				break;
		}
	}
	
	private void createJavaFile(Program p) throws IOException {
		// Create new file and writer object
		String filename = nameOfClass + ".java";
		BufferedWriter writer = new BufferedWriter(new FileWriter("ExportedCode/" + filename));
		
		// Create header and main methdd
		writer.write("public Class " + nameOfClass);
		writer.write("\n\n\t");
		writer.write("public static void main (String[] args) {");
		writer.write("\n\t\t");
		
		// Now add lines of code from Program object
		
		// Finalize all brackets and EOF
		writer.write("\n\t");
		writer.write("}");
		writer.write("\n");
		writer.write("}");
	}
	
	private void createCPPFile(Program p) {
		
	}
	
	private void createPythonFile(Program p) {
		
	}
}
