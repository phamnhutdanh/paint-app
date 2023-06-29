package models;

import java.awt.Color;

public class FilledTemp {
    private int startIndex, endIndex;
    private Color color;
    private static final int CANVAS_INDEX = -1;

    public FilledTemp(int startIndex, int endIndex, Color color) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.color = color;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static int getCanvasIndex() {
        return CANVAS_INDEX;
    }
}
