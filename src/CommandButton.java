// CommandButton.java

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CommandButton extends JButton {
	private int cmdNum;			// This stores the command number for the CommandButton
	private String lang;
	private String cmd;
	private String name;
	private String params;
	private Command c;
	
	public CommandButton(String str) {
		super(str);
		cmdNum = 0;
	}
	
	public CommandButton(String str, String langToSet, int idx) {
		super(str);
		lang = langToSet;
		cmdNum = idx;
	}
	
	public void createCommand() {
		String strToProcess = cmd + " " + name + " = " + params;
		c = Interpretor.interpret(strToProcess, lang);
		Interpretor.createLineOfCode(c);
	}
	
	public Command getCommand() {
		return c;
	}
	
	public void addActionListener() {
		ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Runner_v2.createCommandInputWindow(cmdNum);
            }
        };
        
        super.addActionListener(al);
	}
	
	
	
	public int getCmdNum() {
		return cmdNum;
	}
	
	public void setCmdNum(int num) {
		cmdNum = num;
	}
	
	public void setCommand(String command) {
		cmd = command;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String nameToSet) {
		name = nameToSet;
	}
	
	public String getParams() {
		return params;
	}
	
	public void SetParams(String p) {
		params = p;
	}
}
