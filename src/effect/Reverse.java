package effect;

import game.Game;
import game.Player;

public class Reverse implements SpecialEffect {

	@Override
	public void perform(Game game) {
		int distance = game.rollDie() * -1;
		Player player = game.getCurrrentPlayer();
		game.move(player, distance);
		
	}

}
