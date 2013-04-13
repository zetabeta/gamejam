package com.gamejam.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

    private int xSize = 5;
    private int ySize = 5;
    private Tile[][] tiles = new Tile[xSize][ySize];
    private int currentCurserX;
    private int currentCurserY;

    public Board() {
        ArrayList<Content.Name> contentList = new ArrayList<Content.Name>(Arrays.asList(contents));
        Collections.shuffle(contentList);
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                Tile tile = new Tile();
                if (i == 0 && j == 0) {
                    tile.setContent(Content.Name.PLAYER);
                    tile.setVisible(true);
                } else if (i == 4 && j == 4) {
                    tile.setContent(Content.Name.TREASURE);
                    tile.setVisible(true);
                } else {
                    tile.setContent(contentList.get(i + 5 * j));
                    tile.setVisible(false);
                }

                tiles[i][j] = tile;

            }
        }
        currentCurserX = 0;
        currentCurserY = 0;
        tiles[currentCurserX][currentCurserY].setCurrent(true);
    }

    public int getCurrentCurserX() {
        return currentCurserX;
    }

    public int getCurrentCurserY() {
        return currentCurserY;
    }

    public void updateCurser(int x, int y) {
        tiles[currentCurserX][currentCurserY].setCurrent(false);
        currentCurserX = x;
        currentCurserY = y;
        tiles[currentCurserX][currentCurserY].setCurrent(true);
        tiles[currentCurserX][currentCurserY].setVisible(true);
    }

    public Tile getCurrentCurserTile() {
        return tiles[currentCurserX][currentCurserY];
    }

    Content.Name[] contents = new Content.Name[] { Content.Name.BODO, Content.Name.EMPTY, Content.Name.CLOSED, Content.Name.EMPTY,
            Content.Name.FRAGE, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.BODO, Content.Name.EMPTY,
            Content.Name.EMPTY, Content.Name.TRAP, Content.Name.BODO, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.BODO,
            Content.Name.EMPTY, Content.Name.BODO, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.BODO, Content.Name.EMPTY,
            Content.Name.EMPTY, Content.Name.BODO, Content.Name.CLOSED };

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void moveUp() {
        int newX = getCurrentCurserX();
        int newY = getCurrentCurserY() - 1;
        if (newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            updateCurser(newX, newY);
        }
    }

    public void moveDown() {
        int newX = getCurrentCurserX();
        int newY = getCurrentCurserY() + 1;
        if (newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            updateCurser(newX, newY);
        }

    }

    public void moveRight() {
        int newX = getCurrentCurserX() + 1;
        int newY = getCurrentCurserY();
        if (newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            updateCurser(newX, newY);
        }
    }

    public void moveLeft() {
        int newX = getCurrentCurserX() - 1;
        int newY = getCurrentCurserY();
        if (newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            updateCurser(newX, newY);
        }
    }
}
