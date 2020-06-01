package game;

import components.Board;
import player.Computer;
import player.Player;

public class GameLoop {
	
	private static String move;
	private static boolean isRunning = true;
	private static boolean isPlayer1Turn = true;
	private static boolean isWaitingForMove = false;
	private static Player[] players = new Player[2];
	
	public static void setMove(String move) {
		GameLoop.move = move;
	}
	
	public static boolean isRunning() {
		return isRunning;
	}
	
	public static void main(String[] args) {
		players[0] = new Player("Player1", 'r');
		players[1] = new Player("Player2", 'y');
		
		// TODO: Create Window Class
		
//		Window.init();
		
//		Window.addWindowListener(new WindowListener() {
//
//			@Override
//			public void windowOpened(WindowEvent e) {}
//
//			@Override
//			public void windowClosing(WindowEvent e) {
//				isRunning = false;
//				System.out.println("hi");
//			}
//
//			@Override
//			public void windowClosed(WindowEvent e) {}
//
//			@Override
//			public void windowIconified(WindowEvent e) {}
//
//			@Override
//			public void windowDeiconified(WindowEvent e) {}
//
//			@Override
//			public void windowActivated(WindowEvent e) {}
//
//			@Override
//			public void windowDeactivated(WindowEvent e) {}
//			
//		});
		
		while (isRunning) {
			if (!isWaitingForMove) {
				players[isPlayer1Turn ? 0 : 1].requestMove();
			}
			if (move != null) {
				System.out.println(move);
				isPlayer1Turn = !isPlayer1Turn;
				isWaitingForMove = false;
				move = null;
				Board.logGrid();
			}
		}
	}
	
}
