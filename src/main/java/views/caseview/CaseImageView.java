package views.caseview;

import javafx.geometry.Rectangle2D;
import model.util.Util;

public enum CaseImageView {
    REVERSE_SQUARE(new Rectangle2D(7 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    SQUARE(new Rectangle2D(4 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),

    DOWN(new Rectangle2D(6 * Util.slotSizeProperty.get(), 0, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    RIGHT(new Rectangle2D(7 * Util.slotSizeProperty.get(), 0, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    LEFT(new Rectangle2D(9 * Util.slotSizeProperty.get(), 0, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    UP(new Rectangle2D(6 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),

    RIGHT_DOWN_CORNER(new Rectangle2D(3 * Util.slotSizeProperty.get(), 0, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    RIGHT_UP_CORNER(new Rectangle2D(3 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    LEFT_DOWN_CORNER(new Rectangle2D(5 * Util.slotSizeProperty.get(), 0, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    LEFT_UP_CORNER(new Rectangle2D(5 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),

    RIGHT_DOWN_UP_T(new Rectangle2D(3 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    UP_LEFT_RIGHT_T(new Rectangle2D(4 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get() * 2, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    LEFT_DOWN_UP_T(new Rectangle2D(5 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    DOWN_LEFT_RIGHT_T(new Rectangle2D(4 * Util.slotSizeProperty.get(), 0, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),

    RIGHT_LEFT(new Rectangle2D(8 * Util.slotSizeProperty.get(), 0, Util.slotSizeProperty.get(), Util.slotSizeProperty.get())),
    UP_DOWN(new Rectangle2D(6 * Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get(), Util.slotSizeProperty.get()));


    private final Rectangle2D view;

    CaseImageView(Rectangle2D view) {
        this.view = view;
    }

    public Rectangle2D getView() {
        return view;
    }
}
