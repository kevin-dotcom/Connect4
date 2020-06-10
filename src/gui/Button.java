package gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

import math.Maths;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-10-2020
 * Description: Button class.
*/
public abstract class Button {
	
	/**
	 * Position and size.
	 */
	protected int x, y, width, height;
	
	/**
	 * Textures.
	 */
	protected Texture texture, pressedTexture;
	
	/**
	 * Stores states of the button.
	 */
	protected boolean isPressed, isVisible;
	
	/**
	 * Dictates the action done when the button is clicked.
	 */
	protected OnClickListener onClick;
	
	/**
	 * Creates the button.
	 * @param x the x-position.
	 * @param y the y-position.
	 * @param width the width.
	 * @param height the height.
	 * @param texture the texture.
	 * @param pressedTexture the texture when the button is pressed.
	 * @param onClick what should happen on a click.
	 */
	public Button(int x, int y, int width, int height, Texture texture, Texture pressedTexture, OnClickListener onClick) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.pressedTexture = pressedTexture;
		this.onClick = onClick;
		
		resizeTextures();
	}
	
	/**
	 * Resizes the texture to fit the dictated size.
	 */
	protected void resizeTextures() {
		texture = texture.scale((double)width / (double)texture.getWidth(), (double)height / (double)texture.getHeight());
		pressedTexture = pressedTexture.scale((double)width / (double)pressedTexture.getWidth(), (double)height / (double)pressedTexture.getHeight());
	}
	
	/**
	 * Extending buttons should override this to draw the button.
	 * @param g the graphics.
	 * @param observer the image observer.
	 */
	public abstract void paint(Graphics g, ImageObserver observer);
	
	/**
	 * Activates when the button is pressed.
	 * @param e the MouseEvent relating to the button press.
	 */
	public void onClick(MouseEvent e) {
		onClick.onClick();
	}
	
	/**
	 * Checks if the given coordinate is within the bounds of the button.
	 * @param x the x-position.
	 * @param y the y-position.
	 * @return {@code true} if it is in the bounds.
	 */
	public boolean inBounds(int x, int y) {
		return Maths.inRange(x, this.x, this.x + width) && Maths.inRange(y, this.y, this.y + height);
	}
	
	/**
	 * @return the x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the texture.
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @param texture the texture to set.
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	/**
	 * @return the pressedTexture.
	 */
	public Texture getPressedTexture() {
		return pressedTexture;
	}

	/**
	 * @param pressedTexture the pressedTexture to set.
	 */
	public void setPressedTexture(Texture pressedTexture) {
		this.pressedTexture = pressedTexture;
	}
	
	/**
	 * @return {@code true} if the button is pressed.
	 */
	public boolean isPressed() {
		return isPressed;
	}
	
	/**
	 * @param pressed if the button is pressed.
	 */
	public void setPressed(boolean pressed) {
		isPressed = pressed;
	}
	
	/**
	 * @return {@code true} if the button is visible.
	 */
	public boolean isVisible() {
		return isVisible;
	}
	
	/**
	 * @param visible the visibility to be set.
	 */
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
}
