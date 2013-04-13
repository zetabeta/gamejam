package com.gamejam.core;

import java.util.HashMap;
import java.util.Map;

import playn.core.Image;

public class Content {

    public Map<Name, String> imgs;
    public Map<Name, Image> cached;

    public Content() {
        this.imgs = new HashMap<Content.Name, String>();
        this.imgs.put(Name.BODO, "images/bodo.png");
        this.imgs.put(Name.TRAP, "images/trap.png");
        this.imgs.put(Name.EMPTY, "images/buttonlight.png");
        this.imgs.put(Name.CLOSED, "images/locked.png");
        this.imgs.put(Name.FRAGE, "images/question.png");
        this.imgs.put(Name.TREASURE, "images/ninjas.png");
        this.imgs.put(Name.PLAYER, "images/ninjas.png");

    }

    public String getImage(Name name) {
        return this.imgs.get(name);
    }

    public enum Name {
        BODO, TRAP, EMPTY, CLOSED, FRAGE, TREASURE, PLAYER;
    }

}
