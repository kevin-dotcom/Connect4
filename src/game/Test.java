package game;

import ai.Computer;

public class Test {
	
	public static void main(String[] args) {
		Computer c = new Computer(Computer.HARD);
		
		System.out.println(c.getDifficulty());
	}
	
}
