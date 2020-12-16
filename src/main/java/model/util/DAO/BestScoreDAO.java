package model.util.DAO;

import model.BestScore;
import model.util.Util;

import java.util.Collection;

/**
 * @author Ribeyrolles Matthieu
 * 30/11/2020, 21:30
 */
public interface BestScoreDAO {
  default boolean isNewBestScore(int score) {
    if (score == 0) return false; // a best score cannot be 0

    try {
      Collection<BestScore> bestScores = load();  // load the current saved best scores

      if (bestScores.size() < Util.maxBestScores) {   // if we have less best scores saved than the max allowed
        return true;
      }

      for (BestScore bs : bestScores) {
        if (bs.getScore() < score) return true; // compare scores
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return false;
  }

  Collection<BestScore> load();
  void save(BestScore bestScore);
  void reset();
}
