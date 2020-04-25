// FileCreator.java
// Written by Jacob Krucinski on 6/13/19
// This file is responsible for creating the file with your code based on the Program Object

// Import required packages
import java.time.LocalDateTime;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
	// Instance variables
	private String nameOfClass;
	private String lang;
	private Program userCode;
	
	/**
	 * Constructor for FileCreator object
	 * @param Name of the class/file
	 * @param Language of the exported file
	 * @param Program object (containing the user code)
	 */
	public FileCreator(String nameToSet, String langToSet, Program pToSet) {
		nameOfClass = nameToSet;
		lang = langToSet;
		userCode = pToSet;
	}
	
	/**
	 * This method creates the proper code based on the language the user specified
	 * @throws IOException
	 */
	public void createCodeFile() throws IOException {
		// Determine the proper code to generate based on language parameter
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
	
	/**
	 * This method creates the .java file from the user code
	 * @param A Program object
	 * @throws IOException
	 */
	private void createJavaFile(Program p) throws IOException {
		// Create new file and writer object
		String filename = nameOfClass + ".java";
		BufferedWriter writer = new BufferedWriter(new FileWriter("ExportedCode/" + filename));
		
		// Create name and date title
		// Acquire time date from system
		LocalDateTime now = LocalDateTime.now();
		//String date = now.toString().substring(0, 10);
		writer.write("// " + filename + "\n");
		writer.write("// This program was auto-generated from your pseudocode on " + now.toString() + "\n\n");
		
		// Create header and main method
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
	
	/**
	 * This method creates the .cpp file from the user code
	 * @param A Program object
	 * @throws IOException
	 */
	private void createCPPFile(Program p) throws IOException {
		// Create new file and writer object
		String filename = nameOfClass + ".cpp";
		BufferedWriter writer = new BufferedWriter(new FileWriter("ExportedCode/" + filename));
		
		// Create name and date title
		// Acquire time date from system
		LocalDateTime now = LocalDateTime.now();
		//String date = now.toString().substring(0, 10);
		writer.write("// " + filename + "\n");
		writer.write("// This program was auto-generated from your pseudocode on " + now.toString() + "\n\n");
		
		// Create header and main method
		writer.write("#include <iostream>" + "\n");
		writer.write("using namespace std;" + "\n\n");
		writer.write("int main() {");
		writer.write("\n");
		
		// Now add lines of code from Program object
		for (int i = 0; i < userCode.numCommands(); i++) {
			String currentIndent = "\t";
			writer.write(currentIndent + userCode.getCommand(i).getLineOfCode() + "\n");
		}
		
		// Add return 0 statement
		writer.write("\t" + "return 0;");
		
		// Finalize all brackets and EOF
		writer.write("\n");
		writer.write("}");
		
		// Close writer buffer
		writer.close();
	}
	
	/**
	 * This method creates the .py file from the user code
	 * @param A Program object
	 * @throws IOException
	 */
	private void createPythonFile(Program p) throws IOException {
		// Create new file and writer object
		String filename = nameOfClass + ".py";
		BufferedWriter writer = new BufferedWriter(new FileWriter("ExportedCode/" + filename));
		
		// Create name and date title
		// Acquire time date from system
		LocalDateTime now = LocalDateTime.now();
		//String date = now.toString().substring(0, 10);
		writer.write("# " + filename + "\n");
		writer.write("# This program was auto-generated from your pseudocode on " + now.toString() + "\n\n");
		
		// Now add lines of code from Program object
		for (int i = 0; i < userCode.numCommands(); i++) {
			writer.write(userCode.getCommand(i).getLineOfCode() + "\n");
		}
		
		// Close writer buffer
		writer.close();
	}
}
