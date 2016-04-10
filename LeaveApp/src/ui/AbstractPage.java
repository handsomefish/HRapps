package ui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class AbstractPage extends JFrame{

	AbstractPage(){
		super();
	}
	
	abstract public void refresh();
}
