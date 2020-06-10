package game;

import components.Board;
import event.Move;
import event.MovesList;
import file.FileIO;
import gui.TextureLibrary;
import gui.Window;
import player.Computer;
import player.Player;
import statistics.GameStatistics;
import statistics.Timer;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Game loop.
 */
public class GameLoop {

	/**
	 * Stores previous move.
	 */
	private static Move move;

	/**
	 * Stores if the program is running.
	 */
	private static boolean isRunning;

	/**
	 * Stores if it is the first player's turn.
	 */
	private static boolean isPlayer1Turn = true;

	/**
	 * Stores if the game already requested a move.
	 */
	private static boolean isWaitingForMove = false;

	/**
	 * An array of the players.
	 */
	private static Player[] players = new Player[2];

	/**
	 * Game statistics.
	 */
	private static GameStatistics gs;

	/**
	 * A list of the moves made.
	 */
	private static MovesList moves;

	/**
	 * Writes the statistics to a file.
	 */
	public static void writeStatistics() {
		String date = Timer.getCurrentTime("yyyy_MM_dd HH_mm_ss");

		String filename = "Statistics/Game Statistics/Game Statistics " + date + ".txt";

		FileIO.beginWriting(filename);
		FileIO.print(gs);
		FileIO.endWriting();

		System.out.println("Written game statistics to " + filename);
	}

	/**
	 * @return the moves list.
	 */
	public static MovesList getMovesList() {
		return moves;
	}

	/**
	 * @param move the move.
	 */
	public static void setMove(Move move) {
		GameLoop.move = move;
	}
	
	/**
	 * Sets the player.
	 * @param red the red player.
	 * @param yellow the yellow player.
	 */
	public static void setPlayers(Player red, Player yellow) {
		players[0] = red;
		players[1] = yellow;
	}

	/**
	 * @return {@code true} if the game is running.
	 */
	public static boolean isRunning() {
		return isRunning;
	}

	/**
	 * Stops the game.
	 */
	public static void stopRunning() {
		isRunning = false;
	}
	
	/**
	 * Called after player choses to play against a computer or another player.
	 */
	public static void run() {
		new Thread(() -> {
			// Create timers

			Timer.createTimer("Game");
			Timer.createTimer(players[0].getName());
			Timer.createTimer(players[1].getName());

			isRunning = true;

			// Initialize

			gs = new GameStatistics(players[0], players[1]);
			moves = new MovesList();

			// Start timing
			Timer.startTiming();
			Timer.startTimer(players[0].getName());
			Timer.startTimer("Game");

			while (isRunning) {
				if (!isWaitingForMove) { // Check if game should request move from player
					players[isPlayer1Turn ? 0 : 1].requestMove();
					isWaitingForMove = true;
				}
				if (move != null) { // Check if a move has been made
					if (Board.connect4(move.getRow(), move.getColumn(), move.getColour())) { // Check for winner
						gs.setWinner(move.getColour() == 'r' ? players[0] : players[1]);
						isRunning = false;
					}
					else if (!Board.canMove()) { // Check if tied
						isRunning = false;
					}

					// Swap timers

					else if (isPlayer1Turn) {
						Timer.stopTimer(players[0].getName());
						Timer.startTimer(players[1].getName());;
					}
					else {
						Timer.stopTimer(players[1].getName());
						Timer.startTimer(players[0].getName());
					}

					moves.add(move); // Add to move list

					// Swap turns

					isPlayer1Turn = !isPlayer1Turn;
					isWaitingForMove = false;
					move = null;

					Board.logGrid();
					Window.update();
				}

				// Update the timers.

				Timer.updateTimers();
			}
			
			Window.disableInput();
			Window.showVictoryMessage(gs.getWinner());
			
			// Stop the timers

			Timer.stopTiming();

			// Send time to the statistics

			gs.setTime(Timer.getTimer(players[0].getName()), Timer.getTimer(players[1].getName()), Timer.getTimer("Game"));

			// Stores statistics against computer

			if (players[1] instanceof Computer) {
				gs.updateComputerAverageStatistics();
			}

			// Stop player threads if active

			for (Player player : players) {
				player.close();
			}
		}).start();
	}

	public static void main(String[] args) {
		// TODO: Create Window Class

		TextureLibrary.loadTextures();
		Window.init();
	}

}
