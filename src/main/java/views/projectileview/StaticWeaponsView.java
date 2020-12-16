package views.projectileview;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.util.Util;
import model.weapons.StaticWeapon;
import java.util.ArrayList;
import java.util.List;

public class StaticWeaponsView extends Group {

    private List<StaticWeapon> staticWeapons;
    private List<ImageView> staticWeaponsImages;

    /**
     * Constructor for the static weapons view
     * @param staticWeapons list of static weapons
     */
    public StaticWeaponsView(List<StaticWeapon> staticWeapons) {
        this.staticWeapons = staticWeapons;
        this.staticWeaponsImages = new ArrayList<>();
    }


    /**
     * draw method
     */
    public void draw() {
        staticWeaponsImages.clear();
        this.getChildren().clear();
        for(StaticWeapon s : staticWeapons) {
            if(s.isTriggered()) {
                ImageView staticImageView = new ImageView();
                switch(s.getType()) {
                    case LANDMINE:
                        staticImageView.setImage(new Image("projectile/landmine/mine.png", Util.slotSizeProperty.get() * 0.8, Util.slotSizeProperty.get() * 0.8,true,false));
                    default:
                        break;
                }

                // define the coordinates of the view
                staticImageView.setX(Util.slotSizeProperty.multiply(s.getX()).add(Util.slotSizeProperty.divide(2).subtract(staticImageView.getImage().getWidth()/ 2)).get());
                staticImageView.setY(Util.slotSizeProperty.multiply(s.getY()).add(Util.slotSizeProperty.divide(2).subtract(staticImageView.getImage().getWidth() / 2)).get());
                staticWeaponsImages.add(staticImageView);
            } else if(!s.isTriggered() && !s.hasTriggeredAnimation()) {
                // TODO manage explosion here

                s.setTriggeredAnimation(true);
            }
        }
        this.getChildren().addAll(staticWeaponsImages);
    }
}
