package views.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 * MainMenuButton view
 */
public class MainMenuButton extends Button {
  private Tooltip tooltip;

  /**
   * @param text (:String)                                    : the text to dislay on the button
   * @param tooltip (:String)                                 : the text to display on the tooltip
   * @param event (:{@link EventHandler}<{@link ActionEvent}) : the event to trigger when action is done on the button
   * */
  public MainMenuButton(String text, String tooltip, EventHandler<MouseEvent> event) {
    super(text);
    this.tooltip = new Tooltip(tooltip);  // create a tooltip

    this.setTooltip(this.tooltip);  // add the tooltip on the button
    this.setOnMousePressed(event);  //set up the event to trigger on action
  }
}
