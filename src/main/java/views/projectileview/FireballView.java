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
        setCenterX(Util.slotSizeProperty.multiply(fireball.getxPrec()).get() + Util.slotSizeProperty.get() / 2);
        setCenterY(Util.slotSizeProperty.multiply(fireball.getyPrec()).get() + Util.slotSizeProperty.get() / 2);

        setFill(Color.RED);
    }


    public void draw(double ratio) {

        setCenterX(Util.slotSizeProperty.multiply(fireball.getxPrec() + ratio * fireball.getDirection().getX_dir()).get() + Util.slotSizeProperty.get() / 2);
        setCenterY(Util.slotSizeProperty.multiply(fireball.getyPrec() + ratio * fireball.getDirection().getY_dir()).get() + Util.slotSizeProperty.get() / 2);

    }

    public Fireball getFireball() {
        return fireball;
    }
}
