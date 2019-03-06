package helloworld;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MNISTReader {

	
		String train_label_filename;
		String train_image_filename;
		
		
		DataInputStream label_data_stream = null; 
		DataInputStream image_data_stream = null; 
	
		
		ArrayList<int[][]> image_list;
		int[] label_list;
		
		// this is the default constructor
		public MNISTReader() {
			
		}
		public MNISTReader(String labelpath, String imagePath) {
			this.train_label_filename = labelpath;
			this.train_image_filename= imagePath;
			
			image_list = new ArrayList<int[][]>();
		}
		
		public int[] getLabels() {
			return label_list;
		}
		
		public ArrayList<int[][]> getImages() { 
			return image_list;
		}
		

		public void readMNISTData() {
			/*this loads the train labels and images for use 
			 * in the Knn algorithm */
			try {
				label_data_stream = new DataInputStream(new FileInputStream(train_label_filename));
				image_data_stream = new DataInputStream(new FileInputStream(train_image_filename));
				 
				int startcode_img = image_data_stream.readInt();
				int startcode_label = label_data_stream.readInt();
				
				System.out.println("start code: images = " + startcode_img + 
						" startcode labels = " + startcode_label);
				
				int number_of_labels = label_data_stream.readInt();
				int number_of_images = image_data_stream.readInt();
				
				System.out.println("number of labels and images: " + number_of_labels + " and " + number_of_images);

				int image_height = image_data_stream.readInt();
				int image_width = image_data_stream.readInt();
				
				System.out.println("Image Size: " + image_width + " x" + image_height);
				
				byte[] label_data = new byte[number_of_labels];
				
				int image_size = image_height * image_width;
				byte[] image_data = new byte[image_size * number_of_images];
				
				label_data_stream.read(label_data);
				label_data_stream.read(image_data);
				
				int[][] image;
				label_list = new int[number_of_labels];
				
				for(int i=0; i< number_of_labels; i++)
				{
					int label = label_data[i];
					label_list[i] = label;
					
					
					System.out.println(label);
					
					image = new int[image_width][image_height];
					
					for(int row = 0; row < image_height; row++) { 
						for(int col = 0; col < image_width; col++) {
							
							image[row][col] = image_data[(i*image_size) +((row*image_width) + col)];
						}
					}
					image_list.add(image);
				}
				
			}
			catch(IOException e) {
				
			}
		  
		    }
		
		
		  public double getDistance(final double[] features1, final double[] features2) {
		        double sum = 0;
		        for (int i = 0; i < features1.length; i++)
		            sum += Math.pow(features1[i] - features2[i], 2);
		        return Math.sqrt(sum);
		        
		  }
		  
}
	