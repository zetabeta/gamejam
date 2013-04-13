package com.gamejam.core;

public class Tile {

    private Content.Name content;
    private boolean visible;
    private boolean current;
    private boolean hasChanged = true;

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
        setHasChanged(true);
    }

    public Content.Name getContent() {
        return content;
    }

    public void setContent(Content.Name content) {
        this.content = content;
        setHasChanged(true);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        setHasChanged(true);
    }

    public boolean hasChanged() {
        boolean temp = hasChanged;
        hasChanged = false;
        return temp;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }
}
