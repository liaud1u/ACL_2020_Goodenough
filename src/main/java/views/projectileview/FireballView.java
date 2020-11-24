package views.projectileview;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.projectile.Fireball;
import model.util.Util;

public class FireballView extends Group {
    private final Fireball fireball;
    private final ImageView view; // The current sprite to display

    private int frame;  // Current frame (for animations)

    public FireballView(Fireball fireball, Image[] sprite) {
        this.fireball = fireball;

        this.frame = 0;

        final double size = Util.slotSizeProperty.multiply(Util.RATIO_FIREBALL).get();

        this.view = new ImageView(sprite[0]); // the current sprite to display
        this.view.setViewport(new Rectangle2D(0, 0, size, size)); // set the viewport

        switch (fireball.getDirection()) {
            case DOWN:
                view.setImage(sprite[0]);
                break;
            case UP:
                view.setImage(sprite[1]);
                break;
            case LEFT:
                view.setImage(sprite[2]);
                break;
            case RIGHT:
                view.setImage(sprite[3]);
                break;
        }

        view.setX(Util.slotSizeProperty.multiply(fireball.getxPrec()).get());
        view.setY(Util.slotSizeProperty.multiply(fireball.getyPrec()).get());


        this.getChildren().add(view);
    }


    public void draw(double ratio) {


        final double size = Util.slotSizeProperty.multiply(Util.RATIO_FIREBALL).get();

        view.setX(Util.slotSizeProperty.multiply(fireball.getxPrec() + ratio * fireball.getDirection().getX_dir()).get());
        view.setY(Util.slotSizeProperty.multiply(fireball.getyPrec() + ratio * fireball.getDirection().getY_dir()).get());


        frame = (frame + 1) % 25;
        int printedFrame = frame / 5;

        // we set the current viewport of the viewport
        view.setViewport(new Rectangle2D(printedFrame * size, 0, size, size));
    }

    public Fireball getFireball() {
        return fireball;
    }
}
