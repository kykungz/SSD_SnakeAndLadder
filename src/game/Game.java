package game;

import java.util.ArrayList;
import java.util.List;

import effect.Freeze;
import effect.Ladder;
import effect.Reverse;
import effect.Snake;
import effect.Winning;

public class Game {
	private Board board;
	private List<Player> players;
	private Die die;
	private int currentPlayerIndex;
	private boolean playing;
	private List<Move> moves;

	public Game(int player) {
		this.moves = new ArrayList<>();
		this.die = new Die();
		this.board = new Board();
		this.players = new ArrayList<Player>();
		this.playing = true;
		for (int i = 0; i < player; i++) {
			players.add(new Player("Player " + (i + 1), board.getSquare(1)));
		}

		this.currentPlayerIndex = 0;

		this.board.addEffect(2, new Ladder(board.getSquare(38)));
		this.board.addEffect(7, new Ladder(board.getSquare(14)));
		this.board.addEffect(8, new Ladder(board.getSquare(31)));
		this.board.addEffect(15, new Ladder(board.getSquare(26)));
		this.board.addEffect(21, new Ladder(board.getSquare(42)));
		this.board.addEffect(28, new Ladder(board.getSquare(84)));
		this.board.addEffect(36, new Ladder(board.getSquare(44)));
		this.board.addEffect(51, new Ladder(board.getSquare(67)));
		this.board.addEffect(71, new Ladder(board.getSquare(91)));
		this.board.addEffect(78, new Ladder(board.getSquare(98)));
		this.board.addEffect(87, new Ladder(board.getSquare(94)));

		this.board.addEffect(16, new Snake(board.getSquare(6)));
		this.board.addEffect(46, new Snake(board.getSquare(25)));
		this.board.addEffect(49, new Snake(board.getSquare(11)));
		this.board.addEffect(62, new Snake(board.getSquare(19)));
		this.board.addEffect(64, new Snake(board.getSquare(60)));
		this.board.addEffect(74, new Snake(board.getSquare(53)));
		this.board.addEffect(89, new Snake(board.getSquare(68)));
		this.board.addEffect(92, new Snake(board.getSquare(88)));
		this.board.addEffect(95, new Snake(board.getSquare(75)));
		this.board.addEffect(99, new Snake(board.getSquare(80)));

		this.board.addEffect(70, new Freeze());
		this.board.addEffect(56, new Freeze());
		this.board.addEffect(6, new Freeze());

		this.board.addEffect(44, new Reverse());
		this.board.addEffect(84, new Reverse());

		this.board.addEffect(100, new Winning());
	}

	public void reset() {
		this.playing = true;
		for (Player p : players) {
			p.setMovable(true);
			p.setSquare(board.getSquare(1));
		}
	}

	public boolean isPlaying() {
		return playing;
	}

	public void end() {
		this.playing = false;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int rollDie() {
		return die.roll();
	}

	public Player getCurrrentPlayer() {
		return this.players.get(currentPlayerIndex);
	}

	public void next() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}

	public List<Move> getReplay() {
		return moves;
	}

	public void move(Player player, int distance) {
		Move m = new Move() {
			@Override
			public void execute(Game game) {
				int number = player.getSquare().getNumber();
				if (player.isMovable()) {
					player.setSquare(board.getSquare(number + distance));
				}
			}
		};
		m.execute(this);
		moves.add(m);
	}

	public void move(Player player, Square square) {
		Move m = new Move() {
			@Override
			public void execute(Game game) {
				if (player.isMovable()) {
					player.setSquare(square);
				}
			}
		};
		m.execute(this);
		moves.add(m);
	}
}
