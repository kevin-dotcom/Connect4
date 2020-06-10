package gui;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class ColourButton extends Button {

	public ColourButton(int x, int y, int width, int height, String textureID,
			OnClickListener onClick) {
		super(x, y, width, height, TextureLibrary.getTexture(textureID), TextureLibrary.getTexture("pressed_" + textureID), onClick);
		
	}

	@Override
	public void paint(Graphics g, ImageObserver observer) {
		if (isVisible) {
			g.drawImage(isPressed ? pressedTexture.getImage() : texture.getImage(), x, y - height / 2, observer);
		}
	}

}
