package event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Move {
	
	private String time;
	private int r, c;
	private char colour;
	
	public Move(int r, int c, char colour) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String time = dtf.format(now);
		
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
		return '[' + time + "]: " + colour + " " + r + " " + c;
	}
	
}
