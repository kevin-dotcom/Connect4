package game;

public class Board {
	
	public static int[][] board = new int[6][7];
	
	public static int[][] getBoard() {
		return board;
	}
	
	public static int getLongestInARow(int team) {
		int longest = 0;
		
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				if (board[r][c] == team) {
					longest = Math.max(longest, getNumberInARow(team, r, c, -1, 1));
					longest = Math.max(longest, getNumberInARow(team, r, c, 0, 1));
					longest = Math.max(longest, getNumberInARow(team, r, c, 1, 0));
					longest = Math.max(longest, getNumberInARow(team, r, c, 1, 1));
					
					if (longest == 4) {
						return longest;
					}
				}
			}
		}
		
		return longest;
	}
	
	public static int getNumberInARow(int team, int r, int c, int dr, int dc) {
		if (r == -1 || r == 6 || c == -1 || c == 7) {
			return 0;
		}
		else if (board[r][c] != team) {
			return 0;
		}
		else {
			return getNumberInARow(team, r + dr, c + dc, dr, dc) + 1;
		}
	}
	
}
