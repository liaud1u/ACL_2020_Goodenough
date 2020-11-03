package model;

import fxengine.Cmd;
import fxengine.Game;
import model.player.Direction;
import model.player.Player;
import model.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * <p>
 * Version avec personnage qui peut se deplacer. A completer dans les
 * versions suivantes.
 */
public class PacmanGame implements Game {

	private final Player player;
	private final Labyrinthe labyrinthe;
	private final Pastille[][] tabPastille;

	/**
	 * constructeur avec fichier source pour le help
	 */
	public PacmanGame(String source) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();

		} catch (IOException e) {
			System.out.println("Help not available");
		}


		labyrinthe = new Labyrinthe(Util.MAZE_SIZE, true);
		labyrinthe.genererLabyrinthe();

		player = new Player(this);

		tabPastille = new Pastille[labyrinthe.getTaille()][labyrinthe.getTaille()];

		for (int i = 0; i < tabPastille.length; i++) {
			for (int j = 0; j < tabPastille.length; j++) {
				tabPastille[i][j] = new ScorePastille();
			}

		}

	}

	/**
	 * faire evoluer le jeu suite a une commande
	 *
	 * @param commande
	 */
	public void evolve(Cmd commande) {

		//System.out.println("Execute "+commande);
		if (commande != Cmd.IDLE)
			player.setCurrentMoveDirection(Direction.valueOf(commande.name()));
		player.go();
	}

	/**
	 * verifier si le jeu est fini
	 */
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

	public Player getPlayer() {
		return player;
	}


	public Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}

	public Pastille[][] getPastille() {
		return tabPastille;
	}

	public boolean willPlayerCollide() {
		int x, y;

		x = player.getX();
		y = player.getY();


		x += player.getCurrentMoveDirection().getX_dir();
		y += player.getCurrentMoveDirection().getY_dir();

		
		//System.out.println(x+" " +y);

		//En cas de dépassement des bords
		return x >= Util.MAZE_SIZE * Util.slotSizeProperty.get() - Util.slotSizeProperty.get()/2 + Util.wallSizeProperty.get()/2
				|| y >= Util.MAZE_SIZE * Util.slotSizeProperty.get() - Util.slotSizeProperty.get()/2 + Util.wallSizeProperty.get()/2
				|| y < Util.slotSizeProperty.get()/2 - Util.wallSizeProperty.get()/2
				|| x < Util.slotSizeProperty.get()/2 - Util.wallSizeProperty.get()/2 ;


		/**
		 Direction currentDir = player.getCurrentMoveDirection();

		 int currentCaseX = (x ) / 32 ;
		 int currentCaseY = (y ) / 32 ;

		 Case current  = getLabyrinthe().getPlateau()[currentCaseY][currentCaseX];


		 System.out.println(currentCaseX+" "+currentCaseY+" "+current.isMurNord());


		 Rectangle player = new Rectangle(getPlayer().getX()-10,getPlayer().getY()-10,20,20);

		 Rectangle haut = new  Rectangle(currentCaseY * 32,currentCaseX * 32,  32, 4);


		 System.out.println(player.toRectBounds().intersects(haut.toRectBounds()));
		**/
	}
}
