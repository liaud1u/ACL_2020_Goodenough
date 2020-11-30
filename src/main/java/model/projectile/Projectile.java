package model.projectile;

import model.labyrinthe.Labyrinthe;

/**
 * Object projectile
 */
public interface Projectile {
    /**
     * Mouvement of the projectile
     */
    void move();

    /**
     * Return true if the projectile is a fireball
     *
     * @return boolean true if the projectile is a fireball, else false
     */
    boolean isFireball();

    /**
     * Return the x coord of the projectile
     *
     * @return int x
     */
    int getX();

    /**
     * Return the y coord of the projectile
     *
     * @return int y
     */
    int getY();

    /**
     * Return the precedent x coord of the projectile
     *
     * @return int x
     */
    int getxPrec();

    /**
     * Return the precedent y coord of the projectile
     *
     * @return int y
     */
    int getyPrec();

    /**
     * Destroy the projectile (for animation...)
     */
    void destroy();

    /**
     * Evolve projectille into the world
     * @param labyrinthe Labyrinthe
     */
    void evolve(Labyrinthe labyrinthe);

    /**
     * Return true if projectile is alive
     * @return boolean true if alive, false else
     */
    boolean isAlive();

    /**
     * Getter to know if fireball has destroy monster or not
     * @return boolean true if has, false if not
     */
    boolean hasDestroyMonster();
}
