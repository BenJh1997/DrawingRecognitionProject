package helloworld;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageFileHandler extends FileHandler{

	/* Class is used to link training files  */
	
	int width;
	int height;
	String image_type;
	BufferedImage img;
	
	public ImageFileHandler() {
	}
	public ImageFileHandler(String file_name){
		super(file_name);
		img = null;
		
	}
	
	@Override
	public void readFile() throws IOException {
		if(fp.isFile() && fp.exists()) {
			img = ImageIO.read(fp);
		}
	}

	@Override
	public void writeFile(String file_name) throws IOException {
		
	}

	public BufferedImage readFile(String file_name) {
		
		try {
			img = ImageIO.read(new File(file_name));
		} catch (IOException e) {
			e.printStackTrace();
			img = null;
		}
		return img;
	}
	
	public BufferedImage getImage() {
		return img;
	}
	

	
}