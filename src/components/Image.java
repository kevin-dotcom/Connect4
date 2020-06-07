package components;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Image {
	
	private BufferedImage image;
	int x, y;
	
	public Image(BufferedImage image) {
		this.image = image;
	}
	
	public Image scale(double scaleWidth, double scaleHeight) {
	    BufferedImage scaledimage = null;
	    
	    if (image != null) {
	    	double newWidth = image.getWidth() * scaleWidth;
	    	double newHeight = image.getHeight() * scaleHeight;
	        scaledimage = new BufferedImage((int)newWidth, (int)newHeight, image.getType());
	        Graphics2D g = scaledimage.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(scaleWidth, scaleHeight);
	        g.drawRenderedImage(image, at);
	    }
	    
	    return new Image(scaledimage);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
