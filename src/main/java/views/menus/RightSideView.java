package views.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.util.DAO.ConcreteFileFactory;
import model.util.SpriteTools;
import model.util.Util;
import model.util.DAO.files.FileType;
import views.AmmoView;
import views.ScoreView;
import views.TimerView;

public class RightSideView extends VBox {

    private Label buttonExit;
    private TimerView timerView;
    private ScoreView scoreView;
    private BestScoresView bestScoresView;
    private AmmoView ammoView;

    public RightSideView() {
        SpriteTools.setImageSize(Util.rightWidthProperty.get(), 24);
        this.getStylesheets().add("fonts/fontstyle.css");
        this.layoutXProperty().bind(Util.slotSizeProperty.multiply(Util.MAZE_SIZE));
        this.setAlignment(Pos.TOP_CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        this.prefHeightProperty().bind(Util.currentWindowHeightProperty);
        this.prefWidthProperty().bind(Util.rightWidthProperty);
        this.setSpacing(50);
        this.setPadding(new Insets(20,0,0,0));
        this.init();
    }


    private void init(){
        this.scoreView = new ScoreView();
        this.timerView = new TimerView();
        this.ammoView = new AmmoView();
        try {
            this.bestScoresView = new BestScoresView(new ConcreteFileFactory().getLeaderboardDAO(FileType.XML).load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Label scoreLabel = new Label("Score : ");
        Label timerLabel = new Label("Time left : ");
        buttonExit = new Label("MENU");

        scoreLabel.getStyleClass().add("text_");
        timerLabel.getStyleClass().add("text_");

        buttonExit.getStyleClass().add("button");
        VBox scoreBox = new VBox(scoreLabel, this.scoreView);
        VBox timerBox = new VBox(timerLabel, this.timerView);
        scoreBox.setAlignment(Pos.TOP_CENTER);
        timerBox.setAlignment(Pos.TOP_CENTER);
        buttonExit.setTextFill(Color.BLACK);
        buttonExit.setOnMousePressed(event -> {
            ((MainView) this.getScene()).initMenu();
        });

        this.getChildren().addAll(scoreBox, timerBox, this.ammoView, bestScoresView, buttonExit);
    }

    public void draw(int score, int timer, int ammos) {
        //FIXME : find out why currentWindowProperty is NaN in init()
        if(buttonExit != null) buttonExit.setTranslateY(Util.currentWindowHeightProperty.multiply(.1).get());
        scoreView.draw(score);
        timerView.draw(timer);
        this.ammoView.draw(ammos);
    }
}
