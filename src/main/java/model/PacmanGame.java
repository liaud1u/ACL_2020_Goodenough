package model;

import fxengine.Cmd;
import fxengine.Game;
import fxengine.GameTimer;
import model.player.Direction;
import model.player.Player;
import model.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * <p>
 * Version avec personnage qui peut se deplacer. A completer dans les
 * versions suivantes.
 */
public class PacmanGame implements Game {
	private GameTimer gameTimer = new GameTimer(Util.timer);

	public GameTimer getGameTimer() {
		return gameTimer;
	}

	/**
	 * Joueur principal
	 */
	private final Player player;

	/**
	 * Labyrinthe principal
	 */
	private final Labyrinthe labyrinthe;

	/**
	 * Tableau des pastilles
	 */
	private final Pastille[][] tabPastille;

	/**
	 * Liste des pastilles restantes
	 */
	private final ArrayList<Pastille> pastilles;

	/**
	 * Score
	 */
	private int score;


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


		labyrinthe = new Labyrinthe(Util.MAZE_SIZE, Util.MAZE_SIZE);

		player = new Player(this);

		tabPastille = new Pastille[Util.MAZE_SIZE-1][Util.MAZE_SIZE-1];

		for (int i = 0; i < tabPastille.length; i++) {
			for (int j = 0; j < tabPastille.length; j++) {
				tabPastille[i][j] = new ScorePastille(i, j);
			}
		}

		pastilles = new ArrayList<>();

		for (int i = 0; i < tabPastille.length; i++) {
			for (int j = 0; j < tabPastille.length; j++) {
				pastilles.add(tabPastille[i][j]);
			}
		}

		score = 0;
		this.gameTimer.play();
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

	/**
	 * Renvoie le joueur
	 *
	 * @return Player joueur principal
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Renvoie le labyrinthe
	 *
	 * @return Labyrinthe labyrinthe principal
	 */
	public Labyrinthe getLabyrinthe() {
		return labyrinthe;
	}

	/**
	 * Tableau de pastille du jeu
	 *
	 * @return Tableau de pastille
	 */
	public Pastille[][] getPastille() {
		return tabPastille;
	}

	/**
	 * Détermine si le joueur vas manger une pastille
	 */
	public void willPlayerEatPastille() {

		double x = player.getX();
		double y = player.getY();


		ArrayList<Pastille> toRemove = new ArrayList<>();

		for (Pastille p : pastilles) {
			double dx = x - p.getX();
			double dy = y - p.getY();


			//On calcule la distance entre chaque pastille et le joueur principal
			double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

			//Si les deux objets se touchent, alors la pastille est mangée
		 	if (distance < (Util.slotSizeProperty.get() * Util.RATIO_PERSONNAGE)*0.6) {
				if (!p.isRamassee()) {
					p.ramasser();
					score+=p.getValue();
					toRemove.add(p);
				}
			}

		}

		pastilles.removeAll(toRemove);

		if (pastilles.size() == 0)
			System.out.println("VICTOIRE");
	}

	/**
	 * Détermine si le joueur va rentrer en contact avec un mur
	 *
	 * @return true si contact avec un mur, false sinon
	 */
	public boolean willPlayerCollide() {
		double x, y;

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

	/**
	 * Getter de la valeur du score
	 * @return int score
	 */
	public int getScore(){
		return score;
	}
}
