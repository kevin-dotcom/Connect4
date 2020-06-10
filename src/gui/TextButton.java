package gui;

import java.awt.*;
import java.awt.image.ImageObserver;

import math.Maths;

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
			
			font = Maths.getMaxFittingFontSize(g, new Font("Ariel", Font.BOLD, 1), text, width * 9 / 10, height * 9 / 10);
		}
		
		
		g.setFont(font);
		g.setColor(Color.DARK_GRAY);
		FontMetrics fm = g.getFontMetrics();
		
		g.drawRect(x - 1, y - 1, width + 1, height + 1);
		g.drawImage(isPressed ? pressedTexture.getImage() : texture.getImage(), x, y, observer);

		g.drawString(text, x + (width - fm.stringWidth(text)) / 2, y + height * 2 / 3);
	}
	
}
