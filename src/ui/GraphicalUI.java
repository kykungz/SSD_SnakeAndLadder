package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Game;
import game.Move;
import game.Player;

public class GraphicalUI extends JFrame {

	private Game game;
	private JPanel board;

	public GraphicalUI(Game game) {
		super("Snake and Ladder");
		this.game = game;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		initComponents();
		this.pack();
		board.repaint();
	}

	private void initComponents() {
		JButton rollButton = new JButton(game.getCurrrentPlayer().getName() + "'s Turn. [ROLL]");
		rollButton.setPreferredSize(new Dimension(0, 50));
		rollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(() -> {
					rollButton.setEnabled(false);
					int direction = 1;
					int distance = game.rollDie();
					System.out.println("Rolled " + distance);
					Player player = game.getCurrrentPlayer();
					System.out.printf("%s moved from %s ", player.getName(), player.getSquare().getNumber());
					for (int i = 0; i < distance; i++) {
						try {
							game.move(player, direction);
							if (player.getSquare().getNumber() >= 100) {
								direction = -1;
							}
							board.repaint();
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					System.out.println("to " + player.getSquare().getNumber());

					// Activate Effect
					player.getSquare().performEffects(game);

					board.repaint();
					if (!game.isPlaying()) {
						Object[] options = { "Watch Replay", "Play Again" };
						int answer = JOptionPane.showOptionDialog(getContentPane(),
								"Game Over. Winner is " + player.getName(), "Game Over", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						game.reset();
						if (answer == JOptionPane.YES_OPTION) {
							System.out.println("Watch Replay");
							for (Move m : game.getReplay()) {
								m.execute(game);
								try {
									Thread.sleep(100);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								board.repaint();
							}
						}
					} else {
						game.next();
					}
					rollButton.setEnabled(true);
					rollButton.setText(game.getCurrrentPlayer().getName() + "'s Turn. [ROLL]");
					board.repaint();
				}).start();
			}
		});
		board = new BoardPanel();
		this.setLayout(new BorderLayout());
		this.add(board, BorderLayout.CENTER);
		this.add(rollButton, BorderLayout.SOUTH);
	}

	private class BoardPanel extends JPanel {
		private Image background;
		private Image knight;
		private static final int SIZE = 700;

		public BoardPanel() {
			super();
			this.setPreferredSize(new Dimension(SIZE, SIZE));
			try {
				background = ImageIO.read(GraphicalUI.class.getResourceAsStream("/assets/board.png"));
				knight = ImageIO.read(GraphicalUI.class.getResourceAsStream("/assets/knight2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			final int BLOCK_SIZE = SIZE / 10;
			g.drawImage(background, 0, 0, SIZE, SIZE, null);
			for (Player player : game.getPlayers()) {
				int index = player.getSquare().getNumber() - 1;
				int x;
				if ((index / 10) % 2 == 0) {
					x = BLOCK_SIZE * (index % 10);
				}  else if (index == 19 || index == 39 || index == 59 || index == 79 || index == 99) {
					x = 0;
				}else {
					x = SIZE - (BLOCK_SIZE * ((index + 1) % 10));
				}
				g.drawImage(knight, x, SIZE - (BLOCK_SIZE * ((index / 10) + 1)), BLOCK_SIZE - 10, BLOCK_SIZE - 10,
						null);
				g.drawString(player.getName(), x + 10, SIZE - (BLOCK_SIZE * ((index / 10) + 1)));
			}
		}
	}

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Snake and Ladder");
		JButton one = new JButton("1 Player");
		JButton two = new JButton("2 Players");
		JButton three = new JButton("3 Players");
		JButton four = new JButton("4 Players");
		
		one.addActionListener(e -> {
			Game game = new Game(1);
			GraphicalUI ui = new GraphicalUI(game);
			ui.setVisible(true);
			frame.dispose();
		});
		two.addActionListener(e -> {
			Game game = new Game(2);
			GraphicalUI ui = new GraphicalUI(game);
			ui.setVisible(true);
			frame.dispose();
		});
		three.addActionListener(e -> {
			Game game = new Game(3);
			GraphicalUI ui = new GraphicalUI(game);
			ui.setVisible(true);
			frame.dispose();
		});
		four.addActionListener(e -> {
			Game game = new Game(4);
			GraphicalUI ui = new GraphicalUI(game);
			ui.setVisible(true);
			frame.dispose();
		});
		
		frame.setLayout(new FlowLayout());
		frame.add(one);
		frame.add(two);
		frame.add(three);
		frame.add(four);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}