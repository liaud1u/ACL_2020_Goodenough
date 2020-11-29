package model;

import fxengine.Cmd;
import fxengine.Game;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.GhostType;
import model.monster.Monster;
import model.monster.MonsterState;
import model.monster.movementstrategy.FollowMovementStrategy;
import model.monster.movementstrategy.RandomMovementStrategy;
import model.monster.movementstrategy.StaticMovementStrategy;
import model.pastille.AmmoPastille;
import model.pastille.Pastille;
import model.pastille.ScorePastille;
import model.pastille.TimePastille;
import model.player.Direction;
import model.player.Player;
import model.player.PlayerType;
import model.projectile.Fireball;
import model.projectile.Projectile;
import model.util.RandomGenerator;
import model.util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * <p>
 * Version avec personnage qui peut se deplacer. A completer dans les
 * versions suivantes.
 */
public class PacmanGame implements Game {

  /**
   * Etat du jeu
   */
  private final PacmanGameState gameState;

  /**
   * Booleen de changement de niveau
   */
  private boolean justChanged;

  /**
   * Timer du jeu
   */
  private final GameTimer gameTimer = new GameTimer(Util.timer);

  /**
   * Joueur principal
   */
  private final Player player;


  /**
   * Joueur secondaire
   */
  private Player secondPlayer;

  /**
   * Labyrinthe principal
   */
  private Labyrinthe labyrinthe;

  /**
   * Liste des monstres
   */
  private ArrayList<Monster> monstres = new ArrayList<>();


  /**
   * All projectiles of the game
   */
  private final ArrayList<Projectile> projectiles = new ArrayList<>();

  /**
   * Score
   */
  private int score;

  /**
   * Number of ammos
   */
  private int ammos;

  private boolean hasShooted;

  /**
   * Current level of the game
   */
  private int level;

  /**
   * Current player moving
   */
  private int playerTurn = 1;

  /**
   * constructeur avec fichier source pour le help
   */
  public PacmanGame(String source) {
    level = 0;
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

    this.player = new Player(this, PlayerType.PLAYER1);
    if(Util.player >= 1)
      this.secondPlayer = new Player(this, PlayerType.PLAYER2);

    this.gameState  = new PacmanGameState();
    this.score = 0;

    this.ammos = 1;
    this.changeLevel(); // Generate the maze, the coins and the monsters
    this.justChanged = false;
    this.hasShooted = false;
  }


  /**
   * Getter of the current level of the game
   * @return int level
   */
  public int getLevel() {
    return level;
  }

  /**
   * Gettter of the monster
   * @return List of all monster
   */
  public ArrayList<Monster> getMonstres() {
    return monstres;
  }

  /**
   * Get the current player wich move
   * @return int playermoving
   */
  public int getPlayerTurn() {
    return playerTurn;
  }

  /**
   * Set the current player moving
   * @param playerTurn int player wich is moving
   */
  public void setPlayerTurn(int playerTurn) {
    this.playerTurn = playerTurn;
  }

  /**
   * faire evoluer le jeu suite a une commande
   *
   * @param commande
   */
  public void evolve(Cmd commande) {
    Player playerEvolving;

    if(playerTurn==1)
      playerEvolving=player;
    else
      playerEvolving=secondPlayer;

    if (commande == Cmd.SHOOT) {
      if(ammos > 0) {
        summonFireball();
        if(!hasShooted) {
          ammos--;
        }
      }
      this.hasShooted = true;
    } else {
      if (commande != Cmd.IDLE && commande != Cmd.SHOOT) {
        playerEvolving.setCurrentMoveDirection(Direction.valueOf(commande.name()));
        if(this.hasShooted) {
          this.hasShooted = false;
        }

      }
    }
  }

  /**
   * Update all the world
   */
  public void evolveTheWorld() {
    player.go();

    if(Util.player>1)
      secondPlayer.go();

    if (allPastillesEaten()) {
      gameState.setState(PacmanGameState.EtatJeu.VICTOIRE);
    } else if ( willPlayerCollideMob() || gameTimer.isOver()) {
      gameState.setState(PacmanGameState.EtatJeu.PERDU);
    } else {
      for (Monster monstre : monstres) {
        monstre.actionMovement();
      }

      ArrayList<Projectile> toRemove = new ArrayList<>();

      for (Projectile p : projectiles) {
        if (labyrinthe.getCaseLabyrinthe(p.getX(), p.getY()).getMonstre() != null) {
          p.destroy();
          toRemove.add(p);
          labyrinthe.getCaseLabyrinthe(p.getX(), p.getY()).getMonstre().destroy();
        }

        p.move();

        if (labyrinthe.getCaseLabyrinthe(p.getX(), p.getY()).estUnMur()) {
          p.destroy();
          toRemove.add(p);
        }

        if (labyrinthe.getCaseLabyrinthe(p.getxPrec(), p.getyPrec()).getMonstre() != null) {
          p.destroy();
          toRemove.add(p);
          labyrinthe.getCaseLabyrinthe(p.getxPrec(), p.getyPrec()).getMonstre().destroy();
        }

        if (labyrinthe.getCaseLabyrinthe(p.getX(), p.getY()).getMonstre() != null && !toRemove.contains(p)) {
          p.destroy();
          toRemove.add(p);
          labyrinthe.getCaseLabyrinthe(p.getX(), p.getY()).getMonstre().destroy();
        }
      }

      for (Projectile p : toRemove)
        projectiles.remove(p);
    }
  }

  /**
   * Create fireball ahead of the current player
   */
  public void summonFireball() {
    Player playerSummoning;

    if(playerTurn==1)
      playerSummoning = player;
    else
      playerSummoning=secondPlayer;

    Direction dir = playerSummoning.getCurrentMoveDirection();

    if (dir == Direction.IDLE)
      dir = Direction.DOWN;

    if (!labyrinthe.getCaseLabyrinthe(playerSummoning.getX() + dir.getX_dir(), playerSummoning.getY() + dir.getY_dir()).estUnMur()) {
      Fireball fireball = new Fireball(dir, playerSummoning.getX() + dir.getX_dir(), playerSummoning.getY() + dir.getY_dir());
      projectiles.add(fireball);

      fireball.setxPrec(playerSummoning.getX());
      fireball.setyPrec(playerSummoning.getY());
    }
  }

  /**
   * Getter of all the projectiles of the game
   * @return List of all the projectiles
   */
  public ArrayList<Projectile> getProjectiles() {
    return projectiles;
  }

  /**
   * Restart the timer
   */
  public void restartTimer() {
    gameTimer.play();
  }

  /**
   * Reset the timer
   */
  public void resetTimer() {
    gameTimer.reset();
  }

  /**
   * Change the level when winning or loosing
   */
  public void changeLevel() {
    if(this.isWon()) {
      this.level++;
    } else {
      this.level = 0;
      this.score = 0;
    }
    gameState.setState(PacmanGameState.EtatJeu.EN_COURS);
    this.gameTimer.play();
    labyrinthe = new Labyrinthe(Util.MAZE_SIZE, Util.MAZE_SIZE);
    for (Monster m : monstres)
      m.destroy();

    Difficulty difficulty;

    if (level < 1) {
      difficulty = Difficulty.EASY;
    } else if (level < 2) {
        difficulty = Difficulty.MEDIUM;
    } else {
        difficulty = Difficulty.HARD;
    }


    this.monstres = new ArrayList<>();
    this.generateMonster(difficulty.getNbMonstreStatic(), difficulty.getNbMonstreRandom(), difficulty.getNbMonstreFollow());


    this.generateAllPastilles(difficulty.getScorePastilleAmount(), 5, 5);


    this.gameTimer.setCurrentTimer(difficulty.getTime());
    resetTimer();
    restartTimer();

    player.spawn();
    secondPlayer.spawn();

    this.justChanged = true;

    ArrayList<Projectile> toRemove = new ArrayList<>();

    for (Projectile p : projectiles) {
      p.destroy();
      toRemove.add(p);
    }

    projectiles.removeAll(toRemove);

  }

  /**
   * Generate monster in the maze
   * @param amountStatic Amount of static monster
   * @param amountRandom Amount of random monster
   * @param amountFollow Amount of following monster
   */
  private void generateMonster(int amountStatic, int amountRandom, int amountFollow) {
    List<GhostType> ghostTypes = Arrays.asList(GhostType.values());
    Stack<Case> spawnable = labyrinthe.getSpawnableCase();
    int cptType = 0;

    //We generate monster by priority order, to avoid monster block other monster

    for (int i = 0; i < amountRandom; i++) {
      Case selected = spawnable.pop();
      Monster monstre = new Monster(this, selected.getX(), selected.getY(), ghostTypes.get(cptType++ % 4));
      monstre.setMovementStrategy(new RandomMovementStrategy(monstre, labyrinthe));

      selected.addMonster(monstre);
      monstres.add(monstre);
    }

    for (int i = 0; i < amountFollow; i++) {
      Case selected = spawnable.pop();
      Monster monstre = new Monster(this, selected.getX(), selected.getY(), ghostTypes.get(cptType++ % 4));
      monstre.setMovementStrategy(new FollowMovementStrategy(monstre, labyrinthe,player,secondPlayer));
      monstres.add(monstre);
      selected.addMonster(monstre);
    }

    for (int i = 0; i < amountStatic; i++) {
      Case selected = spawnable.pop();
      Monster monstre = new Monster(this, selected.getX(), selected.getY(), ghostTypes.get(cptType++ % 4));
      monstre.setMovementStrategy(new StaticMovementStrategy());
      monstres.add(monstre);
      selected.addMonster(monstre);
    }
  }

  /**
   * Generate pastille in the maze
   * @param amountScore amount of score pastilles (increases when level becomes harder)
   * @param amountTime amount of time pastilles (decreases when level becomes harder)
   * @param amountAmmo amount of munitions pastilles (decreases when level becomes harder)
   *
   */
  private void generateAllPastilles(int amountScore, int amountTime, int amountAmmo) {

    int nbCasesDisponibles = labyrinthe.getNbCasesLibres();
    if (nbCasesDisponibles < (amountScore+amountTime+amountAmmo)) {
      System.err.println("ERREUR : Impossible de mettre les pastilles  dans un labyrinthe possédant " + nbCasesDisponibles + " cases libres !");
    } else {
      this.generatePastille(Pastille.Type.SCORE, amountScore);
      this.generatePastille(Pastille.Type.AMMO, amountAmmo);
      this.generatePastille(Pastille.Type.TIME, amountTime);
    }
  }

  private void generatePastille(Pastille.Type type, int amount){
    Pastille p = null;
    Case[][] cases = labyrinthe.getLabyrinthe();
    for (int i = 0; i < amount; i++) {
      int x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
      int y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
      while (cases[x][y].estUnMur() || cases[x][y].hasEntity() ||
              ( x == Util.MAZE_SIZE / 2 && y ==Util.MAZE_SIZE / 2- 1) // No coin on the monster output
      ) {
        x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
        y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
      }
      switch(type) {
        case AMMO:
          cases[x][y].addAmmoPastille(new AmmoPastille());
        break;
        case TIME:
          cases[x][y].addTimePastille(new TimePastille(10)); // TODO : added time in difficulty
        break;
        case SCORE:
        default:
          cases[x][y].addScorePastille(new ScorePastille(10)); // TODO : added score in difficulty
          this.labyrinthe.addPastille();
        break;
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
   * Setter to set if the level of the game just changed
   * @param justChanged boolean
   */
  public void setJustChanged(boolean justChanged) {
    this.justChanged = justChanged;
  }

  /**
   * Getter wich return if the game just changed
   * @return boolean, justChanged
   */
  public boolean hasJustChanged() {
    return this.justChanged;
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
   * Getter of the second player
   *
   * @return Secondary player
   */
  public Player getSecondPlayer() {
    return secondPlayer;
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
   * Détermine si le joueur va entrer en collision avec un monstre
   */
  public boolean willPlayerCollideMob() {
    Player playerCheckCollide;

    if(playerTurn==1)
      playerCheckCollide=player;
    else
      playerCheckCollide=secondPlayer;


    if (this.labyrinthe.getCaseLabyrinthe(playerCheckCollide.getX(), playerCheckCollide.getY()).getMonstre() != null) {
      Monster monster = labyrinthe.getCaseLabyrinthe(playerCheckCollide.getX(), playerCheckCollide.getY()).getMonstre();
      if(playerCheckCollide.isInvincible()) {
        monster.destroy();
        return false;
      }
      return monster.getLifeState() == MonsterState.ALIVE;
    }
    return false;
  }

  /**
   * Détermine si le joueur vas manger une pastille
   */
  public void isEatingAPastaga() {

    Player playerEat;

    if(playerTurn==1)
      playerEat=player;
    else
      playerEat=secondPlayer;

    final Case currentCase = this.labyrinthe.getCaseLabyrinthe(playerEat.getX(), playerEat.getY());

    if (currentCase.hasScorePastille()) {
      ScorePastille sp = currentCase.getScorePastille();
      score += sp.getScore();
      this.labyrinthe.removePastille();
    }
    if(currentCase.hasAmmoPastille()) {
      AmmoPastille ap = currentCase.getAmmoPastille();
      if(ammos < Util.MAX_AMMOS) ammos++;
      playerEat.setInvincible();
    }
    if(currentCase.hasTimePastille()) {
      TimePastille tp = currentCase.getTimePastille();
      gameTimer.addTime(tp.getTime());
    }

    currentCase.destroyPastilles();
  }

  /**
   * Check if the player will collide a wall
   * @return true if collide, else false
   */
  public boolean willPlayerCollide() {
    Player playerCheckCollide;

    if(playerTurn==1)
      playerCheckCollide=player;
    else
      playerCheckCollide=secondPlayer;

    int x = playerCheckCollide.getX() + playerCheckCollide.getCurrentMoveDirection().getX_dir();
    int y = playerCheckCollide.getY() + playerCheckCollide.getCurrentMoveDirection().getY_dir();

    return this.willPlayerCollideWall(x, y) ;
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
