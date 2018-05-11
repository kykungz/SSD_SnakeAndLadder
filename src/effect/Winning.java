package effect;

import game.Game;

public class Winning implements SpecialEffect {

 @Override
 public void perform(Game game) {
  game.end();
 }

}