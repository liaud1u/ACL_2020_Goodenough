package model;

import exceptions.NegativeScoreException;
import model.util.Util;

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

  public BestScore(String playerName, int score) throws NegativeScoreException {

    if(score < 0) throw new NegativeScoreException("ERREUR : Impossible d'obtenir un score négatif");
    if(playerName.length() > 4) playerName = playerName.substring(0, Util.maxPlayerNameSize);

    this.score = score;
    this.playerName = playerName;
  }
}
