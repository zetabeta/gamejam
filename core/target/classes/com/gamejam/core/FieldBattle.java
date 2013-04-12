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
<<<<<<< HEAD

    @Override
    public void init() {

        Image bgImage = assets().getImage("images/background.png");
=======
    Content content = new Content();

    @Override
    public void init() {
        Image bgImage = assets().getImage("images/BGWorldmap.png");
>>>>>>> 4bddc895b0e0515c4bdc377900e5fa15abb00665
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        initKeyboardListener();
<<<<<<< HEAD





=======
>>>>>>> 4bddc895b0e0515c4bdc377900e5fa15abb00665


    }

    @Override
    public void paint(float delta) {
<<<<<<< HEAD
    }

    @Override
    public void update(float delta) {



        Tile[][] tiles = board.getTiles();
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {

                Image matrixImage = assets().getImage("images/feldungeklickt.png");
                if(tiles[i][j].isCurrent()) {
                    matrixImage = assets().getImage("images/feldgeklickt.png");
=======
        Tile[][] tiles = board.getTiles();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {

                Image matrixImage = assets().getImage("images/feldungeklickt.png");
                if(tiles[i][j].isVisible()) {
                    matrixImage = assets().getImage(content.getImage(tiles[i][j].getContent()));
>>>>>>> 4bddc895b0e0515c4bdc377900e5fa15abb00665
                }
                ImageLayer matrix = graphics().createImageLayer(matrixImage);
                graphics().rootLayer().add(matrix);
                matrix.setTranslation(i * 100, j * 100);

            }
        }
    }

    @Override
    public int updateRate() {
        return 0;
    }

    private void initKeyboardListener() {
        System.out.println("initKeyboardListener");
        PlayN.keyboard().setListener(new Keyboard.Adapter() {
            @Override
            public void onKeyDown(Event event) {

                if(event.key() == Key.UP) {
<<<<<<< HEAD
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
=======
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
>>>>>>> 4bddc895b0e0515c4bdc377900e5fa15abb00665
                }
                if(event.key() == Key.ENTER) {
                    System.out.println("key enter");
                }



            }
        });
    }
}
