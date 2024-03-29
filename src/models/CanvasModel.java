package models;

import utils.SHAPE_TYPE;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.awt.Robot;

public class CanvasModel {
    private int shapeThickness;
    private int mousePressedX;
    private int mousePressedY;
    private int mouseDraggedX;
    private int mouseDraggedY;
    private int width, height,
            startX, startY;
    private Color shapeColor;
    private final Color canvasColor;
    private SHAPE_TYPE shapeType;

    private final List<Shape> shapes;
    private final List<FilledTemp> filledTemps;
    private final List<FilledTemp> filledTempsRedo;
    private final List<Shape> shapesRedo;

    private Robot robot;
    private boolean mousePressed, mouseDragged;

    private final List<Boolean> filledTempDelay;
    private final List<Boolean> filledTempDelayRedo;
    private Image imageTemp, imageDefault;
    private boolean imageOpened;
    private double widthScale, heightScale;
    private boolean isBlank;
    private int sizeWidth, sizeHeight;

    public CanvasModel() {
        shapeThickness = 4;
        canvasColor = new Color(255, 255, 255);
        isBlank = true;
        shapes = new ArrayList<>();
        filledTemps = new ArrayList<>();
        filledTempsRedo = new ArrayList<>();
        shapesRedo = new ArrayList<>();
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println("Robot creation failed!\n" + ex);
        }

        mousePressed = false;
        mouseDragged = false;

        filledTempDelay = new ArrayList<>();
        filledTempDelayRedo = new ArrayList<>();

        imageOpened = false;
        widthScale = 1.0;
        heightScale = 1.0;
    }

    public void clearCanvas() {
        shapes.clear();
        filledTemps.clear();
        filledTempsRedo.clear();
        shapesRedo.clear();
        filledTempDelay.clear();
        filledTempDelayRedo.clear();
        if (this.imageOpened) {
            imageOpened = false;
            this.imageDefault.flush();
            this.imageTemp.flush();
        }
    }

    public int getShapeThickness() {
        return shapeThickness;
    }

    public void setListShapesEndOfShapeAtPosition(int pos) {
        shapes.get(pos).setEndOfShape();
    }

    public void setShapeThickness(int shapeThickness) {
        this.shapeThickness = shapeThickness;
    }

    public int getMousePressedX() {
        return mousePressedX;
    }

    public void setMousePressedX(int mousePressedX) {
        this.mousePressedX = mousePressedX;
    }

    public int getMousePressedY() {
        return mousePressedY;
    }

    public void setMousePressedY(int mousePressedY) {
        this.mousePressedY = mousePressedY;
    }

    public int getMouseDraggedX() {
        return mouseDraggedX;
    }

    public void setMouseDraggedX(int mouseDraggedX) {
        this.mouseDraggedX = mouseDraggedX;
    }

    public int getMouseDraggedY() {
        return mouseDraggedY;
    }

    public void setMouseDraggedY(int mouseDraggedY) {
        this.mouseDraggedY = mouseDraggedY;
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

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }

    public Color getCanvasColor() {
        return canvasColor;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public List<FilledTemp> getFilledTemps() {
        return filledTemps;
    }

    public List<FilledTemp> getFilledTempsRedo() {
        return filledTempsRedo;
    }

    public List<Shape> getShapesRedo() {
        return shapesRedo;
    }


    public Robot getRobot() {
        return robot;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMouseDragged() {
        return mouseDragged;
    }

    public void setMouseDragged(boolean mouseDragged) {
        this.mouseDragged = mouseDragged;
    }

    public List<Boolean> getFilledTempDelay() {
        return filledTempDelay;
    }

    public List<Boolean> getFilledTempDelayRedo() {
        return filledTempDelayRedo;
    }

    public Image getImageTemp() {
        return imageTemp;
    }

    public void setImageTemp(Image imageTemp) {
        this.imageTemp = imageTemp;
    }

    public Image getImageDefault() {
        return imageDefault;
    }

    public void setImageDefault(Image imageDefault) {
        this.imageDefault = imageDefault;
    }

    public boolean isImageOpened() {
        return imageOpened;
    }

    public void setImageOpened(boolean imageOpened) {
        this.imageOpened = imageOpened;
    }

    public double getWidthScale() {
        return widthScale;
    }

    public void setWidthScale(double widthScale) {
        this.widthScale = widthScale;
    }

    public double getHeightScale() {
        return heightScale;
    }

    public void setHeightScale(double heightScale) {
        this.heightScale = heightScale;
    }

    public int getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(int sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public int getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(int sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public SHAPE_TYPE getShapeType() {
        return shapeType;
    }

    public void setShapeType(SHAPE_TYPE shapeType) {
        this.shapeType = shapeType;
    }

    public void clearShapeRedo() {
        shapesRedo.clear();
    }

    public void clearFilledTempsRedo() {
        filledTempsRedo.clear();
    }

    public void clearFilledTempDelayRedo() {
        filledTempDelayRedo.clear();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void addFilledTempDelay(boolean check) {
        filledTempDelay.add(check);
    }

    public void addFilledTemp(FilledTemp filledTemp) {
        filledTemps.add(filledTemp);
    }

    public void addFilledTempsRedo(FilledTemp filledTemp) {
        filledTempsRedo.add(filledTemp);
    }

    public void addFilledTempDelayRedo(boolean check) {
        filledTempDelayRedo.add(check);
    }

    public void setShapesPressedAtPosition(int pos, boolean check) {
        shapes.get(pos).setPressed(check);
    }

    public void removeFilledTempDelay(int pos) {
        filledTempDelay.remove(pos);
    }

    public void removeFilledTempDelayRedo(int pos) {
        filledTempDelayRedo.remove(pos);

    }

    public void removeFilledTempsRedo(int pos) {
        filledTempsRedo.remove(pos);

    }

    public void removeFilledTemps(int pos) {
        filledTemps.remove(pos);

    }

    public void setColorFilledTempsAtPosition(int pos, Color color) {
        filledTemps.get(pos).setColor(color);
    }

    public void setColorFilledTempsRedoAtPosition(int pos, Color color) {
        filledTempsRedo.get(pos).setColor(color);
    }

    public void setColorShapesAtPosition(int pos, Color color) {
        shapes.get(pos).setColor(color);
    }

    public void addShapeRedo(Shape shape) {
        shapesRedo.add(shape);
    }

    public void removeShapeAtPosition(int pos) {
        shapes.remove(pos);
    }

    public void removeShapeRedoAtPosition(int pos) {
        shapesRedo.remove(pos);
    }

    public boolean isBlank() {
        return isBlank;
    }

    public void setBlank(boolean blank) {
        isBlank = blank;
    }
}
