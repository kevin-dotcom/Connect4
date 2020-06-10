package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JFrame;

import game.GameLoop;
import player.Player;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-10-2020
 * Description: The window.
*/
public class Window {
	
	/**
	 * The frame.
	 */
	private static JFrame frame;
	
	/**
	 * The panel.
	 */
	private static BoardPanel boardPanel;
	
	/**
	 * The size of the window.
	 */
	private static Dimension appSize;
	
	/**
	 * If the window has been disposed.
	 */
	private static boolean disposed;
	
	/**
	 * Activates when something happens to the window.
	 */
	private static WindowListener listener = new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {

		}

		@Override
		public void windowClosing(WindowEvent e) {
			disposed = true;
			GameLoop.stopRunning();
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {

		}

		@Override
		public void windowDeiconified(WindowEvent e) {

		}

		@Override
		public void windowActivated(WindowEvent e) {

		}

		@Override
		public void windowDeactivated(WindowEvent e) {

		}

	};

	/**
	 * Initalizes the window.
	 */
	public static void init() {
		// Creates the frame
		
		frame = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		getAppSize();

		frame.setBounds((screenSize.width - appSize.width) / 2, (screenSize.height - appSize.height) / 2, appSize.width, appSize.height);
		frame.addWindowListener(listener);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);

		boardPanel = new BoardPanel();
		frame.add(boardPanel);

		frame.setVisible(true);
		frame.requestFocus();
		
		// Mouse Events
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				boardPanel.onMouseReleased(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				boardPanel.onMousePressed(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

		});
	}
	
	/**
	 * @param listener the listener.
	 */
	public static void addWindowListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}
	
	/**
	 * Enables the colour button with the given colour.
	 * @param colour the colour.
	 */
	public static void enableColourButtons(char colour) {
		boardPanel.setColourButtonsVisible(true);
		boardPanel.setColourButtonsColour(colour);
	}
	
	/**
	 * Disables the colour buttons.
	 */
	public static void disableColourButtons() {
		boardPanel.setColourButtonsVisible(false);
	}

	/**
	 * Disposes the frame, if not disposed.
	 */
	public static void dispose() {
		if (!disposed) {
			frame.dispose();
			disposed = true;
		}
	}
	
	/**
	 * @return the board panel.
	 */
	public static Panel getPanel() {
		return boardPanel;
	}
	
	/**
	 * @return the application size.
	 */
	public static Dimension getAppSize() {
		if (appSize == null) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			appSize = new Dimension(screenSize.width - 100, screenSize.height - 100);
		}

		return appSize;
	}
	
	/**
	 * Shows the victory message.
	 * @param winner the winner.
	 */
	public static void showVictoryMessage(Player winner) {
		boardPanel.showVictoryMessage(winner);
	}
	
	/**
	 * Disables input.
	 */
	public static void disableInput() {
		boardPanel.disableInput();
	}

	/**
	 * Updates the window.
	 */
	public static void update() {
		boardPanel.update();
	}

}
