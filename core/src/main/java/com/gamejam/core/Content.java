package com.gamejam.core;

import java.util.HashMap;
import java.util.Map;

import playn.core.Image;

public class Content {

    public Map<Name, String> imgs;
    public Map<Name, Image> cached;

    public Content() {
        this.imgs = new HashMap<Content.Name, String>();
        this.imgs.put(Name.BODO, "images/felderBODO.png");
        this.imgs.put(Name.TRAP, "images/felderTRAP.png");
        this.imgs.put(Name.EMPTY, "images/felderEMPTY.png");
        this.imgs.put(Name.CLOSED, "images/felderLOCKED.png");
        this.imgs.put(Name.FRAGE, "images/felderQUESTION.png");
        this.imgs.put(Name.TREASURE, "images/felderBRAIN2.png");
        this.imgs.put(Name.PLAYER, "images/feldHERO.png");
        this.imgs.put(Name.ENEMY, "images/felderENEMY.png");
    }

    public String getImage(Name name) {
        return this.imgs.get(name);
    }

    public enum Name {

        BODO, TRAP, EMPTY, CLOSED, FRAGE, TREASURE, ENEMY, PLAYER;
    }
}
