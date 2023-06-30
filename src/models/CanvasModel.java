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
    private int mousePressedX, mousePressedY,
            mouseReleasedX, mouseReleasedY,
            mouseDraggedX, mouseDraggedY;
    private int width, height,
            startX, startY;
    private Color shapeColor, canvasColor;
    private SHAPE_TYPE shapeType;

    private List<Shape> shapes;
    private List<FilledTemp> filledTemps;
    private List<FilledTemp> filledTempsRedo;
    private List<Shape> shapesRedo;

    private Robot robot;
    private boolean mousePressed, mouseDragged;

    private List<Boolean> filledTempDelay;
    private List<Boolean> filledTempDelayRedo;
    private Image imageTemp, imageDefault;
    private boolean imageOpened;
    private double widthScale, heightScale;
    private boolean isBlank;
    private int sizeWidth, sizeHeight;

    public CanvasModel() {
        shapeThickness = 4;
        canvasColor = new Color(238, 238, 238);
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
        canvasColor = new Color(238, 238, 238);
        if (this.imageOpened == true) {
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

    public int getMouseReleasedX() {
        return mouseReleasedX;
    }

    public void setMouseReleasedX(int mouseReleasedX) {
        this.mouseReleasedX = mouseReleasedX;
    }

    public int getMouseReleasedY() {
        return mouseReleasedY;
    }

    public void setMouseReleasedY(int mouseReleasedY) {
        this.mouseReleasedY = mouseReleasedY;
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

    public void setCanvasColor(Color canvasColor) {
        this.canvasColor = canvasColor;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public List<FilledTemp> getFilledTemps() {
        return filledTemps;
    }

    public void setFilledTemps(List<FilledTemp> filledTemps) {
        this.filledTemps = filledTemps;
    }

    public List<FilledTemp> getFilledTempsRedo() {
        return filledTempsRedo;
    }

    public void setFilledTempsRedo(List<FilledTemp> filledTempsRedo) {
        this.filledTempsRedo = filledTempsRedo;
    }

    public List<Shape> getShapesRedo() {
        return shapesRedo;
    }

    public void setShapesRedo(List<Shape> shapesRedo) {
        this.shapesRedo = shapesRedo;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
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

    public void setFilledTempDelay(List<Boolean> filledTempDelay) {
        this.filledTempDelay = filledTempDelay;
    }

    public List<Boolean> getFilledTempDelayRedo() {
        return filledTempDelayRedo;
    }

    public void setFilledTempDelayRedo(List<Boolean> filledTempDelayRedo) {
        this.filledTempDelayRedo = filledTempDelayRedo;
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
