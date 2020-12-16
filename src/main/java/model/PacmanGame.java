package model;

import exceptions.InvalidPastilleAmountException;
import exceptions.TooMuchPastillesException;
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
import model.weapons.*;
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
  private final GameTimer gameTimer = new GameTimer(Difficulty.EASY.getTime());

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
   * All static weapons of the game
   */
  private List<StaticWeapon> staticWeapons = new ArrayList<>();


  /**
   * Score
   */
  private int score;

  /**
   * Number of ammos
   */
  private int ammos;

  /**
   * Number of static weapons
   */
  private int staticWeaponsCount;

  /**
   * Boolean if you just dropped a static weapon
   */
  private boolean droppedStaticWeapon;

  /**
   * Boolean if you just shooted
   */
  private boolean shooted;

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
    this.staticWeaponsCount = 1;
    this.changeLevel(); // Generate the maze, the coins and the monsters
    this.justChanged = false;
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
   * Getter for amount of current static weapons
   * @return number of current static weapons
   */
  public int getStaticWeaponsCount() {
    return staticWeaponsCount;
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


    switch(commande) {
      case SHOOT:
        summonFireball();
        shooted = true;
        break;
      case PLACE_WEAPON:
        addStaticWeapon(playerEvolving.getxPrec(), playerEvolving.getyPrec(), StaticWeaponType.LANDMINE);
        droppedStaticWeapon = true;
        break;
      case UP:
      case DOWN:
      case LEFT:
      case RIGHT:
        playerEvolving.setCurrentMoveDirection(Direction.valueOf(commande.name()));
        break;
      default:
        droppedStaticWeapon = false;
        shooted = false;
        break;
    }

  }

  /**
   * Adds a static weapon at the given location
   * If a static weapon already exists at this location, re enable it and update its type
   * @param x coord x for the static weapon
   * @param y coord y for the static weapon
   * @param type type of the static weapon
   */
  private void addStaticWeapon(int x, int y, StaticWeaponType type) {
    if(staticWeaponsCount >= 1 && !droppedStaticWeapon) {
      boolean exists = false;
      for(StaticWeapon sw : staticWeapons) {
        if(sw.getX() == x && sw.getY() == y) {
          sw.setTriggered(true);
          sw.setTriggeredAnimation(false);
          sw.setType(type);
          exists = true;
          staticWeaponsCount--;
          break;
        }
      }
      if(!exists) {
        staticWeapons.add(new Landmine(x,y));
        staticWeaponsCount--;
      }
    }
  }

  public void addWeapon(StaticWeapon weapon){
    staticWeapons.add(weapon);
    staticWeaponsCount++;
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
            score += Util.SCORE_ON_KILL;
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
    } else if (willHitMob || gameTimer.isOver() || isWalkingOnLandmine()) {
      gameState.setState(PacmanGameState.EtatJeu.PERDU);
    } else {
      for (Monster monstre : monstres) {
        monstre.actionMovement();
        if(player.isInvincible() || secondPlayer.isInvincible()) {
          if(player.getTimerInvincible().toSeconds() > Util.INVINCIBLE_TIME-4) {
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
   * used for debug purposes
   * set the current number of ammos given in parameter
   * @param ammos new current number of ammos
   */
  public void setAmmos(int ammos) {
    this.ammos = ammos;
  }

  /**
   * used for debug purposes
   * set the current labyrinthe with given parameter
   * @param labyrinthe new labyrinthe
   */
  public void setLabyrinthe(Labyrinthe labyrinthe) {
    this.labyrinthe = labyrinthe;
  }

  /**
   * get the current static ammos currently in the maze
   * @return list of static weapons
   */
  public List<StaticWeapon> getStaticWeapons() {
    return staticWeapons;
  }

  /**
   * Create fireball ahead of the current player
   */
  public void summonFireball() {
    Player playerSummoning;

    if(ammos>0 && !shooted) {

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

    this.staticWeapons = new ArrayList<>();

    try {
      this.generateAllPastilles(difficulty.getScorePastilleAmount(), difficulty.getTimePastilleAmount(), difficulty.getInvincibilityPastilleAmount(),difficulty.getAmmosPastilleAmount(), difficulty.getAmmosPastilleAmount());
    } catch(InvalidPastilleAmountException | TooMuchPastillesException error) {
      System.err.println(error.getMessage());
      System.exit(-1);
    }


    this.gameTimer.setInitialTimer(difficulty.getTime());

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
   *
   * Generate all coins for the maze
   *
   * @param amountScore amount of score coins (increases when level becomes harder)
   * @param amountTime amount of time coins (decreases when level becomes harder)
   * @param amountAmmo amount of munitions coins (decreases when level becomes harder)
   * @param amountInvincibility amount of invicibility coins (decreases when level becomes harder)
   *
   * @throws TooMuchPastillesException if there isn't enough space in the maze to generate the coins
   * @throws InvalidPastilleAmountException if the number of coins to generate is negative
   */
  private void generateAllPastilles (int amountScore, int amountTime, int amountInvincibility, int amountAmmo, int amountStaticWeapon) throws TooMuchPastillesException, InvalidPastilleAmountException {
      this.generatePastille(PastilleType.SCORE, amountScore);
      this.generatePastille(PastilleType.AMMO, amountAmmo);
      this.generatePastille(PastilleType.TIME, amountTime);
      this.generatePastille(PastilleType.INVINCIBILITY, amountInvincibility);
      this.generatePastille(PastilleType.LANDMINE, amountStaticWeapon);
  }

  /**
   * Method to place the coins in the maze
   *
   * This method should be private and is public for test purposes.
   *
   * @param type type of coins to place
   * @param amount amount of coins to place
   *
   * @throws TooMuchPastillesException if there isn't enough space in the maze to generate the coins
   * @throws InvalidPastilleAmountException if the number of coins to generate is negative
   */
  public void generatePastille(PastilleType type, int amount) throws InvalidPastilleAmountException, TooMuchPastillesException {
    int nbCasesDisponibles = labyrinthe.getNbCasesLibres();
    if(amount <= 0) {
      throw new InvalidPastilleAmountException("ERREUR : Impossible de générer un nombre de pastilles négatif.");
    }
    if(amount > nbCasesDisponibles) {
      throw new TooMuchPastillesException("ERREUR : Impossible de mettre les pastilles  dans un labyrinthe possédant " + nbCasesDisponibles + " cases libres !");
    }
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
          cases[x][y].addPastille(new AmmoPastille(this));
        break;
        case TIME:
          cases[x][y].addPastille(new TimePastille(this, 10));
        break;
        case INVINCIBILITY:
          cases[x][y].addPastille(new InvinciblePastille(this));
          break;
        case LANDMINE:
          cases[x][y].addPastille(new LandminePastille(this));
          break;
        case SCORE:
        default:
          cases[x][y].addPastille(new ScorePastille(this, 50));
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
   * Getter du gamestate
   * @return PacmanGameState
   */
  public PacmanGameState getGameState() {
    return gameState;
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
        score += Util.SCORE_ON_KILL;
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

  /**
   * Method used to add mines to both players
   */
  public void addLandMine() {
    if(staticWeaponsCount < Util.MAX_MINES) staticWeaponsCount++;
  }

  /**
   * Function who returns true when you have the maximum amount possible of ammos
   * @return boolean true when you have the maximum amount possible of ammos
   */
  public boolean hasMaximumAmmos() {
    return this.ammos >= Util.MAX_AMMOS;
  }

  /**
   * Function who returns true when you have the maximum amount possible of landmines
   * @return boolean true when you have the maximum amount possible of landmines
   */
  public boolean hasMaximumLandmines() {
    return this.staticWeaponsCount >= Util.MAX_MINES;
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
   * Function who returns true when the player walks on a static weapon
   * @return boolean true true when the player walks on a static weapon
   */
  public boolean isWalkingOnLandmine() {
    Player playerWalking;

    if (playerTurn == 1)
      playerWalking = player;
    else
      playerWalking = secondPlayer;

    for (StaticWeapon sw : staticWeapons) {
      if(sw.isTriggered()) {
        if (this.labyrinthe.getCaseLabyrinthe(sw.getX(), sw.getY()).getMonstre() != null) {
          Monster monster = labyrinthe.getCaseLabyrinthe(sw.getX(), sw.getY()).getMonstre();
          sw.destroy();
          monster.destroy();
          score += Util.SCORE_ON_KILL;
        }
        if(playerWalking.getX() == sw.getX() && playerWalking.getY() == sw.getY()) {
          sw.destroy();
          return true;
        }
      }
    }
    return false;
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

    boolean collideSpawnable;

    Case cases = labyrinthe.getCaseLabyrinthe(x,y);

    collideSpawnable= labyrinthe.getSpawnableCase().contains(cases);

    return this.willPlayerCollideWall(x, y) || collideSpawnable;
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
