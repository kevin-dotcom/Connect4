package event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Name: Kevin Zhang
 * Teacher: Mr. Anandarajan
 * Date: 06-07-2020
 * Description: Stores the moves.
*/

public class MovesList {
	
	/**
	 * A list of the moves made.
	 */
	private List<Move> list = new ArrayList<>();
	
	/**
	 * Adds a move to the list.
	 * @param move the move.
	 */
	public void add(Move move) {
		list.add(move);
	}
	
	/**
	 * Gets the list.
	 * @return the list.
	 */
	public List<Move> getList() {
		return new ArrayList<>(list);
	}
	
	/**
	 * Gets a list containing only moves made with chips of the given colour.
	 * @param colour the colour.
	 * @return the list.
	 */
	public List<Move> getList(char colour) {
		return list.stream().filter(move -> move.getColour() == colour).collect(Collectors.toList());
	}
	
}
