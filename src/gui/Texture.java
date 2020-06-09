package gui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Stores info and allow modification of images.
*/

public class Texture {
	
	/**
	 * Image.
	 */
	private BufferedImage image;
	
	/**
	 * Creates the image.
	 * @param image the image.
	 */
	public Texture(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Scales the image.
	 * @param scaleWidth the width scale.
	 * @param scaleHeight the height scale.
	 * @return an Image object containing the scaled image.
	 */
	public Texture scale(double scaleWidth, double scaleHeight) {
	    BufferedImage scaledimage = null;
	    
	    if (image != null) {
	    	double newWidth = image.getWidth() * scaleWidth;
	    	double newHeight = image.getHeight() * scaleHeight;
	        scaledimage = new BufferedImage((int)newWidth, (int)newHeight, image.getType()); // Create BufferedImage with correct size
	        Graphics2D g = scaledimage.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(scaleWidth, scaleHeight); // Transformation
	        g.drawRenderedImage(image, at); // Create
	    }
	    
	    return new Texture(scaledimage);
	}
	
	/**
	 * @return the image.
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * @return the width of the image.
	 */
	public int getWidth() {
		return image.getWidth();
	}
	
	/**
	 * The height of the image.
	 * @return
	 */
	public int getHeight() {
		return image.getHeight();
	}
	
}
