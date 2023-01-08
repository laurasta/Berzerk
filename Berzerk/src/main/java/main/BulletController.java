package main;

import entity.Bullet;
import entity.Ghost;
import entity.Player;

import java.awt.*;
import java.util.LinkedList;

public class BulletController {
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    Bullet TempBullet;
    GamePanel gp;
    KeyHandler keyH;
    Player p;

    public BulletController(GamePanel gp) {
        this.gp = gp;
    }

    public void tick() {
        for (int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);
            if (TempBullet.getY() < 0) {
                removeBullet(TempBullet);
            }
            TempBullet.tick();
        }
    }

    public void release() {
        if (keyH.spacePressed == true) {
            addBullet(new Bullet(p.getX(), p.getY(), gp));
        }
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < b.size(); i++) {
            TempBullet = b.get(i);
            TempBullet.draw(g);
        }
    }

    public void addBullet(Bullet block) {
        b.add(block);
    }

    public void removeBullet(Bullet block) {
        b.remove(block);
    }
}
