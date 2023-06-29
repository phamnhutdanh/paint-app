package models;

import utils.SHAPE_TYPE;

import java.awt.Color;

public class Shape {
    private Color color;
    private int thickness;
    private int x, y, width, height;
    private SHAPE_TYPE type;
    private boolean endOfShape = false;
    private boolean pressed;

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

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public SHAPE_TYPE getType() {
        return type;
    }

    public void setType(SHAPE_TYPE type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}
