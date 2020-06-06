package event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovesList {
	
	private List<Move> list = new ArrayList<>();
	
	public void add(Move move) {
		list.add(move);
	}
	
	public List<Move> getList() {
		return new ArrayList<>(list);
	}
	
	public List<Move> getList(char colour) {
		return list.stream().filter(move -> move.getColour() == colour).collect(Collectors.toList());
	}
	
}
