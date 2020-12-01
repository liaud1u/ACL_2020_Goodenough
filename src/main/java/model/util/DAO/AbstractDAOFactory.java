package model.util.DAO;

import model.util.DAO.files.FileType;

/**
 * @author Ribeyrolles Matthieu
 * 30/11/2020, 21:03
 */
public abstract class AbstractDAOFactory {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
//  public abstract AbstractDAOFactory getAbstractDAOFactory();
  public abstract BestScoreDAO getLeaderboardDAO(FileType fIleType);
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
