package gui;

import java.awt.*;
import java.awt.image.ImageObserver;

import math.Maths;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-10-2020
 * Description: Button that contains text.
*/
public class TextButton extends Button {
	
	/**
	 * The text.
	 */
	private String text;
	
	/**
	 * The font.
	 */
	private Font font;
	
	/**
	 * Creates the button.
	 * @param text the text.
	 * @param x the x-position.
	 * @param y the y-position.
	 * @param width the width.
	 * @param height the height.
	 * @param onClick what happens on a click.
	 */
	public TextButton(String text, int x, int y, int width, int height, OnClickListener onClick) {
		super(x, y, width, height, TextureLibrary.getTexture("text_button"), TextureLibrary.getTexture("pressed_text_button"), onClick);
		
		this.text = text;
	}
	
	@Override
	/**
	 * Paints this button.
	 */
	public void paint(Graphics g, ImageObserver observer) {
		if (!isVisible) {
			return;
		}
		
		// Gets biggest font that fits in this button
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
