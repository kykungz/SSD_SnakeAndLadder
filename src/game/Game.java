package game;

import effect.Ladder;
import effect.Snake;
import square.Square;

public class Game {
	private Board board;
	private Player[] players;
	private Die die;
	private int currentPlayerIndex;

	public Game() {
		this.reset();
	}

	public void reset() {
		this.die = new Die();
		this.board = new Board();
		this.players = new Player[2];
		players[0] = new Player("Jacky", board.getSquare(0));
//		players[1] = new Player("Jitti", board.getSquare(19));
		players[1] = new Player("James", board.getSquare(0));
		this.currentPlayerIndex = 0;

		this.board.addEffect(2, new Ladder(board.getSquare(38-1)));
		this.board.addEffect(7, new Ladder(board.getSquare(14-1)));
		this.board.addEffect(8, new Ladder(board.getSquare(31-1)));
		this.board.addEffect(15, new Ladder(board.getSquare(26-1)));
		this.board.addEffect(21, new Ladder(board.getSquare(42-1)));
		this.board.addEffect(28, new Ladder(board.getSquare(84-1)));
		this.board.addEffect(36, new Ladder(board.getSquare(44-1)));
		this.board.addEffect(51, new Ladder(board.getSquare(67-1)));
		this.board.addEffect(71, new Ladder(board.getSquare(91-1)));
		this.board.addEffect(78, new Ladder(board.getSquare(98-1)));
		this.board.addEffect(87, new Ladder(board.getSquare(94-1)));

		this.board.addEffect(16, new Snake(board.getSquare(6-1)));
		this.board.addEffect(46, new Snake(board.getSquare(25-1)));
		this.board.addEffect(49, new Snake(board.getSquare(11-1)));
		this.board.addEffect(62, new Snake(board.getSquare(19-1)));
		this.board.addEffect(64, new Snake(board.getSquare(60-1)));
		this.board.addEffect(74, new Snake(board.getSquare(53-1)));
		this.board.addEffect(89, new Snake(board.getSquare(68-1)));
		this.board.addEffect(92, new Snake(board.getSquare(88-1)));
		this.board.addEffect(95, new Snake(board.getSquare(75-1)));
		this.board.addEffect(99, new Snake(board.getSquare(80-1)));

	}

	public Player[] getPlayers() {
		return players;
	}

	public int rollDie() {
		return die.roll();
	}

	public Player getCurrrentPlayer() {
		return this.players[currentPlayerIndex];
	}

	public void next() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
	}

	public void move(Player player, int distance) {
		int index = player.getSquare().getNumber() - 1;
		player.setSquare(board.getSquare(index + distance));
	}

	public void move(Player player, Square square) {
		player.setSquare(square);
	}
}
