package gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

import math.Maths;

public abstract class Button {
	
	protected int x, y, width, height;
	protected Texture texture, pressedTexture;
	protected boolean isPressed, isVisible;
	protected OnClickListener onClick;
	
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
	
	protected void resizeTextures() {
		texture = texture.scale((double)width / (double)texture.getWidth(), (double)height / (double)texture.getHeight());
		pressedTexture = pressedTexture.scale((double)width / (double)pressedTexture.getWidth(), (double)height / (double)pressedTexture.getHeight());
		System.out.println("Resized!");
	}

	public abstract void paint(Graphics g, ImageObserver observer);
	
	public void onClick(MouseEvent e) {
		onClick.onClick();
	}
	
	public boolean inBounds(int x, int y) {
		return Maths.inRange(x, this.x, this.x + width) && Maths.inRange(y, this.y, this.y + height);
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the texture
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * @param texture the texture to set
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	/**
	 * @return the pressedTexture
	 */
	public Texture getPressedTexture() {
		return pressedTexture;
	}

	/**
	 * @param pressedTexture the pressedTexture to set
	 */
	public void setPressedTexture(Texture pressedTexture) {
		this.pressedTexture = pressedTexture;
	}
	
	public boolean isPressed() {
		return isPressed;
	}
	
	public void setPressed(boolean pressed) {
		isPressed = pressed;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
}
