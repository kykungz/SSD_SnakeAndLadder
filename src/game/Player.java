package game;

import square.Square;

public class Player {
	private String name;
	private Square square;

	public Player(String name, Square square) {
		this.name = name;
		this.square = square;
	}

	public String getName() {
		return name;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public Square getSquare() {
		return square;
	}
}
