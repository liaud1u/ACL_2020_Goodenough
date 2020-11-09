package model;

import fxengine.Cmd;
import fxengine.Game;
import fxengine.GameTimer;
import model.monster.Monstre;
import model.player.Direction;
import model.player.Player;
import model.util.RandomGenerator;
import model.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * <p>
 * Version avec personnage qui peut se deplacer. A completer dans les
 * versions suivantes.
 */
public class PacmanGame implements Game {


	public PacmanGameState getGameState() {
		return gameState;
	}

	/**
	 * Etat du jeu
	 */
	private PacmanGameState gameState;

	/**
	 * Timer du jeu
	 */
	private GameTimer gameTimer = new GameTimer(Util.timer);

	/**
	 * Joueur principal
	 */
	private final Player player;

	/**
	 * Labyrinthe principal
	 */
	private Labyrinthe labyrinthe;

	public ArrayList<Monstre> getMonstres() {
		return monstres;
	}

	/**
	 * Liste des monstres
	 */
	private ArrayList<Monstre> monstres;



	/**
	 * Liste des pastilles restantes
	 */
	private ArrayList<Pastille> pastilles;

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

		this.gameState  = new PacmanGameState();

		//changed = false;

		labyrinthe = new Labyrinthe(Util.MAZE_SIZE, Util.MAZE_SIZE);

		player = new Player(this);

		// TODO : when difficulty implemented, change hardcoded values here
		this.generatePastilles(5);
		this.generateMonstres(3);

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


		if(allPastillesEaten()) {
			gameState.setState(PacmanGameState.EtatJeu.VICTOIRE);
			labyrinthe = new Labyrinthe(Util.MAZE_SIZE, Util.MAZE_SIZE);
			// TODO : when difficulty implemented, change hardcoded values here
			generatePastilles(5);
			generateMonstres(3);
		} else if(isPlayerAttacked()){
			System.out.println("ATTACKED !");
			gameState.setState(PacmanGameState.EtatJeu.PERDU);
		} else {
			gameState.setState(PacmanGameState.EtatJeu.EN_COURS);
		}
	}


	/**
	 * Méthode permettant de générer des monstres
	 * @param nbMonstres nombre de monstres à générer
	 */
	private void generateMonstres(int nbMonstres) {
		monstres = new ArrayList<>();
		Case[][] cases = labyrinthe.getLabyrintheVUE();
		int nbCasesDisponibles = labyrinthe.getNbCasesLibres();
		if(nbCasesDisponibles < nbMonstres) {
			System.err.println("ERREUR : Impossible de mettre " + nbMonstres + " monstres dans un labyrinthe possédant " + nbCasesDisponibles + " cases libres !");
			return;
		}
		for(int i = 0 ; i < nbMonstres ; i ++) {
			int x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
			int y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
			if(!cases[x][y].estUnMur() && !cases[x][y].possedeEntite()) {
				cases[x][y].setPossedeEntite(true);
				monstres.add(new Monstre(this,x,y));
				i++;
			}
			i--;
		}
	}

	/**
	 * Méthode permettant de générer des pastilles
	 * @param nbPastilles nombre de pastilles à générer
	 */
	private void generatePastilles(int nbPastilles) {
		pastilles = new ArrayList<>();
		Case[][] cases = labyrinthe.getLabyrintheVUE();
		int nbCasesDisponibles = labyrinthe.getNbCasesLibres();
		if(nbCasesDisponibles < nbPastilles) {
			System.err.println("ERREUR : Impossible de mettre " + nbPastilles + " pastilles dans un labyrinthe possédant " + nbCasesDisponibles + " cases libres !");
			return;
		}
		for(int i = 0 ; i < nbPastilles ; i ++) {
			int x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
			int y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
				if(!cases[x][y].estUnMur()
				&& !cases[x][y].possedeEntite()) {
					cases[x][y].setPossedeEntite(true);
					pastilles.add(new ScorePastille(x,y));
					i++;
				}

			i--;
		}
	}


	/**
	 * Méthode permettant de savoir si l'ensemble des pastilles ont été récupérées
	 * par le joueur (le permettant de passer au niveau suivant)
	 *
	 * @return booleen vrai si toutes les pastilles ont été récupérées
	 */
	public boolean allPastillesEaten() {
		if(pastilles.size() == 0) return false;
		for(Pastille p : pastilles) {
			if(!p.isRamassee()) return false;
		}
		return true;
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
	public List<Pastille> getPastille() {
		return pastilles;
	}


	/**
	 * Détermine si le joueur est assez proche d'un monstre pour être attaqué
	 */
	public boolean isPlayerAttacked() {
		boolean attacked = false;
		for(Monstre m : monstres) {
			double dx = player.getX() - m.getPosX();
			double dy = player.getY() - m.getPosY();
			double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
			if(distance < (Util.slotSizeProperty.get() * Util.RATIO_PERSONNAGE)*0.6) {
				attacked = true;
				break;
			}
		}
		return attacked;
	}



	/**
	 * Détermine si le joueur va entrer en collision avec un monstre
	 * TODO : INFO this method is in WIP
	 */
	public boolean willPlayerCollideMob() {
		double x, y;

		x = player.getX();
		y = player.getY();


		x += player.getCurrentMoveDirection().getX_dir();
		y += player.getCurrentMoveDirection().getY_dir();

		for(Monstre m : monstres) {
			if (x == m.getPosX() && y == m.getPosY()) {
				return true;
			}
		}
		return false;

	}


	/**
	 * Détermine si le joueur vas manger une pastille
	 */
	public void willPlayerEatPastille() {

		double x = player.getX();
		double y = player.getY();


		for (Pastille p : pastilles) {
			double dx = x - p.getPosX();
			double dy = y - p.getPosY();


			//On calcule la distance entre chaque pastille et le joueur principal
			double distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

			//Si les deux objets se touchent, alors la pastille est mangée
		 	if (distance < (Util.slotSizeProperty.get() * Util.RATIO_PERSONNAGE)) {
				if (!p.isRamassee()) {
					p.ramasser();
					score+=p.getValue();
				}
			}

		}

	}

	/**
	 * Détermine si le joueur va rentrer en contact avec un mur
	 *
	 * @return true si contact avec un mur, false sinon
	 */
	public boolean willPlayerCollideWall() {
		double x, y;

		x = player.getX();
		y = player.getY();

		x += player.getCurrentMoveDirection().getX_dir();
		y += player.getCurrentMoveDirection().getY_dir();


		final double rightAndBottomBorder =
		  Util.slotSizeProperty
			.multiply(Util.MAZE_SIZE)
			  .subtract(Util.slotSizeProperty.divide(2))
				.add(Util.wallSizeProperty.divide(2))
				  .get();

		final double leftAndTopBorder =
		  Util.slotSizeProperty
			.divide(2)
			  .subtract(Util.wallSizeProperty.divide(2))
				.get();

		return
		  x >= rightAndBottomBorder || x < leftAndTopBorder ||
		  y >= rightAndBottomBorder || y < leftAndTopBorder;



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

  /**
   * Getter du timer du jeu
   * @return GameTime timer
   */
  public GameTimer getGameTimer() {
    return gameTimer;
  }
}
