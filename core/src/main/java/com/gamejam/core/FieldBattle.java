package com.gamejam.core;

import java.util.Random;
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
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

//test
public class FieldBattle implements Game {

    Board board = new Board();
    Content content = new Content();
    ImageLayer focusBackgroundLayer;
    ImageLayer layer2;
    ImageLayer layer3;
    ImageLayer layerTrap;
    ImageLayer layerBodo;
    CanvasImage textBodo;
    ImageLayer unicorn;
    GroupLayer group;
    GroupLayer unicornGroup;
    int offset = 80;
    GroupLayer subgroup;
    CanvasImage textCanvas;
    ImageLayer textImageLayer;
    String[] texts = new String[]{"Welcher Sinn hat keinen Sinn?\n[J] Unsinn\n[N] Der Sinn des Lebens",
        "Welcher Planet wird Abendstern \ngenannt?\n[J] Venus\n[N] Mars", "Was verbraucht mehr Strom?\n[J] Das kleine Licht in meinem "
        + "Kühlschrank!\n[N] Dein Hirn.", "Wer muss manchmal beim Orgasmus \nniesen, weil der Hirnstamm \n„durcheinander“ ist?\n[J] Männer…\n[N] Frauen!",
        "Welche/r Leiter nützt der \nFeuerwehr nicht?\n[J] Tonleiter \n[N] Wehrleiter",
        "Wer war der Erfinder der Glühbirne?\n[J] Unbekannt\n[N] Thomas Alva Edison",
        "Was steht mitten in Paris?\n[J] Das „r“\n[N] Der Eiffelturm!"};
    String[] bodosTexte = new String[]{
        "Gnak, gnak, gnak \nSchau mal hinter dich, \nein dreikoepfiger Affe!",
        "Wusstest du, dass das kleine \nLicht im Kuehlschrank mehr Strom \nverbraucht, als unser Gehirn?",
        "Im Hirnstamm ist manchmal so \nviel los, dass Maenner beim Orgasmus \nniesen muessen… \ndas kann dir ja nun nicht passieren..",
        "Unser Gehirn verbraucht nur 12 Watt, \nalso pro Tag die Energie zweier großer \nBananen. Wie habt ihr das denn damals \nim Osten gemacht?",
        "Im Uebringen: Die Insel, die du dir am \nAnfang erschaffen hast, heißt \n„N‘Ropinu“ und ist weit über die Grenzen \ndeines matschigen Gehirns "
        + "bekannt… \ndenk mal drueber nach!",
        "Wenn man die gesamten \n„Nervenbahnen“ des Gehirns \naneinander reihen wuerde, \nkaeme man auf eine Laenge von \nknapp 100 Kilometern. \nWahnsinn… "};
    int txtIdx = 0;
    boolean tmpEventQuestion = false;
    boolean tmpEventTrap = false;
    boolean tmpEventBodo = false;

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
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {

                int translationI = offset + i * 100;
                int translationJ = j * 100;

                if(i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if(tiles[i][j].hasChanged()) {
                        Image focused = assets().getImage("images/feldHERO.png");
                        unicorn = graphics().createImageLayer(focused);
                        unicornGroup.add(unicorn);
                        unicornGroup.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if(tiles[i][j].hasChanged()) {
                        if(tiles[i][j].isVisible()) {
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

        Image bodoImage = assets().getImage("images/popupBODO.png");
        layerBodo = graphics().createImageLayer(bodoImage);
        group.add(layerBodo);
        layerBodo.setTranslation(50, 20);
        layerBodo.setVisible(false);
        textBodo = graphics().createImage(500, 500);

        Image trapImage = assets().getImage("images/popupTRAP.png");
        layerTrap = graphics().createImageLayer(trapImage);
        group.add(layerTrap);
        layerTrap.setTranslation(50, 20);
        layerTrap.setVisible(false);
        textCanvas = graphics().createImage(500, 500);

        initKeyboardListener();
    }

    private void displayText(String text, int translationx, int translationy) {
        textCanvas.canvas().clear();
        Canvas canvas = textCanvas.canvas();
        String[] lines = text.split("\n");

        for(int i = 0; i < lines.length; ++i) {
            canvas.drawText(lines[i], 200, 10 * 2 * (i + 1));
        }
        textImageLayer = graphics().createImageLayer(textCanvas);
        textImageLayer.setTranslation(translationx, translationy);
        group.add(textImageLayer);

    }

    @Override
    public void paint(float delta) {
    }

    @Override
    public void update(float delta) {

        Tile[][] tiles = board.getTiles();
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles[i].length; j++) {

                int translationI = offset + i * 100;
                int translationJ = j * 100;

                if(i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if(tiles[i][j].hasChanged()) {
                        unicornGroup.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if(tiles[i][j].isBlocked()) {

                        image = assets().getImage("images/felderLOCKED.png");
                        layer2 = graphics().createImageLayer(image);
                        subgroup.add(layer2);
                        layer2.setTranslation(translationI, translationJ);
                    }
                    if(tiles[i][j].hasChanged()) {

                        if(tiles[i][j].isVisible()) {
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

        if(board.eventOccurred) {
            if(board.currentEventType == Content.Name.FRAGE) {
                layer3.setVisible(true);
                tmpEventQuestion = true;
                try {
                    displayText(this.texts[this.txtIdx], 160, 85);
                    txtIdx++;
                } catch(ArrayIndexOutOfBoundsException e) {
                    txtIdx = 0;
                    displayText(this.texts[this.txtIdx], 160, 85);
                }

            } else if(board.currentEventType == Content.Name.TRAP) {
                layerTrap.setVisible(true);
                tmpEventTrap = true;
            } else if(board.currentEventType == Content.Name.TRAP) {
                layerTrap.setVisible(true);
                tmpEventTrap = true;
            } else if(board.currentEventType == Content.Name.BODO) {
                layerBodo.setVisible(true);
                tmpEventBodo = true;
                try {
                    displayText(this.bodosTexte[this.txtIdx], 140, 85);
                    txtIdx++;
                } catch(ArrayIndexOutOfBoundsException e) {
                    txtIdx = 0;
                    displayText(this.bodosTexte[this.txtIdx], 160, 85);
                }
            } else if(board.currentEventType.equals(Content.Name.ENEMY)) {
                Random rand = new Random();
                int randNumber = rand.nextInt(100);
                if(randNumber % 2 == 0) {
                    board.blockTile(board.getCurrentCurserX(), board.getCurrentCurserY());
                    board.updateCursor(board.getLastX(), board.getLastY());
                }
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
                if(!tmpEventTrap) {
                    if(event.key() == Key.Y) {
                        if(tmpEventQuestion) {
                            tmpEventQuestion = false;
                            if(layer3.visible()) {
                                layer3.setVisible(false);
                                textImageLayer.setVisible(false);
                            }
                            board.handleAnswer(true);
                        }


                    }

                    if(event.key() == Key.N) {
                        if(tmpEventQuestion) {
                            tmpEventQuestion = false;
                            if(layer3.visible()) {
                                layer3.setVisible(false);
                                textImageLayer.setVisible(false);
                            }
                            board.handleAnswer(false);
                        }
                    }

                    if(event.key() == Key.UP) {
                        if(layer3.visible()) {
                            layer3.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if(layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveUp();
                    }
                    if(event.key() == Key.DOWN) {

                        if(layer3.visible()) {
                            layer3.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if(layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveDown();

                    }
                    if(event.key() == Key.RIGHT) {

                        if(layer3.visible()) {
                            layer3.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if(layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveRight();

                    }
                    if(event.key() == Key.LEFT) {
                        if(layer3.visible()) {
                            layer3.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if(layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveLeft();
                    }
                }



                if(event.key() == Key.ENTER) {
                    if(tmpEventTrap) {
                        if(layerTrap.visible()) {
                            layerTrap.setVisible(false);
                            board.handeTrap();
                        }
                    }
                    if(tmpEventBodo) {
                        if(layerBodo.visible()) {
                            textImageLayer.setVisible(false);

                        }
                    }
                    tmpEventTrap = false;

                }

            }
        });
    }
}
