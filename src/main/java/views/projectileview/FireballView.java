package views.projectileview;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.projectile.Fireball;
import model.util.Util;

public class FireballView extends Circle {
    private final Fireball fireball;

    public FireballView(Fireball fireball) {
        this.fireball = fireball;

        final double size = Util.slotSizeProperty.multiply(Util.RATIO_PASTILLE).get();

        setRadius(size / 2);
        setCenterX(fireball.getX() * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
        setCenterY(fireball.getY() * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);

        setFill(Color.RED);
    }


    public void draw() {
        setCenterX(fireball.getX() * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
        setCenterY(fireball.getY() * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
    }

    public Fireball getFireball() {
        return fireball;
    }
}
