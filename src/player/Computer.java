package player;

import java.util.*;

import components.Board;
import math.Maths;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Computer.
*/
public class Computer extends Player {
	
	/**
	 * Stores the player.
	 */
	private Player player;
	
	/**
	 * Creates a computer.
	 * @param colour the colour.
	 * @param player the player.
	 */
	public Computer(char colour, Player player) {
		super("Computer", colour);
		
		this.player = player;
	}
	
	@Override
	/**
	 * Creates a thread and makes a move.
	 */
	public void requestMove() {
		thread = new Thread(() -> {
			int[] movePriority = new int[7]; // Lower priority value means computer should make the move
			Arrays.fill(movePriority, Integer.MAX_VALUE);
			
			boolean playerHasGoodMove = false;
			// Loop through columns and check next 3 possible position to determine priority
			for (int i = 0; i < 7; i++) {
				for (int j = Board.tops[i]; j < Board.tops[i] + 3 && j <= 6; j++) {
					int longestPossible = Board.longestInARow(j, i, player.colour, false);
					if (longestPossible >= 3) {
						movePriority[i] = 5 - longestPossible + Board.getDistanceToClosestChip(j, i, player.colour);
						
						switch (j - Board.tops[i]) {
							case 0:
								playerHasGoodMove = true;
								break;
							case 1:
								movePriority[i] += longestPossible == 3 ? 100 : 500;
								break;
							case 2:
								movePriority[i] += longestPossible == 3 ? 50 : 250;
								break;
							default:
								break;
						}
						
						break;
					}
				}
			}
			
			if (playerHasGoodMove) { // Stop player from making good move
				List<Integer> bestMoves = new ArrayList<>();
				int bestMovePriority = 4;
				
				for (int i = 0; i < 7; i++) {
					if (movePriority[i] < bestMovePriority) {
						bestMoves.clear();
						bestMoves.add(i);
						bestMovePriority = movePriority[i];
					}
					else if (movePriority[i] == bestMovePriority) {
						bestMoves.add(i);
					}
				}
				
				bestMoves.sort(new Comparator<Integer>() { // If tied, block player by placing closest to centre

					@Override
					public int compare(Integer c1, Integer c2) {
						return Integer.compare(Maths.getDistance(3 - c1, 0), Maths.getDistance(3 - c2, 0));
					}
					
				});
				
				Board.placeChip(bestMoves.get(0), colour);
				return;
			}
			
			// Loops through column to check if the computer has a good move
			for (int i = 0; i < 7; i++) {
				for (int j = Board.tops[i]; j < Board.tops[i] + 3 && j <= 6; j++) {
					int longestPossible = Board.longestInARow(j, i, colour, false);
					if (longestPossible >= 3) {
						if (movePriority[i] == Integer.MAX_VALUE) {
							movePriority[i] = 5 - longestPossible + Board.getDistanceToClosestChip(j, i, colour);
						}
						else {
							movePriority[i] += 5 - longestPossible + Board.getDistanceToClosestChip(j, i, colour);
						}
						
						switch (j - Board.tops[i]) {
							case 0:
								break;
							case 1:
								movePriority[i] += longestPossible == 3 ? 25 : 125;
								break;
							case 2:
								movePriority[i] += longestPossible == 3 ? 50 : 250;
								break;
							default:
								break;
						}
						
						break;
					}
				}
			}
			
			// Get best move(s)
			List<Integer> bestMoves = new ArrayList<>();
			int bestMovePriority = Integer.MAX_VALUE;
			for (int i = 0; i < 7; i++) {
				if (movePriority[i] < bestMovePriority) {
					bestMoves.clear();
					bestMoves.add(i);
					bestMovePriority = movePriority[i];
				}
				else if (movePriority[i] == bestMovePriority) {
					bestMoves.add(i);
				}
			}
			
			bestMoves.sort(new Comparator<Integer>() { // Sort by proximity to the centre column

				@Override
				public int compare(Integer c1, Integer c2) {
					return Integer.compare(Maths.getDistance(3 - c1, 0), Maths.getDistance(3 - c2, 0));
				}
				
			});
			
			if (bestMoves.size() == 1) { // Has one good move
				Board.placeChip(bestMoves.get(0), colour);
			}
			else {
				// Block the player
				
				int bestMove = -1, moveClosestPlayerChip = 7;
				for (int i = 0; i < bestMoves.size(); i++) {
					int c = bestMoves.get(i);
					int closestChip = Board.getDistanceToClosestChip(Board.tops[c], c, player.colour);
					if (closestChip < moveClosestPlayerChip) {
						bestMove = c;
						moveClosestPlayerChip = closestChip;
					}
				}
				Board.placeChip(bestMove, colour);
			}
		});
		
		thread.start();
	}

}
