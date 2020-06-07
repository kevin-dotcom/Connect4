package player;

import java.io.*;

import components.Board;
import game.GameLoop;

public class Player {
	
	private String name;
	
	protected char colour;
	protected Thread thread;
	
	// Temporary: Will only remain until Window class is created
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public Player(String name, char colour) {
		this.name = name;
		this.colour = colour;
	}
	
	public void requestMove() {
		thread = new Thread(() -> {
			// TODO: Get working with GUI
			
			// Enable buttons
			
			// Will be added when GUI implemented
			// Window.enableInputButtons();
			
			// TODO: Customize button to reflect chip colour
			
			// Window.setCurrentPlayerColour(colour);

			// TEMPORARY
			// Will only remain for testing purposes / until GUI is created
			while (true) {
				try {
					int column = Integer.parseInt(reader.readLine());
					boolean placedChip = Board.placeChip(column, colour);
					if (!placedChip) {
						System.out.println("That move was invalid!");
					}
					else {
						return;
					}
				} catch (NumberFormatException e) {
					GameLoop.stopRunning();
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		thread.start();
	}
	
	public void close() {
		if (thread != null) {
			thread.interrupt();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public char getColour() {
		return colour;
	}
	
}
