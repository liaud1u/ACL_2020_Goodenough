package views.projectileview;

import javafx.scene.Group;
import model.projectile.Fireball;
import model.projectile.Projectile;

import java.util.ArrayList;

public class ProjectileView extends Group {

    private final ArrayList<FireballView> fireballViews;

    private final ArrayList<Projectile> projectiles;

    private final ArrayList<Projectile> printedProjectiles;


    public ProjectileView(ArrayList<Projectile> projectileList) {
        this.projectiles = projectileList;
        fireballViews = new ArrayList<>();
        printedProjectiles = new ArrayList<>();
        for (Projectile p : projectileList) {
            if (p.isFireball()) {
                Fireball fireball = (Fireball) p;
                FireballView fireballView = new FireballView(fireball);
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
                FireballView fireballView = new FireballView(fireball);
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
