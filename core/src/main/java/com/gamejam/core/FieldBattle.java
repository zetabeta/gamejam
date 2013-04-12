package com.gamejam.core;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.PlayN;
import static playn.core.PlayN.*;
//test
public class FieldBattle implements Game {

    Board board = new Board();

    @Override
    public void init() {
        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        initKeyboardListener();







    }

    @Override
    public void paint(float delta) {
        Tile[][] tiles = board.getTiles();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {

                Image matrixImage = assets().getImage("images/feldungeklickt.png");
                if(tiles[i][j].isVisible()) {
                    matrixImage = assets().getImage("images/feldgeklickt.png");
                }
                ImageLayer matrix = graphics().createImageLayer(matrixImage);
                graphics().rootLayer().add(matrix);
                matrix.setTranslation(i * 100, j * 100);

            }
        }
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public int updateRate() {
        return 25;
    }

    private void initKeyboardListener() {
        System.out.println("initKeyboardListener");
        PlayN.keyboard().setListener(new Keyboard.Adapter() {
            @Override
            public void onKeyDown(Event event) {

                if(event.key() == Key.UP) {
                    board.updateTiles(board.getCurrentCurserX(), board.getCurrentCurserY() - 1, true);
                }
                if(event.key() == Key.DOWN) {
                    board.updateTiles(board.getCurrentCurserX(), board.getCurrentCurserY() + 1, true);
                }
                if(event.key() == Key.RIGHT) {
                    board.updateTiles(board.getCurrentCurserX() + 1, board.getCurrentCurserY(), true);
                }
                if(event.key() == Key.LEFT) {
                    board.updateTiles(board.getCurrentCurserX() - 1, board.getCurrentCurserY(), true);
                }
                if(event.key() == Key.ENTER) {
                    System.out.println("key enter");
                }



            }
        });
    }
}
