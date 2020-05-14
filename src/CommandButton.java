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
	
	public CommandButton(String str) {
		super(str);
		cmdNum = 0;
	}
	
	public CommandButton(String str, int idx) {
		super(str);
		cmdNum = idx;
	}
	
	public Command createCommand() {
		String strToProcess = cmd + " " + name + " = " + params;
		Command c = Interpretor.interpret(strToProcess, lang);
		c.setLanguage(lang);
		Interpretor.createLineOfCode(c);
		
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
	
	public void setParams(String p) {
		params = p;
	}
	
	public void setLanguage(String language) {
		lang = language;
	}
}
