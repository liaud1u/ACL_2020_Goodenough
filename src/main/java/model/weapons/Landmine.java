package model.weapons;

public class Landmine implements StaticWeapon{
    private int x;
    private int y;
    private boolean alive;

    /**
     * @param x x coord of the bomb
     * @param y y coord of the bomb
     */
    public Landmine(int x, int y) {
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void destroy() {
        alive = false;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }
}
