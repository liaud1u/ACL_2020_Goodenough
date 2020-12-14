package model.weapons;

public class Landmine implements StaticWeapon{
    private int x;
    private int y;
    private StaticWeaponType type;
    private boolean triggered;
    private boolean triggeredAnimation;

    /**
     * Constructor of a landmine
     * @param x x coord of the bomb
     * @param y y coord of the bomb
     */
    public Landmine(int x, int y) {
        this.x = x;
        this.y = y;
        this.triggered = true;
        this.type = StaticWeaponType.LANDMINE;
        this.triggeredAnimation = false;
    }

    /**
     * Return the x coord of the static weapon
     *
     * @return int x
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Return the y coord of the static weapon
     *
     * @return int y
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Destroy the static weapon
     */
    @Override
    public void destroy() {
        triggered = false;
    }

    /**
     * Returns true if static weapon is still not triggered
     * @return boolean true if not triggered, false otherwise
     */
    @Override
    public boolean isTriggered() {
        return triggered;
    }

    /**
     * Setter for the triggered state of the static weapon
     * @param triggered representing new triggered state
     */
    @Override
    public void setTriggered(boolean alive) {
        this.triggered = alive;
    }

    /**
     * setter for type of the static weapon
     * @param type typeof the static weapon
     */
    @Override
    public void setType(StaticWeaponType type) {
        this.type = type;
    }

    /**
     * getter for the type of the static weapon
     * @return StaticWeaponType type of the static weapon
     */
    @Override
    public StaticWeaponType getType() {
        return this.type;
    }

    /**
     * getter for the boolean representing if the animation for the explosion has occured
     * @return boolean representing if the animation for the explosion has occured
     */
    @Override
    public boolean hasTriggeredAnimation() {
        return false;
    }

    /**
     * setter for the boolean representing if the animation for the explosion has occured
     * @param triggeredAnimation boolean representing if the animation for the explosion has occured
     */
    @Override
    public void setTriggeredAnimation(boolean triggeredAnimation) {
        this.triggeredAnimation = triggeredAnimation;
    }
}
