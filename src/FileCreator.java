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
	 * @param nameToSet - Name of the class/file
	 * @param langToSet - Language of the exported file
	 * @param pToSet - Program object (containing the user code)
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
	 * This method creates the .java file from the user code (stored in Program object)
	 * @param p - A Program object
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
		
		// Include any user specified packages
		for (int i = 0; i < userCode.numIncludes(); i++) {
			writer.write(userCode.getInclude(i) + "\n");
		}
		writer.write("\n");
		
		// Create header and main method
		writer.write("public class " + nameOfClass + " {");
		writer.write("\n\n\t");
		writer.write("public static void main (String[] args) {");
		writer.write("\n");
		
		// Add commands from Program object
		for (int i = 0; i < userCode.numCommands(); i++) {
			// Check for one liners
			String code = userCode.getCommand(i).getLineOfCode();
			if (code.indexOf("\n") == -1) {
				userCode.getCommand(i).setIndentLevel(2);  						// Set typical one liner indent
				
				System.out.println(userCode.getCommand(i).toString());
				
				writer.write(userCode.getCommand(i).indent() + code + "\n");
			}
			else {
				System.out.println(userCode.getCommand(i).toString());
				writer.write(userCode.getCommand(i).getLineOfCode() + "\n");
			}
		}
		
		// Add functions from Program object
		
		// Finalize all brackets and EOF
		writer.write("\n\t");
		writer.write("}");
		writer.write("\n");
		writer.write("}");
		
		// Close writer buffer
		writer.close();
	}
	
	/**
	 * This method creates the .cpp file from the user code (stored in Program object)
	 * @param p - A Program object
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
		
		for (int i = 0; i < userCode.numIncludes(); i++) {
			writer.write(userCode.getInclude(i) + "\n");
		}
		writer.write("\n");
		
		// Create header and main method
		writer.write("#include <iostream>" + "\n");
		writer.write("using namespace std;" + "\n\n");
		writer.write("int main() {");
		writer.write("\n");
		
		// Now add lines of code from Program object
		for (int i = 0; i < userCode.numCommands(); i++) {
			// Check for one liners
			String code = userCode.getCommand(i).getLineOfCode();
			if (code.indexOf("\n") == -1) {
				userCode.getCommand(i).setIndentLevel(1);
				
				System.out.println(userCode.getCommand(i).toString());
				
				writer.write(userCode.getCommand(i).indent() + code + "\n");
			}
			else {
				System.out.println(userCode.getCommand(i).toString());
				writer.write(userCode.getCommand(i).getLineOfCode() + "\n");
			}
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
	 * This method creates the .py file from the user code (stored in Program object)
	 * @param p - A Program object
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
		
		// Include any user specified packages
		for (int i = 0; i < userCode.numIncludes(); i++) {
			writer.write(userCode.getInclude(i) + "\n");
		}
		writer.write("\n");
		
		// Now add lines of code from Program object
		for (int i = 0; i < userCode.numCommands(); i++) {
			// Check for one liners
			String code = userCode.getCommand(i).getLineOfCode();
			if (code.indexOf("\n") == -1) {
				userCode.getCommand(i).setIndentLevel(0);
				
				System.out.println(userCode.getCommand(i).toString());
				
				writer.write(userCode.getCommand(i).indent() + code + "\n");
			}
			else {
				System.out.println(userCode.getCommand(i).toString());
				writer.write(userCode.getCommand(i).getLineOfCode() + "\n");
			}
		}
		
		// Close writer buffer
		writer.close();
	}
}
