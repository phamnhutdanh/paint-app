package models;

import utils.SHAPE_TYPE;

import java.awt.*;

public class Shape {
    private Color color;
    private final int thickness;
    private final int x;
    private final int y;
    private int width;
    private int height;
    private final SHAPE_TYPE type;
    private boolean endOfShape = false;
    private boolean pressed;

    private String message = "";
    private Font font;

    public Shape(int x, int y, Color color, int thickness, SHAPE_TYPE type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.thickness = thickness;
        this.type = type;
    }

    public Shape(int x, int y, int width, int height, Color color, int thickness, SHAPE_TYPE type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.thickness = thickness;
        this.type = type;
        this.height = height;
        this.width = width;
    }

    public Shape(int x, int y, Font font, Color color, int thickness, String message, SHAPE_TYPE type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.thickness = thickness;
        this.type = type;
        this.font = font;
        this.message = message;
    }

    public boolean isEndOfShape() {
        return endOfShape;
    }

    public void setEndOfShape() {
        endOfShape = true;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getThickness() {
        return thickness;
    }

    public SHAPE_TYPE getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public String getMessage() {
        return message;
    }

    public Font getFont() {
        return font;
    }
}
