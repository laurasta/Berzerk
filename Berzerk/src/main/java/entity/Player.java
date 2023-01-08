package entity;

import main.CheckCollision;
import main.GamePanel;
import main.KeyHandler;
import tiles.TilesManager;

import java.awt.*;

public class Player extends GameObject{

    GamePanel gp;
    KeyHandler keyH;
    TilesManager tilesManager;
    CheckCollision checkC;

    public Player(GamePanel gp, KeyHandler keyH, TilesManager tilesManager, CheckCollision checkC) {
        this.gp = gp;
        this.keyH = keyH;
        this.tilesManager = tilesManager;
        this.checkC = checkC;
        setDefaultValues();
    }

    public void setDefaultValues() {
        x = 200;
        y = 200;
        speed = 4;
        dx = 0;
        dy = 0;
    }

    public void update() {
        dx = 0;
        dy = 0;
        if (keyH.upPressed == true) {
            dy -= speed;
        } else if (keyH.downPressed == true) {
            dy += speed;
        } else if (keyH.leftPressed == true) {
            dx -= speed;
        } else if (keyH.rightPressed == true) {
            dx += speed;
        }
        try {
            checkCollision();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void checkCollision() {
        try {
            tilesManager.create2DIntMatrixFromFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        boolean collides = checkC.collision(x, y, dx, dy, tilesManager.mapTileNum);
        if (!collides) {
            x += dx;
            y += dy;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.green);
        g2.fillOval(x, y, gp.UNIT_SIZE, gp.UNIT_SIZE);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
