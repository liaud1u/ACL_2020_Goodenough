package model.weapons;

public interface StaticWeapon {

    /**
     * Return the x coord of the static weapon
     *
     * @return int x
     */
    int getX();

    /**
     * Return the y coord of the static weapon
     *
     * @return int y
     */
    int getY();

    /**
     * Destroy the static weapon
     */
    void destroy();

    /**
     * Returns true if static weapon is still not triggered
     * @return boolean true if not triggered, false otherwise
     */
    boolean isTriggered();

    /**
     * Setter for the triggered state of the static weapon
     * @param triggered representing new triggered state
     */
    void setTriggered(boolean triggered);

    /**
     * setter for type of the static weapon
     * @param type typeof the static weapon
     */
    void setType(StaticWeaponType type);

    /**
     * getter for the type of the static weapon
     * @return StaticWeaponType type of the static weapon
     */
    StaticWeaponType getType();

    /**
     * getter for the boolean representing if the animation for the explosion has occured
     * @return boolean representing if the animation for the explosion has occured
     */
    boolean hasTriggeredAnimation();

    /**
     * setter for the boolean representing if the animation for the explosion has occured
     * @param triggeredAnimation boolean representing if the animation for the explosion has occured
     */
    void setTriggeredAnimation(boolean triggeredAnimation);
}
