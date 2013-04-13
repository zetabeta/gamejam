package com.gamejam.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.PlayN;

//test
public class FieldBattle implements Game {

    Board board = new Board();
    Content content = new Content();
    ImageLayer focusBackgroundLayer;
    ImageLayer layer2;

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

        focusBackgroundLayer = null;
        layer2 = null;

        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                int translationI = i * 100;
                int translationJ = j * 100;

                if (i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if (tiles[i][j].hasChanged()) {
                        Image focused = assets().getImage("images/felderCHAR.png");
                        focusBackgroundLayer = graphics().createImageLayer(focused);
                        graphics().rootLayer().add(focusBackgroundLayer);
                        focusBackgroundLayer.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if (tiles[i][j].hasChanged()) {
                        if (tiles[i][j].isVisible()) {
                            String img = content.getImage(tiles[i][j].getContent());
                            image = assets().getImage(img);
                        } else {

                            image = assets().getImage("images/fogofwarfeldungeklickt.png");
                        }
                        layer2 = graphics().createImageLayer(image);
                        graphics().rootLayer().add(layer2);
                        layer2.setTranslation(translationI, translationJ);
                    }

                }

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

                if (event.key() == Key.UP) {
                    board.moveUp();
                }
                if (event.key() == Key.DOWN) {
                    board.moveDown();
                }
                if (event.key() == Key.RIGHT) {
                    board.moveRight();
                }
                if (event.key() == Key.LEFT) {
                    board.moveLeft();
                }
                if (event.key() == Key.ENTER) {
                    System.out.println("key enter");
                }

            }
        });
    }
}
