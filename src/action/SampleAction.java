package action;

import java.awt.event.WindowEvent; //for CloseListener()
import java.awt.event.WindowAdapter; //for CloseListener()

/**
 * 
 * EXAMPLE LINK http://www.austintek.com/mvc/
 */

public class SampleAction extends WindowAdapter {

	public SampleAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void windowClosing(WindowEvent e) {
		e.getWindow().setVisible(false);
		System.exit(0);
	} // windowClosing()

}
