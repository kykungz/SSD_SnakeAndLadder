package effect;

import game.Square;

public class Ladder extends Portal {

	public Ladder(Square target) {
		super(target);
	}

	@Override
	public String toString() {
		return "Ladder";
	}
}
