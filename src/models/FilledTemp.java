package models;

import java.awt.Color;

public class FilledTemp {
    private final int startIndex;
    private final int endIndex;
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

    public int getEndIndex() {
        return endIndex;
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
