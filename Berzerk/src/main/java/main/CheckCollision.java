package main;

public class CheckCollision {
    GamePanel gp;

    public CheckCollision(GamePanel gp) {
        this.gp = gp;
    }

    public boolean collision(int x, int y, int dx, int dy, int[][] walls) {
        if (x + dx < 0 || x + dx + gp.UNIT_SIZE > gp.SCREEN_WIDTH || y + dy < 0 || y + dy + gp.UNIT_SIZE > gp.SCREEN_HEIGHT) {
            return true;
        }
        int arrayX = (x + dx) / gp.UNIT_SIZE;
        int arrayY = (y + dy) / gp.UNIT_SIZE;
        return walls[arrayY][arrayX] == 1;
    }
}
