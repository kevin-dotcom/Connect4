package player;

import java.util.*;

import components.Board;
import math.Maths;

public class Computer extends Player {

	private Player player;
	
	public Computer(char colour, Player player) {
		super("Computer", colour);
		
		this.player = player;
	}

	@Override
	public void requestMove() {
		new Thread(() -> {
			int[] movePriority = new int[7];
			Arrays.fill(movePriority, Integer.MAX_VALUE);
			
			boolean playerHasGoodMove = false;
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
			
			if (playerHasGoodMove) {
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
				
				bestMoves.sort(new Comparator<Integer>() {

					@Override
					public int compare(Integer c1, Integer c2) {
						return Integer.compare(Maths.getDistance(3 - c1, 0), Maths.getDistance(3 - c2, 0));
					}
					
				});
				
				Board.placeChip(bestMoves.get(0), colour);
				return;
			}
			
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
			
			bestMoves.sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer c1, Integer c2) {
					return Integer.compare(Maths.getDistance(3 - c1, 0), Maths.getDistance(3 - c2, 0));
				}
				
			});
			
			if (bestMoves.size() == 1) {
				Board.placeChip(bestMoves.get(0), colour);
			}
			else {
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
		}).start();
	}

}
