package effect;

import square.Square;

public class Snake extends Portal {

	public Snake(Square target) {
		super(target);
	}

	@Override
	public String toString() {
		return "Snake";
	}

}
