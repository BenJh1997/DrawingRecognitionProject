package helloworld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
	
	public MainController(ImageModel imgModel) {
	}
// works as an action event to perform a task
	@Override 
	
	
	public void actionPerformed(ActionEvent e) { 
		
		System.out.println("Button Clicked: " + e.getActionCommand());
	

}
}