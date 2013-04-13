package com.gamejam.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Canvas;
import playn.core.CanvasImage;
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
    ImageLayer layerTrap;
    ImageLayer unicorn;
    GroupLayer group;
    GroupLayer unicornGroup;
    int offset = 80;
    GroupLayer subgroup;
    CanvasImage textCanvas;
    ImageLayer textImageLayer;
    String[] texts = new String[] { "hello folks! watta \n beautiful day, isn't it?  Y or N?", "frage2", "frage3", "puzzle4", "penis",
            "wurst", "salamipizza!", "hotdog", "ice cream!" };
    int txtIdx = 0;
    boolean tmpEventQuestion = false;
    boolean tmpEventTrap = false;

    // Layer comixBubble;
    @Override
    public void init() {
        Image bgImage = assets().getImage("images/background.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);
        group = graphics().createGroupLayer();
        graphics().rootLayer().add(group);
        subgroup = graphics().createGroupLayer();
        unicornGroup = graphics().createGroupLayer();
        graphics().rootLayer().add(unicornGroup);
        group.add(subgroup);
        // //
        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                int translationI = offset + i * 100;
                int translationJ = j * 100;

                if (i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if (tiles[i][j].hasChanged()) {
                        Image focused = assets().getImage("images/feldHERO.png");
                        unicorn = graphics().createImageLayer(focused);
                        unicornGroup.add(unicorn);
                        unicornGroup.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if (tiles[i][j].hasChanged()) {
                        if (tiles[i][j].isVisible()) {
                            String img = content.getImage(tiles[i][j].getContent());
                            image = assets().getImage(img);
                        } else {
                            image = assets().getImage("images/fogofwar.png");
                        }
                        layer2 = graphics().createImageLayer(image);
                        subgroup.add(layer2);
                        layer2.setTranslation(translationI, translationJ);
                    }
                }
            }
        }

        Image heroImage = assets().getImage("images/popupHERO.png");
        layer3 = graphics().createImageLayer(heroImage);
        group.add(layer3);
        layer3.setTranslation(50, 20);
        layer3.setVisible(false);
        textCanvas = graphics().createImage(500, 500);

        Image trapImage = assets().getImage("images/popupTRAP.png");
        layerTrap = graphics().createImageLayer(trapImage);
        group.add(layerTrap);
        layerTrap.setTranslation(50, 20);
        layerTrap.setVisible(false);
        textCanvas = graphics().createImage(500, 500);

        initKeyboardListener();
    }

    private void displayText(String text) {
        Canvas canvas = textCanvas.canvas();
        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; ++i) {
            canvas.drawText(lines[i], 200, 20 * 2 * (i + 1));
        }
        textImageLayer = graphics().createImageLayer(textCanvas);
        textImageLayer.setTranslation(160, 60);
        group.add(textImageLayer);

    }

    @Override
    public void paint(float delta) {
    }

    @Override
    public void update(float delta) {

        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                int translationI = offset + i * 100;
                int translationJ = j * 100;

                if (i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if (tiles[i][j].hasChanged()) {
                        System.out.println("first");
                        unicornGroup.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if (tiles[i][j].isBlocked()) {
                        System.out.println("second");
                        image = assets().getImage("images/felderLOCKED.png");
                        layer2 = graphics().createImageLayer(image);
                        subgroup.add(layer2);
                        layer2.setTranslation(translationI, translationJ);
                    }
                    if (tiles[i][j].hasChanged()) {
                        System.out.println("third");
                        if (tiles[i][j].isVisible()) {
                            String img = content.getImage(tiles[i][j].getContent());
                            image = assets().getImage(img);
                        } else {
                            image = assets().getImage("images/fogofwar.png");
                        }
                        layer2 = graphics().createImageLayer(image);
                        subgroup.add(layer2);

                        layer2.setTranslation(translationI, translationJ);
                    }
                }
            }
        }

        if (board.eventOccurred) {
            if (board.currentEventType == Content.Name.FRAGE) {
                layer3.setVisible(true);
                displayText(this.texts[this.txtIdx]);
                txtIdx++;
                tmpEventQuestion = true;

            } else if (board.currentEventType == Content.Name.TRAP) {
                layerTrap.setVisible(true);

            } else if (board.currentEventType.equals(Content.Name.ENEMY)) {
                board.blockTile(board.getCurrentCurserX(), board.getCurrentCurserY());
                board.updateCursor(board.getLastX(), board.getLastY());
            }
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

                if (event.key() == Key.Y) {
                    if (tmpEventQuestion) {
                        tmpEventQuestion = false;
                        if (layer3.visible()) {

                            layer3.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.handleAnswer(true);
                    }

                }

                if (event.key() == Key.N) {
                    if (tmpEventQuestion) {
                        tmpEventQuestion = false;
                        if (layer3.visible()) {
                            layer3.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.handleAnswer(false);
                    }
                }

                if (event.key() == Key.UP) {
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        textImageLayer.setVisible(false);
                    }
                    board.moveUp();
                }
                if (event.key() == Key.DOWN) {
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        textImageLayer.setVisible(false);
                    }
                    board.moveDown();
                }
                if (event.key() == Key.RIGHT) {
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        textImageLayer.setVisible(false);
                    }
                    board.moveRight();
                }
                if (event.key() == Key.LEFT) {
                    if (layer3.visible()) {
                        layer3.setVisible(false);
                        textImageLayer.setVisible(false);
                    }
                    board.moveLeft();
                }

                if (event.key() == Key.ENTER) {
                    if (tmpEventTrap) {
                        if (layerTrap.visible()) {
                            layerTrap.setVisible(false);
                        }
                        board.handeTrap();
                    }

                }

            }
        });
    }
}
