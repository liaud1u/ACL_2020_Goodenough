package views.pastille;

import javafx.scene.image.Image;

public class ScorePastilleView extends PastilleView{
    /**
     * @param x (:int)              item's x coordinate
     * @param y (:int)              item's y coordinate
     */
    public ScorePastilleView(int x, int y) {
        super(x, y);
        // set the image to display
        this.setImage(new Image("pastilles/scorePastille.png", size, size,true,false));

    }
}
