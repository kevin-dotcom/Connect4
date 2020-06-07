package components;

import java.util.Arrays;
import java.util.List;

import event.Move;
import game.GameLoop;
import math.Maths;

public class Board {
	
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
	
	public static char[][] grid = new char[6][7];
	public static int[] tops = new int[7];
	
	public static boolean placeChip(int column, char colour) {
		if (tops[column] == 6) {
			return false;
		}
		
		Board.grid[Board.tops[column]][column] = colour;
		GameLoop.setMove(new Move(Board.tops[column], column, colour));
		Board.tops[column]++;
		
		return true;
	}
	
	public static boolean connect4(int r, int c, char colour) {
		return longestInARow(r, c, colour, true) >= 4;
	}
	
	public static boolean canMove() {
		return Arrays.stream(tops).filter(top -> top < 6).count() > 0;
	}
	
	public static int longestInARow(int r, int c, char colour, boolean requireStartingCellColour) {
		int longest = 0;
		
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.UP_LEFT, Direction.DOWN_RIGHT));
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.UP, Direction.DOWN));
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.UP_RIGHT, Direction.DOWN_LEFT));
		longest = Math.max(longest, longestInARow(r, c, colour, requireStartingCellColour, Direction.LEFT, Direction.RIGHT));
		
		return longest;
	}
	
	public static int longestInARow(int r, int c, char colour, boolean requireCurrentCellColour, Direction... directions) {
		if (!Maths.inRange(r, 0, 5) || !Maths.inRange(c, 0, 6)) {
			return 0;
		}
		else if (requireCurrentCellColour && grid[r][c] != colour) {
			return 0;
		}
		else {
			int length = 0;
			
			for (Direction dir : directions) {
				length += longestInARow(r + dir.dx, c + dir.dy, colour, true, dir);
			}
			
			return length + 1;
		}
	}
	
	public static int getDistanceToClosestChip(int r, int c, char colour) {
		List<Move> moves = GameLoop.getMovesList().getList(colour);
		
		if (moves.size() == 0) {
			return -1;
		}
		else {
			int closestChipDistance = Integer.MAX_VALUE;
			
			for (Move move : moves) {
				int distance = Maths.getDistance(c, r, move.getColumn(), move.getRow());
				closestChipDistance = Math.min(closestChipDistance, distance);
			}
			
			return closestChipDistance;
		}
	}
	
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
