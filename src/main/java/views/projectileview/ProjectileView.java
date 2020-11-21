package views.projectileview;

import javafx.scene.Group;
import model.projectile.Fireball;
import model.projectile.Projectile;

import java.util.HashMap;
import java.util.List;

public class ProjectileView extends Group {

    private final HashMap<Fireball, FireballView> fireballViews = new HashMap<>();
    private final List<Projectile> projectiles;

    public ProjectileView(List<Projectile> projectileList) {
        this.projectiles = projectileList;
        for (Projectile p : projectileList) {
            if (p.isFireball()) {
                Fireball fireball = (Fireball) p;
                FireballView fireballView = new FireballView(fireball);
                fireballViews.put(fireball, fireballView);
                getChildren().add(fireballView);
            }
        }
    }

    public void draw() {
        for (Projectile p : projectiles) {
            if (p.isFireball() && !fireballViews.containsKey(p)) {
                Fireball fireball = (Fireball) p;
                FireballView fireballView = new FireballView(fireball);
                fireballViews.put(fireball, fireballView);
                getChildren().add(fireballView);
            }
        }
        for (FireballView view : fireballViews.values()) {
            view.draw();
        }
    }
}
