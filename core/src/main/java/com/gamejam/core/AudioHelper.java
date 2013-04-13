package com.gamejam.core;

import playn.core.PlayN;
import playn.core.Sound;

public class AudioHelper {

    public AudioHelper() {
        Sound test = PlayN.assets().getSound("sounds/mouseover.mp3");
        test.setLooping(true);
        test.prepare();
        test.play();
    }

}
