package helloworld;

import javax.swing.UIManager;
import java.io.FileNotFoundException;

public class ApplicationLauncher {
 
	//Main method included to launch app

	public static void main(String[] args) throws FileNotFoundException  {
		// TODO Auto-generated method stub
		
		try { 
			
			UIManager.setLookAndFeel("javax.swing.plaf.NimbusLookAndFeel");
		}
		
		catch(Exception e) { 
			System.out.println("Error occured while setting " + "up the look and feel" + e.toString());
		}
		
		//runs the main view class
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				try { 
					new MainView();
				} catch (Exception e) {
					
					System.out.println("Error occured while initiating" 
					+ "the Swing thread. Please try again later...");
				}
			}
		});
		
				
				Connection.neuralNetwork = new NeuralNetwork("H:\\TestImages\\nn_weights", 0.3, 100, 25, 10);
		        System.out.println(Connection.neuralNetwork);
		        Connection.conversionWindow = new ConversionWindow();
		        Connection.drawingPanel = new DrawingPanel();
		        /* linked to the connection class and uses weights 
		         * to train the network to predict the drawn digit
		        
		        
		        This code links to the MNISTReader class */
				MNISTReader reader = new MNISTReader("H:\\TestImages\\train_Labels", "H:\\TestImages\\train_images");
				reader.readMNISTData();
	

	}

}
