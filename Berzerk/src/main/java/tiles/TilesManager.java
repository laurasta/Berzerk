package tiles;

import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class TilesManager {
    GamePanel gp;
    public int[][] mapTileNum;
    int xpoz = 0, ypoz = 0;

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.N_BLOCKS][gp.N_BLOCKS];
        try {
            create2DIntMatrixFromFile();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        int xpos = 0, ypos = 0;
        for (int row = 0; row < mapTileNum.length; row++) {
            for (int ind = 0; ind < mapTileNum[row].length; ind++) {
                if (mapTileNum[row][ind] == 1) {
                    g2.fillRect(xpos, ypos, gp.UNIT_SIZE, gp.UNIT_SIZE);
                }
                if ((xpos += gp.UNIT_SIZE) >= gp.SCREEN_WIDTH) {
                    xpos = 0;
                }
            }
            if ((ypos += gp.UNIT_SIZE) >= gp.SCREEN_HEIGHT) {
                ypos = 0;
            }
        }
    }

    public void create2DIntMatrixFromFile() throws Exception {
        File inFile = new File("/Users/laurastaugaityte/Documents/JAVA programming/Berzerk/src/main/java/maps/maze.txt");
        Scanner in = null;
        try {
            in = new Scanner(inFile);
            int intLength = 0;
            String[] length = in.nextLine().trim().split(" ");
            for (int i = 0; i < length.length; i++) {
                intLength++;
            }
            in.close();
            in = new Scanner(inFile);
            int lineCount = 0;
            while (in.hasNextLine()) {
                String[] currentLine = in.nextLine().trim().split("\\s+");
                for (int i = 0; i < currentLine.length; i++) {
                    mapTileNum[lineCount][i] = Integer.parseInt(currentLine[i]);
                }
                lineCount++;
            }
            in.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}




