package models;

import java.awt.Color;

public class Shape {
    public static final int RECTANGLE = 0;
    public  static final int BRUSH = 1;
    public   static final int CIRCLE = 2;
    public   static final int LINE = 3;
    public  static final int ERASER = 4;
    public  static final int COLOR_PICKER = 5;
    public  static final int FILL = 6;
    public  static final int NULL = -1;
    public Color color;
    public float thickness;
    public int type, x, y, width, height;
    private boolean endOfShape = false;
    public boolean pressed;

    public Shape(int x, int y, Color color, float thickness, int type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.thickness = thickness;
        this.type = type;
    }

    public Shape(int x, int y, int width, int height, Color color, float thickness, int type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.thickness = thickness;
        this.type = type;
        this.height = height;
        this.width = width;
    }

    public boolean isEndOfShape() {
        return endOfShape;
    }

    public void setEndOfShape() {
        endOfShape = true;
    }
}
