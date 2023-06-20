package panels;

import models.FilledTemp;
import models.Shape;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.RenderingHints;
import java.awt.Robot;

public class CanvasPanel extends JPanel {
    public int shapeType, shapeThickness = 4;
    public int mousePressedX, mousePressedY, mouseReleasedX, mouseReleasedY, mouseDraggedX, mouseDraggedY;
    public int width, height, startX, startY;
    public Color shapeColor, canvasColor = new Color(238, 238, 238);
    public Robot robot;
    public List<Shape> shapes = new ArrayList<>();
    public List<FilledTemp> filledTemps = new ArrayList<>();
    public List<FilledTemp> filledTempsRedo = new ArrayList<>();
    public List<Shape> shapesRedo = new ArrayList<>();
    public boolean mousePressed = false;
    public List<Boolean> filledTempDelay = new ArrayList<>();
    public List<Boolean> filledTempDelayRedo = new ArrayList<>();
    public Image imageTemp, imageDefault;
    public boolean imageOpened = false;
    public double widthScale = 1.0, heightScale = 1.0;
    public boolean mouseDragged = false;
    public int sizeWidth, sizeHeight;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;

    public CanvasPanel() {
        sizeWidth = 500;
        sizeHeight = 500;
        this.setPreferredSize(new Dimension(sizeWidth, sizeHeight));
        setBackground(canvasColor);
        shapeColor = Color.black;
        shapeType = Shape.BRUSH;
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.out.println("Robot creation failed!\n" + ex);
        }
    }

    public void resetCanvas() {
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
        setBackground(canvasColor);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.scale(widthScale, heightScale);
        if (imageOpened) {
            g2d.drawImage(imageTemp, 0, 0, null);
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawShapes(g2d);
    }

    public void drawShapes(Graphics2D g2d) {
        for (int i = 0; i < shapes.size(); i++) {
            g2d.setColor(shapes.get(i).color);
            switch (shapes.get(i).type) {
                case Shape.CIRCLE:
                    g2d.setStroke(new BasicStroke(shapes.get(i).thickness));
                    g2d.drawOval(shapes.get(i).x, shapes.get(i).y, shapes.get(i).width, shapes.get(i).height);
                    break;
                case Shape.BRUSH:
                    g2d.setStroke(new BasicStroke(shapes.get(i).thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    if (shapes.get(i).pressed) {
                        g2d.drawLine(shapes.get(i).x, shapes.get(i).y, shapes.get(i).x, shapes.get(i).y);
                    } else if (i != 0 && !shapes.get(i - 1).isEndOfShape() && shapes.get(i - 1).type == Shape.BRUSH) {
                        g2d.drawLine(shapes.get(i - 1).x, shapes.get(i - 1).y, shapes.get(i).x, shapes.get(i).y);
                    }
                    break;
                case Shape.RECTANGLE:
                    g2d.setStroke(new BasicStroke(shapes.get(i).thickness));
                    g2d.drawRect(shapes.get(i).x, shapes.get(i).y, shapes.get(i).width, shapes.get(i).height);
                    break;
                case Shape.LINE:
                    g2d.setStroke(new BasicStroke(shapes.get(i).thickness));
                    g2d.drawLine(shapes.get(i).x, shapes.get(i).y, shapes.get(i).width, shapes.get(i).height);
                    break;
                case Shape.ERASER:
                    g2d.setStroke(new BasicStroke(shapes.get(i).thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    if (shapes.get(i).pressed) {
                        g2d.drawLine(shapes.get(i).x, shapes.get(i).y, shapes.get(i).x, shapes.get(i).y);
                    } else if (i != 0 && !shapes.get(i - 1).isEndOfShape() && shapes.get(i - 1).type == Shape.ERASER) {
                        g2d.drawLine(shapes.get(i - 1).x, shapes.get(i - 1).y, shapes.get(i).x, shapes.get(i).y);
                    }
                default:
                    break;
            }
        }
        //For shapes not added to list yet
        if (mousePressed) {
            switch (shapeType) {
                case Shape.RECTANGLE:
                    g2d.setColor(shapeColor);
                    g2d.setStroke(new BasicStroke(shapeThickness));
                    calculateShapePoint();
                    g2d.drawRect(startX, startY, width, height);
                    break;
                case Shape.CIRCLE:
                    g2d.setStroke(new BasicStroke(shapeThickness));
                    g2d.setColor(shapeColor);
                    calculateShapePoint();
                    g2d.drawOval(startX, startY, width, height);
                    break;
                case Shape.LINE:
                    g2d.setStroke(new BasicStroke(shapeThickness));
                    g2d.setColor(shapeColor);
                    calculateShapePoint();
                    g2d.drawLine(startX, startY, width, height);
                    break;
                default:
                    break;
            }
        }
    }

    public void zoom(float val) {
        widthScale = val / 20;
        heightScale = val / 20;
        if (widthScale == 0) {
            widthScale = 0.05;
        }
        if (heightScale == 0) {
            heightScale = 0.05;
        }
        scaleCanvas();
    }

    public void scaleCanvas() {
        this.setPreferredSize(new Dimension((int) (sizeWidth * widthScale), (int) (sizeHeight * heightScale)));
        revalidate();
        repaint();
    }

    public void calculateShapePoint() {
        if (shapeType == Shape.LINE) {
            startX = mousePressedX;
            startY = mousePressedY;
            width = mouseDraggedX;
            height = mouseDraggedY;
            return;
        }
        if (mouseDraggedX < mousePressedX) {
            if (mouseDraggedY < mousePressedY) {
                startY = mouseDraggedY;
                height = mousePressedY - mouseDraggedY;
            } else {
                startY = mousePressedY;
                height = mouseDraggedY - mousePressedY;
            }
            startX = mouseDraggedX;
            width = mousePressedX - mouseDraggedX;

        } else if (mouseDraggedY < mousePressedY) {
            startX = mousePressedX;
            startY = mouseDraggedY;
            width = mouseDraggedX - mousePressedX;
            height = mousePressedY - mouseDraggedY;
        } else {
            startX = mousePressedX;
            startY = mousePressedY;
            width = mouseDraggedX - mousePressedX;
            height = mouseDraggedY - mousePressedY;
        }
    }

    public void replaceMouseListener(MouseListener mouseListener) {
        removeMouseListener(this.mouseListener);
        this.mouseListener = mouseListener;
        addMouseListener(this.mouseListener);
    }

    public void replaceMouseMotionListener(MouseMotionListener mouseMotionListener) {
        removeMouseMotionListener(this.mouseMotionListener);
        this.mouseMotionListener = mouseMotionListener;
        addMouseMotionListener(this.mouseMotionListener);
    }
}
