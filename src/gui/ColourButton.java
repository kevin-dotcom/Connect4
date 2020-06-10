package gui;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-10-2020
 * Description: A button that purely consists of a colour.
*/
public class ColourButton extends Button {
	
	/**
	 * Creates the button.
	 * @param x the x-position.
	 * @param y the y-position.
	 * @param width the width.
	 * @param height the height.
	 * @param textureID the name of the texture. (Must have a file called "pressed_" + textureID)
	 * @param onClick what happens when the button is clicked.
	 */
	public ColourButton(int x, int y, int width, int height, String textureID,
			OnClickListener onClick) {
		super(x, y, width, height, TextureLibrary.getTexture(textureID), TextureLibrary.getTexture("pressed_" + textureID), onClick);
		
	}

	@Override
	/**
	 * Paints the graphics.
	 */
	public void paint(Graphics g, ImageObserver observer) {
		if (isVisible) {
			g.drawImage(isPressed ? pressedTexture.getImage() : texture.getImage(), x, y - height / 2, observer);
		}
	}

}
