package model;

/**
 * @author Ribeyrolles Matthieu
 * 29/11/2020, 13:49
 */
public class BestScore {
  private int score;
  private String playerName;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public int getScore() {
    return score;
  }

  public String getPlayerName() {
    return playerName;
  }

  // setters
  // private
  // public

  @Override
  public String toString() {
    return String.format("Best score done by %s, with a score of %d",
      this.playerName,
      this.score
    );
  }

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public BestScore(String playerName, int score) {
    //TODO: force the player name to a certain length (e.g. 3 or 4)
    this.score = score;
    this.playerName = playerName;
  }
}
