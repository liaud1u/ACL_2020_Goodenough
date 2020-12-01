package model;

import fxengine.Cmd;
import fxengine.Game;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.GhostType;
import model.monster.Monster;
import model.monster.MonsterState;
import model.movementstrategy.FollowMovementStrategy;
import model.movementstrategy.RandomMovementStrategy;
import model.movementstrategy.StaticMovementStrategy;
import model.pastille.*;
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
   * Last second where the player has summon projectile (for the cooldown)
   */
  private int lastSecondSummoningProjectileP1 = -1;

  /**
   * Last second where the player has summon projectile (for the cooldown)
   */
  private int lastSecondSummoningProjectileP2 = -1;

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

  public void pauseAllInstances() {
    this.gameTimer.pause();
    this.player.pauseTimer();
  }

  public void replayAllInstances() {
    this.gameTimer.play();
    this.player.restartTimer();
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
        summonFireball();
    } else {
      if (commande != Cmd.IDLE ) {
        playerEvolving.setCurrentMoveDirection(Direction.valueOf(commande.name()));
      }
    }
  }

  public int getAmmos() {
    return ammos;
  }

  /**
   * Update all the world
   */
  public void evolveTheWorld() {
    player.go();

    if(Util.player>1)
      secondPlayer.go();


    ArrayList<Projectile> toRemove = new ArrayList<>();

    for (Projectile p : projectiles) {
        p.evolve(labyrinthe);

        if(!p.isAlive()) {
          toRemove.add(p);

          if(p.hasDestroyMonster())
            score += 500;
        }
    }

    for (Projectile p : toRemove)
      projectiles.remove(p);

    boolean willHitMob;

    setPlayerTurn(1);
    willHitMob=willPlayerCollideMob();

    if(Util.player>1) {
      setPlayerTurn(2);
      willHitMob=willHitMob||willPlayerCollideMob();
    }


    if (allPastillesEaten()) {
      gameState.setState(PacmanGameState.EtatJeu.VICTOIRE);
    } else if (willHitMob || gameTimer.isOver()) {
      gameState.setState(PacmanGameState.EtatJeu.PERDU);
    } else {
      for (Monster monstre : monstres) {
        monstre.actionMovement();
        if(player.isInvincible() || secondPlayer.isInvincible()) {


          if(player.getTimerInvincible().toSeconds()>Util.INVINCIBLE_TIME-4) {
            monstre.setFear((int) ((player.getTimerInvincible().toSeconds()) % 2));
          }
          else {
            monstre.setFear(0);
          }
        } else {
          monstre.removeFear();
        }
      }

    }
  }


  /**
   * Create fireball ahead of the current player
   */
  public void summonFireball() {
    Player playerSummoning;

    if(ammos>0) {

      if (playerTurn == 1) {
        playerSummoning = player;
        //If player has shoot a fireball in the previous second, cancel
        if (lastSecondSummoningProjectileP1 == gameTimer.getCurrentTimer())
          return;
        lastSecondSummoningProjectileP1 = gameTimer.getCurrentTimer();
      } else {
        playerSummoning = secondPlayer;
        //If player has shoot a fireball in the previous second, cancel
        if (lastSecondSummoningProjectileP2 == gameTimer.getCurrentTimer())
          return;
        lastSecondSummoningProjectileP2 = gameTimer.getCurrentTimer();
      }

      Direction dir = playerSummoning.getCurrentMoveDirection();

      if (dir == Direction.IDLE)
        dir = Direction.DOWN;

      if (!labyrinthe.getCaseLabyrinthe(playerSummoning.getX() + dir.getX_dir(), playerSummoning.getY() + dir.getY_dir()).estUnMur()) {
        Fireball fireball = new Fireball(dir, playerSummoning.getX() + dir.getX_dir(), playerSummoning.getY() + dir.getY_dir());
        projectiles.add(fireball);

        fireball.setxPrec(playerSummoning.getX());
        fireball.setyPrec(playerSummoning.getY());

        ammos--;
      }
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
      this.ammos = 1;
    }

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


    this.generateAllPastilles(difficulty.getScorePastilleAmount(), difficulty.getTimePastilleAmount(), difficulty.getAmmosPastilleAmount(), difficulty.getInvincibilityPastilleAmount());


    this.gameTimer.setCurrentTimer(difficulty.getTime());
    resetTimer();
    replayAllInstances();

    player.spawn();
    secondPlayer.spawn();

    lastSecondSummoningProjectileP1 = lastSecondSummoningProjectileP2 = -1;

    ArrayList<Projectile> toRemove = new ArrayList<>();

    for (Projectile p : projectiles) {
      p.destroy();
      toRemove.add(p);
    }

    projectiles.removeAll(toRemove);

    this.justChanged = true;


    gameState.setState(PacmanGameState.EtatJeu.EN_COURS);
    this.gameTimer.play();

  }

  /**
   * Generate monster in the maze
   * @param amountStatic Amount of static monster
   * @param amountRandom Amount of random monster
   * @param amountFollow Amount of following monster
   */
  private void generateMonster(int amountStatic, int amountRandom, int amountFollow) {
    List<GhostType> ghostTypes = Arrays.asList(GhostType.values());
    Stack<Case> spawnable = (Stack<Case>) labyrinthe.getSpawnableCase().clone();
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
   * Generate all coins for the maze
   * This method checks if there is enough space to place the coins in the maze and if
   * there is enough space calls another method to place each type of coin
   *
   * @param amountScore amount of score coins (increases when level becomes harder)
   * @param amountTime amount of time coins (decreases when level becomes harder)
   * @param amountAmmo amount of munitions coins (decreases when level becomes harder)
   * @param amountInvincibility amount of invicibility coins (decreases when level becomes harder)
   *
   */
  private void generateAllPastilles(int amountScore, int amountTime, int amountAmmo, int amountInvincibility) {

    int nbCasesDisponibles = labyrinthe.getNbCasesLibres();
    if (nbCasesDisponibles < (amountScore+amountTime+amountAmmo)) {
      System.err.println("ERREUR : Impossible de mettre les pastilles  dans un labyrinthe possédant " + nbCasesDisponibles + " cases libres !");
    } else {
      this.generatePastille(PastilleType.SCORE, amountScore);
      this.generatePastille(PastilleType.AMMO, amountAmmo);
      this.generatePastille(PastilleType.TIME, amountTime);
      this.generatePastille(PastilleType.INVINCIBILITY, amountInvincibility);
    }
  }

  /**
   * Method to place the coins in the maze
   * @param type type of coins to place
   * @param amount amount of coins to place
   */
  private void generatePastille(PastilleType type, int amount){
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
          cases[x][y].addPastille(new AmmoPastille(this, type));
        break;
        case TIME:
          cases[x][y].addPastille(new TimePastille(this, type,10));
        break;
        case INVINCIBILITY:
          cases[x][y].addPastille(new InvinciblePastille(this,type));
          break;
        case SCORE:
        default:
          cases[x][y].addPastille(new ScorePastille(this, type, 100));
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
        score += 500;
        return false;
      }
      return monster.getLifeState() == MonsterState.ALIVE;
    }
    return false;
  }


  /**
   * Method used to add ammos to both players called by the coin when eaten
   */
  public void addAmmos() {
    if(ammos < Util.MAX_AMMOS) ammos++;
  }

  public boolean hasMaximumAmmos() {
    return this.ammos == Util.MAX_AMMOS;
  }

  /**
   * Method used to add score to the game called by the coin when eaten
   * @param score amount of score given
   */
  public void addScore(int score) {
    if(score >= 0) this.score += score;
    labyrinthe.removePastille();
  }

  /**
   * Method used to add time to the game called by the coin when eaten
   * @param time amount of time added
   */
  public void addTime(int time) {
    if(time >= 0) this.gameTimer.addTime(time);
  }

  /**
   * Methode used to make all the players invincibles by the coin when eaten
   */
  public void setPlayerInvincible() {
    player.setInvincible();
    secondPlayer.setInvincible();
  }

  /**
   * Détermine si le joueur vas manger une pastille
   */
  public void isEatingAPastille() {

    Player playerEat;

    if(playerTurn==1)
      playerEat=player;
    else
      playerEat=secondPlayer;

    final Case currentCase = this.labyrinthe.getCaseLabyrinthe(playerEat.getX(), playerEat.getY());
    currentCase.removePastille();
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
