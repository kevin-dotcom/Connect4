package components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Stores info and allow modification of images.
*/

public class Image {
	
	/**
	 * Image.
	 */
	private BufferedImage image;
	
	/**
	 * Position to be drawn.
	 */
	int x, y;
	
	/**
	 * Creates the image.
	 * @param image the image.
	 */
	public Image(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * Scales the image.
	 * @param scaleWidth the width scale.
	 * @param scaleHeight the height scale.
	 * @return an Image object containing the scaled image.
	 */
	public Image scale(double scaleWidth, double scaleHeight) {
	    BufferedImage scaledimage = null;
	    
	    if (image != null) {
	    	double newWidth = image.getWidth() * scaleWidth;
	    	double newHeight = image.getHeight() * scaleHeight;
	        scaledimage = new BufferedImage((int)newWidth, (int)newHeight, image.getType()); // Create BufferedImage with correct size
	        Graphics2D g = scaledimage.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(scaleWidth, scaleHeight); // Transformation
	        g.drawRenderedImage(image, at); // Create
	    }
	    
	    return new Image(scaledimage);
	}
	
	/**
	 * @return the image.
	 */
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * @return the x-position.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @param x the new x-position.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the y-position.
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param y the new y-position.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
