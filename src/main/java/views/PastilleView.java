package views;

import javafx.scene.shape.Circle;
import model.Pastille;
import model.util.Util;

/**
 * Vue de la pastille
 */
public class PastilleView extends Circle {

    /**
     * Pastille à afficher
     */
    private final Pastille pastille;

    /**
     * Constructeur de la vue
     *
     * @param pastille Pastille à afficher
     * @param x        int coordonnée x de la pastille
     * @param y        int coordonnée y de la pastille
     */
    public PastilleView(Pastille pastille, int x, int y) {
        super(Util.slotSizeProperty.get() / 6);

        this.pastille = pastille;
        this.setFill(pastille.getCouleurPastille());

        this.setCenterX(x * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
        this.setCenterY(y * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);

    }

    /**
     * Draw de la pastille (pour la mise à jour lors d'un ramassage)
     */
    public void draw() {
        this.setVisible(!pastille.isRamassee());
    }
}
