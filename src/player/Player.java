package player;

import java.io.*;

import components.Board;
import game.GameLoop;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Player class.
*/
public class Player {
	
	/**
	 * The name.
	 */
	private String name;
	
	/**
	 * The colour.
	 */
	protected char colour;
	
	/**
	 * Thread for making a move.
	 */
	protected Thread thread;
	
	// Temporary: Will only remain until Window class is created
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * Creates a player.
	 * @param name the name.
	 * @param colour the colour.
	 */
	public Player(String name, char colour) {
		this.name = name;
		this.colour = colour;
	}
	
	/**
	 * Creates a thread to make a move.
	 */
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
	
	/**
	 * Stop the threads.
	 */
	public void close() {
		if (thread != null) {
			thread.interrupt();
		}
	}
	
	/**
	 * @return the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the colour.
	 */
	public char getColour() {
		return colour;
	}
	
}
