package model.util.DAO;

import model.util.DAO.files.FileType;

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
