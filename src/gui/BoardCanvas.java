package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import components.Image;

public class BoardCanvas extends Canvas {

	private static final long serialVersionUID = -550457220750909458L;
	
	private List<Image> images = new ArrayList<>();
	
	public void paint(Graphics g) {
		for (Image image : images) {
			g.drawImage(image.getImage(), image.getX(), image.getY(), this);
		}
	}
	
	public void addImage(Image image) {
		images.add(image);
	}
	
	public void clear() {
		images.clear();
	}
	
}
