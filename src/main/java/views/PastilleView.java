package views;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.Pastille;
import model.util.Util;

/**
 * Vue de la pastille
 */
public class PastilleView extends Group {

    /**
     * Pastille à afficher
     */
    private final Pastille pastille;


    /**
     * Vue de l'image à afficher
     */
    private ImageView view;


    /**
     * Constructeur de la vue
     *
     * @param pastille Pastille à afficher
     * @param x        int coordonnée x de la pastille
     * @param y        int coordonnée y de la pastille
     */
    public PastilleView(Pastille pastille, double x, double y) {
        double size = Util.RATIO_PASTILLE * Util.slotSizeProperty.get();
        Image image =new Image("pastille.png",size,size,true,false);

        this.pastille = pastille;

        view = new ImageView(image);

        view.setX(x * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2 - size/2);
        view.setY(y * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2 - size/2);

        getChildren().add(view);
    }

    /**
     * Draw de la pastille (pour la mise à jour lors d'un ramassage)
     */
    public void draw() {
        this.setVisible(!pastille.isRamassee());
    }
    
}
