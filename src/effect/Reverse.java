package effect;

import game.Game;

public class Reverse implements SpecialEffect {

	@Override
	public void perform(Game game) {
		int distance = game.rollDie();
	}

}
