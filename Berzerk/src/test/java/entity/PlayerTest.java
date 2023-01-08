package entity;

import main.CheckCollision;
import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.Test;
import tiles.TilesManager;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void checkCollision() {
        GamePanel gp = new GamePanel();
        TilesManager tm = new TilesManager(gp);
        CheckCollision chk = new CheckCollision(gp);
        KeyHandler kh = new KeyHandler();
        Player p = new Player(gp,kh, tm, chk);
        //The First tile is in position x=1, y=1, so the collision should be "true"
        assertEquals(true, chk.collision(1, 1, p.dx, p.dy, tm.mapTileNum),
                "If the given position matches the position of the tile, then returns true");
    }
}