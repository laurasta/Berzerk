package entity;

import main.GamePanel;

import java.awt.*;

public class Bullet extends GameObject {
    private double x;
    private double y;

    public Bullet(double x, double y, GamePanel gp) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        y -= 5;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillOval((int) x, (int) y, 10, 10);
    }

    public double getY() {
        return y;
    }
    public double getX() { return x; }
}
