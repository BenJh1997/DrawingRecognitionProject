package helloworld;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class MainView {
			
	
	private JFrame mainWindow;
	
	private JButton openFileBtn;
	private JTextField fileNameTxt;
	private JLabel fileNameLbl;
	private JButton grayScaleBtn;
	private JButton rgbButton;
	private JButton edgeButton;
	private JButton drawCanvasBtn;
	
	private JPanel displayPanel;
	private JLabel imageLabel;
	
	private JFileChooser fileChooser;

	MainController controller;
	
	DrawingWindow drawingWindow;
	DrawingPanel drawingPanel;
	MNISTReader mnistReader;

	private ImageModel imgModel; 
	
	
	public MainView() { 
		
	
		imgModel = new ImageModel();
		
		controller = new MainController(this.imgModel);
		
		mainWindow = new JFrame("Handwritten recognition"); 
		
		mainWindow.setLayout(new BorderLayout());
		
		 
		
		//mainWindow.setSize(new Dimension(200, 100));;

		
		//creates and adds button to the main window
		JPanel fileSelectPanel = new JPanel();
		
		fileNameLbl = new JLabel ("File name: ");
		fileNameTxt = new JTextField(50);
		fileNameTxt.setEnabled(false);
		openFileBtn = new JButton("Open File");
		
		fileSelectPanel.add(fileNameLbl);
		fileSelectPanel.add(fileNameTxt);
		fileSelectPanel.add(openFileBtn);
		fileSelectPanel.setBackground(Color.LIGHT_GRAY);
		
		mainWindow.add(fileSelectPanel, BorderLayout.PAGE_START);
		
		JPanel opButtonPanel = new JPanel();
		
		JButton rgbButton = new JButton("RGB Image");
		JButton grayScaleBtn = new JButton("Grayscale Image");
		JButton edgeButton = new JButton("Edge Image");
		JButton drawCanvasBtn = new JButton("Draw Panel");
	
		
		opButtonPanel.add(rgbButton);
		opButtonPanel.add(grayScaleBtn);
		opButtonPanel.add(edgeButton);
		opButtonPanel.add(drawCanvasBtn);
		
		 JPanel southLayout = new JPanel();
	        southLayout.setLayout(new BoxLayout(southLayout,
	        		BoxLayout.Y_AXIS));
	        
	        // add both flow layouts to southLayout
	        southLayout.add(fileSelectPanel);
	        southLayout.add(opButtonPanel);
	        opButtonPanel.setBackground(Color.LIGHT_GRAY);
	        
	        // add southLayout to mainWindow's south
	        mainWindow.add(southLayout, BorderLayout.SOUTH);
	        
	        // instantiate a new JPanel to hold image labels
	        displayPanel = new JPanel();
	        displayPanel.setPreferredSize(new Dimension(250, 250));
	        displayPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	        imageLabel = new JLabel();
	        displayPanel.setBackground(Color.DARK_GRAY);
	        displayPanel.add(imageLabel);
	        
	        // add image panel to the center of the main window
	        mainWindow.add(displayPanel, BorderLayout.CENTER);

		
		drawCanvasBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
			
				if(drawCanvasBtn != null ) {
				drawingWindow = new DrawingWindow();
				drawingWindow.setVisible(true);
				
			}
			}
		});
	

		
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the width and height of mainWindow
		// mainWindow.setSize(new Dimension(200, 100));;
		mainWindow.pack();
        // Display the window.
		mainWindow.setVisible(true);
		
		
		// Event handling
		// Register action listener with openFileBtn
		//openFileBtn.addActionListener(controller);
				
		// Using annonymous inner class
		openFileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File selectedFile = showFileChooserDialog();
				
				if(selectedFile != null) {
					fileNameTxt.setText(selectedFile.getAbsolutePath());
					try {
						imgModel.LoadImage(selectedFile);
					} catch (IOException e1) {
						
						System.out.println("IO Exception in "
								+ "imageLoad(); " + e.toString());
						
						JOptionPane.showMessageDialog(null, 
								"Error in loading the selected image!", 
								"Image Error", 
								JOptionPane.ERROR_MESSAGE);
						fileNameTxt.setText("");
					}
					

				}
				else
					fileNameTxt.setText("No file selected");	
			}
		});		
		
		rgbButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = imgModel.getRGBImage();
				if(img != null) {
					displayImage(img);
				}
				else {
					JOptionPane.showMessageDialog(null, 
							"No image file is choosen", 
							"Image error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		grayScaleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = imgModel.getGrayscaleImage();
				if (img != null)
					displayImage(img);
				else {
					JOptionPane.showMessageDialog(null, 
							"Error in loading the grayscale image", 
							"Image error", 
							JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
		
		edgeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = imgModel.getEdgeImage();
				if (img != null)
					displayImage(img);
				else {
					JOptionPane.showMessageDialog(null, 
							"Error in loading the edge image", 
							"Image error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	// creates a file chooser button that links to users files and allows them
	// to upload image and display
	private void displayImage(BufferedImage img) {
		imageLabel.setIcon(new ImageIcon(img));
	}
	
	
	
	private File showFileChooserDialog() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new 
				File(System.getProperty("user.home")));
	    int status = fileChooser.showOpenDialog(this.mainWindow);
	    File selected_file = null;
	    if (status == JFileChooser.APPROVE_OPTION) {
	        selected_file = fileChooser.getSelectedFile();
	    }
	    return selected_file;
	}

}


			
