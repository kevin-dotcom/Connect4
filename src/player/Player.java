package player;

import gui.Window;

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
		Window.enableColourButtons(colour);
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
