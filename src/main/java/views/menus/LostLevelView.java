package views.menus;

import model.PacmanGame;


/** class used to display the lost level menu
 * */
public class LostLevelView extends EndLevelView{
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
      "Level failed!\n%s\nYou reached the level %d, with a score of %d!\n\nReady to start again?",
      (this.game.getGameTimer().isOver()) ? "You ran out of time :(" : "",  // if timer is over, print a message
      this.game.getLevel() + 1,
      this.game.getScore()
    ));

    this.button.setText("Try again"); // set text for the button
  }

  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  /** @param game (:{@link PacmanGame}) the current game
   * */
  public LostLevelView(PacmanGame game) {
    super(game);

    this.init();
  }
}
