package views.menus;

import model.PacmanGame;

/** class used to display a won level menu
 * */
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

    this.text.setText(String.format(  // set the text for a lost, with the level and the score reached
      "Congrats!\nYou're currently on level %d, with a score of %d!\n\nReady to continue?",
      this.game.getLevel() + 1,
      this.game.getScore()
    ));

    this.text.getStyleClass().add("big_text_");

    this.button.setText("Next level");  // set the button text
  }

  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  /** @param game (:{@link PacmanGame}) the current game
   * */
  public WonLevelView(PacmanGame game) {
    super(game);
  }
}
