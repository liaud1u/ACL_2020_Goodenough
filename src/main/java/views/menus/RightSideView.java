package views.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.util.DAO.ConcreteFileFactory;
import model.util.DAO.files.FileType;
import model.util.SpriteTools;
import model.util.Util;
import views.rightpanels.AmmoView;
import views.rightpanels.ScoreView;
import views.rightpanels.StaticWeaponView;
import views.TimerView;

/**
 * Right side of the screen view
 */
public class RightSideView extends VBox {

    private Label buttonExit;
    private TimerView timerView;   // the display for the timer
    private ScoreView scoreView;    // the display for the score
    private BestScoresView bestScoresView;  // the display for the best scores
    private AmmoView ammoView;  //  the display for the ammo left
    private StaticWeaponView staticWeaponView;

    /**
     * Constructor
     */
    public RightSideView() {
        SpriteTools.setImageSize(Util.rightWidthProperty.get(), 24);    // set image size using external method

        this.getStylesheets().add("fonts/fontstyle.css");   // bind stylesheet
        this.layoutXProperty().bind(Util.slotSizeProperty.multiply(Util.MAZE_SIZE));    // set it to the right of the game part
        this.setAlignment(Pos.TOP_CENTER);  // center elements
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));    // a black background
        this.prefHeightProperty().bind(Util.currentWindowHeightProperty);   // bind height relative to current window height
        this.prefWidthProperty().bind(Util.rightWidthProperty); // bind width
        this.setSpacing(50);    // spacing between elements
        this.setPadding(new Insets(20,0,0,0));  // apdding to prevent elements to be too close to up and down borders
        this.init();
    }

    /**
     * This method initializes all the view
     * It also adds new Labels to introduce properly the views
     * Button to go back to the menu is also added
     * */
    private void init(){
        // init the views
        this.scoreView = new ScoreView();
        this.timerView = new TimerView();
        this.ammoView = new AmmoView();
        this.staticWeaponView = new StaticWeaponView();
        try {
            // load the best scores and create the view
            this.bestScoresView = new BestScoresView(new ConcreteFileFactory().getLeaderboardDAO(FileType.XML).load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Label scoreLabel = new Label("Score : ");   // label to introduce the score view
        Label timerLabel = new Label("Time left : ");   // label to introduce the timer view

        // button to go back to the menu, with a tooltip
        this.buttonExit = new Label("Menu");
        this.buttonExit.getStyleClass().add("button");
        this.buttonExit.setOnMousePressed(event -> ((MainView) this.getScene()).initMenu());

        // add the style classes
        scoreLabel.getStyleClass().add("text_");
        timerLabel.getStyleClass().add("text_");

        // final arangments to display properly the elements
        VBox scoreBox = new VBox(scoreLabel, this.scoreView);
        VBox timerBox = new VBox(timerLabel, this.timerView);
        scoreBox.setAlignment(Pos.TOP_CENTER);
        timerBox.setAlignment(Pos.TOP_CENTER);

        // finally add the elements to the root
        this.getChildren().addAll(scoreBox, timerBox, this.ammoView, this.staticWeaponView,bestScoresView, buttonExit);
    }

    /**
     * @param score (:int)      : the current score
     * @param timer (:int)      : the current timer
     * @param ammos (:ammos)    : the current ammo
     * */
    public void draw(int score, int timer, int ammos, int staticWeapons) {
        //FIXME : find out why currentWindowProperty is NaN in init()

        // if button not null, set a translation to set it to the bottom of the window
        if(buttonExit != null) buttonExit.setTranslateY(Util.currentWindowHeightProperty.multiply(0.05).get());
        scoreView.draw(score);  // call draw method fot the score
        timerView.draw(timer);  // call draw method for the timer
        this.ammoView.draw(ammos);  // call the draw method for the ammo view
        this.staticWeaponView.draw(staticWeapons);
    }
}
