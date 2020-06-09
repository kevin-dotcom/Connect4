package gui;

import java.awt.*;
import java.awt.image.ImageObserver;

public class TextButton extends Button {
	
	private String text;
	private Font font;
	
	public TextButton(String text, int x, int y, int width, int height, OnClickListener onClick) {
		super(x, y, width, height, TextureLibrary.getTexture("text_button"), TextureLibrary.getTexture("pressed_text_button"), onClick);
		
		this.text = text;
	}
	
	@Override
	public void paint(Graphics g, ImageObserver observer) {
		if (!isVisible) {
			return;
		}
		
		if (font == null) {
			int fontSize = 1;
			
			font = new Font("Ariel", Font.BOLD, getMaxFittingFontSize(g, new Font("Ariel", Font.BOLD, 1), text, width * 9 / 10, height * 9 / 10));
			
			Font nextFont = null;
			int fontWidth, nextTextHeight = 0;
		}
		
		g.setFont(font);
		g.setColor(Color.DARK_GRAY);
		
		g.drawRect(x, y, width, height);
		g.drawImage(isPressed ? pressedTexture.getImage() : texture.getImage(), x, y, observer);

		g.drawString(text, x + width / 30, y  + height * 3 / 4);
	}
	
	 private int getMaxFittingFontSize(Graphics g, Font font, String string, int width, int height){
		    int minSize = 0;
		    int maxSize = 288;
		    int curSize = font.getSize();

		    while (maxSize - minSize > 2){
		    	FontMetrics fm = g.getFontMetrics(new Font(font.getName(), font.getStyle(), curSize));
		    	int fontWidth = fm.stringWidth(string);
		    	int fontHeight = fm.getLeading() + fm.getMaxAscent() + fm.getMaxDescent();

		    	if ((fontWidth > width) || (fontHeight > height)){
		    		maxSize = curSize;
		    		curSize = (maxSize + minSize) / 2;
		    	}
		    	else {
		    		minSize = curSize;
		    		curSize = (minSize + maxSize) / 2;
		    	}
		    }

		    return curSize;
		  }
	
}
