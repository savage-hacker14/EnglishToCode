// Runner_v2.java
// This program has the features of the Runner class but also supports code for the GUI

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Runner_v2 {
	private static JFrame initWindow = new JFrame("Init Window");
	private static JFrame cmdsWindow = new JFrame("Commands");
	
	private static JFrame cmdInputWindow = new JFrame("Command Input");
	private static JFrame cmdDetailsWindow = new JFrame("Command Details");
	
	private static JFrame forLoopSetupWindow = new JFrame("For Loop Setup");
	//private static JFrame[] forLoopCmdsWindow = new JFrame[5];  // This is an array bc there can be nested for loops
	
	private static JFrame ifElseSetupWindow = new JFrame("If Else Setup");
	private static JFrame ifElseTrueCmdsWindow = new JFrame("If Else True Commands");
	private static JFrame ifElseFalseCmdsWindow = new JFrame("If Else False Commands");
	
	
	private static String[] programParams;				// 0 - Name, 1 - Language, 3 - Number of commands
	private static CommandButton[] cmdButtons;			// Command buttons displayed in "Commands" window
	private static CommandButton[][] subCmdButtons;		// 2D array because nested commands each have a set of command buttons
	
	private static int currentCmdIdx;					// Int used to control which command is being modified
	private static String currentCmdType;			
	private static boolean isSubCmd;
	private static String currentCommandString = "";	// STICK TO STRING BUILDING AND NOT OBJECT MUTATIONS FOR COMMAND PROCESSING
	
	// Program variables
	private static ArrayList<Command> userCmds = new ArrayList<Command>();
	private static ArrayList<String> userIncludes = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createAndShowAllGUIs();
	}
	
    private static void createAndShowAllGUIs() {       
        // Set up and display init window
    	createInitWindow();
        initWindow.setPreferredSize(new Dimension(300,150));
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
    	String[] languages = {"java", "c++", "python"};
    	JComboBox langDropDown = new JComboBox(languages);
    	
    	inputs.add(new JLabel("Name of Program:"));
    	inputs.add(nameofProgram);
    	inputs.add(new JLabel("Language:"));
    	inputs.add(langDropDown);
    	inputs.add(new JLabel("Number of Commands:"));
    	inputs.add(numCommands);
    	
    	// Add ActionListener for submit button
    	
        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	// Get data from user
            	programParams[0] = nameofProgram.getText();
            	programParams[1] = (String)langDropDown.getSelectedItem();
            	programParams[2] = numCommands.getText();
            	//System.out.print(programParams[0] + "\t" + programParams[1] + "\t" + programParams[2] + "\n");
            	
            	// Close init window
            	initWindow.setVisible(false);
            	
            	// Open up command input window
            	createCommandsWindow();
            }
        });
        submit.setMnemonic('X');

    	
    	// Add JPanel to pane
    	initWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	initWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    }
    
    private static void createCommandsWindow() {
    	// Set up the command window
    	cmdsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Create JPanel to put the add command buttons on
    	JPanel cmds = new JPanel();
    	
    	int numCommands = Integer.parseInt(programParams[2]);
    	cmdButtons = new CommandButton[numCommands];
    	userCmds = new ArrayList<Command>();
    	for (int i = 0; i < numCommands; i++) {
    		// Set size properly
    		userCmds.add(new Command());
    	}
    	
    	cmdsWindow.setPreferredSize(new Dimension(numCommands * 150,100));
    	
	    cmds.setLayout(new GridLayout(1,numCommands));
	    for (int i = 0; i < numCommands; i++ ) {
	    	cmdButtons[i] = new CommandButton(Integer.toString(i), programParams[1], i);
	    	
	    	cmdButtons[i].addActionListener();
	    	
	    	cmds.add(cmdButtons[i]);
	    }
	    JButton export = new JButton("EXPORT");
	    cmds.add(export);
	    
    	export.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			Program p = new Program(userCmds, userIncludes);
    			FileCreator f = new FileCreator(programParams[0], programParams[1], p);
    			try {
					f.createCodeFile();
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
    	});
	    
	    cmdsWindow.getContentPane().add(cmds);
	    cmdsWindow.setLocation(100, 0);
	    
	    cmdsWindow.pack();
	    cmdsWindow.setVisible(true);
    }
    
    public static void createCommandInputWindow() {   	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(2,2));
    	
    	JComboBox cmds = new JComboBox(Interpretor.cmds);
    	JButton submit = new JButton("SUBMIT");
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			String cmd = (String)cmds.getSelectedItem();
    			currentCmdType = cmd;
    			
    			currentCommandString += (String)cmds.getSelectedItem();
    			
    			if (!(cmd.equals("ForLoop"))) {
        			createRegCommandDetailWindow();
    			}
    			else {
    				createForLoopSetupWindow();
    				isSubCmd = true;
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
    	//System.out.println("regCmdDetailWindow " + currentCmdIdx);

    	//cmdDetailsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            	
            	if (currentCommandString.indexOf("ForLoop") != -1) {
            		currentCommandString += ";";
            	}
            	
    			System.out.println(currentCommandString);       	
            	
    			if (!isSubCmd) {
                	// Create Command object from currentCommandString
                	Command c = Interpretor.interpret(currentCommandString, programParams[1]);
                	c.setLanguage(programParams[1]);
                	Interpretor.createLineOfCode(c);
                		
                	// Only add regular commands to the userCmds array list
                	userCmds.set(currentCmdIdx, c);
                	
                	// Clear old command data
                	currentCommandString = "";
                	currentCmdType = "";
                	isSubCmd = false;
    			}
    			
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
    	
    	// Add placeholder to command string for parameters
    	currentCommandString += "&&&";
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
	   			// Get all values from text fields
    			String loopVar = loopVarName.getText();
    			String start = startVal.getText();
    			String end = endVal.getText();
    			String inc = incVal.getText();
    			
    			// Fill the place holder with for loop parameters
    			String flParamStr = "(\"" + loopVar + "\"" + "," + start + "," + end + "," + inc + ")(";
    			currentCommandString = currentCommandString.replaceAll("&&&", flParamStr);
    			
    			// Add final parenthesis
    			currentCommandString += ")";
    			
    			// Clean up final semicolon from last sub command
    			currentCommandString = currentCommandString.replaceAll(";\\)", "\\)");
    			
            	// Create Command object from currentCommandString
            	Command c = Interpretor.interpret(currentCommandString, programParams[1]);
            	c.setLanguage(programParams[1]);
            	Interpretor.createLineOfCode(c);
            		
            	// Add command to the userCmds array list
            	userCmds.set(currentCmdIdx, c);
            	
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
            	//removeCommandButton(idx);
            	cmdButtons[currentCmdIdx].setVisible(false);
            }
    	});
    	submit.setMnemonic('X');
    	
    	addCmds.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			createCommandInputWindow();
            }
    	});

    	forLoopSetupWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	forLoopSetupWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	forLoopSetupWindow.pack();
    	forLoopSetupWindow.setVisible(true);
    }
    
    private static void createIfElseSetupWindow() {
    	
    }
}
