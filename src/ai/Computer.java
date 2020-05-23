package ai;

public class Computer {
	
	public static final Difficulty EASY = Difficulty.EASY,
			MEDIUM = Difficulty.MEDIUM,
			HARD = Difficulty.HARD;
	
	enum Difficulty {
		EASY(1),
		MEDIUM(3),
		HARD(5);
		
		private final int level;
		
		private Difficulty(int level) {
			this.level = level;
		}
	}
	
	private Difficulty difficulty;

	public Computer(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty.level;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
}
