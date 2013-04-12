package com.gamejam.core;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import static playn.core.PlayN.*;

public class FieldBattle implements Game {

    @Override
    public void init() {
        // create and add background image layer
        Image bgImage = assets().getImage("images/bg.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphics().rootLayer().add(bgLayer);
//        int width = 640;
//        int height = 480;
//        CanvasImage bgImage = graphics().createImage(width, height);
//        Canvas canvas = bgImage.canvas();
//        canvas.drawText("Hello text!", 100, 200);
//        canvas.setFillColor(0xff87ceeb);
//        canvas.fillRect(0, 0, width, height);
//        ImageLayer bg = graphics().createImageLayer(bgImage);
//        graphics().rootLayer().add(bg);



//        createMatrix(10, 10);
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
}
