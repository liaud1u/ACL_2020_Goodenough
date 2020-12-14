package model.weapons;

public interface StaticWeapon {

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
     * Destroy the projectile (for animation...)
     */
    void destroy();

    /**
     * Return true if projectile is alive
     * @return boolean true if alive, false else
     */
    boolean isAlive();
}
