package model;

import fxengine.Cmd;
import fxengine.Game;
import model.monster.GhostType;
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
  private final PacmanGameState gameState;

  /**
   * Timer du jeu
   */
  private final GameTimer gameTimer = new GameTimer(Util.timer);

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
  private ArrayList<Monstre> monstres = new ArrayList<>();





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
    this.changeLevel(); // Generate the maze, the coins and the monsters
    this.gameState  = new PacmanGameState();
    this.player = new Player(this);
    this.score = 0;
    this.gameTimer.play();
  }

  /**
   * faire evoluer le jeu suite a une commande
   *
   * @param commande
   */
  public void evolve(Cmd commande) {
    //System.out.println("Execute "+commande);

    if (this.gameTimer.isOver()) this.gameState.setState(PacmanGameState.EtatJeu.PERDU);

    if (commande != Cmd.IDLE)
      player.setCurrentMoveDirection(Direction.valueOf(commande.name()));

    player.go();
    if (allPastillesEaten()) {
      this.changeLevel();
      gameState.setState(PacmanGameState.EtatJeu.VICTOIRE);
    } else if (willPlayerCollideMob() || gameTimer.isOver()) {
      this.changeLevel();
      gameTimer.reset();
      gameState.setState(PacmanGameState.EtatJeu.PERDU);
    } else {
      gameState.setState(PacmanGameState.EtatJeu.EN_COURS);

      for (Monstre monstre : monstres) {
        monstre.actionMovement();
      }
    }
  }



  public void pauseTimer() {
    gameTimer.pause();
  }

  public void restartTimer() {
    gameTimer.play();
  }
  public void resetTimer() {
    gameTimer.reset();
  }
  public void resetScore() {
    score = 0;
  }
  private void changeLevel() {
    labyrinthe = new Labyrinthe(Util.MAZE_SIZE, Util.MAZE_SIZE);

    for (Monstre m : monstres)
      m.destroy();

    this.monstres = new ArrayList<>();
    this.generateEntity(3, false);
    this.generateEntity(3, true);
  }

  private void generateEntity(int entities, boolean areTheyMonsters) {
    Case[][] cases = labyrinthe.getLabyrinthe();
    int nbCasesDisponibles = labyrinthe.getNbCasesLibres();

    if(nbCasesDisponibles < entities) {
      System.err.println("ERREUR : Impossible de mettre " + entities + ((areTheyMonsters) ? " monstres" : " pastilles") + " dans un labyrinthe possédant " + nbCasesDisponibles + " cases libres !");
      return;
    }

    for(int i = 0 ; i < entities ; i ++) {
        if ((areTheyMonsters)) {

          Case spawn = (Case) labyrinthe.getSpawnableCase().toArray()[i % 3];
          cases[spawn.getX()][spawn.getY()].setMonster(true);
          monstres.add(new Monstre(this, spawn.getX(), spawn.getY(), GhostType.values()[RandomGenerator.getRandomValue(GhostType.values().length)]));
        }
        else {
          int x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
          int y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
          while (cases[x][y].estUnMur() || cases[x][y].hasEntity()) {
            x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
            y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
          }
          Pastille p = new ScorePastille(x, y);
          cases[x][y].setPastille(p);
          this.labyrinthe.addPastille();
        }
    }
  }

  /**
   * Méthode permettant de savoir si l'ensemble des pastilles ont été récupérées
   * par le joueur (le permettant de passer au niveau suivant)
   *
   * @return booleen vrai si toutes les pastilles ont été récupérées
   */
  public boolean allPastillesEaten() {
    return labyrinthe.getLeftPastilles() == 0;
  }

  /**
   * verifier si le jeu est fini
   */
  public boolean isGameOver() {
    // le jeu n'est jamais fini
    return false;
  }

  /**
   * Méthode retournant vrai si le joueur a gagné le niveau actuel
   * @return booleen representant la victoire
   */
  public boolean isWon() {
    return gameState.getState() == PacmanGameState.EtatJeu.VICTOIRE;
  }

  /**
   * Méthode retournant vrai si le joueur a perdu le niveau actuel
   * @return booleen representant la defaite
   */
  public boolean isLost() {
    return gameState.getState() == PacmanGameState.EtatJeu.PERDU;
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
   * Détermine si le joueur va entrer en collision avec un monstre
   */
  public boolean willPlayerCollideMob() {
    int x = this.player.getX() + player.getCurrentMoveDirection().getX_dir();
    int y = this.player.getY() + player.getCurrentMoveDirection().getY_dir();

    return this.labyrinthe.getCaseLabyrinthe(x, y).hasMonster();
  }

  /**
   * Détermine si le joueur vas manger une pastille
   */
  public void isEatingAPastaga() {
    final Case currentCase = this.labyrinthe.getCaseLabyrinthe(this.player.getX(), this.player.getY());

    if (currentCase.hasPastille()) {
      Pastille p = currentCase.getPastille();
      p.setRamassee(true);
      score += p.getValue();
      currentCase.destroyPastille();
      this.labyrinthe.removePastille();
    }
  }

  public boolean willPlayerCollide() {
    int x = this.player.getX() + player.getCurrentMoveDirection().getX_dir();
    int y = this.player.getY() + player.getCurrentMoveDirection().getY_dir();

    return this.willPlayerCollideWall(x, y);
  }

  /**
   * @param posX (:int), the x position to check
   * @param posY (:int), the y position to check
   * */
  private boolean willPlayerCollideWall(int posX, int posY) {
    return this.labyrinthe.getCaseLabyrinthe(posX, posY).estUnMur();
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
