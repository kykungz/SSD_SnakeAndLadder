package effect;

import game.Game;
import game.Player;
import square.Square;

public abstract class Portal implements SpecialEffect {
	private Square target;

	public Portal(Square target) {
		this.target = target;
	}

	@Override
	public void perform(Game game) {
		Player currentPlayer = game.getCurrrentPlayer();
		game.move(currentPlayer, target);
	}

}
