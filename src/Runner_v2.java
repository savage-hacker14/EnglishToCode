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
	private static JFrame forLoopCmdsWindow = new JFrame("For Loop Commands");
	
	private static JFrame ifElseSetupWindow = new JFrame("If Else Setup");
	private static JFrame ifElseTrueCmdsWindow = new JFrame("If Else True Commands");
	private static JFrame ifElseFalseCmdsWindow = new JFrame("If Else False Commands");
	
	
	private static String[] programParams;				// 0 - Name, 1 - Language, 3 - Number of commands
	private static CommandButton[] cmdButtons;			// Command buttons displayed in "Commands" window
	private static CommandButton[] subCmdButtons;
	
	private static int currentCmdIdx;					// Int used to control which command is being modified
	private static String currentCommandType = "";
	private static Command currentCommand = new Command();
	private static Command currentSubCommand = new Command();
	
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
	    	
	    	currentCommandType = "RegCommand";
	    	cmdButtons[i].addActionListener(currentCommandType);
	    	
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
    
    public static void createCommandInputWindow(int idx, String commandType) {
    	//System.out.println("cmdInputWindow " + idx);
    	System.out.println("cmdInputWindow " + commandType);
    	
    	currentCmdIdx = idx;
    	currentCommandType = commandType;
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(2,2));
    	
    	JComboBox cmds = new JComboBox(Interpretor.cmds);
    	JButton submit = new JButton("SUBMIT");
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if (currentCommandType.equals("RegCommand")) {
    				currentCommand.setCommand((String)cmds.getSelectedItem());
    				
                	if (!(currentCommand.getCommand().equals("ForLoop") || currentCommand.getCommand().equals("If"))) {
                		createRegCommandDetailWindow();
                	}
                	else {
                		if (currentCommand.getCommand().equals("ForLoop")) {
                			createForLoopSetupWindow();
                		}
                	}
    			}
				// Never getting into this loop wtf
				if (currentCommandType.equals("ForLoop")) {
					currentSubCommand.setCommand((String)cmds.getSelectedItem());
					
                	if (!(currentSubCommand.getCommand().equals("ForLoop") || currentSubCommand.getCommand().equals("If"))) {
                		createRegCommandDetailWindow();
                	}
                	else {
                		if (currentSubCommand.getCommand().equals("ForLoop")) {
                			createForLoopSetupWindow();
                		}
                	}
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
    	System.out.println("cmdDetailsWindow " + currentCommandType);
    	
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
            	if (currentCommandType.equals("RegCommand")) {
                	currentCommand.setName(varName.getText());
                	currentCommand.setParameters(params.getText());
                	currentCommand.setType(Interpretor.findType(currentCommand.getCommand(), currentCommand.getParameters(), currentCommand.getParameters()));
            		
                	// Set instance variables of command button
                	cmdButtons[currentCmdIdx].setCmdNum(currentCmdIdx);
                	cmdButtons[currentCmdIdx].setCommand(currentCommand);
            		
                	Command c = cmdButtons[currentCmdIdx].createCommand();
                	
	            	// Only add regular commands to the userCmds array list
	            	userCmds.set(currentCmdIdx, c);
	            	
	            	// Close this window along with the command input window
	            	cmdDetailsWindow.setVisible(false);
	            	cmdInputWindow.setVisible(false);
	            	
	            	// Remove + button and replace it with toString string
	            	//removeCommandButton(idx);
	            	cmdButtons[currentCmdIdx].setVisible(false);
            	}
        		if (currentCommandType.equals("ForLoop")) {            			
                	currentSubCommand.setName(varName.getText());
                	currentSubCommand.setParameters(params.getText());
                	currentSubCommand.setType(Interpretor.findType(currentSubCommand.getCommand(), currentSubCommand.getParameters(), currentSubCommand.getParameters()));
            		
                	// Set instance variables of command button
                	subCmdButtons[currentCmdIdx].setCmdNum(currentCmdIdx);
                	subCmdButtons[currentCmdIdx].setCommand(currentSubCommand);
            		
                	Command c = subCmdButtons[currentCmdIdx].createCommand();
                	
        			// Add the the sub command to the commands array list of the ForLoop object
        			((ForLoop) currentCommand).setCommand(currentCmdIdx, c);
        			
        			//Command flCmd = currentCommand;
        			
	            	// Close this window along with the command input window
	            	cmdDetailsWindow.setVisible(false);
	            	cmdInputWindow.setVisible(false);
	            	
	            	// Remove + button and replace it with toString string
	            	subCmdButtons[currentCmdIdx].setVisible(false);
	            	
	            	// Hide ForLoop command button in main commands menu once all sub commands are inputted
	            	if (currentCmdIdx == subCmdButtons.length - 1) {
	            		cmdButtons[currentCmdIdx].setVisible(false);
	            		
	            		// Properly indent the ForLoop command and its sub commands
	           			currentCommand.setLanguage(programParams[1]);
	        			String lan = currentCommand.getLanguage();
	        			if (lan.equals("java")) {
	        				((ForLoop) currentCommand).setIndentLevels(2);
	        			}
	        			else if (lan.equals("c++")) {
	        				((ForLoop) currentCommand).setIndentLevels(1);
	        			}
	        			else {
	        				// Python
	        				((ForLoop) currentCommand).setIndentLevels(0);
	        			}
	        			
	        			// Create lines of code for ForLoop object
	        			Interpretor.createLineOfCode(currentCommand);
	            		
	        			// Add ForLoop command to the main program commands list
	            		userCmds.set(currentCmdIdx, currentCommand);
	            	}
        		}
        	
            	// Clear any old text
            	varName.setText("");
            	params.setText("");
            	
            	// Clear old command data
            	currentCommand = new Command();
            }
        });
    	submit.setMnemonic('X');
    	
    	cmdDetailsWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	cmdDetailsWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	cmdDetailsWindow.pack();
    	cmdDetailsWindow.setVisible(true);
    }
    
    private static void createForLoopSetupWindow() {
    	forLoopSetupWindow.setPreferredSize(new Dimension(300,175));
    	forLoopSetupWindow.setLocation(100,225);
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(5,2));
    	
    	JTextField loopVarName = new JTextField("");
    	JTextField startVal = new JTextField("");
    	JTextField endVal = new JTextField("");
    	JTextField incVal = new JTextField("");
    	JTextField numCmds = new JTextField("");
    	JButton submit = new JButton("SUBMIT");
    	
    	inputs.add(new JLabel("Loop variable name:"));
    	inputs.add(loopVarName);
    	inputs.add(new JLabel("Start value:"));
    	inputs.add(startVal);
    	inputs.add(new JLabel("End value (excl):"));
    	inputs.add(endVal);
    	inputs.add(new JLabel("Increment Value:"));
    	inputs.add(incVal);
    	inputs.add(new JLabel("Number of commands:"));
    	inputs.add(numCmds);
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			// Get all values from text fields
    			String loopVar = loopVarName.getText();
    			String start = startVal.getText();
    			String end = endVal.getText();
    			String inc = incVal.getText();
    			int numCommands = Integer.parseInt(numCmds.getText());
    			
    			// Set up basics for ForLoop
    			currentCommand = new ForLoop();
    			currentCommand.setLanguage(programParams[1]);
    			
    			((ForLoop) currentCommand).setLoopVar(loopVar);
    			((ForLoop) currentCommand).setStart(start);
    			((ForLoop) currentCommand).setEnd(end);
    			((ForLoop) currentCommand).setIncrement(inc);
    			((ForLoop) currentCommand).setNumCommands(numCommands);
    			currentCommand.setType(Interpretor.findForLoopType(start));
    			
    			// Call command input window (tweak it first so that it can work with this)
    			createSubCommandsWindow("ForLoop", numCommands);
            }
    	});
    	submit.setMnemonic('X');
    	
    	forLoopSetupWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	forLoopSetupWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	forLoopSetupWindow.pack();
    	forLoopSetupWindow.setVisible(true);
    }
    
    private static void createSubCommandsWindow(String subCmdType, int numCommands) {
    	// Create JPanel to put the add command buttons on
    	JPanel cmds = new JPanel();
    	
    	if (subCmdType.equals("ForLoop")) {       	
      	  	subCmdButtons = new CommandButton[numCommands];
        	
        	forLoopCmdsWindow.setPreferredSize(new Dimension(numCommands * 150,100));
        	
    	    cmds.setLayout(new GridLayout(1,numCommands));
    	    for (int i = 0; i < numCommands; i++ ) {
    	    	subCmdButtons[i] = new CommandButton(Integer.toString(i), programParams[1], i);
    	    	
    	    	subCmdButtons[i].addActionListener("ForLoop");
    	    	
    	    	cmds.add(subCmdButtons[i]);
    	    }
    	}
    	
    	forLoopCmdsWindow.getContentPane().add(cmds);
    	forLoopCmdsWindow.setLocation(100,400);
    	forLoopCmdsWindow.pack();
    	forLoopCmdsWindow.setVisible(true);
    }
    
    private static void createIfElseSetupWindow() {
    	
    }
}
