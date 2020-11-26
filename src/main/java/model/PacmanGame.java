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
import model.pastille.Pastille;
import model.pastille.ScorePastille;
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

  public PacmanGameState getGameState() {
    return gameState;
  }

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

  public ArrayList<Monster> getMonstres() {
    return monstres;
  }

  private final ArrayList<Projectile> projectiles = new ArrayList<>();

  /**
   * Score
   */
  private int score;

  public int getLevel() {
    return level;
  }

  private int level;

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
    this.changeLevel(); // Generate the maze, the coins and the monsters
    this.justChanged = false;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

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
      if (commande != Cmd.IDLE && commande != Cmd.SHOOT)
        playerEvolving.setCurrentMoveDirection(Direction.valueOf(commande.name()));
    }
  }

  public void evolveTheWorld() {
    player.go();

    if(Util.player>1)
      secondPlayer.go();

    if (allPastillesEaten()) {
      gameState.setState(PacmanGameState.EtatJeu.VICTOIRE);
    } else if (willPlayerCollideMob() || gameTimer.isOver()) {
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

  public ArrayList<Projectile> getProjectiles() {
    return projectiles;
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

    if (level <= 1) {
      difficulty = Difficulty.EASY;
    } else if (level <= 3) {
        difficulty = Difficulty.MEDIUM;
    } else {
        difficulty = Difficulty.HARD;
    }


    this.monstres = new ArrayList<>();
    this.generateMonster(difficulty.getNbMonstreStatic(), difficulty.getNbMonstreRandom(), difficulty.getNbMonstreFollow());

    this.generatePastille(difficulty.getPastilleAmount());


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

  private void generateMonster(int amountStatic, int amountRandom, int amountFollow) {
    List<GhostType> ghostTypes = Arrays.asList(GhostType.values());
    Stack<Case> spawnable = labyrinthe.getSpawnableCase();
    int cptType = 0;

    for (int i = 0; i < amountRandom; i++) {
      Case selected = spawnable.pop();
      Monster monstre = new Monster(this, selected.getX(), selected.getY(), ghostTypes.get(cptType++ % 4));
      monstre.setMovementStrategy(new RandomMovementStrategy(monstre, this));

      selected.setMonster(monstre);
      monstres.add(monstre);
    }

    for (int i = 0; i < amountFollow; i++) {
      Case selected = spawnable.pop();
      Monster monstre = new Monster(this, selected.getX(), selected.getY(), ghostTypes.get(cptType++ % 4));
      monstre.setMovementStrategy(new FollowMovementStrategy(monstre, this));
      monstres.add(monstre);
      selected.setMonster(monstre);
    }

    for (int i = 0; i < amountStatic; i++) {
      Case selected = spawnable.pop();
      Monster monstre = new Monster(this, selected.getX(), selected.getY(), ghostTypes.get(cptType++ % 4));
      monstre.setMovementStrategy(new StaticMovementStrategy());
      monstres.add(monstre);
      selected.setMonster(monstre);
    }
  }

  private void generatePastille(int entities) {
    Case[][] cases = labyrinthe.getLabyrinthe();
    int nbCasesDisponibles = labyrinthe.getNbCasesLibres();
    if (nbCasesDisponibles < entities) {
      System.err.println("ERREUR : Impossible de mettre  les pastilles  dans un labyrinthe possédant " + nbCasesDisponibles + " cases libres !");
      return;
    }

    for (int i = 0; i < entities; i++) {
        int x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
          int y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
          while (cases[x][y].estUnMur() || cases[x][y].hasEntity() ||
                  ( x == Util.MAZE_SIZE / 2 && y ==Util.MAZE_SIZE / 2- 1) // No coin on the monster output
          ) {
            x = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
            y = RandomGenerator.getRandomValue(Util.MAZE_SIZE - 1);
          }
          Pastille p = new ScorePastille(x, y);
          cases[x][y].setPastille(p);
          this.labyrinthe.addPastille();

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

  public void setJustChanged(boolean justChanged) {
    this.justChanged = justChanged;
  }
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

    if (currentCase.hasPastille()) {
      Pastille p = currentCase.getPastille();
      p.setRamassee(true);
      score += p.getValue();
      currentCase.destroyPastille();
      this.labyrinthe.removePastille();
    }
  }

  public boolean willPlayerCollide() {
    Player playerCheckCollide;

    if(playerTurn==1)
      playerCheckCollide=player;
    else
      playerCheckCollide=secondPlayer;

    int x = playerCheckCollide.getX() + playerCheckCollide.getCurrentMoveDirection().getX_dir();
    int y = playerCheckCollide.getY() + playerCheckCollide.getCurrentMoveDirection().getY_dir();

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
