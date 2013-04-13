package com.gamejam.core;

import java.util.HashMap;
import java.util.Map;

public class Content {

    private Map<Name, String> imgs;

    public Content() {
        this.imgs = new HashMap<Content.Name, String>();
        this.imgs.put(Name.BODO, "images/felderBODO.png");
        this.imgs.put(Name.TRAP, "images/felderTRAP.png");
        this.imgs.put(Name.EMPTY, "images/feldungeklickt.png");
        this.imgs.put(Name.CLOSED, "images/felderCLOSED.png");
        this.imgs.put(Name.FRAGE, "images/felderFRAGE.png");
        this.imgs.put(Name.TREASURE, "images/felderBRAIN.png");
        this.imgs.put(Name.PLAYER, "images/felderCHAR.png");
    }

    public String getImage(Name name) {
        return this.imgs.get(name);
    }

    public enum Name {
        BODO, TRAP, EMPTY, CLOSED, FRAGE, TREASURE, PLAYER;
    }

}
