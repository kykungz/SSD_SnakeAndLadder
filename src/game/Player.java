package game;

public class Player {
	private String name;
	private Square square;
	private boolean movable;

	public Player(String name, Square square) {
		this.name = name;
		this.square = square;
		this.movable = true;
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

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}
}
