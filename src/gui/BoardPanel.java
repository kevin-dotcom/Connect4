package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import components.Board;
import game.GameLoop;
import math.Maths;
import player.Computer;
import player.Player;
import statistics.Timer;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Panel containing the game board.
 */
public class BoardPanel extends Panel {

	private static final long serialVersionUID = 8158722145861447126L;
	
	/**
	 * Stores if the game has started or ended.
	 */
	private boolean gameStarted, gameEnded;
	
	/**
	 * Textures of the chips.
	 */
	private Texture blankTexture, redTexture, yellowTexture;
	
	/**
	 * The top-left corner of the board.
	 */
	private int x, y;
	
	/**
	 * The size of, and therefore the offset between, each unit on the board.
	 */
	private int offset;
	
	/**
	 * List of the buttons in the panel.
	 */
	private List<Button> buttons = new ArrayList<>();
	
	/**
	 * The button that is being pressed, when applicable.
	 * Null when no button is being pressed.
	 */
	private Button pressedButton;
	
	/**
	 * Game mode selection buttons.
	 */
	private Button playAgainstPlayer, playAgainstComputer;
	
	/**
	 * The colour of current player.
	 */
	private char currentColour;
	
	/**
	 * Creates the board panel.
	 */
	public BoardPanel() {
		// Retrieve textures
		
		blankTexture = TextureLibrary.getTexture("blank");
		redTexture = TextureLibrary.getTexture("red_chip");
		yellowTexture = TextureLibrary.getTexture("yellow_chip");

		size = Window.getAppSize();

		getBoardSize();
		resizeTextures();
		
		// TODO: Link menu
		
		Button menuButton = new TextButton("Menu", size.width / 50, size.height / 50, size.width / 10, size.height / 10, new OnClickListener() {

			@Override
			public void onClick() {
				System.out.println("Clicked!");
				Timer.stopTiming();
			}

		});

		menuButton.setVisible(true);
		
		buttons.add(menuButton);
		
		// Game mode selection
		
		playAgainstPlayer = new TextButton("Player vs Player", size.width / 3, size.height / 4 - 10, size.width / 3, size.height / 4, new OnClickListener() {

			@Override
			public void onClick() {
				if (gameEnded) {
					return;
				}

				GameLoop.setPlayers(new Player("Player 1", 'r'), new Player("Player 2", 'y'));

				GameLoop.run();
				gameStarted = true;

				playAgainstPlayer.setVisible(false);
				playAgainstComputer.setVisible(false);
			}

		});

		playAgainstPlayer.setVisible(true);

		buttons.add(playAgainstPlayer);

		playAgainstComputer = new TextButton("Player vs Computer", size.width / 3, size.height / 2 + 10, size.width / 3, size.height / 4, new OnClickListener() {

			@Override
			public void onClick() {
				Player player = new Player("Player 1", 'r');

				GameLoop.setPlayers(player, new Computer('y', player));

				GameLoop.run();
				gameStarted = true;

				playAgainstPlayer.setVisible(false);
				playAgainstComputer.setVisible(false);
			}

		});

		playAgainstComputer.setVisible(true);

		buttons.add(playAgainstComputer);
		
		// Buttons for placing chips
		
		for (int i = 0; i < 7; i++) {
			int column = i;

			buttons.add(new ColourButton(i * offset + x + 5, size.height * 9 / 10, offset - 10, size.height / 20,
					"",
					new OnClickListener() {

				@Override
				public void onClick() {
					if (gameEnded) {
						return;
					}

					boolean placed = Board.placeChip(column, currentColour);
					if (!placed) {
						displayChipNotPlacedMessage();
					}
				}

			}));
		}
		
		// Keyboard bindings
		
		InputMap inputMap = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

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
					if (gameEnded) {
						return;
					}

					boolean placed = Board.placeChip(column - 1, currentColour);
					if (!placed) {
						displayChipNotPlacedMessage();
					}
				}

			});
		}
	}
	
	/**
	 * Tells the user that a chip has not been placed.
	 */
	private void displayChipNotPlacedMessage() {
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
	
	/**
	 * Displays who won.
	 * @param player the player who has won.
	 */
	public void showVictoryMessage(Player player) {
		new Thread(() -> {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Graphics g = getGraphics();

			String text = player.getName() + " Won!";

			int width = size.width * 2 / 3;
			int height = size.height / 20;

			Font font = Maths.getMaxFittingFontSize(g, new Font("Ariel", Font.BOLD, 1), text, width, height);

			g.setColor(Color.RED);
			g.setFont(font);

			FontMetrics fm = g.getFontMetrics();

			g.drawString(text, (size.width - fm.stringWidth(text)) / 2, size.height / 20);
		}).start();
	}

	/**
	 * Disables input.
	 */
	public void disableInput() {
		for (Button button : buttons) {
			button.setVisible(false);
		}

		update();

		gameEnded = true;
	}
	
	/**
	 * Gets the size of the board.
	 */
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
	
	/**
	 * Resizes the textures.
	 */
	private void resizeTextures() {
		blankTexture = blankTexture.scale((double)offset / (double)blankTexture.getWidth(), (double)offset / (double)blankTexture.getHeight());
		redTexture = redTexture.scale((double)offset / (double)redTexture.getWidth(), (double)offset / (double)redTexture.getHeight());
		yellowTexture = yellowTexture.scale((double)offset / (double)yellowTexture.getHeight(), (double)offset / (double)yellowTexture.getHeight());
	}

	@Override
	/**
	 * Paints this panel.
	 */
	protected void paintComponent(Graphics g) {
		if (gameStarted) {

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
		else {
			playAgainstPlayer.paint(g, this);
			playAgainstComputer.paint(g, this);
		}
	}

	@Override
	/**
	 * Called when a mouse button is released.
	 */
	public void onMouseReleased(MouseEvent e) {
		if (e.getButton() != MouseEvent.BUTTON1 || gameEnded) {
			return;
		}

		if (pressedButton != null) {
			pressedButton.setPressed(false);

			if (pressedButton.inBounds(e.getX(), e.getY())) {
				pressedButton.onClick(e);
			}

			pressedButton = null;

			update();
		}
	}

	@Override
	/**
	 * Called when a mouse button is pressed.
	 */
	public void onMousePressed(MouseEvent e) {
		if (e.getButton() != MouseEvent.BUTTON1 || gameEnded) {
			return;
		}

		System.out.println(e.getX() + " " + e.getY());

		for (Button button : buttons) {
			if (button.inBounds(e.getX(), e.getY())) {
				button.setPressed(true);
				pressedButton = button;
				update();
				break;
			}
		}
	}
	
	/**
	 * Repaints the panel on the AWT event dispatching thread.
	 */
	public void update() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				repaint();
			}
		});
	}
	
	/**
	 * Sets the visibility of the colour buttons
	 * @param visible the visibilitiy of the colour buttons.
.	 */
	public void setColourButtonsVisible(boolean visible) {
		for (Button button : buttons) {
			if (button instanceof ColourButton) {
				button.setVisible(visible);
			}
		}

		update();
	}
	
	/**
	 * Sets the colour on the colour buttons.
	 * @param colour the colour.
	 */
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
