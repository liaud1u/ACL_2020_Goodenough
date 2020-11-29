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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * @author Ribeyrolles Matthieu
 * 29/11/2020, 15:22
 */
public abstract class FileWriter<result, source> {
  protected FileWriter() throws TransformerConfigurationException {
  }
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  public static void addBestScore(BestScore bestScore) throws ParserConfigurationException, SAXException, IOException, TransformerException {
    File file = new File(Util.BEST_SCORES_URL);

    // create all instances for the reading of the xml file
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    document.getDocumentElement().normalize();  // optional

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
    if (FileReader.loadBestScores().size() >= 4) {
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
      }

    }

    // add the modifications to the xml file
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(document);
    StreamResult result = new StreamResult(new File(Util.BEST_SCORES_URL));
    transformer.transform(source, result);
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
