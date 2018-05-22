package game;

public abstract class Move {

	private Player player;
	private int distance;

	public Move(Player player, int distance) {
		this.player = player;
		this.distance = distance;
	}

	public abstract void execute(Game game);
}
