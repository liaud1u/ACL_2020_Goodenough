package views.pastille;

import javafx.scene.image.Image;

public class AmmoPastilleView extends PastilleView{
    /**
     * @param x (:int)              item's x coordinate
     * @param y (:int)              item's y coordinate
     */
    public AmmoPastilleView(int x, int y) {
        super(x, y);
        // set the image to display
        this.setImage(new Image("pastilles/ammoPastille.png", size, size,true,false));
    }
}
