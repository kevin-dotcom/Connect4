package gui;


import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import components.Board;
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
	
	private List<Button> buttons = new ArrayList<>();
	private Button pressedButton;
	private char currentColour;
	
	public BoardPanel() {
		blankTexture = TextureLibrary.getTexture("blank");
		redTexture = TextureLibrary.getTexture("red_chip");
		yellowTexture = TextureLibrary.getTexture("yellow_chip");
		
		getBoardSize();
		resizeTextures();
		
		Button menuButton = new TextButton("Menu", 0, 0, 200, 100, new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("Clicked!");
				Timer.stopTiming();
			}
			
		});
		
		menuButton.setVisible(true);
		
		buttons.add(menuButton);
		
		Dimension size = Window.getAppSize();
		
		for (int i = 0; i < 7; i++) {
			int column = i;
			
			buttons.add(new ColourButton(i * offset + x + 5, size.height * 9 / 10, offset - 10, size.height / 20,
					"",
					new OnClickListener() {
				
				@Override
				public void onClick() {
					Board.placeChip(column, currentColour);
				}
				
			}));
		}
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				Board.placeChip(e.getKeyCode() - '1', currentColour);
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				Board.placeChip(e.getKeyCode() - '1', currentColour);
			}
			
		});
		
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
		
		InputMap inmap = getInputMap();
		ActionMap actmap = getActionMap();
		
		inmap.put(KeyStroke.getKeyStroke('1'), "chip_column_one");
		actmap.put("chip_column_one", new Action() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hi");
			}

			@Override
			public Object getValue(String key) {
				return null;
			}

			@Override
			public void putValue(String key, Object value) {
				
			}

			@Override
			public void setEnabled(boolean b) {
				
			}

			@Override
			public boolean isEnabled() {
				return false;
			}

			@Override
			public void addPropertyChangeListener(PropertyChangeListener listener) {
				
			}

			@Override
			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub
				
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
