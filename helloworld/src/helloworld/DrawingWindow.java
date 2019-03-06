package helloworld;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;



@SuppressWarnings("serial")
public class DrawingWindow extends JFrame {		 
		private JButton btnReset;
        private JButton btnRecognize;
		private Component frame;
		ImageScanner Imagescanner;
		DrawingWindow drawingWindow; 
		

        
         
         
        public DrawingWindow() {
        	/* this creates a window which contains a drawing panel for the user 
        	 * to draw in, and a button to convert the drawn digit.*/
                setTitle("Drawing Panel");
                
                
                setSize(new Dimension(340, 430));
                setLocationRelativeTo(null);
                
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);
                setLayout(null);
                setBackground(Color.BLACK);
                Connection.drawingPanel = new DrawingPanel();
                JLabel lblWarning = new JLabel("Draw Above and Convert");
                lblWarning.setBounds(85, 290, 160, 30);
                btnRecognize = new JButton("Convert");
                btnRecognize.setBounds(40, 340, 120, 60);
                btnRecognize.setFocusPainted(false);
                btnRecognize.addActionListener(new ConversionListener());
        		btnReset = new JButton("Reset");
        		btnReset.setBounds(180, 340, 120, 60);
        		btnReset.setFocusPainted(false);
        		btnReset.addActionListener(new ResetButtonListener());
        		setBackground(Color.BLACK);
         
                getContentPane().add(Connection.drawingPanel);
                getContentPane().add(btnRecognize);
                getContentPane().add(btnReset);
                getContentPane().add(lblWarning);
                setVisible(true);
		
        }
        
       
    	private class ResetButtonListener implements ActionListener {
    		// this is creating a reset button so that the user can start over.
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    			Connection.drawingWindow = new DrawingWindow();
    		}

    	}
}