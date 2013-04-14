package com.gamejam.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

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
import playn.core.Sound;

//test
public class FieldBattle implements Game {

    Board board = new Board();
    Content content = new Content();
    AudioHelper audio;
    ImageLayer tilesLayer;
    ImageLayer questionLayer;
    ImageLayer layerTrap;
    ImageLayer layerBodo;
    ImageLayer layerWin;
    ImageLayer gameoverLayer;
    ImageLayer fightLayer;
    CanvasImage textBodo;
    ImageLayer unicorn;
    GroupLayer boardGroup;
    GroupLayer unicornGroup;
    GroupLayer chatGroup;
    GroupLayer outcomeGroup;
    int offset = 80;
    GroupLayer subgroup;
    CanvasImage textCanvas;
    ImageLayer textImageLayer;

    String[] texts = new String[] { "Welcher Sinn hat keinen Sinn?\n[Y] Unsinn\n[N] Der Sinn des Lebens",
            "Welcher Planet wird Abendstern \ngenannt?\n[Y] Venus\n[N] Mars",
            "Was verbraucht mehr Strom?\n[Y] Das kleine Licht in meinem " + "Kühlschrank!\n[N] Dein Hirn.",
            "Wer muss manchmal beim Orgasmus \nniesen, weil der Hirnstamm \n„durcheinander“ ist?\n[Y] Männer…\n[N] Frauen!",
            "Welche/r Leiter nützt der \nFeuerwehr nicht?\n[Y] Tonleiter \n[N] Wehrleiter",
            "Wer war der Erfinder der Glühbirne?\n[Y] Unbekannt\n[N] Thomas Alva Edison",
            "Was steht mitten in Paris?\n[Y] Das „r“\n[N] Der Eiffelturm!" };
    String[] bodosTexte = new String[] {
            "Gnak, gnak, gnak \nSchau mal hinter dich, \nein dreikoepfiger Affe!",
            "Wusstest du, dass das kleine \nLicht im Kuehlschrank mehr Strom \nverbraucht, als unser Gehirn?",
            "Im Hirnstamm ist manchmal so \nviel los, dass Maenner beim Orgasmus \nniesen muessen… \ndas kann dir ja nun nicht passieren..",
            "Unser Gehirn verbraucht nur 12 Watt, \nalso pro Tag die Energie zweier großer \nBananen. Wie habt ihr das denn damals \nim Osten gemacht?",
            "Im Uebringen: Die Insel, die du dir am \nAnfang erschaffen hast, heißt \n„N‘Ropinu“ und ist weit über die Grenzen \ndeines matschigen Gehirns "
                    + "bekannt… \ndenk mal drueber nach!",
            "Wenn man die gesamten \n„Nervenbahnen“ des Gehirns \naneinander reihen wuerde, \nkaeme man auf eine Laenge von \nknapp 100 Kilometern. \nWahnsinn… " };

    int txtIdx = 0;
    boolean tmpEventQuestion = false;
    boolean tmpEventTrap = false;
    boolean tmpEventBodo = false;
    boolean tempEventFight = false;

    // Layer comixBubble;
    @Override
    public void init() {

        Image bgImage = assets().getImage("images/background.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        boardGroup = graphics().createGroupLayer();
        graphics().rootLayer().add(boardGroup);
        subgroup = graphics().createGroupLayer();
        boardGroup.add(subgroup);

        unicornGroup = graphics().createGroupLayer();
        graphics().rootLayer().add(unicornGroup);
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
                        tilesLayer = graphics().createImageLayer(image);
                        subgroup.add(tilesLayer);
                        tilesLayer.setTranslation(translationI, translationJ);
                    }
                }
            }
        }

        chatGroup = graphics().createGroupLayer();
        graphics().rootLayer().add(chatGroup);

        outcomeGroup = graphics().createGroupLayer();
        graphics().rootLayer().add(outcomeGroup);

        Image heroImage = assets().getImage("images/popupHERO.png");
        questionLayer = graphics().createImageLayer(heroImage);
        chatGroup.add(questionLayer);
        questionLayer.setTranslation(50, 20);
        questionLayer.setVisible(false);
        textCanvas = graphics().createImage(500, 500);

        Image bodoImage = assets().getImage("images/popupBODO.png");
        layerBodo = graphics().createImageLayer(bodoImage);
        chatGroup.add(layerBodo);
        layerBodo.setTranslation(40, 5);
        layerBodo.setVisible(false);
        textBodo = graphics().createImage(500, 500);

        Image trapImage = assets().getImage("images/popupTRAP.png");
        layerTrap = graphics().createImageLayer(trapImage);
        chatGroup.add(layerTrap);
        layerTrap.setTranslation(50, 20);
        layerTrap.setVisible(false);

        Image fightImage = assets().getImage("images/popupENEMY.png");
        fightLayer = graphics().createImageLayer(fightImage);
        chatGroup.add(fightLayer);
        fightLayer.setTranslation(30, 20);
        fightLayer.setVisible(false);

        Image winImage = assets().getImage("images/popupVICTORY.png");
        layerWin = graphics().createImageLayer(winImage);
        outcomeGroup.add(layerWin);
        layerWin.setTranslation(50, 20);
        layerWin.setVisible(false);

        Image gameover = assets().getImage("images/gameover.png");
        gameoverLayer = graphics().createImageLayer(gameover);
        outcomeGroup.add(gameoverLayer);
        gameoverLayer.setTranslation(50, 20);
        gameoverLayer.setVisible(false);

        initKeyboardListener();

        Sound test = PlayN.assets().getSound("sounds/background1");
        test.setLooping(true);
        test.setVolume(1f);
        test.prepare();
        test.play();
    }

    private void displayText(String text, int translationx, int translationy) {
        textCanvas.canvas().clear();
        Canvas canvas = textCanvas.canvas();
        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; ++i) {
            canvas.drawText(lines[i], 200, 10 * 2 * (i + 1));
        }
        textImageLayer = graphics().createImageLayer(textCanvas);
        textImageLayer.setTranslation(translationx, translationy);
        chatGroup.add(textImageLayer);

    }

    @Override
    public void paint(float delta) {
    }

    @Override
    public void update(float delta) {
        if (board.isGameOver()) {
            gameoverLayer.setVisible(true);
        }

        Tile[][] tiles = board.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {

                int translationI = offset + i * 100;
                int translationJ = j * 100;

                if (4 == board.getCurrentCurserX() && 4 == board.getCurrentCurserY()) {
                    layerWin.setVisible(true);
                }

                // if(board.isGameover(board.getCurrentCurserX(),
                // board.getCurrentCurserY())) {
                // layerLose.setVisible(true);
                // }

                if (i == board.getCurrentCurserX() && j == board.getCurrentCurserY()) {
                    if (tiles[i][j].hasChanged()) {
                        unicornGroup.setTranslation(translationI, translationJ);
                    }
                } else {
                    Image image;
                    if (tiles[i][j].hasChanged()) {

                        if (tiles[i][j].isBlocked()) {
                            image = assets().getImage("images/felderLOCKED.png");
                            tilesLayer = graphics().createImageLayer(image);
                            subgroup.add(tilesLayer);
                            tilesLayer.setTranslation(translationI, translationJ);
                        } else if (tiles[i][j].isVisible()) {
                            String img = content.getImage(tiles[i][j].getContent());
                            image = assets().getImage(img);
                        } else {
                            image = assets().getImage("images/fogofwar.png");
                        }
                        tilesLayer = graphics().createImageLayer(image);
                        subgroup.add(tilesLayer);

                        tilesLayer.setTranslation(translationI, translationJ);
                    }
                }
            }
        }

        if (board.eventOccurred) {
            if (board.currentEventType == Content.Name.FRAGE) {
                questionLayer.setVisible(true);

                tmpEventQuestion = true;
                try {
                    displayText(this.texts[this.txtIdx], 160, 85);
                    txtIdx++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    txtIdx = 0;
                    displayText(this.texts[this.txtIdx], 160, 85);
                }

            } else if (board.currentEventType == Content.Name.TRAP) {
                layerTrap.setVisible(true);
                tmpEventTrap = true;
            } else if (board.currentEventType == Content.Name.BODO) {
                layerBodo.setVisible(true);
                tmpEventBodo = true;
                try {
                    displayText(this.bodosTexte[this.txtIdx], 140, 85);
                    txtIdx++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    txtIdx = 0;
                    displayText(this.bodosTexte[this.txtIdx], 160, 85);
                }

            } else if (board.currentEventType.equals(Content.Name.ENEMY)) {
                tempEventFight = true;
                fightLayer.setVisible(true);

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
                if (!tmpEventTrap && !tempEventFight) {
                    if (event.key() == Key.Y) {
                        if (tmpEventQuestion) {
                            tmpEventQuestion = false;
                            if (questionLayer.visible()) {
                                questionLayer.setVisible(false);
                                textImageLayer.setVisible(false);
                            }
                            board.handleAnswer(true);
                        }

                    }

                    if (event.key() == Key.N) {
                        if (tmpEventQuestion) {
                            tmpEventQuestion = false;
                            if (questionLayer.visible()) {
                                questionLayer.setVisible(false);
                                textImageLayer.setVisible(false);
                            }
                            board.handleAnswer(false);
                        }
                    }

                    if (event.key() == Key.UP) {
                        if (questionLayer.visible()) {
                            questionLayer.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if (layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveUp();
                    }
                    if (event.key() == Key.DOWN) {

                        if (questionLayer.visible()) {
                            questionLayer.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if (layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveDown();

                    }
                    if (event.key() == Key.RIGHT) {

                        if (questionLayer.visible()) {
                            questionLayer.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if (layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveRight();

                    }
                    if (event.key() == Key.LEFT) {
                        if (questionLayer.visible()) {
                            questionLayer.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        if (layerBodo.visible()) {
                            layerBodo.setVisible(false);
                            textImageLayer.setVisible(false);
                        }
                        board.moveLeft();
                    }
                }

                if (event.key() == Key.ENTER) {
                    if (tmpEventTrap) {
                        if (layerTrap.visible()) {
                            layerTrap.setVisible(false);
                            board.handeTrap();
                        }
                    }
                    if (tmpEventBodo) {
                        if (layerBodo.visible()) {
                            textImageLayer.setVisible(false);

                        }
                    }
                    if (tempEventFight) {
                        if (fightLayer.visible()) {
                            fightLayer.setVisible(false);
                            tempEventFight = false;
                        }
                        Random rand = new Random();
                        int randNumber = rand.nextInt(100);
                        if (randNumber % 2 == 0) {
                            board.blockTile(board.getCurrentCurserX(), board.getCurrentCurserY());
                            board.updateCursor(board.getLastX(), board.getLastY());
                        }
                    }
                    tmpEventTrap = false;

                }

            }
        });
    }
}
