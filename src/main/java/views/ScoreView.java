package views;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.PacmanGame;
import model.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ScoreView extends Group {
    /**
     * Tableau de la liste des vues des chiffres
     */
    private ImageView[] valueView;

    /**
     * Modele
     */
    private PacmanGame game;

    /**
     * Ressource contenant les chiffres de 0 à 9
     */
    private Image sprite;

    /**
     * Largeur de la vue
     */
    private double width;

    /**
     * Hauteur de la vue
     */
    private double heigth;

    /**
     * Constructeur de la vue du score
     * @param game PacmanGame modele
     * @param width int largeur de la vue
     * @param height int hauteur de la vue
     */
    public ScoreView(PacmanGame game, int width, int height, int x, int y)
    {
        this.game = game;
        this.heigth=height;
        this.width=width;
        sprite = new Image("score.png", width/Util.SCORE_SIZE*10,height,true,false);
        valueView = new ImageView[Util.SCORE_SIZE];

        int[] scoreTab = {0,0,0,0,0,0,0,0};
        int score = game.getScore();

        //Initialisation à 0 du compteur
        for(int cpt = Util.SCORE_SIZE-1; cpt >= 0;cpt -- ){

            //Récupération du chiffre à afficher
            scoreTab[cpt]=score%10;
            score/=10;

            //Création et inialisation de la ImageView correspondante
            valueView[cpt] = new ImageView(sprite);
            valueView[cpt].setX(x + width/Util.SCORE_SIZE*cpt);
            valueView[cpt].setY(y);

            //Sélection de la bonne partie de l'image
            valueView[cpt].setViewport(new Rectangle2D(width/10 *scoreTab[cpt],0,width/10,heigth));

            getChildren().add(valueView[cpt]);
        }
    }

    /**
     * Méthode pour rafraichir le compteur
     */
    public void draw(){
        int[] scoreTab = {0,0,0,0,0,0,0,0};
        int score = game.getScore();


        for(int cpt = Util.SCORE_SIZE-1; cpt >= 0;cpt -- ){
            scoreTab[cpt]=score%10;
            score/=10;

            valueView[cpt].setViewport(new Rectangle2D(width/10 *scoreTab[cpt],0,width/10,heigth));
        }
    }
}
