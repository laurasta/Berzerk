package main;

import entity.Bullet;
import entity.GameObject;
import entity.Ghost;
import entity.Player;
import tiles.TilesManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    int FPS = 60;
    public final int SCREEN_WIDTH = 600;
    public final int SCREEN_HEIGHT = 600;
    public final int UNIT_SIZE = 24;
    public int N_BLOCKS = 25;

    //SYSTEM STUFF
    KeyHandler keyH = new KeyHandler();
    TilesManager tilesMn = new TilesManager(this);
    public CheckCollision cChecker = new CheckCollision(this);

    GameObject player = new Player(this, keyH, tilesMn, cChecker);
    GameObject ghost = new Ghost(this, tilesMn, cChecker);
    GameObject ghost2 = new Ghost(this, tilesMn, cChecker);
    GameObject ghost3 = new Ghost(this, tilesMn, cChecker);

    BulletController bulletController = new BulletController(this);
    Thread gameThread;

    //GAME STATE
    private State state = State.START;

    public enum State {
        START, RUNNING, GAME_OVER;
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            if (keyH.spacePressed == true) {
                bulletController.addBullet(new Bullet(player.getX(), player.getY(), this));
            }
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                tick();
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void tick() {
        bulletController.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        bulletController.render(g2);
        tilesMn.draw(g2);
        player.draw(g2);
        ghost.draw(g2);
        ghost2.draw(g2);
        ghost3.draw(g2);
        g2.dispose();
    }

    public void update() {
        player.update();
        ghost.update();
        ghost2.update();
        ghost3.update();
    }

    public int keepInScreenY(int y) {
        if (y < 0) {
            y = 0;
        }
        if (y + UNIT_SIZE > SCREEN_HEIGHT) {
            y = SCREEN_HEIGHT - UNIT_SIZE;
        }
        return y;
    }

    public int keepInScreenX(int x) {
        if (x < 0) {
            x = 0;
        }
        if (x + UNIT_SIZE > SCREEN_WIDTH) {
            x = SCREEN_WIDTH - UNIT_SIZE;
        }
        return x;
    }

    private void drawGameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (SCREEN_WIDTH - fm.stringWidth(msg)) / 2,
                SCREEN_HEIGHT / 2);
    }
}
