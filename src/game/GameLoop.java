package game;

import components.Board;
import player.Player;
import statistics.Timer;

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
	
	public static void stopRunning() {
		isRunning = false;
	}
	
	public static void main(String[] args) {
		players[0] = new Player("Player1", 'r');
		players[1] = new Player("Player2", 'y');
		Timer.createTimer("Game");
		Timer.createTimer("Player 1");
		Timer.createTimer("Player 2");
		
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
		
		Timer.startTiming();
		Timer.startTimer("Player 1");
		Timer.startTimer("Game");
		while (isRunning) {
			if (!isWaitingForMove) {
				players[isPlayer1Turn ? 0 : 1].requestMove();
			}
			if (move != null) {
				if (isPlayer1Turn) {
					Timer.stopTimer("Player 1");
					Timer.startTimer("Player 2");;
				}
				else {
					Timer.stopTimer("Player 2");
					Timer.startTimer("Player 1");
				}
				
				Timer.updateTimers();
				
				System.out.println(move);
				isPlayer1Turn = !isPlayer1Turn;
				isWaitingForMove = false;
				move = null;
				Board.logGrid();
			}
		}
		
		Timer.stopTiming();
		
		System.out.println(Timer.getTimer("Player 1").getTime());
		System.out.println(Timer.getTimer("Player 2").getTime());
		System.out.println(Timer.getTimer("Game").getTime());
	}
	
}
