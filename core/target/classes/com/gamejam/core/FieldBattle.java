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

        Image bgImage = assets().getImage("images/background.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        initKeyboardListener();







    }

    @Override
    public void paint(float delta) {
    }

    @Override
    public void update(float delta) {



        Tile[][] tiles = board.getTiles();
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {

                Image matrixImage = assets().getImage("images/feldungeklickt.png");
                if(tiles[i][j].isCurrent()) {
                    matrixImage = assets().getImage("images/feldgeklickt.png");
                }
                ImageLayer matrix = graphics().createImageLayer(matrixImage);
                graphics().rootLayer().add(matrix);
                matrix.setTranslation(i * 100, j * 100);

            }
        }
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
                    board.updateCurser(board.getCurrentCurserX(), board.getCurrentCurserY() - 1);
                }
                if(event.key() == Key.DOWN) {
                    board.updateCurser(board.getCurrentCurserX(), board.getCurrentCurserY() + 1);
                }
                if(event.key() == Key.RIGHT) {
                    board.updateCurser(board.getCurrentCurserX() + 1, board.getCurrentCurserY());
                }
                if(event.key() == Key.LEFT) {
                    board.updateCurser(board.getCurrentCurserX() - 1, board.getCurrentCurserY());
                }
                if(event.key() == Key.ENTER) {
                    System.out.println("key enter");
                }



            }
        });
    }
}
