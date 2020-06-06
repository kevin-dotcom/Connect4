package player;

import components.Board;

public class Computer extends Player {

	public Computer(char colour) {
		super("Computer", colour);
	}

	@Override
	public void requestMove() {
		new Thread(() -> {
			boolean placedChip = Board.placeChip(colour, 0);
			if (!placedChip) {
				System.out.println("That move was invalid!");
				return;
			}
		}).start();
	}

}
