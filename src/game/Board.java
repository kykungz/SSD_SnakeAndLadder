package game;

import effect.SpecialEffect;
import square.Square;

public class Board {
	private Square[] squares;

	public Board() {
		squares = new Square[100];
		for (int i = 0; i < 100; i++) {
			squares[i] = new Square(i + 1);
		}
	}
	
	public void addEffect(int number, SpecialEffect effect) {
		squares[number - 1].addEffect(effect);
	}

	public Square getSquare(int number) {
		return squares[number];
	}
}
