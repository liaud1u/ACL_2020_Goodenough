package views.menus;

import model.PacmanGame;

/**
 * @author Ribeyrolles Matthieu
 * 14/11/2020, 16:00
 */
public class LostLevelView extends EndLevelView{
  private boolean lostCauseOfTimer;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  @Override
  protected void init() {
    super.init();

    this.text.setText(String.format(
      "Level failed!\n%s\nYou reached the level %d, with a score of %d!\n\nReady to start again?",
      (this.lostCauseOfTimer) ? "You ran out of time :(" : "",
      this.level,
      this.score
    ));

    this.button.setText("Try again");
  }

  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public LostLevelView(int level, PacmanGame game) {
    super(level, game);
    this.lostCauseOfTimer = game.isGameOver();

    this.init();
  }
}
