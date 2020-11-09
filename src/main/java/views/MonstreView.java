package views;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.monster.Monstre;
import model.util.Util;

public class MonstreView extends Group {

    private final Monstre monstre;
    private final Image sprite;
    private final ImageView view;

    public MonstreView(Monstre monstre) {
        this.monstre = monstre;
        int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_MONSTRE);
        sprite = new Image("monsters/temp_monster.png", size * 3, size, true, false);
        view = new ImageView(sprite);

        view.setViewport(new Rectangle2D(0,0,size,size));
        view.setX(monstre.getX() * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2 - size/2);
        view.setY(monstre.getY()* Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2 - size/2);
        this.init();
    }

    /**
     * Initialisation de la vue
     */
    private void init() {
        this.getChildren().add(view);
    }
}
