package views.caseview;

import javafx.geometry.Rectangle2D;
import model.util.Util;

import static model.util.Util.slotSizeProperty;

/** enum used for the different slots sprites
 * */
public enum CaseImageTiles {
  /** For each different sprite we can have, define the rect used
   * */
  REVERSE_SQUARE    (new Rectangle2D(7 * slotSizeProperty.get(), Util.slotSizeProperty.get(), slotSizeProperty.get(), slotSizeProperty.get())),
  SQUARE            (new Rectangle2D(4 * slotSizeProperty.get(), Util.slotSizeProperty.get(), slotSizeProperty.get(), slotSizeProperty.get())),

  DOWN              (new Rectangle2D(6 * slotSizeProperty.get(), 0, slotSizeProperty.get(), slotSizeProperty.get())),
  RIGHT             (new Rectangle2D(7 * slotSizeProperty.get(), 0, slotSizeProperty.get(), slotSizeProperty.get())),
  LEFT              (new Rectangle2D(9 * slotSizeProperty.get(), 0, slotSizeProperty.get(), slotSizeProperty.get())),
  UP                (new Rectangle2D(6 * slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, slotSizeProperty.get(), slotSizeProperty.get())),

  RIGHT_DOWN_CORNER (new Rectangle2D(3 * slotSizeProperty.get(), 0, slotSizeProperty.get(), slotSizeProperty.get())),
  RIGHT_UP_CORNER   (new Rectangle2D(3 * slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, slotSizeProperty.get(), slotSizeProperty.get())),
  LEFT_DOWN_CORNER  (new Rectangle2D(5 * slotSizeProperty.get(), 0, slotSizeProperty.get(), slotSizeProperty.get())),
  LEFT_UP_CORNER    (new Rectangle2D(5 * slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, slotSizeProperty.get(), slotSizeProperty.get())),

  RIGHT_DOWN_UP_T   (new Rectangle2D(3 * slotSizeProperty.get(), Util.slotSizeProperty.get(), slotSizeProperty.get(), slotSizeProperty.get())),
  UP_LEFT_RIGHT_T   (new Rectangle2D(4 * slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, slotSizeProperty.get(), slotSizeProperty.get())),
  LEFT_DOWN_UP_T    (new Rectangle2D(5 * slotSizeProperty.get(), Util.slotSizeProperty.get(), slotSizeProperty.get(), slotSizeProperty.get())),
  DOWN_LEFT_RIGHT_T (new Rectangle2D(4 * slotSizeProperty.get(), 0, slotSizeProperty.get(), slotSizeProperty.get())),

  RIGHT_LEFT        (new Rectangle2D(8 * slotSizeProperty.get(), 0, slotSizeProperty.get(), slotSizeProperty.get())),
  UP_DOWN           (new Rectangle2D(6 * slotSizeProperty.get(), Util.slotSizeProperty.get(), slotSizeProperty.get(), slotSizeProperty.get()));


  private final Rectangle2D view;

  /**
   * Constructor
   * @param view
   */
  CaseImageTiles(Rectangle2D view) {
    this.view = view;
  }

  /**
   * Return Rectangle to get right view
   * @return Rectangle
   */
  public Rectangle2D getView() {
    return view;
  }
  }
