package game;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import components.Board;
import file.FileIO;
import player.Computer;
import player.Player;
import statistics.GameStatistics;
import statistics.Timer;

public class GameLoop {
	
	private static String move;
	private static boolean isRunning = true;
	private static boolean isPlayer1Turn = true;
	private static boolean isWaitingForMove = false;
	private static Player[] players = new Player[2];
	
	private static GameStatistics gs;
	
	public static void writeStatistics() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");  
		LocalDateTime now = LocalDateTime.now();  
		String date = dtf.format(now);
		
		String filename = "Statistics/Game Statistics/Game Statistics " + date + ".txt";
		
		FileIO.beginWriting(filename);
		FileIO.print(gs);
		FileIO.endWriting();
		
		System.out.println("Written game statistics to " + filename);
	}
	
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
		players[0] = new Player("Player 1", 'r');
		players[1] = new Computer('y');
		Timer.createTimer("Game");
		Timer.createTimer(players[0].getName());
		Timer.createTimer(players[1].getName());
		
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
		
		gs = new GameStatistics(players[0], players[1]);
		
		Timer.startTiming();
		Timer.startTimer(players[0].getName());
		Timer.startTimer("Game");
		while (isRunning) {
			if (!isWaitingForMove) {
				players[isPlayer1Turn ? 0 : 1].requestMove();
				isWaitingForMove = true;
			}
			if (move != null) {
				if (isPlayer1Turn) {
					Timer.stopTimer(players[0].getName());
					Timer.startTimer(players[1].getName());;
				}
				else {
					Timer.stopTimer(players[1].getName());
					Timer.startTimer(players[0].getName());
				}
				
				gs.update(move);
				
				System.out.println(move);
				isPlayer1Turn = !isPlayer1Turn;
				isWaitingForMove = false;
				move = null;
				Board.logGrid();
			}
			
			Timer.updateTimers();
		}
		
		Timer.stopTiming();
		
		gs.setTime(Timer.getTimer(players[0].getName()), Timer.getTimer(players[1].getName()), Timer.getTimer("Game"));
		
		if (players[1] instanceof Computer) {
			gs.updateComputerAverageStatistics();
		}
		
		System.out.print("Enter YES to save game statistics, or NO to exit game: ");
		
		// TODO: create button in GUI to accomplish this task
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String input = reader.readLine();
			
			if (input.equals("YES")) {
				writeStatistics();
			}
			else {
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
