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
    private int lastX;
    private int lastY;
    public boolean eventOccurred = false;

    public Board() {
        ArrayList<Content.Name> contentList = new ArrayList<Content.Name>(Arrays.asList(contents));
        Collections.shuffle(contentList);
        for(int i = 0; i < xSize; i++) {
            for(int j = 0; j < ySize; j++) {
                Tile tile = new Tile();
                if(i == 0 && j == 0) {
                    tile.setContent(Content.Name.PLAYER);
                    tile.setVisible(true);
                } else if(i == 4 && j == 4) {
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
        lastX = 0;
        lastY = 0;
        tiles[currentCurserX][currentCurserY].setCurrent(true);
    }

    public int getCurrentCurserX() {
        return currentCurserX;
    }

    public int getCurrentCurserY() {
        return currentCurserY;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void updateCursor(int x, int y) {
        System.out.println("update x: " + x + " y: " + y);
        tiles[currentCurserX][currentCurserY].setCurrent(false);
        if(currentCurserX == 0 && currentCurserY == 0) {
            tiles[currentCurserX][currentCurserY].setContent(Content.Name.EMPTY);
        }
        currentCurserX = x;
        currentCurserY = y;
        tiles[currentCurserX][currentCurserY].setCurrent(true);
        tiles[currentCurserX][currentCurserY].setVisible(true);
    }

    public Tile getCurrentCurserTile() {
        return tiles[currentCurserX][currentCurserY];
    }
    Content.Name[] contents = new Content.Name[]{Content.Name.BODO, Content.Name.EMPTY, Content.Name.CLOSED, Content.Name.EMPTY,
        Content.Name.FRAGE, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.BODO, Content.Name.TRAP,
        Content.Name.FRAGE, Content.Name.TRAP, Content.Name.BODO, Content.Name.EMPTY, Content.Name.EMPTY, Content.Name.BODO,
        Content.Name.TRAP, Content.Name.BODO, Content.Name.EMPTY, Content.Name.TRAP, Content.Name.BODO, Content.Name.EMPTY,
        Content.Name.EMPTY, Content.Name.BODO, Content.Name.CLOSED};

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void moveUp() {
        saveLastCurser(getCurrentCurserX(), getCurrentCurserY());
        int newX = getCurrentCurserX();
        int newY = getCurrentCurserY() - 1;
        if(newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            if(!this.tiles[newX][newY].isBlocked()) {
                updateCursor(newX, newY);
                processEvent();
            }
        }
    }

    private void saveLastCurser(int x, int y) {
        lastX = x;
        lastY = y;
    }

    public void moveDown() {
        saveLastCurser(getCurrentCurserX(), getCurrentCurserY());
        int newX = getCurrentCurserX();
        int newY = getCurrentCurserY() + 1;
        if(newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            if(!this.tiles[newX][newY].isBlocked()) {
                updateCursor(newX, newY);
                processEvent();
            }

        }

    }

    public void handleAnswer(boolean answer) {
        System.out.println("answer " + answer);
        if(answer) {
            // do nothing
        } else {
            blockTile(getCurrentCurserX(), getCurrentCurserY());
            updateCursor(getLastX(), getLastY());
        }
    }

    public void moveRight() {
        saveLastCurser(getCurrentCurserX(), getCurrentCurserY());
        int newX = getCurrentCurserX() + 1;
        int newY = getCurrentCurserY();
        if(newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            if(!this.tiles[newX][newY].isBlocked()) {
                updateCursor(newX, newY);
                processEvent();
            }

        }
    }

    public void moveLeft() {
        saveLastCurser(getCurrentCurserX(), getCurrentCurserY());
        int newX = getCurrentCurserX() - 1;
        int newY = getCurrentCurserY();
        if(newX >= 0 && newY >= 0 && newX < xSize && newY < xSize) {
            if(!this.tiles[newX][newY].isBlocked()) {
                updateCursor(newX, newY);
                processEvent();
            }

        }
    }

    private void processEvent() {
        if(!tiles[currentCurserX][currentCurserY].getContent().equals(Content.Name.EMPTY)) {
            eventOccurred = true;
            currentEventType = tiles[currentCurserX][currentCurserY].getContent();
        }
    }
    public Content.Name currentEventType = null;

    public void blockTile(int newX, int newY) {
        System.out.println("block x: " + newX + " y: " + newY);
        tiles[newX][newY].setBlocked(true);
    }

    public void handeTrap() {
        blockTile(getCurrentCurserX(), getCurrentCurserY());
        updateCursor(getLastX(), getLastY());
    }
}
