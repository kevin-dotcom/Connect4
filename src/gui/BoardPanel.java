package gui;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import components.Board;
import math.Maths;
import statistics.Timer;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Canvas to draw images.
*/

public class BoardPanel extends JPanel {
	
	// TODO: Document this file
	
	private static final long serialVersionUID = -550457220750909458L;
	
	private Texture blankTexture, redTexture, yellowTexture;
	private int x, y, offset;
	
	Dimension size;
	
	private List<Button> buttons = new ArrayList<>();
	private Button pressedButton;
	private char currentColour;
	
	public BoardPanel() {
		blankTexture = TextureLibrary.getTexture("blank");
		redTexture = TextureLibrary.getTexture("red_chip");
		yellowTexture = TextureLibrary.getTexture("yellow_chip");
		
		size = Window.getAppSize();
		
		getBoardSize();
		resizeTextures();
		
		Button menuButton = new TextButton("Menu", size.width / 50, size.height / 50, size.width / 10, size.height / 10, new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("Clicked!");
				Timer.stopTiming();
			}
			
		});
		
		menuButton.setVisible(true);
		
		buttons.add(menuButton);
		
		for (int i = 0; i < 7; i++) {
			int column = i;
			
			buttons.add(new ColourButton(i * offset + x + 5, size.height * 9 / 10, offset - 10, size.height / 20,
					"",
					new OnClickListener() {
				
				@Override
				public void onClick() {
					boolean placed = Board.placeChip(column, currentColour);
					if (!placed) {
						displayChipNotPlacedMessage();
					}
				}
				
			}));
		}
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (pressedButton != null) {
					pressedButton.setPressed(false);
					pressedButton = null;
					update();
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				for (Button button : buttons) {
					if (button.inBounds(e.getX(), e.getY())) {
						button.setPressed(true);
						pressedButton = button;
						update();
						break;
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				for (Button button : buttons) {
					if (button.inBounds(e.getX(), e.getY()) && button.isVisible()) {
						button.onClick(e);
					}
				}
			}
			
		});
		
		InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, true), "placeColumn1");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, true), "placeColumn2");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, true), "placeColumn3");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, true), "placeColumn4");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, true), "placeColumn5");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0, true), "placeColumn6");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0, true), "placeColumn7");
		
		
		
		ActionMap actionMap = getActionMap();
		
		for (int i = 1; i <= 7; i++) {
			int column = i;
			actionMap.put("placeColumn" + i, new AbstractAction() {

				private static final long serialVersionUID = 323216131492034828L;

				@Override
				public void actionPerformed(ActionEvent e) {
					boolean placed = Board.placeChip(column - 1, currentColour);
					if (!placed) {
						displayChipNotPlacedMessage();
					}
				}
				
			});
		}
	}
	
	protected void displayChipNotPlacedMessage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(50);
					
					Graphics g = getGraphics();
					
					String text = "Invalid Move!";
					
					int width = size.width * 2 / 3;
					int height = size.height / 20;
					
					Font font = Maths.getMaxFittingFontSize(g, new Font("Ariel", Font.BOLD, 1), text, width, height);
					
					g.setColor(Color.RED);
					g.setFont(font);
					
					FontMetrics fm = g.getFontMetrics();
					
					g.drawString(text, (size.width - fm.stringWidth(text)) / 2, size.height / 20);
					
					Thread.sleep(1000);
					
					update();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
	}

	private void getBoardSize() {
		int maxX = size.width / 7;
		int maxY = size.height * 2 / 15;
		int unitSize = Math.min(maxX, maxY);
	
		offset = unitSize;
		
		if (maxX < maxY) {
			y = (size.height - unitSize * 6) / 2;
		}
		else {
			x = (size.width - unitSize * 7) / 2;
			y = size.height / 14;
		}
	}
	
	private void resizeTextures() {
		blankTexture = blankTexture.scale((double)offset / (double)blankTexture.getWidth(), (double)offset / (double)blankTexture.getHeight());
		redTexture = redTexture.scale((double)offset / (double)redTexture.getWidth(), (double)offset / (double)redTexture.getHeight());
		yellowTexture = yellowTexture.scale((double)offset / (double)yellowTexture.getHeight(), (double)offset / (double)yellowTexture.getHeight());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(20, 20, 230));
		g.fillRect(x, y, offset * 7, offset * 6);
		
		for (Button button : buttons) {
			button.paint(g, this);
		}
		
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				switch (Board.grid[r][c]) {
					case '\0':
						g.drawImage(blankTexture.getImage(), c * offset + x, (5 - r) * offset + y, this);
						break;
					case 'r':
						g.drawImage(redTexture.getImage(), c * offset + x, (5 - r) * offset + y, this);
						break;
					case 'y':
						g.drawImage(yellowTexture.getImage(), c * offset + x, (5 - r) * offset + y, this);
						break;
					default:
						break;
				}
			}
		}
	}
	
	public void update() {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				repaint();
			}
		});
	}

	public void setColourButtonsVisible(boolean visible) {
		for (Button button : buttons) {
			if (button instanceof ColourButton) {
				button.setVisible(visible);
			}
		}
		
		update();
	}

	public void setColourButtonsColour(char colour) {
		currentColour = colour;
		
		for (Button button : buttons) {
			if (button instanceof ColourButton) {
				button.setTexture(TextureLibrary.getTexture(colour == 'r' ? "red_button" : "yellow_button"));
				button.setPressedTexture(TextureLibrary.getTexture(colour == 'r' ? "pressed_red_button" : "pressed_yellow_button"));
				button.resizeTextures();
			}
		}
		
		
		
		update();
	}
	
}
