package player;

import game.GameLoop;

public class Computer extends Player implements Runnable {

	private Thread thread = new Thread(this);
	private boolean makeMove;

	public Computer(char colour) {
		super("Computer", colour);
		thread.start();
	}

	@Override
	public void requestMove() {
		makeMove = true;
	}

	@Override
	public void run() {
		while (GameLoop.isRunning()) {
			if (makeMove) {
				System.out.println("Computer move");
				GameLoop.setMove('y' + " 0 0");
				makeMove = false;
			}
		}
	}

}
