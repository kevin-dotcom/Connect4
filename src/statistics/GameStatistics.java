package statistics;

import java.util.List;

import event.Move;
import file.FileIO;
import game.GameLoop;
import math.Maths;
import player.Computer;
import player.Player;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Game Statistics.
*/
public class GameStatistics {
	
	/**
	 * Number of moves.
	 */
	private int numMovesA, numMovesB;
	
	/**
	 * Time per move.
	 */
	private double timePerMoveA, timePerMoveB;
	
	/**
	 * Time.
	 */
	private long totalTimeA, totalTimeB, gameTime;
	
	/**
	 * Player.
	 */
	private Player playerA, playerB;
	
	/**
	 * The winner, if any.
	 */
	private Player winner;
	
	/**
	 * Reads the value from the contents of a file.
	 * @param text the contents.
	 * @param token the token to search for.
	 * @return
	 */
	private static String readValue(String text, String token) {
		int index = text.indexOf(token);
		
		if (index == -1) {
			return null;
		}
		
		int eol = text.indexOf('\n', index);
		
		return text.substring(index + token.length(), eol);
	}
	
	/**
	 * Creates instance with the players.
	 * @param playerA the first player.
	 * @param playerB the second player.
	 */
	public GameStatistics(Player playerA, Player playerB) {
		this.playerA = playerA;
		this.playerB = playerB;
	}
	
	/**
	 * Gets information about time.
	 * @param timerA the timer for the first player.
	 * @param timerB the timer for the second player.
	 * @param totalTime the total time.
	 */
	public void setTime(Timer timerA, Timer timerB, Timer totalTime) {
		List<Long> moveTimesA = timerA.getMoveTimes();
		List<Long> moveTimesB = timerB.getMoveTimes();
		
		numMovesA = moveTimesA.size();
		numMovesB = moveTimesB.size();
		
		totalTimeA = timerA.getTime();
		totalTimeB = timerB.getTime();
		gameTime = totalTime.getTime();
		
		timePerMoveA = (double)totalTimeA / (double)numMovesA;
		timePerMoveB = (double)totalTimeB / (double)numMovesB;
	}
	
	/**
	 * Sets the winner.
	 * @param winner the winner.
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	/**
	 * Change statistics against computers.
	 */
	public void updateComputerAverageStatistics() {
		String filepath = "Statistics/Computer Statistics.txt";
		
		if (FileIO.fileExists(filepath)) { // Existing data
			String contents = FileIO.readFile(filepath); // Read file
			
			// Get data
			
			int gamesPlayed = Integer.parseInt(readValue(contents, "Total Games Played: "));
			int gamesWon = Integer.parseInt(readValue(contents, "Total Games Won: "));
			double avgGameTime = Maths.timeToSeconds(readValue(contents, "Average Game Time: "));
			double fastestGameTime = Maths.timeToSeconds(readValue(contents, "Fastest Game Time: "));
			double avgMovesMade = Double.parseDouble(readValue(contents, "Average Moves Made: "));
			int leastMovesMade = Integer.parseInt(readValue(contents, "Least Moves Made: "));
			double avgMoveTime = Maths.timeToSeconds(readValue(contents, "Average Move Time: "));
			
			// Update data
			
			avgGameTime = Maths.getAverage(gamesPlayed, avgGameTime, 1, gameTime / 1000.0);
			
			if (fastestGameTime > gameTime / 1000.0) {
				fastestGameTime = gameTime / 1000.0;
			}
			
			avgMoveTime = Maths.getAverage(avgMovesMade, avgMoveTime, numMovesA, totalTimeA / 1000.0);
			avgMovesMade = Maths.getAverage(gamesPlayed, avgMovesMade, 1, numMovesA);
			
			if (leastMovesMade > numMovesA) {
				leastMovesMade = numMovesA;
			}
			
			// Write data
			
			FileIO.beginWriting(filepath);
			
			FileIO.printf("Total Games Played: %d%n", ++gamesPlayed);
			FileIO.printf("Total Games Won: %d%n", winner != null && !(winner instanceof Computer) ? ++gamesWon : gamesWon);
			FileIO.printf("W/L Ratio: %.2f%n", (double)gamesWon / gamesPlayed);
			FileIO.printf("Average Game Time: %s%n", Maths.secondsToCombination(avgGameTime));
			FileIO.printf("Fastest Game Time: %s%n", Maths.secondsToCombination(fastestGameTime));
			FileIO.printf("Average Moves Made: %.2f%n", avgMovesMade);
			FileIO.printf("Least Moves Made: %d%n", leastMovesMade);
			FileIO.printf("Average Move Time: %s%n", Maths.secondsToCombination(avgMoveTime));
			
			FileIO.endWriting();
		}
		else {
			// Write data
			
			FileIO.beginWriting(filepath);
			
			FileIO.println("Total Games Played: 1");
			FileIO.printf("Total Games Won: %d%n", winner != null && !(winner instanceof Computer) ? 1 : 0);
			FileIO.printf("W/L Ratio: %.2f%n", winner != null && !(winner instanceof Computer) ? 1.0 : 0.0);
			FileIO.printf("Average Game Time: %s%n", Maths.millisecondsToCombination(gameTime));
			FileIO.printf("Fastest Game Time: %s%n", Maths.millisecondsToCombination(gameTime));
			FileIO.printf("Average Moves Made: %.2f%n", (double)numMovesA);
			FileIO.printf("Least Moves Made: %d%n", numMovesA);
			FileIO.printf("Average Move Time: %s%n", Maths.millisecondsToCombination(timePerMoveA));
			
			FileIO.endWriting();
		}
	}
	
	public Player getWinner() {
		return winner;
	}
	
	@Override
	/**
	 * Converts it into a string.s
	 */
	public String toString() {
		String output = String.format("Game Length: %.2fs%n", (double)gameTime / 1000.0);
		output += playerA.getName() + " (" + (playerA.getColour() == 'r' ? "red" : "yellow") + ")\n";
		output += "----------\n";
		output += "Number of Moves: " + numMovesA + '\n';
		output += String.format("Average Time per Move: %.2fs%n", (double)timePerMoveA / 1000.0);
		output += String.format("Total Time: %.2fs%n", (double)totalTimeA / 1000.0);
		output += "----------\n";
		output += playerB.getName() + " (" + (playerB.getColour() == 'r' ? "red" : "yellow") + ")\n";
		output += "----------\n";
		output += "Number of Moves: " + numMovesB + '\n';
		output += String.format("Average Time per Move: %.2fs%n", (double)timePerMoveB / 1000.0);
		output += String.format("Total Time: %.2fs%n", (double)totalTimeB / 1000.0);
		
		if (winner != null) {
			output += "----------\n";
			output += "Winner: " + winner.getName() + '\n';
		}
		
		output += "----------\n";
		
		for (Move move : GameLoop.getMovesList().getList()) {
			output += move.toString() + '\n';
		}
		
		return output;
	}
	
}
