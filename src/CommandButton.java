// CommandButton.java

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CommandButton extends JButton {
	private int cmdNum;			// This stores the command number for the CommandButton
	private String lang;
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
	
	public Command createCommand() {
		c.setLanguage(lang);
		Interpretor.createLineOfCode(c);
		//System.out.println(c.toString());
		
		return c;
	}
	
	public void addActionListener(String commandType) {
		ActionListener al = null;
		
		if (commandType.equals("RegCommand")) {
			al = new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	            	Runner_v2.createCommandInputWindow(cmdNum, "RegCommand");
	            }
	        };
		}
		else {
			if (commandType.equals("ForLoop")) {
				al = new ActionListener(){
		            public void actionPerformed(ActionEvent e){
		            	Runner_v2.createCommandInputWindow(cmdNum, "ForLoop");
		            }
		        };
			}
		}
		
        super.addActionListener(al);
	}
	
	
	
	public int getCmdNum() {
		return cmdNum;
	}
	
	public void setCmdNum(int num) {
		cmdNum = num;
	}
	
	public void setCommand(Command cToSet) {
		c = cToSet;
	}
}
