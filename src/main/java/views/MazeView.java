package views;

import model.Case;
import model.Labyrinthe;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

// FIXME : This class needs to be renamed correctly AND implemented in JavaFX (currently AWT/Swing)

public class MazeView extends JFrame {


    private Labyrinthe laby;

    public MazeView(Labyrinthe laby) {
        super("Labyrinthe");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(3);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());


        JPanel mazePanel = new MazePanel(laby);

        mazePanel.setPreferredSize(new Dimension(laby.getTaille()*20,laby.getTaille()*20));
        this.add(mazePanel,BorderLayout.CENTER);
        this.pack();
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2);
        setResizable(false);
        setVisible(true);
    }


    class MazePanel extends JPanel implements Observer {


        private final static int TAILLE_CASE = 20;
        private final static int EPAISSEUR_MUR = 2;
        private Labyrinthe laby;
        public MazePanel(Labyrinthe laby) {
            this.laby = laby;
        }


        public void update(Observable o, Object arg) {
            this.repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int diffTailleCase = TAILLE_CASE - EPAISSEUR_MUR;

            Case[][] plateau = laby.getPlateau();
            for(int x = 0 ; x < laby.getTaille() ; x++) {
                for (int y = 0; y < laby.getTaille(); y++) {
                    Case c = plateau[x][y];
                    g.setColor(Color.WHITE);
                    g.fillRect( c.getY() * TAILLE_CASE,c.getX() * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                    g.setColor(new Color(100,100,255));
                    if (c.isMurEst()) {
                        g.fillRect(c.getY() * TAILLE_CASE + diffTailleCase,c.getX() * TAILLE_CASE ,  EPAISSEUR_MUR, TAILLE_CASE);
                    }
                    if (c.isMurOuest()) {
                        g.fillRect(c.getY() * TAILLE_CASE,c.getX() * TAILLE_CASE,  EPAISSEUR_MUR, TAILLE_CASE);
                    }
                    if (c.isMurNord()) {
                        g.fillRect(c.getY() * TAILLE_CASE,c.getX() * TAILLE_CASE,  TAILLE_CASE, EPAISSEUR_MUR);
                    }
                    if (c.isMurSud()) {
                        g.fillRect(c.getY() * TAILLE_CASE ,c.getX() * TAILLE_CASE + diffTailleCase,  TAILLE_CASE, EPAISSEUR_MUR);
                    }
                }
            }


        }
    }
}
