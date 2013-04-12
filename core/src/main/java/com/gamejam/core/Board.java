package com.gamejam.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

    private int xSize = 5;
    private int ySize = 5;
    Content.Name[] contents = new Content.Name[]{Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY};
    private Tile[][] tiles = new Tile[xSize][ySize];
    private int currentCurserX;
    private int currentCurserY;
    private Tile[][] currentCurser = new Tile[xSize][ySize];

    public Board() {
        ArrayList<Content.Name> contentList = new ArrayList<Content.Name>(Arrays.asList(contents));
        Collections.shuffle(contentList);
        for(int i = 0; i < xSize; i++) {
            for(int j = 0; j < ySize; j++) {
                Tile tile = new Tile();
                tile.setContent(contentList.get(i + 5 * j));
                tile.setVisible(false);
                tile.setX(i);
                tile.setY(j);
                tiles[i][j] = tile;

            }
        }
        currentCurser[0][0].setVisible(true);
    }

    public void updateTiles(int x, int y, boolean visibility) {
        currentCurserX = x;
        currentCurserY = y;
        tiles[currentCurserX][currentCurserY].setVisible(visibility);
    }

    public Tile getCurrentCurserTile() {
        return tiles[currentCurserX][currentCurserY];
    }
}
