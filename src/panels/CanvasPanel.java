package panels;

import models.CanvasModel;
import models.Shape;
import utils.SHAPE_TYPE;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {
    private final CanvasModel canvasModel;
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    public CanvasPanel(int sizeWidth, int sizeHeight) {
        super();
        canvasModel = new CanvasModel();
        canvasModel.setSizeWidth(sizeWidth);
        canvasModel.setSizeHeight(sizeHeight);
        this.setPreferredSize(new Dimension(sizeWidth, sizeHeight));
        setBackground(canvasModel.getCanvasColor());
        canvasModel.setShapeColor(Color.black);
        canvasModel.setShapeType(SHAPE_TYPE.BRUSH);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.scale(canvasModel.getWidthScale(), canvasModel.getHeightScale());
        if (canvasModel.isImageOpened()) {
            g2d.drawImage(canvasModel.getImageTemp(), 0, 0, null);
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawShapes(g2d);
        if (canvasModel.isMouseDragged() || canvasModel.isMousePressed()) {
            canvasModel.setBlank(false);
        }
    }

    public void drawShapes(Graphics2D g2d) {
        List<Shape> shapes = canvasModel.getShapes();
        for (int i = 0; i < shapes.size(); i++) {
            g2d.setColor(shapes.get(i).getColor());
            switch (shapes.get(i).getType()) {
                case CIRCLE:
                    g2d.setStroke(new BasicStroke(shapes.get(i).getThickness()));
                    g2d.drawOval(shapes.get(i).getX(), shapes.get(i).getY(), shapes.get(i).getWidth(), shapes.get(i).getHeight());
                    break;
                case BRUSH:
                    g2d.setStroke(new BasicStroke(shapes.get(i).getThickness(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    if (shapes.get(i).isPressed()) {
                        g2d.drawLine(shapes.get(i).getX(), shapes.get(i).getY(), shapes.get(i).getX(), shapes.get(i).getY());
                    } else if (i != 0 && !shapes.get(i - 1).isEndOfShape() && shapes.get(i - 1).getType() == SHAPE_TYPE.BRUSH) {
                        g2d.drawLine(shapes.get(i - 1).getX(), shapes.get(i - 1).getY(), shapes.get(i).getX(), shapes.get(i).getY());
                    }
                    break;
                case RECTANGLE:
                    g2d.setStroke(new BasicStroke(shapes.get(i).getThickness()));
                    g2d.drawRect(shapes.get(i).getX(), shapes.get(i).getY(), shapes.get(i).getWidth(), shapes.get(i).getHeight());
                    break;
                case LINE:
                    g2d.setStroke(new BasicStroke(shapes.get(i).getThickness()));
                    g2d.drawLine(shapes.get(i).getX(), shapes.get(i).getY(), shapes.get(i).getWidth(), shapes.get(i).getHeight());
                    break;
                case ERASER:
                    g2d.setStroke(new BasicStroke(shapes.get(i).getThickness(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    if (shapes.get(i).isPressed()) {
                        g2d.drawLine(shapes.get(i).getX(), shapes.get(i).getY(), shapes.get(i).getX(), shapes.get(i).getY());
                    } else if (i != 0 && !shapes.get(i - 1).isEndOfShape() && shapes.get(i - 1).getType() == SHAPE_TYPE.ERASER) {
                        g2d.drawLine(shapes.get(i - 1).getX(), shapes.get(i - 1).getY(), shapes.get(i).getX(), shapes.get(i).getY());
                    }
                case TEXT:
                    g2d.setStroke(new BasicStroke(shapes.get(i).getThickness()));
                    g2d.setFont(shapes.get(i).getFont());
                    g2d.drawString(shapes.get(i).getMessage(), shapes.get(i).getX(), shapes.get(i).getY());
                    break;
                default:
                    break;
            }
        }

        Color shapeColor = canvasModel.getShapeColor();
        int shapeThickness = canvasModel.getShapeThickness();
        if (canvasModel.isMousePressed()) {
            g2d.setColor(shapeColor);
            g2d.setStroke(new BasicStroke(shapeThickness));
            calculateShapePoint();
            int startX = canvasModel.getStartX(),
                    startY = canvasModel.getStartY(), width = canvasModel.getWidth(),
                    height = canvasModel.getHeight();
            switch (canvasModel.getShapeType()) {
                case RECTANGLE -> g2d.drawRect(startX, startY, width, height);
                case CIRCLE -> g2d.drawOval(startX, startY, width, height);
                case LINE -> g2d.drawLine(startX, startY, width, height);
                default -> {
                }
            }
        }
    }

    public void resetCanvas() {
        canvasModel.clearCanvas();
        setBackground(canvasModel.getCanvasColor());
        repaint();
    }

    public void scaleCanvas() {
        int newWith = (int) (canvasModel.getSizeWidth()*canvasModel.getWidthScale() );
        int newHeight = (int) (canvasModel.getSizeHeight()*canvasModel.getHeightScale());
        this.setPreferredSize(new Dimension(newWith,newHeight));
        this.revalidate();
        this.repaint();
    }
    public void resizeCanvas(int width, int height) {
        canvasModel.setSizeHeight(height);
        canvasModel.setSizeWidth(width);
        if (canvasModel.isImageOpened()) {
            canvasModel.setImageTemp(canvasModel.getImageDefault().getScaledInstance(width,height, Image.SCALE_SMOOTH));
        }
        scaleCanvas();
    }

    private void calculateShapePoint() {
        int mousePressedX = canvasModel.getMousePressedX(), mouseDraggedX = canvasModel.getMouseDraggedX(),
                mousePressedY = canvasModel.getMousePressedY(), mouseDraggedY = canvasModel.getMouseDraggedY();
        if (canvasModel.getShapeType() == SHAPE_TYPE.LINE) {
            canvasModel.setStartX(mousePressedX);
            canvasModel.setStartY(mousePressedY);
            canvasModel.setWidth(mouseDraggedX);
            canvasModel.setHeight(mouseDraggedY);
            return;
        }
        if (mouseDraggedX < mousePressedX) {
            if (mouseDraggedY < mousePressedY) {
                canvasModel.setStartY(mouseDraggedY);
                canvasModel.setHeight(mousePressedY - mouseDraggedY);
            } else {
                canvasModel.setStartY(mousePressedY);
                canvasModel.setHeight(mouseDraggedY - mousePressedY);
            }

            canvasModel.setStartX(mouseDraggedX);
            canvasModel.setWidth(mousePressedX - mouseDraggedX);

        } else if (mouseDraggedY < mousePressedY) {
            canvasModel.setStartX(mousePressedX);
            canvasModel.setStartY(mouseDraggedY);
            canvasModel.setWidth(mouseDraggedX - mousePressedX);
            canvasModel.setHeight(mousePressedY - mouseDraggedY);
        } else {
            canvasModel.setStartX(mousePressedX);
            canvasModel.setStartY(mousePressedY);
            canvasModel.setWidth(mouseDraggedX - mousePressedX);
            canvasModel.setHeight(mouseDraggedY - mousePressedY);
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

    public CanvasModel getCanvasModel() {
        return canvasModel;
    }
}
