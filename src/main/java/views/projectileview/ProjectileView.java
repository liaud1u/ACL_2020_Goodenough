package views.projectileview;

import javafx.scene.Group;
import javafx.scene.image.Image;
import model.projectile.Fireball;
import model.projectile.Projectile;
import model.util.Util;

import java.util.ArrayList;

public class ProjectileView extends Group {

    private final ArrayList<FireballView> fireballViews;

    private final ArrayList<Projectile> projectiles;

    private final ArrayList<Projectile> printedProjectiles;

    private final Image[] fireballSprite = new Image[4];  // The sprites for the fireball, load here to avoid load image each time


    public ProjectileView(ArrayList<Projectile> projectileList) {
        this.projectiles = projectileList;
        fireballViews = new ArrayList<>();
        printedProjectiles = new ArrayList<>();


        final int size = (int) Util.slotSizeProperty.multiply(Util.RATIO_FIREBALL).get();
        final int spriteSize = size * 8;

        // Initialize all the sprites
        fireballSprite[0] = new Image("projectile/fireball/fireball_down.png", spriteSize, size, true, false);   // going down sprite
        fireballSprite[1] = new Image("projectile/fireball/fireball_up.png", spriteSize, size, true, false);     // going up sprite
        fireballSprite[2] = new Image("projectile/fireball/fireball_left.png", spriteSize, size, true, false);   // going left sprite
        fireballSprite[3] = new Image("projectile/fireball/fireball_right.png", spriteSize, size, true, false);  // going right sprite


        for (Projectile p : projectileList) {
            if (p.isFireball()) {
                Fireball fireball = (Fireball) p;
                FireballView fireballView = new FireballView(fireball, fireballSprite);
                fireballViews.add(fireballView);
                getChildren().add(fireballView);
            }
        }
    }


    public void draw(double ratio) {

        //Affichage des projectiles

        //On stocke tout les projectiles à ne plus afficher
        ArrayList<FireballView> viewToDelete = new ArrayList<>();

        //On parcours les vues des Boules de Feu pour enlever celle qui sont liées à des projectiles "mort"
        for (FireballView view : fireballViews) {
            if (!projectiles.contains(view.getFireball())) {
                //Si le projectile n'existe plus dans le modele, on le stocke dans la liste de supression
                viewToDelete.add(view);
                printedProjectiles.remove(view.getFireball());
            }
        }

        //On enleve de la lise des vues actuelles, les vues à supprimer que l'on a récupéré précedement
        for (FireballView toDelete : viewToDelete) {
            toDelete.setVisible(false);
            getChildren().remove(toDelete);
            fireballViews.remove(toDelete);
        }

        //On parcours tout les projectiles pour ajouter les vues correspondant à de potentiels nouveau projectiles
        for (Projectile p : projectiles) {
            if (p.isFireball() && !printedProjectiles.contains(p)) {
                Fireball fireball = (Fireball) p;
                FireballView fireballView = new FireballView(fireball, fireballSprite);
                fireballViews.add(fireballView);
                getChildren().add(fireballView);
                printedProjectiles.add(p);
            }
        }

        //Enfin, on met à jour les vues
        for (FireballView view : fireballViews) {
            view.draw(ratio);
        }

    }
}
