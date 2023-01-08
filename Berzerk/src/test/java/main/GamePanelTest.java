package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamePanelTest {

    @Test
    void keepInScreenX() {
        GamePanel gp = new GamePanel();
        assertEquals(gp.SCREEN_WIDTH - gp.UNIT_SIZE, gp.keepInScreenX(gp.SCREEN_WIDTH+gp.UNIT_SIZE),
                "If position is over the screen, it should be brought back to the screen");
    }

    @Test
    void  keepInScreenY(){
        GamePanel gp = new GamePanel();
        assertEquals(gp.SCREEN_HEIGHT - gp.UNIT_SIZE, gp.keepInScreenY(gp.SCREEN_HEIGHT+gp.UNIT_SIZE),
                "If position is over the screen, it should be brought back to the screen");
    }
}