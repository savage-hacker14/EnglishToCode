// Runner_v2.java
// This program has the features of the Runner class but also supports code for the GUI

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Runner_v2 {
	private static JFrame initWindow = new JFrame("Init Window");
	private static JCheckBox chooseSaveDirectory = new JCheckBox("Specify code destination before export");
	
	private static JFrame inclWindowButton = new JFrame("Add Include");
	private static JFrame inclWindow = new JFrame("Include Packages");
	
	private static JFrame funcWindowButton = new JFrame("Add Function");
	private static JFrame funcWindow = new JFrame("Function Setup");
	
	private static JFrame cmdsWindow = new JFrame("Commands");
	
	private static JFrame cmdInputWindow = new JFrame("Command Input");
	private static JFrame cmdDetailsWindow = new JFrame("Command Details");
	
	private static JFrame forLoopSetupWindow = new JFrame("For Loop Setup");
	//private static JFrame[] forLoopCmdsWindow = new JFrame[5];  // This is an array bc there can be nested for loops
	
	private static JFrame ifElseSetupWindow = new JFrame("If Else Setup");
	private static JFrame ifElseLogicExp = new JFrame("Sublogic Expression");
	private static int numTrueCmds;
	private static int numFalseCmds;
	
	private static JFrame matSetup = new JFrame("Array/Matrix Setup");
	private static JFrame matValues = new JFrame("Array/Matrix Values");
	
	private static String[] programParams;				// 0 - Name, 1 - Language, 3 - Number of commands
	private static CommandButton[] cmdButtons;			// Command buttons displayed in "Commands" window
	
	private static int currentCmdIdx;					// Int used to control which command is being modified
	private static String currentCmdType;			
	private static boolean isSubCmd;
	private static boolean firstClick = true;
	private static String currentCommandString = "";	// STICK TO STRING BUILDING AND NOT OBJECT MUTATIONS FOR COMMAND PROCESSING
	
	// Program variables
	private static boolean allLanguages = false;
	private static ArrayList<Command> userCmdsJava = new ArrayList<Command>();
	private static ArrayList<Command> userCmdsCPP = new ArrayList<Command>();
	private static ArrayList<Command> userCmdsPython = new ArrayList<Command>();
	private static ArrayList<String> userIncludesJava = new ArrayList<String>();
	private static ArrayList<String> userIncludesCPP = new ArrayList<String>();
	private static ArrayList<String> userIncludesPython = new ArrayList<String>();
	private static ArrayList<Function> userFunctionsJava = new ArrayList<Function>();
	private static ArrayList<Function> userFunctionsCPP = new ArrayList<Function>();
	private static ArrayList<Function> userFunctionsPython = new ArrayList<Function>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createAndShowAllGUIs();
	}
	
    private static void createAndShowAllGUIs() {       
        // Set up and display init window
    	createInitWindow();
        initWindow.setPreferredSize(new Dimension(300,170));
        initWindow.pack();
        initWindow.setVisible(true);
    }
    
    private static void createInitWindow() {
    	// Create and set up the window.
        initWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create String array to store use inputs in GUI
        programParams = new String[3];
    	
    	// Create text field for necessary inputs on init window
    	JTextField nameofProgram = new JTextField("");
    	JTextField language = new JTextField("");
    	JTextField numCommands = new JTextField("");
    	
    	// Create submit button
    	JButton submit = new JButton("SUBMIT");
    	
    	// Create JPanel to put the text fields on
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(3,2));
    	
    	// Create dropdown options for Language
    	String[] languages = {"java", "c++", "python", "ALL"};
    	JComboBox langDropDown = new JComboBox(languages);
    	
    	// Add 3 fields to window
    	inputs.add(new JLabel(" Name of Program:"));
    	inputs.add(nameofProgram);  	
    	inputs.add(new JLabel(" Language:"));
    	inputs.add(langDropDown);
    	inputs.add(new JLabel(" Number of Commands:"));
    	inputs.add(numCommands);
    	
    	// Add checkbox to specify code file save location
    	JPanel setDestinationLoc = new JPanel();
    	setDestinationLoc.add(chooseSaveDirectory);
    	
    	// Add ActionListener for submit button
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	// Get data from user
            	programParams[0] = nameofProgram.getText();
            	programParams[1] = (String)langDropDown.getSelectedItem();
            	programParams[2] = numCommands.getText();
            	
            	if (programParams[1].equals("ALL")) {
            		allLanguages = true;
            	}
            	         	
            	// Close init window
            	initWindow.setVisible(false);
            	
            	// Open up command input window and includes button window
            	createCommandsWindow();
            	createIncludeButtonWindow();
            	createFunctionButtonWindow();
            }
        });
        submit.setMnemonic('X');


    	// Add JPanel to pane
    	initWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	initWindow.getContentPane().add(setDestinationLoc, BorderLayout.CENTER);
    	initWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    }
    
    private static void createCommandsWindow() {
    	// Set up the command window
    	cmdsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Create JPanel to put the add command buttons on
    	JPanel cmds = new JPanel();
    	
    	int numCommands = Integer.parseInt(programParams[2]);
    	cmdButtons = new CommandButton[numCommands];
    	
    	// Set layout depending on # of commands
    	int rows = (int) Math.ceil(numCommands / 4.0);
    	int cols = 4;
    	cmds.setLayout(new GridLayout(rows, cols));
    	
    	for (int i = 0; i < numCommands; i++) {
    		// Set size properly
    		userCmdsJava.add(new Command());
    		userCmdsCPP.add(new Command());
    		userCmdsPython.add(new Command());
    	}
    	
    	cmdsWindow.setPreferredSize(new Dimension(cols * 75, rows * 65));
    	
	    for (int i = 0; i < numCommands; i++ ) {
	    	cmdButtons[i] = new CommandButton(Integer.toString(i + 1), programParams[1], i);
	    	
	    	cmdButtons[i].addActionListener();
	    	
	    	cmds.add(cmdButtons[i]);
	    }
	    JButton export = new JButton("EXPORT");
	    cmds.add(export);
	    
    	export.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			// Create Program and FileCreator objects for ALL languages
    			Program pJava = new Program(userCmdsJava, userIncludesJava, userFunctionsJava);
    			FileCreator fJava = new FileCreator(programParams[0], "java", pJava);
    			Program pCPP = new Program(userCmdsCPP, userIncludesCPP, userFunctionsCPP);
    			FileCreator fCPP = new FileCreator(programParams[0], "c++", pCPP);
    			Program pPython = new Program(userCmdsPython, userIncludesPython, userFunctionsPython);
    			FileCreator fPython = new FileCreator(programParams[0], "python", pPython);
    			
    			// Check if user specified a directory to save the code file in
    			if (chooseSaveDirectory.isSelected()) {
	    			// Create a directory chooser and display the window
	    			final JFileChooser fc = new JFileChooser();
	    			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    			fc.setLocation(100, 0);
	    			fc.setVisible(true);
	    			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
	    			
	    			// Get file result
	    			int result = fc.showOpenDialog(cmds);
	    			
	    		    if (result == JFileChooser.APPROVE_OPTION) {    		    	
	    		    	if (allLanguages == false) {
	    		    		// Save code files for SPECIFIED language to user-selected directory
		    		    	String lang = programParams[1];
		    		    	String fileType = "";
		    				switch (lang) {
			    				case "java":
			    					fileType = ".java";
			    					break;
			    				case "c++":
			    					fileType = ".cpp";
			    					break;
			    				case "python":
			    					fileType = ".py";
			    					break;
		    				}
		    				
		    				File saveFile = null;
		    				if (System.getProperty("os.name").indexOf("Windows") != -1) {
		    					// Windows filesystem
		    					saveFile = new File(fc.getSelectedFile().toString() + "\\"+ programParams[0] + fileType);
		    				}
		    				else {
		    					// Linux/Mac filesystem
		    					saveFile = new File(fc.getSelectedFile().toString() + "/"+ programParams[0] + fileType);
		    				}
	    		    		
	    		    		// Determine which Program and FileCreator object to use and then create code file
		    				switch (lang) {
			    				case "java":
									try {
										fJava.createCodeFile(saveFile);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
			    					break;
			    				case "c++":
									try {
										fCPP.createCodeFile(saveFile);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
			    					break;
			    				case "python":
									try {
										fPython.createCodeFile(saveFile);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
			    					break;
		    				}
	    		    	}
	    		    	else {
	    		    		// Save code files for ALL languages to selected directory
		    				File saveFileJava = null;
		    				File saveFileCPP = null;
		    				File saveFilePython = null;
		    				
		    				// Create File objects to save to
		    				if (System.getProperty("os.name").indexOf("Windows") != -1) {
		    					// Windows filesystem
		    					saveFileJava = new File(fc.getSelectedFile().toString() + "\\"+ programParams[0] + ".java");
		    					saveFileCPP = new File(fc.getSelectedFile().toString() + "\\"+ programParams[0] + ".cpp");
		    					saveFilePython = new File(fc.getSelectedFile().toString() + "\\"+ programParams[0] + ".py");
		    				}
		    				else {
		    					// Linux/Mac filesystem
		    					saveFileJava = new File(fc.getSelectedFile().toString() + "/" + programParams[0] + ".java");
		    					saveFileCPP = new File(fc.getSelectedFile().toString() + "/" + programParams[0] + ".cpp");
		    					saveFilePython = new File(fc.getSelectedFile().toString() + "/" + programParams[0] + ".py");
		    				}
		    				
		    				// Write code to the File objects
		    				try {
								fJava.createCodeFile(saveFileJava);
			    				fCPP.createCodeFile(saveFileCPP);
			    				fPython.createCodeFile(saveFilePython);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	    		    	}
	    		    }
	    		    
	    		    // Exit program
	    		    System.exit(0);
    			}
    			else { 
    				// Save code to current directory (mainly used in testing)
    				if (allLanguages == false) {
    					// Save code for SPECIFIED language to current directory (mainly used in testing)
	    		    	String lang = programParams[1];
	    				switch (lang) {
		    				case "java":
								try {
									fJava.createCodeFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		    					break;
		    				case "c++":
								try {
									fCPP.createCodeFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		    					break;
		    				case "python":
								try {
									fPython.createCodeFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
		    					break;
	    				}
    				}
    				else {
    					// Save code for ALL languages to current directory (mainly used in testing)
    					try {
							fJava.createCodeFile();
	    					fCPP.createCodeFile();
	    					fPython.createCodeFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
    				}
    			}
            }
    	});
	    
	    cmdsWindow.getContentPane().add(cmds);
	    cmdsWindow.setLocation(100, 0);
	    
	    cmdsWindow.pack();
	    cmdsWindow.setVisible(true);
    }
    
    public static void createIncludeButtonWindow() {
    	JButton addInclude = new JButton("+");
    	
    	addInclude.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			createIncludeDetailWindow();
			}
    	});

    	inclWindowButton.getContentPane().add(addInclude);
    	inclWindowButton.setPreferredSize(new Dimension(275, 75));
    	inclWindowButton.setLocation(cmdsWindow.getX() + cmdsWindow.getWidth(), 0);
    	
    	inclWindowButton.pack();
    	inclWindowButton.setVisible(true);
    }
    
    public static void createIncludeDetailWindow() {
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(1,2));
    	
    	JTextField libraryName = new JTextField();
    	JButton submit = new JButton("SUBMIT");
    	
    	inputs.add(new JLabel(" Name of library"));
    	inputs.add(libraryName);
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			String libName_StrCommand = "Include(" + libraryName.getText() + ")";
    			String libName_IncludeCode_Java = Interpretor.interpretInclude(libName_StrCommand, "java");
    			String libName_IncludeCode_CPP = Interpretor.interpretInclude(libName_StrCommand, "c++");
    			String libName_IncludeCode_Python = Interpretor.interpretInclude(libName_StrCommand, "python");
    			
    			userIncludesJava.add(libName_IncludeCode_Java);
    			userIncludesCPP.add(libName_IncludeCode_CPP);
    			userIncludesPython.add(libName_IncludeCode_Python);
    			
    			libraryName.setText("");
    			
    			inclWindow.setVisible(false);
			}
    	});
    	submit.setMnemonic('X');
    	
	    inclWindow.getContentPane().add(inputs, BorderLayout.NORTH);
	    inclWindow.getContentPane().add(submit, BorderLayout.SOUTH);
	    inclWindow.setPreferredSize(new Dimension(275,85));
	    inclWindow.setLocation(inclWindowButton.getX(), inclWindowButton.getHeight());
	    
	    inclWindow.pack();
	    inclWindow.setVisible(true);
    }
    
    public static void createCommandInputWindow(int cmdIdx) {   	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(2,2));
    	
    	JComboBox cmds = new JComboBox(Interpretor.cmds);
    	JButton submit = new JButton("SUBMIT");
    	
    	// Set proper command index
    	currentCmdIdx = cmdIdx;
    	
    	//  Used for IfElse objects
    	numTrueCmds = 0;
    	numFalseCmds = 0;
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			String cmd = (String)cmds.getSelectedItem();
    			currentCmdType = cmd;
    			//System.out.println(cmd);
    			
    			currentCommandString += (String)cmds.getSelectedItem();
    			
    			if (cmd.equals("var") || cmd.contentEquals("display")) {
    				createRegCommandDetailWindow();
    			}
    			
    			if (cmd.equals("ForLoop")) {
    				createForLoopSetupWindow();
    				isSubCmd = true;
    			}
    			
    			if (cmd.equals("If")) {
    				createIfElseSetupWindow();
    			}
    			
    			if (cmd.equals("mat") || cmd.equals("arr")) {
    				createMatSetupWindow();
    			}
			}
    	});
    	submit.setMnemonic('X');
    	
    	inputs.add(new JLabel("Command:"));
    	inputs.add(cmds);
    	
    	cmdInputWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	cmdInputWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	
    	cmdInputWindow.setPreferredSize(new Dimension(300,120));
    	cmdInputWindow.setLocation(100, 100);
    	
    	cmdInputWindow.pack();
    	cmdInputWindow.setVisible(true);
    }
    
    private static void createRegCommandDetailWindow() {
    	cmdDetailsWindow.setPreferredSize(new Dimension(300,110));
    	cmdDetailsWindow.setLocation(100,225);
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(2,2));
    	
    	JTextField varName = new JTextField("");
    	JTextField params = new JTextField("");
    	JButton submit = new JButton("SUBMIT");
    	
    	varName.setText("");
    	params.setText("");
    	
    	inputs.add(new JLabel("Variable Name"));
    	inputs.add(varName);
    	inputs.add(new JLabel("Parameters"));
    	inputs.add(params);
    	
    	// Add action listener for submit button
    	submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
            	if (currentCmdType.equals("var")) {
            		currentCommandString += " " + varName.getText() + " = " + params.getText();
            	}
            	else if (currentCmdType.equals("display")) {
            		currentCommandString += " " + varName.getText() + params.getText();
            	}
            	
            	if (currentCommandString.indexOf("ForLoop") != -1 || currentCommandString.indexOf("If") != -1 
            			|| currentCommandString.indexOf("Function") != -1) {
            		currentCommandString += ";";
            	}
            	
    			System.out.println(currentCommandString);   
            	
    			if (!isSubCmd) {
    				// Don't interpret current command string until user clicks submit on window
    				
                	// Create Command object from currentCommandString
                	Command cJava = Interpretor.interpret(currentCommandString, "java");
                	Interpretor.createLineOfCode(cJava);
                	Command cCPP = Interpretor.interpret(currentCommandString, "c++");
                	Interpretor.createLineOfCode(cCPP);
                	Command cPython = Interpretor.interpret(currentCommandString, "python");
                	Interpretor.createLineOfCode(cPython);
                		
                	// Only add regular commands to the userCmds array list
                	userCmdsJava.set(currentCmdIdx, cJava); 
                	userCmdsCPP.set(currentCmdIdx, cCPP);
                	userCmdsPython.set(currentCmdIdx, cPython);

                	// Clear old command data
                	currentCommandString = "";
                	currentCmdType = "";
                	isSubCmd = false;
                	
                	// Close command input window and remove command button button
                	cmdInputWindow.setVisible(false);
                	cmdButtons[currentCmdIdx].setVisible(false);
    			}
    			else {
    				cmdInputWindow.setVisible(false);
    			}
    			
              	// Close command input window and clear command button
            	cmdDetailsWindow.setVisible(false);
            	
            	// Clear any old text
            	varName.setText("");
            	params.setText("");
            }
        });
    	submit.setMnemonic('X');
    	
    	cmdDetailsWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	cmdDetailsWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	cmdDetailsWindow.pack();
    	cmdDetailsWindow.setVisible(true);
    }
    
    private static void createForLoopSetupWindow() {
    	forLoopSetupWindow.setPreferredSize(new Dimension(300,200));
    	forLoopSetupWindow.setLocation(100,225);
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(5,2));
    	
    	JTextField loopVarName = new JTextField("");
    	JTextField startVal = new JTextField("");
    	JTextField endVal = new JTextField("");
    	JTextField incVal = new JTextField("");
    	JButton addCmds = new JButton("+");
    	JButton submit = new JButton("SUBMIT");
    	
    	inputs.add(new JLabel("Loop variable name:"));
    	inputs.add(loopVarName);
    	inputs.add(new JLabel("Start value:"));
    	inputs.add(startVal);
    	inputs.add(new JLabel("End value (excl):"));
    	inputs.add(endVal);
    	inputs.add(new JLabel("Increment Value:"));
    	inputs.add(incVal);
    	inputs.add(new JLabel("Add commands:"));
    	inputs.add(addCmds);
    	
    	// First addCmd click coming
    	firstClick = true;
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
	   			// Get all values from text fields
    			String loopVar = loopVarName.getText();
    			String start = startVal.getText();
    			String end = endVal.getText();
    			String inc = incVal.getText();
    			
    			// Add final parenthesis
    			int numLeftParenthesis = numOcurranceOfChar('(', currentCommandString);
    			int numRightParenthesis = numOcurranceOfChar(')', currentCommandString);
    			int missingRight = numLeftParenthesis - numRightParenthesis;
    			for (int i = 0; i < missingRight; i++) {
        			currentCommandString += ")";
    			}
    			
    			// Clean up final semicolon from last sub command
    			currentCommandString = currentCommandString.replaceAll(";\\)", "\\)");
    			
    			System.out.println(currentCommandString);
    			
            	// Create Command object from currentCommandString
            	Command cJava = Interpretor.interpret(currentCommandString, "java");
            	Interpretor.createLineOfCode(cJava);
            	Command cCPP = Interpretor.interpret(currentCommandString, "c++");
            	Interpretor.createLineOfCode(cCPP);
            	Command cPython = Interpretor.interpret(currentCommandString, "python");
            	Interpretor.createLineOfCode(cPython);
            		
            	// Only add regular commands to the userCmds array lists
            	userCmdsJava.set(currentCmdIdx, cJava); 
            	userCmdsCPP.set(currentCmdIdx, cCPP);
            	userCmdsPython.set(currentCmdIdx, cPython);
            	
            	currentCommandString = "";
            	currentCmdType = "";
            	isSubCmd = false;
    			
            	// Clear any old text
            	loopVarName.setText("");
            	startVal.setText("");
            	endVal.setText("");
            	incVal.setText("");
            	
            	// Close this window
            	forLoopSetupWindow.setVisible(false);
            	
            	// Close the command input window
            	cmdInputWindow.setVisible(false);
            	
            	// Remove + button and replace it with toString string
            	cmdButtons[currentCmdIdx].setVisible(false);
            }
    	});
    	submit.setMnemonic('X');
    	
    	addCmds.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if (firstClick) {
        			String loopVar = loopVarName.getText();
        			String start = startVal.getText();
        			String end = endVal.getText();
        			String inc = incVal.getText();
    				
    				currentCommandString += "(\"" + loopVar + "\"" + "," + start + "," + end + "," + inc + ")(";
    				firstClick = false;
    				
    				isSubCmd = true;
    			}
    			createCommandInputWindow(currentCmdIdx);
            }
    	});

    	forLoopSetupWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	forLoopSetupWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	forLoopSetupWindow.pack();
    	forLoopSetupWindow.setVisible(true);
    }
    
    private static void createIfElseSetupWindow() {
    	ifElseSetupWindow.setPreferredSize(new Dimension(300,150));
    	ifElseSetupWindow.setLocation(100,225);
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(3,2));
    	
    	JButton addSubLogicExp = new JButton("+");
    	JButton addTrueCmds = new JButton("+");
    	JButton addFalseCmds = new JButton("+");
    	JButton submit = new JButton("SUBMIT");
    	
    	inputs.add(new JLabel("Sublogic Expressions:"));
    	inputs.add(addSubLogicExp);
    	inputs.add(new JLabel("True Commands:"));
    	inputs.add(addTrueCmds);
    	inputs.add(new JLabel("False Commands:"));
    	inputs.add(addFalseCmds);
    	
    	// First addCmd click coming
    	firstClick = true;
    	
    	// Action Listener for adding sublogic expression to the IfElse command
    	addSubLogicExp.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if (firstClick) {			
    				currentCommandString += "{Logic(";
    				
    				firstClick = false;
    			}
    			createSubLogicExpWindow();
            }
    	});
    	
    	// Action Listener for adding the commands to be executed if the logic evaluates to true
    	addTrueCmds.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if (numTrueCmds == 0) {
	    			// Add finalized text for logic expression in ifElse statement ONCE 
	    			int[] allCommaLocations = Interpretor.findAllCommas(currentCommandString);
	    			int lastCommaLocation = allCommaLocations[allCommaLocations.length - 1];
	    			String tempCommandString = currentCommandString.substring(lastCommaLocation);
	    			currentCommandString = currentCommandString.substring(0, lastCommaLocation) + tempCommandString.replaceFirst(",", ")}{");
	    			
	    			isSubCmd = true;
	    			
	    			numTrueCmds++;
    			}
    			else {
    				createCommandInputWindow(currentCmdIdx);
    			}
            }
    	});
    	
    	// Action Listener for adding the commands to be executed if the logic evaluates to false
    	addFalseCmds.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if (numFalseCmds == 0) {
	    			// Add finalized text for logic expression in ifElse statement ONCE 
	    			int[] allSemiColLocations = Interpretor.findAllSemicolons(currentCommandString);
	    			int lastSemiColLocation = allSemiColLocations[allSemiColLocations.length - 1];
	    			String tempCommandString = currentCommandString.substring(lastSemiColLocation);
	    			currentCommandString = currentCommandString.substring(0, lastSemiColLocation) + tempCommandString.replaceFirst(";", "}{");
	    			
	    			isSubCmd = true;
	    			
    				cmdInputWindow.setVisible(true);
	    			
	    			numFalseCmds++;
    			}
    			else {
    				createCommandInputWindow(currentCmdIdx);
    			}
            }
    	});
    	
    	// Action Listener for submitting the whole IfElse command to be interpreted
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			// Fix final string formatting
    			int[] allSemiColLocations = Interpretor.findAllSemicolons(currentCommandString);
    			int lastSemiColLocation = allSemiColLocations[allSemiColLocations.length - 1];
    			String tempCommandString = currentCommandString.substring(lastSemiColLocation);
    			currentCommandString = currentCommandString.substring(0, lastSemiColLocation) + tempCommandString.replaceFirst(";", "}");
    			
            	// Create Command object from currentCommandString
            	Command cJava = Interpretor.interpret(currentCommandString, "java");
            	Interpretor.createLineOfCode(cJava);
            	Command cCPP = Interpretor.interpret(currentCommandString, "c++");
            	Interpretor.createLineOfCode(cCPP);
            	Command cPython = Interpretor.interpret(currentCommandString, "python");
            	Interpretor.createLineOfCode(cPython);
            		
            	// Only add regular commands to the userCmds array list
            	userCmdsJava.set(currentCmdIdx, cJava); 
            	userCmdsCPP.set(currentCmdIdx, cCPP);
            	userCmdsPython.set(currentCmdIdx, cPython);
            	
            	currentCommandString = "";
            	currentCmdType = "";
            	isSubCmd = false;
            	
            	// Hide necessary windows
            	ifElseSetupWindow.setVisible(false);
            	cmdInputWindow.setVisible(false);
            	
            	// Hide command button
            	cmdButtons[currentCmdIdx].setVisible(false);
            }
    	});
    	
    	
    	ifElseSetupWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	ifElseSetupWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	ifElseSetupWindow.pack();
    	ifElseSetupWindow.setVisible(true);
    }
    
    private static void createSubLogicExpWindow() {
    	ifElseLogicExp.setPreferredSize(new Dimension(300,100));
    	ifElseLogicExp.setLocation(100,375);
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(1,2));
    	
    	JTextField subLogicExp = new JTextField();
    	JButton submit = new JButton("SUBMIT");
    	
    	inputs.add(new JLabel("Sub logic expression:"));
    	inputs.add(subLogicExp);
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			currentCommandString += subLogicExp.getText() + ",";
    			System.out.println(currentCommandString);
    			ifElseLogicExp.setVisible(false);
    			
    	    	// Clear text field for next use
    	    	subLogicExp.setText("");  			
            }
    	});
    	
    	ifElseLogicExp.getContentPane().add(inputs, BorderLayout.NORTH);
    	ifElseLogicExp.getContentPane().add(submit, BorderLayout.SOUTH);
    	ifElseLogicExp.pack();
    	ifElseLogicExp.setVisible(true);
    }
    
    private static void createMatSetupWindow() {
    	matSetup.setPreferredSize(new Dimension(300,130));
    	matSetup.setLocation(100,225);
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(3, 2));
    	
    	JTextField varName = new JTextField();
    	JTextField numRows = new JTextField();
    	JTextField numCols = new JTextField();
    	JButton submit = new JButton("SUBMIT");
    	
    	inputs.add(new JLabel(" Matrix Name"));
    	inputs.add(varName);
    	inputs.add(new JLabel(" # of Rows"));
    	inputs.add(numRows);
    	inputs.add(new JLabel(" # of Columns"));
    	inputs.add(numCols);
    	
    	// If command is at, use this window but set rows automatically to 1
    	if (currentCmdType.equals("arr")) {
    		numRows.setText("1");
    	}
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			String varNameString = varName.getText();
    			int nRows = Integer.parseInt(numRows.getText());
    			int nCols = Integer.parseInt(numCols.getText());
    			
    			matSetup.setVisible(false);
    			varName.setText("");
    			numRows.setText("");
    			numCols.setText("");
    			
    			createMatValueWindow(varNameString, nRows, nCols);
            }
    	});
    	submit.setMnemonic('X');
    	
    	matSetup.getContentPane().add(inputs, BorderLayout.NORTH);
    	matSetup.getContentPane().add(submit, BorderLayout.SOUTH);
    	matSetup.pack();
    	matSetup.setVisible(true);
    }
    
    private static void createMatValueWindow(String varName, int nRows, int nCols) {
    	matValues.setPreferredSize(new Dimension(60*nCols,38*nRows));
    	matValues.setLocation(100,225);
    	
    	JPanel values = new JPanel();
    	values.setLayout(new GridLayout(nRows, nCols));
    	
    	JButton submit = new JButton("SUBMIT");
    	
    	int numValues = nRows * nCols;
    	JTextField[] valueTextField = new JTextField[numValues];
    	for (int i = 0; i < numValues; i++) {
    		valueTextField[i] = new JTextField();
    		values.add(valueTextField[i]);
    	}
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){    	    	
    	    	// Build the command string
    	    	currentCommandString = "mat " + varName + " = [";
    	    	for (int i = 1; i <= numValues; i++) {
    	    		if (i % nCols == 0 && i != numValues) {
    	    			// Add semicolon after the completion of one row
    	    			currentCommandString += valueTextField[i - 1].getText() +  ";";
    	    		}
    	    		else {
    	    			if (i != numValues) {
    	    				// Add value followed by space
    	    				currentCommandString += valueTextField[i - 1].getText() +  " ";
    	    			}
    	    			else {
    	    				// Add final bracket
    	    				currentCommandString += valueTextField[i - 1].getText() +  "]";
    	    			}
    	    		}
    	    	}
    	    	//System.out.println(currentCommandString);
    	    	
            	// Create Command object from currentCommandString
            	Command cJava = Interpretor.interpret(currentCommandString, "java");
            	Interpretor.createLineOfCode(cJava);
            	Command cCPP = Interpretor.interpret(currentCommandString, "c++");
            	Interpretor.createLineOfCode(cCPP);
            	Command cPython = Interpretor.interpret(currentCommandString, "python");
            	Interpretor.createLineOfCode(cPython);
            		
            	// Only add regular commands to the userCmds array list
            	userCmdsJava.set(currentCmdIdx, cJava); 
            	userCmdsCPP.set(currentCmdIdx, cCPP);
            	userCmdsPython.set(currentCmdIdx, cPython);
            	
            	// Clear variables
            	currentCommandString = "";
            	currentCmdType = "";
            	isSubCmd = false;
            	
            	// Hide windows
            	matValues.setVisible(false);
            	matSetup.setVisible(false);
            	cmdInputWindow.setVisible(false);
            	
            	// Hide button
            	cmdButtons[currentCmdIdx].setVisible(false);
            }
    	});
    	submit.setMnemonic('X');
    	
    	matValues.getContentPane().add(values, BorderLayout.NORTH);
    	matValues.getContentPane().add(submit, BorderLayout.SOUTH);
    	matValues.pack();
    	matValues.setVisible(true);
    }
    
    private static void createFunctionButtonWindow() {
    	funcWindowButton.setPreferredSize(new Dimension(275,75));
    	funcWindowButton.setLocation(inclWindowButton.getX() + inclWindowButton.getWidth(), inclWindowButton.getY());
    	
    	JButton addFunction = new JButton("+");
    	
    	addFunction.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			createFunctionSetupWindow();
			}
    	});
    	
    	funcWindowButton.getContentPane().add(addFunction);
    	funcWindowButton.pack();
    	funcWindowButton.setVisible(true);
    }
    
    private static void createFunctionSetupWindow() {
    	// Create JPanel with input fields and proper layout
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(4,2));
    	
    	// Input fields
    	JTextField funcName = new JTextField();
    	JTextField returnVarName = new JTextField();
    	JTextField params = new JTextField();
    	JButton addCmds = new JButton("+");
    	JButton submit = new JButton("SUBMIT");
    	
    	// Add input fields to panel
    	inputs.add(new JLabel(" Name:"));
    	inputs.add(funcName);
    	inputs.add(new JLabel(" Parameters (, sep):"));
    	inputs.add(params);
    	inputs.add(new JLabel(" Commands:"));
    	inputs.add(addCmds);
    	inputs.add(new JLabel(" Return var (NONE if none):"));
    	inputs.add(returnVarName);
    	
    	firstClick = true;
    	
    	// Add ActionListeners  	
    	addCmds.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if (firstClick) {
    				String name = funcName.getText();
    				String paramsStr = params.getText();
    				currentCommandString += "Function{\"" + name + "\"," + paramsStr + "}{";
    				
    				firstClick = false;
    				
    				isSubCmd = true;
    			}
    			createCommandInputWindow(currentCmdIdx);
			}
    	});
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			// Finalize Function string then process it
    			int[] semiCols = Interpretor.findAllSemicolons(currentCommandString);
    			int lastSemiCol = semiCols[semiCols.length - 1];
    			currentCommandString = currentCommandString.substring(0, lastSemiCol) + "}{";
    			currentCommandString += returnVarName.getText() + "}";
    			
    			System.out.println(currentCommandString);
    			
            	// Create Function objects from currentCommandString
            	Function fJava = (Function) Interpretor.interpret(currentCommandString, "java");
            	Interpretor.createLineOfCode(fJava);
            	Function fCPP = (Function) Interpretor.interpret(currentCommandString, "c++");
            	Interpretor.createLineOfCode(fCPP);
            	Function fPython = (Function) Interpretor.interpret(currentCommandString, "python");
            	Interpretor.createLineOfCode(fPython);

            		
            	// Only add regular commands to the userCmds array list
            	userFunctionsJava.add(fJava); 
            	userFunctionsCPP.add(fCPP);
            	userFunctionsPython.add(fPython);
            	
            	// Clear variables
            	currentCommandString = "";
            	currentCmdType = "";
            	isSubCmd = false;
            	
            	// Hide this window
            	funcWindow.setVisible(false);
			}
    	});
    	
    	funcWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	funcWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	
	    funcWindow.setPreferredSize(new Dimension(275,175));
	    funcWindow.setLocation(funcWindowButton.getX(), funcWindowButton.getHeight());
	    funcWindow.pack();
	    funcWindow.setVisible(true); 
    }
    
    private static int numOcurranceOfChar(char c, String str) {
    	int num = 0;
    	for (int i = 0; i < str.length(); i++) {
    		if (str.charAt(i) == c) {
    			num++;
    		}
    	}
    	
    	return num;
    }
}
