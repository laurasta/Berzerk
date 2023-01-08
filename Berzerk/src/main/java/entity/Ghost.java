package entity;

import main.CheckCollision;
import main.GamePanel;
import tiles.TilesManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ghost extends GameObject{
    GamePanel gp;
    TilesManager tilesManager;
    CheckCollision checkC;
    long lastTurn = System.currentTimeMillis();
    int lastMove;
    Image ghost;

    public Ghost(GamePanel gp, TilesManager tilesMn, CheckCollision checkC) {
        this.gp = gp;
        this.tilesManager = tilesMn;
        this.checkC = checkC;
        setDefaultValues();
        ghost = loadImage();
    }

    public void setDefaultValues() {
        x = 300;
        y = 200;
        speed = 1;
    }

    public Image loadImage() {
        String input = "/Users/laurastaugaityte/Downloads/yellow.png";
        BufferedImage bi;
            try {
                bi = ImageIO.read(new File(input));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return bi.getScaledInstance(gp.UNIT_SIZE, gp.UNIT_SIZE, Image.SCALE_SMOOTH);
    }

    public void moveRandomly(Graphics2D g2d) {
        int randomMovement = (int) (Math.random() * 4);
        if (System.currentTimeMillis() - lastTurn >= 2000) {
            ghostSwitchPosition(g2d, randomMovement, ghost);
            lastTurn = System.currentTimeMillis();
            lastMove = randomMovement;
        } else {
            if (System.currentTimeMillis() - lastTurn < 3000)
                ghostSwitchPosition(g2d, lastMove, ghost);
        }
    }

    private void ghostSwitchPosition(Graphics2D g2, int lastMove, Image ghost) {
        dx = 0;
        dy = 0;
        boolean collides;
        switch (lastMove) {
            case 0:
                dx += speed;
                collides = checkC.collision(x, y, dx, dy, tilesManager.mapTileNum);
                if (!collides) {x += speed;}
                g2.drawImage(ghost, x, y, gp);
                break;
            case 1:
                dx -= speed;
                collides = checkC.collision(x, y, dx, dy, tilesManager.mapTileNum);
                if (!collides) { x -= speed;}
                g2.drawImage(ghost, x, y, gp);
                break;
            case 2:
                dy += speed;
                collides = checkC.collision(x, y, dx, dy, tilesManager.mapTileNum);
                if (!collides) {y += speed;}
                g2.drawImage(ghost, x, y, gp);
                break;
            case 3:
                dy -= speed;
                collides = checkC.collision(x, y, dx, dy, tilesManager.mapTileNum);
                if (!collides) {y -= speed;}
                g2.drawImage(ghost, x, y, gp);
                break;
        }
    }

    public void update() {
        x = gp.keepInScreenX(x);
        y = gp.keepInScreenY(y);
    }

    @Override
    public void draw(Graphics2D g2) {
        ghost = loadImage();
        moveRandomly(g2);
    }
}
