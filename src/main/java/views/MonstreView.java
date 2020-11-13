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


    private final double animX;

    private final double animY;

    public MonstreView(Monstre monstre) {
        this.monstre = monstre;
        int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_MONSTRE);
        sprite = new Image("monsters/temp_monster.png", size, size, true, false);
        view = new ImageView(sprite);

        view.setViewport(new Rectangle2D(0, 0, size, size));

        this.init();

        animX = (int) (Util.slotSizeProperty.get() / 2);
        animY = (int) (Util.slotSizeProperty.get() / 2);
    }

    /**
     * Initialisation de la vue
     */
    private void init() {
        this.getChildren().add(view);

    }

    public void draw(double ratio) {
        int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_MONSTRE);
        //view.setX(monstre.getX() * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2 - size / 2);
        //view.setY(monstre.getY() * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2 - size / 2);

        view.setX(Util.slotSizeProperty.get() * (monstre.getxPrec() + ratio * this.monstre.getMovementStrategy().getDirection().getX_dir()));
        view.setY(Util.slotSizeProperty.get() * (monstre.getyPrec() + ratio * this.monstre.getMovementStrategy().getDirection().getY_dir()));

    }
}
