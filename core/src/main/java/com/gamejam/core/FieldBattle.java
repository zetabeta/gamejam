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

    @Override
    public void init() {
        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);

        initKeyboardListener();







    }

    @Override
    public void paint(float delta) {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Image matrixImage = assets().getImage("images/images.jpeg");
                ImageLayer matrix = graphics().createImageLayer(matrixImage);
                graphics().rootLayer().add(matrix);
                matrix.setTranslation(i * 50, j * 50);
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
                    System.out.println("key up");
                }
                if(event.key() == Key.DOWN) {
                    System.out.println("key down");
                }
                if(event.key() == Key.RIGHT) {
                    System.out.println("key right");
                }
                if(event.key() == Key.LEFT) {
                    System.out.println("key left");
                }
                if(event.key() == Key.ENTER) {
                    System.out.println("key enter");
                }



            }
        });
    }
}
