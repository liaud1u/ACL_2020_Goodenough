package model.util.DAO;

import model.BestScore;
import model.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Ribeyrolles Matthieu
 * 30/11/2020, 21:39
 */
public class BestScoreFileXMLDAO implements BestScoreDAO {
  private final String URL;

  private static volatile BestScoreFileXMLDAO instance;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
  public final Document generateDocument() throws ParserConfigurationException, IOException, SAXException {
    // create all instances for the reading of the xml file
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(new File(this.URL));
    document.getDocumentElement().normalize();  // optional

    return document;
  }

  @Override
  public Collection<BestScore> load() {
    try {
      final Document document = this.generateDocument();

      NodeList tags = document.getElementsByTagName("bestScore"); // get all bestScore tags

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
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Collections.emptyList(); // return no best scores if we fail to load
  }

  @Override
  public void save(BestScore bestScore) {
    try {
      final Document document = this.generateDocument();

      Node root = document.getFirstChild(); // get the root of the xml file

      /**
       *  create the new element bestScore
       *  add the different elements (player name and score) inside it
       * */
      Element bestScoreElement = document.createElement("bestScore");
      Element playerNameElement = document.createElement("player_name");
      playerNameElement.appendChild(document.createTextNode(bestScore.getPlayerName()));
      Element scoreElement = document.createElement("score");
      scoreElement.appendChild(document.createTextNode(String.valueOf(bestScore.getScore())));

      bestScoreElement.appendChild(playerNameElement);
      bestScoreElement.appendChild(scoreElement);

      // if we already have 4 best scores, then remove the last one, the worst one
      if (this.load().size() >= Util.maxBestScores) {
        root.removeChild(document.getElementsByTagName("bestScore").item(3));
      }

      final NodeList tags = document.getElementsByTagName("bestScore"); // get all bestScore tags

      if (tags.getLength() == 0) {
        root.appendChild(bestScoreElement);
      } else {
        for (int i = 0; i < tags.getLength(); i++) {
          Node node = tags.item(i);
          int score;


          if (node.getNodeType() == Node.ELEMENT_NODE) { // if the node is an element
            Element element = (Element) node;
            score = Integer.parseInt(element.getElementsByTagName("score").item(0).getTextContent().trim());

            if (score <= bestScore.getScore()) {
              root.insertBefore(bestScoreElement, node);
              break;
            }
          }

          if (i == tags.getLength()-1) root.appendChild(bestScoreElement);
        }

      }

      // add the modifications to the xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(new File(this.URL));
      transformer.transform(source, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static BestScoreFileXMLDAO getInstance() {
    if (instance == null) {
      synchronized (BestScoreFileXMLDAO.class) {
        if (instance == null) {
          instance = new BestScoreFileXMLDAO();
        }
      }
    }

    return instance;
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
  private BestScoreFileXMLDAO() {
    this.URL = "best_scores.xml";
  }
}
