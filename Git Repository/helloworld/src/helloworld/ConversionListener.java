package helloworld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionListener implements ActionListener {

	
	@Override
	public void actionPerformed(ActionEvent e) {
		//this class is used in the ConversionWindow class to load the images.
		
    			dispose();
    			Connection.conversionWindow.loadImage();
    			Connection.conversionWindow.setVisible(true);
    			Connection.conversionWindow.recognize();
    		}

	private void dispose() {
		
		
	}

	}