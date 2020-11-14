package views.menus;

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
  }

  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public LostLevelView(int level, int score, boolean lostCauseOfTimer) {
    super(level, score);
    this.lostCauseOfTimer = lostCauseOfTimer;

    this.init();
  }
}
