package components;

import game.GameLoop;

public class Board {
	
	public static char[][] grid = new char[6][7];
	public static int[] tops = new int[7];
	
	public static boolean placeChip(char colour, int column) {
		if (tops[column] == 6) {
			return false;
		}
		
		Board.grid[Board.tops[column]][column] = colour;
		GameLoop.setMove(colour + " " + column + " " + Board.tops[column]);
		Board.tops[column]++;
		
		return true;
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
