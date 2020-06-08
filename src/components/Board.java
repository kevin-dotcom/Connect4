package components;

import java.util.Arrays;
import java.util.List;

import event.Move;
import game.GameLoop;
import math.Maths;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Game Board class containing a 7x6 grid.
*/

public class Board {
	
	/**
	 * Direction constants.
	 * @author Kevin Zhang
	 *
	 */
	public static enum Direction {
		
		UP_LEFT(-1, 1),
		UP(0, 1),
		UP_RIGHT(1, 1),
		LEFT(-1, 0),
		RIGHT(1, 0),
		DOWN_LEFT(-1, -1),
		DOWN(0, -1),
		DOWN_RIGHT(1, -1);
		
		final int dx, dy;
		
		Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
		
	}
	
	/**
	 * Grid containing the position of the chips.
	 * Listed in reverse-order vertically (row 0 is the bottom of the grid).
	 */
	public static char[][] grid = new char[6][7];
	
	/**
	 * Array containing the highest position of a chip in each column of the grid.
	 */
	public static int[] tops = new int[7];
	
	/**
	 * Places a chip in a set column if possible and updates the Game Loop.
	 * @param column the column.
	 * @param colour the colour of the chip.
	 * @return {@code true} if a chip was placed.
	 */
	public static boolean placeChip(int column, char colour) {
		if (!Maths.inRange(column, 0, 6) || !Maths.inRange(tops[column], 0, 5)) {
			return false;
		}
		
		Board.grid[Board.tops[column]][column] = colour;
		GameLoop.setMove(new Move(Board.tops[column], column, colour));
		Board.tops[column]++;
		
		return true;
	}
	
	/**
	 * Checks if there is a 4-in-a-row or more at a position.
	 * @param r the row.
	 * @param c the column.
	 * @param colour the colour to check.
	 * @return {@code true} if there is a 4-in-a-row.
	 */
	public static boolean connect4(int r, int c, char colour) {
		return longestInARow(r, c, colour, true) >= 4;
	}
	
	/**
	 * Checks if the board is full.
	 * @return {@code true} if at least 1 column is open.
	 */
	public static boolean canMove() {
		return Arrays.stream(tops).filter(top -> top < 6).count() > 0;
	}
	
	/**
	 * Checks the longest in a row at a position.
	 * 
	 * There is an option to require or ignore the colour of the given cell.
	 * @param r the row.
	 * @param c the column.
	 * @param colour the colour.
	 * @param requireStartingCellColour if the first cell needs to contain the chip with the given colour.
	 * @return the length of the longest, continuous line of the given colour at the position.
	 */
	public static int longestInARow(int r, int c, char colour, boolean requireStartingCellColour) {
		int longest = 0;
		
		// Check longest horizontally, vertically and diagonally.
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.UP_LEFT, Direction.DOWN_RIGHT));
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.UP, Direction.DOWN));
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.UP_RIGHT, Direction.DOWN_LEFT));
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.LEFT, Direction.RIGHT));
		
		return longest;
	}
	
	/**
	 * Checks the longest in a row at a position in the given directions.
	 * 
	 * There is an option to require or ignore the colour of the given cell.
	 * 
	 * Note: the lengths in all given directions will be summed up.
	 * @param r the row.
	 * @param c the column.
	 * @param colour the colour.
	 * @param requireStartingCellColour if the first cell needs to contain the chip with the given colour.
	 * @param directions the directions to consider.
	 * @return the length of the longest, continuous line of the given colour at the position.
	 */
	public static int longestInARow(int r, int c, char colour, boolean requireStartingCellColour, Direction... directions) {
		if (!Maths.inRange(r, 0, 5) || !Maths.inRange(c, 0, 6)) { // Check if in the grid
			return 0;
		}
		else if (requireStartingCellColour && grid[r][c] != colour) { // If colour is required, make sure current cell has the right colour
			return 0;
		}
		else { // Sum the lengths in the given directions
			int length = 0;
			
			for (Direction dir : directions) {
				length += longestInARow(r + dir.dx, c + dir.dy, colour, true, dir);
			}
			
			return length + 1;
		}
	}
	
	/**
	 * Gets the distance to the closest chip of a given colour.
	 * @param r the row.
	 * @param c the column.
	 * @param colour the colour of the chip.
	 * @return the distance, or -1 if none.
	 */
	public static int getDistanceToClosestChip(int r, int c, char colour) {
		List<Move> moves = GameLoop.getMovesList().getList(colour); // Gets position of all chips with the given colour
		
		if (moves.size() == 0) { // Checks if there is a chip of the given colour
			return -1;
		}
		else {
			int closestChipDistance = Integer.MAX_VALUE;
			
			for (Move move : moves) { // Loop through positions
				int distance = Maths.getDistance(c, r, move.getColumn(), move.getRow());
				closestChipDistance = Math.min(closestChipDistance, distance);
			}
			
			return closestChipDistance;
		}
	}
	
	/**
	 * Prints the grid to the console.
	 */
	public static void logGrid() {
		System.out.println("Current Grid:");
		
		for (int i = 5; i >= 0; i--) {
			for (int j = 0; j < 7; j++) {
				if (j < 6) {
					System.out.print(grid[i][j] + ", ");
				}
				else {
					System.out.println(grid[i][j]);
				}
			}
		}
	}
	
}
