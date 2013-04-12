package com.gamejam.core;

public class Tile {

    private Content.Name content;
    private boolean visible;
    private boolean current;

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Content.Name getContent() {
        return content;
    }

    public void setContent(Content.Name content) {
        this.content = content;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
