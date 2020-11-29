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
public abstract class FileLoader {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
  public static Collection<BestScore> loadBestScores() throws IOException, ParserConfigurationException, SAXException {
    File file = new File("best_scores.xml");

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(file);
    document.getDocumentElement().normalize();

    final NodeList tags = document.getElementsByTagName("bestScore");

    BestScore[] bestScores = new BestScore[tags.getLength()];

    for (int i = 0; i < tags.getLength(); i++) {
      Node node = tags.item(i);

      System.out.printf("Current node: %s\n", node.getNodeName());

      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;

        bestScores[i] = new BestScore(
          element.getElementsByTagName("player_name").item(0).getTextContent().trim(),
          Integer.parseInt(element.getElementsByTagName("score").item(0).getTextContent().trim())
        );
      }
    }

    return Arrays.asList(bestScores);
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
