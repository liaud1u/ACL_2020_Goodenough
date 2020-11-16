package views.menus;

import javafx.scene.text.Font;

/**
 * @author Ribeyrolles Matthieu
 * 14/11/2020, 15:30
 */
public class WonLevelView extends EndLevelView {
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
      "Congrats!\nYou're currently on level %d, with a score of %d!\n\nReady to continue?",
      this.level,
      this.score
    ));

    this.button.setText("Next level");
  }


  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public WonLevelView(int level, int score) {
    super(level, score);
  }
}
