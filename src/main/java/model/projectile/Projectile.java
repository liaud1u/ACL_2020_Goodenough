package model.projectile;

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
}
