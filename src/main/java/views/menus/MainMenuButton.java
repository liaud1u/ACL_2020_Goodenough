package views.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * @author Ribeyrolles Matthieu
 * 01/12/2020, 11:58
 */
public class MainMenuButton extends Button {
  private Tooltip tooltip;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public MainMenuButton(String text, String tooltip, EventHandler<ActionEvent> event) {
    super(text);
    this.tooltip = new Tooltip(tooltip);

    this.setTooltip(this.tooltip);
    this.setOnAction(event);
  }
}
