package views.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.BestScore;
import model.util.FileLoader;
import model.util.FileWriter;
import model.util.SpriteTools;
import model.util.Util;
import views.ScoreView;
import views.TimerView;

public class RightSideView extends VBox {

    private Label buttonExit;
    private TimerView timerView;
    private ScoreView scoreView;
    private BestScoresView bestScoresView;

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
        try {
            FileWriter.addBestScore(new BestScore("Toi", 10000000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.scoreView = new ScoreView();
        this.timerView = new TimerView();
        try {
            this.bestScoresView = new BestScoresView(FileLoader.loadBestScores());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Label scoreLabel = new Label("Score : ");
        Label timerLabel = new Label("Time left : ");
        buttonExit = new Label("QUIT");
        scoreLabel.getStyleClass().add("text_");
        timerLabel.getStyleClass().add("text_");
        buttonExit.getStyleClass().add("button");
        VBox scoreBox = new VBox(scoreLabel, this.scoreView);
        VBox timerBox = new VBox(timerLabel, this.timerView);
        scoreBox.setAlignment(Pos.TOP_CENTER);
        timerBox.setAlignment(Pos.TOP_CENTER);
        buttonExit.setTextFill(Color.BLACK);
        buttonExit.setOnMousePressed(event -> System.exit(0));

        this.getChildren().addAll(scoreBox, timerBox, bestScoresView, buttonExit);
    }

    public void draw(int score, int timer) {
        //FIXME : find out why currentWindowProperty is NaN in init()
        if(buttonExit != null) buttonExit.setTranslateY(Util.currentWindowHeightProperty.multiply(.2).get());
        scoreView.draw(score);
        timerView.draw(timer);
    }
}
