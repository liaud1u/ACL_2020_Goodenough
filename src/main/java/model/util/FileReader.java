package model.util;

import model.BestScore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Ribeyrolles Matthieu
 * 29/11/2020, 14:01
 */
public abstract class FileReader {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
  public static boolean canAddThisScore(int score) throws ParserConfigurationException, SAXException, IOException {
    if (score == 0) return false;

    Collection<BestScore> bestScores = loadBestScores();

    if (bestScores.size() < 4) return true;

    for (BestScore bs : bestScores) {
      if (bs.getScore() < score) return true;
    }

    return false;
  }

  public static Collection<BestScore> loadBestScores() throws IOException, ParserConfigurationException, SAXException {
    File file = new File(Util.BEST_SCORES_URL);

    // create all instances for the reading of the xml file
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    document.getDocumentElement().normalize();  // optional

    final NodeList tags = document.getElementsByTagName("bestScore"); // get all bestScore tags

    BestScore[] bestScores = new BestScore[tags.getLength()]; // the array to return

    for (int i = 0; i < tags.getLength(); i++) {
      Node node = tags.item(i);

      if (node.getNodeType() == Node.ELEMENT_NODE) { // if the node is an element
        Element element = (Element) node;

        // create the best score and add it to the array
        bestScores[i] = new BestScore(
          element.getElementsByTagName("player_name").item(0).getTextContent().trim(),
          Integer.parseInt(element.getElementsByTagName("score").item(0).getTextContent().trim())
        );
      }
    }

    return Arrays.asList(bestScores); //return the collection containing the best scores
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
