package square;

import java.util.ArrayList;
import java.util.List;

import effect.SpecialEffect;
import game.Game;

public class Square {
	private List<SpecialEffect> effects;
	private int number;

	public Square(int number) {
		this.number = number;
		this.effects = new ArrayList<>();
	}

	public void addEffect(SpecialEffect effect) {
		this.effects.add(effect);
	}

	public void performEffects(Game game) {
		for (SpecialEffect effect : effects) {
			effect.perform(game);
		}
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return number + "";
	}

}
