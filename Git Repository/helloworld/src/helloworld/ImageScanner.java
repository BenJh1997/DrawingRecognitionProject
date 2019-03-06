package helloworld;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageScanner extends JFrame{
	
	private JFrame Window;
	private JButton btnReset;
    private JLabel lblDigit;
    
    // opens up the window that shows the images drawn and an image
    // the system draws and recognizes.
    
    public ImageScanner() {

	 setTitle("Handwritten Digit Recognition");
     setSize(710, 480);
     setLocationRelativeTo(null);
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     setResizable(false);
     setLayout(null);

     btnReset = new JButton("Reset");
     btnReset.setBounds(565, 400, 100, 25);
     btnReset.setFocusPainted(false);
     lblDigit = new JLabel("");
     lblDigit.setFont(new Font("Verdana", Font.PLAIN, 100));
     lblDigit.setBounds(320, 340, 150, 100);
     getContentPane().add(btnReset);
     getContentPane().add(lblDigit);
 
     
    Window = new JFrame("Handwritten recognition"); 
		
	Window.setLayout(new BorderLayout());


     DrawingPanel drawingPanel; 
     DrawingWindow drawingWindow; 
     MainView mainView;
     
 	
 	Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 	// Set the width and height of mainWindow
 	// mainWindow.setSize(new Dimension(200, 100));;
 	Window.pack();
     // Display the window.
 	Window.setVisible(true);


		
	}
}