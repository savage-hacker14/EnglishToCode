// Runner_v2.java
// This program has the features of the Runner class but also supports code for the GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Runner_v2 {
	private static JFrame initWindow = new JFrame("Init Window");
	private static JFrame cmdsWindow = new JFrame("Commands ");
	private static JFrame cmdInputWindow = new JFrame("Command Input");
	private static JFrame cmdDetailsWindow = new JFrame("Command Details");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createAndShowAllGUIs();
	}
	
    private static void createAndShowAllGUIs() {       
        // Set up and display init window
    	String[] programParams = createInitWindow();
        initWindow.setPreferredSize(new Dimension(300,150));
        initWindow.pack();
        initWindow.setVisible(true);
    }
    
    private static String[] createInitWindow() {
    	// Create and set up the window.
        initWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create String array to store use inputs in GUI
        String[] programParams = new String[3];
    	
    	// Create text field for necessary inputs on init window
    	JTextField nameofProgram = new JTextField();
    	JTextField language = new JTextField();
    	JTextField numCommands = new JTextField();
    	
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
            	System.out.print(programParams[0] + "\t" + programParams[1] + "\t" + programParams[2] + "\n");
            	
            	// Close init window
            	initWindow.setVisible(false);
            	
            	// Open up command input window
            	createCommandsWindow(programParams);
            }
        });
    	
    	// Add JPanel to pane
    	initWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	initWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	
    	// Return program parameters
    	return programParams;
    }
    
    private static void createCommandsWindow(String[] programParams) {
    	// Set up the command window
    	cmdsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	System.out.print(programParams[0] + "\t" + programParams[1] + "\t" + programParams[2] + "\n");
    	
    	// Create JPanel to put the add command buttons on
    	JPanel cmds = new JPanel();
    	
    	int numCommands = Integer.parseInt(programParams[2]);

    	cmdsWindow.setPreferredSize(new Dimension(numCommands * 125,100));
    	
	    cmds.setLayout(new GridLayout(1,numCommands));
	    for (int i = 0; i < numCommands; i++ ) {
	    	JButton addCmd = new JButton("+");
	    	cmds.add(addCmd);
	    	
	    	addCmd.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent e){
	            	createCommandInputWindow();
	            }
	    	});
	    }
	    cmds.add(new JButton("EXPORT"));
	    
	    cmdsWindow.getContentPane().add(cmds);
	    cmdsWindow.setLocation(100, 0);
	    
	    cmdsWindow.pack();
	    cmdsWindow.setVisible(true);
    }
    
    private static void createCommandInputWindow() {
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(2,2));
    	
    	JComboBox cmds = new JComboBox(Interpretor.cmds);
    	JButton submit = new JButton("SUBMIT");
    	
    	submit.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
            	String cmd = (String)cmds.getSelectedItem();
            	
            	if (!(cmd.equals("ForLoop") || cmd.equals("If"))) {
            		createRegCommandDetailWindow();
            	}
            }
    	});
    	
    	inputs.add(new JLabel("Command:"));
    	inputs.add(cmds);
    	
    	cmdInputWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	cmdInputWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	
    	cmdInputWindow.setPreferredSize(new Dimension(300,120));
    	cmdInputWindow.setLocation(100, 100);
    	
    	cmdInputWindow.pack();
    	cmdInputWindow.setVisible(true);
    }
    
    private static String[] createRegCommandDetailWindow() {
    	//cmdDetailsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	cmdDetailsWindow.setPreferredSize(new Dimension(300,110));
    	cmdDetailsWindow.setLocation(100,225);
    	
    	String[] output = new String[2];
    	
    	JPanel inputs = new JPanel();
    	inputs.setLayout(new GridLayout(2,2));
    	
    	JTextField varName = new JTextField();
    	JTextField params = new JTextField();
    	JButton submit = new JButton("SUBMIT");
    	
    	inputs.add(new JLabel("Variable Name"));
    	inputs.add(varName);
    	inputs.add(new JLabel("Parameters"));
    	inputs.add(params);
    	
    	// Add action listener for submit button
    	submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	output[0] = varName.getText();
            	output[1] = params.getText();
            	
            	// Close this window along with the command input window
            	cmdDetailsWindow.setVisible(false);
            	cmdInputWindow.setVisible(false);
            }
        });
    	
    	cmdDetailsWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	cmdDetailsWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	cmdDetailsWindow.pack();
    	cmdDetailsWindow.setVisible(true);
	    
	    return output;
    }

}
