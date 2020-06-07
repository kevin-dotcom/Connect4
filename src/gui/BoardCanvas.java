package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import components.Image;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Canvas to draw images.
*/

public class BoardCanvas extends Canvas {

	private static final long serialVersionUID = -550457220750909458L;
	
	/**
	 * List of images.
	 */
	private List<Image> images = new ArrayList<>();
	
	/**
	 * Draw images.
	 */
	public void paint(Graphics g) {
		for (Image image : images) {
			g.drawImage(image.getImage(), image.getX(), image.getY(), this);
		}
	}
	
	/**
	 * Add an image to the list of images.
	 * @param image the image.
	 */
	public void addImage(Image image) {
		images.add(image);

		revalidate();
		repaint();
	}
	
	/**
	 * Clears the list of images.
	 */
	public void clear() {
		images.clear();

		revalidate();
		repaint();
	}
	
}
