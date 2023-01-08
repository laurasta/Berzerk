package entity;

import java.awt.*;

public abstract class GameObject {
    public int x, y;
    public int dx, dy;
    public int speed;

    public abstract void draw(Graphics2D g2);

    public void update() {
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}