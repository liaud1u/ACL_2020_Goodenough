package views;

import javafx.scene.Group;
import javafx.scene.image.Image;
import model.labyrinthe.Labyrinthe;
import model.util.Util;
import views.caseview.CaseView;


/** class used to display the labyrinth and its slots
 * */
public class LabyrintheView extends Group {
  private final Labyrinthe labyrinthe;  // the labyrinth to display
  private final Image sprite; // the sprites we'll used to display slots

  /** @param labyrinth (:{@link Labyrinthe}), the labyrinth to display
   */
  public LabyrintheView(Labyrinthe labyrinth) {
    this.labyrinthe = labyrinth;

    // create the sprite
    sprite = new Image(
      "maze.png",
      Util.slotSizeProperty.multiply(10).get(),
      Util.slotSizeProperty.multiply(3).get(),
      false,
      true
    );

    // add a new case display (CaseView) for each slot
    for (int ligne = 0; ligne < Util.MAZE_SIZE; ligne++) {
      for (int colonne = 0; colonne < Util.MAZE_SIZE; colonne++)
        this.getChildren().add(new CaseView(sprite, labyrinth.getCaseLabyrinthe(ligne, colonne)));
    }
  }
}





