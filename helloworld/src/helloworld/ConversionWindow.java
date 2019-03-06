package helloworld;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class ConversionWindow extends JFrame {

	private JButton btnReset;
	private JLabel lblDigit;
	private BufferedImage image;
	private int[] rectCoords;
	private boolean[][] bits;
	
	public ConversionWindow() {
		setTitle("Handwritten Digit Recognition");
		setSize(710, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		btnReset = new JButton("Reset");
		btnReset.setBounds(565, 400, 100, 25);
		btnReset.setFocusPainted(false);
		btnReset.addActionListener(new ResetButtonListener());
		lblDigit = new JLabel("");
		lblDigit.setFont(new Font("Verdana", Font.PLAIN, 100));
		lblDigit.setBounds(320, 340, 150, 100);
		getContentPane().add(btnReset);
		getContentPane().add(lblDigit);
		getContentPane().add(new ImagesPanelView());
	}

	// Gets the image data that was drawn in the DrawPanel.
	 
	public void loadImage() {
		boolean[][] data = Connection.drawingPanel.getData();
		image = ImageUtilities.getImage(data);
		rectCoords = ImageUtilities.getSquare(data);
		bits = ImageUtilities.getBits(rectCoords, data);
	}
	
	public void recognize() {
		boolean[][] booleanBits = Connection.conversionWindow.getImageBits();
		int[] intBits = new int[100];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				intBits[10*j + i] = (booleanBits[i][j])? 1 : 0;
		int y = Connection.neuralNetwork.eval(intBits);
		lblDigit.setText(y + "");
	}

	
	public int[] getRectangleCoords() {
		return rectCoords;
	}

	
	public boolean[][] getImageBits() {
		return bits;
	}

	
	  /*this is used to get the drawn image.*/
	 
	public BufferedImage getImage() {
		return image;
	}

	/* this method is called when the user clicks the Reset button*/
	private class ResetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			Connection.drawingPanel = new DrawingPanel();
		}

	}

}