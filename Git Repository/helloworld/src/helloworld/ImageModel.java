package helloworld;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageModel {

	private File fp;
	private BufferedImage bufImg = null;
	private BufferedImage grayscaleImg = null;
	private BufferedImage edgeImg = null;
	
	public ImageModel() {
		// TODO Auto-generated constructor stub
	}

	public void setFilePointer(File fp) {
		this.fp = fp;
	}
	
	public void LoadImage(File fp) throws IOException {
			bufImg = ImageIO.read(fp);
	}
	
	public BufferedImage getRGBImage() {
		return bufImg;
	}
	
	public BufferedImage getGrayscaleImage() {
		if(this.grayscaleImg == null && this.bufImg != null)
			return convertRGBToGrayscale(this.bufImg);
		else
			return this.grayscaleImg;
	}
	
	public BufferedImage getEdgeImage() {
		if(this.edgeImg == null && this.bufImg != null) {
			return computeImageEdges(this.bufImg);
		}
		else {
			return this.edgeImg;
		}
	}
	
	public BufferedImage convertRGBToGrayscale(BufferedImage img) {
		// TODO Auto-generated method stub
		
		this.grayscaleImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				
				int rgbvalue = img.getRGB(x,y);

				int alpha = (rgbvalue >> 24) & 0xff;
				int red = (rgbvalue >> 16) & 0xff;
				int green = (rgbvalue >> 8) & 0xff;
				int blue = (rgbvalue) & 0xff;
				
				// grayscale = ( (0.3 * R) + (0.59 * G) + (0.11 * B) ).				
				int grayscale = (int) ((0.3 * red) + (0.59 * green) + (0.11 * blue));
				int new_pixel_value = 0xFF000000 | ( grayscale << 16 ) |
						(grayscale << 8 ) |
						(grayscale);
				
				this.grayscaleImg.setRGB(x, y, new_pixel_value);
				
			}
		}
		return this.grayscaleImg;
	}
	
	public BufferedImage computeImageEdges(BufferedImage img) {
		// TODO Auto-generated method stub
		
		this.edgeImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		for(int row = 0; row < img.getHeight(); row++) {
			for(int col = 0; col < img.getWidth(); col++) {
				
				int new_pixel_value  = 0;
				
				if (col-1 < 0 || row - 1 < 0 || col+1>=img.getWidth() || row+1 >= img.getHeight()){
					new_pixel_value = 0xFF000000;
					this.edgeImg.setRGB(col, row, new_pixel_value);
					continue;
				}
				
				int p1 = img.getRGB(col-1,row-1) & 0xff;
				int p2 = img.getRGB(col,row-1) & 0xff;
				int p3 = img.getRGB(col+1,row-1) & 0xff;
				
				int p4 = img.getRGB(col-1,row) & 0xff;
				int p5 = img.getRGB(col,row) & 0xff;
				int p6 = img.getRGB(col+1,row) & 0xff;
				
				int p7 = img.getRGB(col-1,row+1) & 0xff;
				int p8 = img.getRGB(col,row+1) & 0xff;
				int p9 = img.getRGB(col+1,row+1) & 0xff;
				
				int pixel_value = Math.abs((p1 + 2 * p2 + p3) - (p7 + 2*p8 + p9)) + Math.abs((p3 + 2 * p6 + p9) - (p1 + 2*p4 + p7));
				
				new_pixel_value = 0xFF000000 | ( pixel_value << 16 ) |
						(pixel_value << 8 ) |
						(pixel_value);
				
				this.edgeImg.setRGB(col, row, new_pixel_value);
				
			}
		}
		return this.edgeImg;
	}
	
	
	
	
	
	
}
