package helloworld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagesPanelView extends JPanel {
	
	 /* this method creates a panel where the image drawn by the user and the 
	  * binary converted image are displayed */
	public ImagesPanelView() {
		setPreferredSize(new Dimension(710, 430));
		setBounds(0, 0, 710, 430);
	}
	
	public void paint(Graphics g) {
		/* used to make the graphics for the draw image*/
		BufferedImage image = Connection.conversionWindow.getImage();
		int[] rCoords = Connection.conversionWindow.getRectangleCoords();
		for (int i = 0; i < rCoords.length; i++) rCoords[i] += 50;
		boolean[][] bits = Connection.conversionWindow.getImageBits();
		if (image != null)
			g.drawImage(image, 50, 50, this);
		
		
		if (rCoords != null) {
			g.setColor(Color.RED);
			g.drawRect(rCoords[0] - 1, rCoords[1] - 1,
				rCoords[2] - rCoords[0] + 2, rCoords[3] - rCoords[1] + 2);	
		}
		
		
		g.setColor(Color.BLACK);
		g.fillRect(380, 50, 280, 280);
		g.setColor(Color.WHITE);
		for (int i = 408; i < 660; i+= 28) {
			g.drawLine(i, 50, i, 330);
			g.drawLine(380, i - 330, 660, i - 330);
		}
		
		/* this paints the binarized images bits. */
		if (bits != null)
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					if (bits[i][j])
						g.fillRect(380 + 28*i, 50 + 28*j, 28, 28);
	}
	
}