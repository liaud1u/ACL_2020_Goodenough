package model.projectile;

public interface Projectile {
    void move();

    void hit();

    boolean isFireball();

    int getX();

    int getY();

    void destroy();
}
