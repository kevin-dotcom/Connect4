package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import components.Board;
import components.Image;
import file.FileIO;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Canvas to draw images.
*/

public class BoardCanvas extends Canvas {
	
	// TODO: Document this file
	
	private static final long serialVersionUID = -550457220750909458L;
	
	private Image blankImage, redImage, yellowImage;
	private int x, y, offset;
	
	public BoardCanvas() {
		blankImage = FileIO.readImage("assets/blank.jpg");
		redImage = FileIO.readImage("assets/red.jpg");
		yellowImage = FileIO.readImage("assets/yellow.jpg");
		
		getBoardSize();
		resizeImages();
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				Board.placeChip(e.getKeyCode() - '0', 'r');
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		});
	}
	
	private void getBoardSize() {
		Dimension appSize = Window.getAppSize();
		
		int maxX = appSize.width / 7;
		int maxY = appSize.height * 2 / 15;
		int unitSize = Math.min(maxX, maxY);
	
		offset = unitSize;
		
		if (maxX < maxY) {
			y = (appSize.height - unitSize * 6) / 2;
		}
		else {
			x = (appSize.width - unitSize * 7) / 2;
			y = appSize.height / 14;
		}
	}
	
	private void resizeImages() {
		blankImage = blankImage.scale(offset / blankImage.getWidth(), offset / blankImage.getHeight());
		redImage = redImage.scale(offset / redImage.getWidth(), offset / redImage.getHeight());
		yellowImage = yellowImage.scale(offset / yellowImage.getHeight(), offset / yellowImage.getHeight());
	}
	
	/**
	 * Draw images.
	 */
	public void paint(Graphics g) {
		g.setColor(new Color(20, 20, 230));
		g.fillRect(x, y, offset * 7, offset * 6);
		
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				switch (Board.grid[r][c]) {
					case '\0':
						g.drawImage(blankImage.getImage(), c * offset + x, (5 - r) * offset + y, this);
						break;
					case 'r':
						g.drawImage(redImage.getImage(), c * offset + x, (5 - r) * offset + y, this);
						break;
					case 'y':
						g.drawImage(yellowImage.getImage(), c * offset + x, (5 - r) * offset + y, this);
						break;
					default:
						break;
			}
			}
		}
	}
	
	public void update() {
		revalidate();
		repaint();
	}
	
}
