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
    private final Rectangle2D[] frames;
    private final double animY;

    /**
     * Frame actuelle (pour les animations)
     */
    private int currentFrame;

    public MonstreView(Monstre monstre) {
        this.monstre = monstre;
        int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_MONSTRE);
        sprite = new Image("monsters/red_ghost.png", size * 8, size, true, false);
        view = new ImageView(sprite);

        currentFrame = 0;

        view.setViewport(new Rectangle2D(0, 0, size, size));

        this.init();

        animX = (int) (Util.slotSizeProperty.get() / 2);
        animY = (int) (Util.slotSizeProperty.get() / 2);

        frames = new Rectangle2D[8];
        for (int i = 0; i < 8; i++)
            frames[i] = new Rectangle2D(size * i, 0, size, size);


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

        currentFrame = (currentFrame + 1) % 20;
        int printedFrame = currentFrame / 10;

        switch (monstre.getMovementStrategy().getDirection()) {
            case UP:
                view.setViewport(frames[0 + printedFrame]);
                break;
            case DOWN:
                view.setViewport(frames[2 + printedFrame]);
                break;
            case LEFT:
                view.setViewport(frames[4 + printedFrame]);
                break;
            case RIGHT:
                view.setViewport(frames[6 + printedFrame]);
                break;
        }


    }
}
