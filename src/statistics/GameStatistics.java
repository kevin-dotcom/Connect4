package statistics;

import java.util.List;

import event.Move;
import file.FileIO;
import game.GameLoop;
import math.Maths;
import player.Computer;
import player.Player;

public class GameStatistics {
	
	private int numMovesA, numMovesB;
	private double timePerMoveA, timePerMoveB;
	private long totalTimeA, totalTimeB, gameTime;
	private Player playerA, playerB;
	private Player winner;
	
	private static String readValue(String text, String token) {
		int index = text.indexOf(token);
		
		if (index == -1) {
			return null;
		}
		
		int eol = text.indexOf('\n', index);
		
		return text.substring(index + token.length(), eol);
	}
	
	public GameStatistics(Player playerA, Player playerB) {
		this.playerA = playerA;
		this.playerB = playerB;
	}
	
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
	
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public void updateComputerAverageStatistics() {
		String filepath = "Statistics/Computer Statistics.txt";
		
		if (FileIO.fileExists(filepath)) {
			String contents = FileIO.readFile(filepath);
			
			int gamesPlayed = Integer.parseInt(readValue(contents, "Total Games Played: "));
			int gamesWon = Integer.parseInt(readValue(contents, "Total Games Won: "));
			double avgGameTime = Maths.timeToSeconds(readValue(contents, "Average Game Time: "));
			double fastestGameTime = Maths.timeToSeconds(readValue(contents, "Fastest Game Time: "));
			double avgMovesMade = Double.parseDouble(readValue(contents, "Average Moves Made: "));
			int leastMovesMade = Integer.parseInt(readValue(contents, "Least Moves Made: "));
			double avgMoveTime = Maths.timeToSeconds(readValue(contents, "Average Move Time: "));
			
			avgGameTime = Maths.getAverage(gamesPlayed, avgGameTime, 1, gameTime / 1000.0);
			
			if (fastestGameTime > gameTime / 1000.0) {
				fastestGameTime = gameTime / 1000.0;
			}
			
			avgMoveTime = Maths.getAverage(avgMovesMade, avgMoveTime, numMovesA, totalTimeA / 1000.0);
			avgMovesMade = Maths.getAverage(gamesPlayed, avgMovesMade, 1, numMovesA);
			
			if (leastMovesMade > numMovesA) {
				leastMovesMade = numMovesA;
			}
			
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
	
	@Override
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
