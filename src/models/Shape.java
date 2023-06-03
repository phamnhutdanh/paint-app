package models;

import utils.TOOLS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class Shape {
    private int x1, x2, y1, y2;
    private Color color;
    private Color fillColor;
    private BasicStroke stroke;
    private String message;
    public boolean transparent;
    private TOOLS shape;
    private Font font;
    public int group = 0;

    public Shape(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke, TOOLS shape, Color fill, boolean transparent) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
        this.shape = shape;
        this.group = 0;
        this.fillColor = fill;
        this.transparent = transparent;
    }

    public Shape(int x1, int y1, int fontSize, Font font, Color color, BasicStroke stroke, TOOLS shape, String message) {
        this.x1 = x1;
        this.y1 = y1;
        this.y2 = 0;
        this.font = font;
        this.x2 = fontSize;
        this.color = color;
        this.stroke = stroke;
        this.shape = shape;
        this.group = 0;
        this.message = message;
    }

    public Shape(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke, TOOLS shape, int group) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
        this.shape = shape;
        this.group = group;

    }

    public TOOLS getShape() {
        return shape;
    }

    public String getMessage() {
        return message;
    }

    public Font getFont() {
        return font;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public Color getColor() {
        return color;
    }

    public Color getfillColor() {
        return fillColor;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public boolean getTransparency() {
        return transparent;
    }

    public int getGroup() {
        return group;
    }
}