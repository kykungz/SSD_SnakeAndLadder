package ui;

import java.util.Scanner;

import game.Game;
import game.Player;

public class ConsoleUI {
	private Game game;

	public ConsoleUI(Game game) {
		this.game = game;
	}

	public void play() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Press Enter to roll dice");
			sc.nextLine();
			int distance = game.rollDie();
			System.out.println("Rolled " + distance);
			Player player = game.getCurrrentPlayer();
			System.out.printf("%s moved from %s ", player.getName(), player.getSquare().getNumber());
			game.move(player, distance);
			System.out.println("to " + player.getSquare().getNumber());
			// Activate Effect
			player.getSquare().performEffects(game);
			game.next();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		ConsoleUI console = new ConsoleUI(game);
		console.play();
	}
}
