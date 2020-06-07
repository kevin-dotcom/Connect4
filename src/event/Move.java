package event;

import statistics.Timer;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Stores information about a move.
*/

public class Move {
	
	/**
	 * The time a move was made, in the format 'HH:mm::ss'.
	 */
	private String time;
	
	/**
	 * The position of the move.
	 */
	private int r, c;
	
	/**
	 * The colour of the chip involved.
	 */
	private char colour;
	
	/**
	 * Creates a Move at the given position and colour.
	 * @param r the row.
	 * @param c the column.
	 * @param colour the colour.
	 */
	public Move(int r, int c, char colour) {
		String time = Timer.getCurrentTime("HH:mm:ss");
		
		this.time = time;
		this.r = r;
		this.c = c;
		this.colour = colour;
	}
	
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return r;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return c;
	}

	/**
	 * @return the colour
	 */
	public char getColour() {
		return colour;
	}

	@Override
	public String toString() {
		return '[' + time + "]: " + (colour == 'r' ? "Red" : "Yellow") + " " + r + " " + c;
	}
	
}
