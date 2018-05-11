package effect;

import game.Game;
import game.Player;

public class Freeze implements SpecialEffect {

 @Override
 public void perform(Game game) {
  Player player = game.getCurrrentPlayer();
  player.setMovable(!player.isMovable());
 }

}