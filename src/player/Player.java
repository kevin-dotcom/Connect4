package player;

import java.io.*;

import components.Board;
import game.GameLoop;

public class Player {
	
	private String name;
	private char colour;
	private long totalTimeMiilis;
	private int moves;
	
	// Temporary: Will only remain until Window class is created
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public Player(String name, char colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public void requestMove() {
		// Will be added when GUI implemented
		// Window.enableInputButtons();
		// Window.setCurrentPlayerColour(colour);
		
		// TEMPORARY
		// Will only remain for testing purposes / until GUI is created
		try {
			int column = Integer.parseInt(reader.readLine());
			boolean placedChip = Board.placeChip(colour, column);
			if (!placedChip) {
				System.out.println("That move was invalid!");
				return;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
