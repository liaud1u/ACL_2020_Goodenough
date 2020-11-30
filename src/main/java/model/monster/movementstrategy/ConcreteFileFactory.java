package model.monster.movementstrategy;

import model.util.AbstractDAOFactory;
import model.util.BestScoreDAO;
import model.util.files.BestScoreFileXMLDAO;
import model.util.files.FileType;

/**
 * @author Ribeyrolles Matthieu
 * 30/11/2020, 22:36
 */
public class ConcreteFileFactory extends AbstractDAOFactory {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
  @Override
  public BestScoreDAO getLeaderboardDAO(FileType fIleType) {
    switch (fIleType) {
      case XML:
      default: return BestScoreFileXMLDAO.getInstance();
    }
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
