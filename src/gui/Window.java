package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JFrame;

import game.GameLoop;
import player.Player;

public class Window {

	private static JFrame frame;
	private static BoardPanel boardPanel;

	private static Dimension appSize;

	private static boolean disposed;

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


	public static void init() {
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

	public static void addWindowListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}

	public static void enableColourButtons(char colour) {
		boardPanel.setColourButtonsVisible(true);
		boardPanel.setColourButtonsColour(colour);
	}

	public static void disableColourButtons() {
		boardPanel.setColourButtonsVisible(false);
	}

	public static void dispose() {
		if (!disposed) {
			frame.dispose();
			disposed = true;
		}
	}

	public static Panel getPanel() {
		return boardPanel;
	}

	public static Dimension getAppSize() {
		if (appSize == null) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			appSize = new Dimension(screenSize.width - 100, screenSize.height - 100);
		}

		return appSize;
	}
	
	public static void showVictoryMessage(Player winner) {
		boardPanel.showVictoryMessage(winner);
	}
	
	public static void disableInput() {
		boardPanel.disableInput();
	}

	public static void update() {
		boardPanel.update();
	}

}
