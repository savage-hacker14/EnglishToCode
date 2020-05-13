// Runner_v2.java
// This program has the features of the Runner class but also supports code for the GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Runner_v2 {
	private static JFrame initWindow = new JFrame("Init Window");
	private static JFrame cmdWindow = new JFrame("Command Input Window");
	
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
            	createCommandInputWindow(programParams);
            }
        });
    	
    	// Add JPanel to pane
    	initWindow.getContentPane().add(inputs, BorderLayout.NORTH);
    	initWindow.getContentPane().add(submit, BorderLayout.SOUTH);
    	
    	// Return program parameters
    	return programParams;
    }
    
    private static void createCommandInputWindow(String[] programParams) {
    	// Set up the command window
    	cmdWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	System.out.print(programParams[0] + "\t" + programParams[1] + "\t" + programParams[2] + "\n");
    	
    	// Create JPanel to put the add command buttons on
    	JPanel cmds = new JPanel();
    	
    	int numCommands = Integer.parseInt(programParams[2]);

    	cmdWindow.setPreferredSize(new Dimension(numCommands * 115,100));
    	
	    cmds.setLayout(new GridLayout(1,numCommands));
	    //JButton addCmd = new JButton("+");
	    for (int i = 0; i < numCommands; i++ ) {
	    	cmds.add(new JButton("+"));
	    }
	    cmds.add(new JButton("EXPORT"));
	    
	    cmdWindow.getContentPane().add(cmds);
	    
	    cmdWindow.pack();
	    cmdWindow.setVisible(true);
    }

}
