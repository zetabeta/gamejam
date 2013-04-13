package com.gamejam.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.GroupLayer;
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
    ImageLayer layer3;
    GroupLayer group;
    int offset = 80;
    GroupLayer subgroup;

    // Layer comixBubble;

    @Override
    public void init() {
        Image bgImage = assets().getImage("images/background1.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);
        group = graphics().createGroupLayer();
        graphics().rootLayer().add(group);
        subgroup = graphics().createGroupLayer();
        group.add(subgroup);
        // //
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                int translationI = offset + i * 100;
                int translationJ = j * 100;

                if (i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if (tiles[i][j].hasChanged()) {
                        Image focused = assets().getImage("images/ninjas.png");
                        focusBackgroundLayer = graphics().createImageLayer(focused);
                        subgroup.add(focusBackgroundLayer);
                        focusBackgroundLayer.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if (tiles[i][j].hasChanged()) {
                        if (tiles[i][j].isVisible()) {
                            String img = content.getImage(tiles[i][j].getContent());
                            image = assets().getImage(img);
                        } else {
                            // image =
                            // assets().getImage("images/fogofwarfeldungeklickt.png");
                            image = assets().getImage("images/button.png");
                        }
                        layer2 = graphics().createImageLayer(image);
                        subgroup.add(layer2);
                        layer2.setTranslation(translationI, translationJ);
                    }
                }
            }
        }
        // /
        Image image = assets().getImage("images/pinguin.png");
        layer3 = graphics().createImageLayer(image);
        group.add(layer3);
        layer3.setTranslation(50, 20);
        layer3.setVisible(false);
        // comixBubble = createMessageText("hhohohohoho!!!", 24, 122);
        // group.add(comixBubble);
        // comixBubble.setVisible(false);

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

                int translationI = offset + i * 100;
                int translationJ = j * 100;

                if (i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if (tiles[i][j].hasChanged()) {
                        Image focused = assets().getImage("images/ninjas.png");
                        focusBackgroundLayer = graphics().createImageLayer(focused);
                        subgroup.add(focusBackgroundLayer);
                        focusBackgroundLayer.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if (tiles[i][j].hasChanged()) {
                        if (tiles[i][j].isVisible()) {
                            String img = content.getImage(tiles[i][j].getContent());
                            image = assets().getImage(img);
                        } else {
                            image = assets().getImage("images/button.png");
                        }
                        layer2 = graphics().createImageLayer(image);
                        subgroup.add(layer2);
                        layer2.setTranslation(translationI, translationJ);
                    }
                }
            }
        }

        if (board.eventOccurred) {
            layer3.setVisible(true);
            // layer3.setDepth(100);
            // comixBubble.setVisible(true);
            board.eventOccurred = false;
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
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        // comixBubble.setVisible(false);
                    }
                    board.moveUp();
                }
                if (event.key() == Key.DOWN) {
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        // comixBubble.setVisible(false);
                    }
                    board.moveDown();
                }
                if (event.key() == Key.RIGHT) {
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        // comixBubble.setVisible(false);
                    }
                    board.moveRight();
                }
                if (event.key() == Key.LEFT) {
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        // comixBubble.setVisible(false);
                    }
                    board.moveLeft();
                }
                if (event.key() == Key.ENTER) {
                    System.out.println("key enter");
                }

            }
        });
    }
}
