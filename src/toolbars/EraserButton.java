package toolbars;

import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EraserButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private PaintGui frame;
    private CanvasPanel canvasPanel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.ERASE));

    public EraserButton(PaintGui frame) {
        super("Eraser");
        this.setIcon(ICON);
        this.frame = frame;
        canvasPanel = frame.getCanvasPanel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().shapeType = Shape.ERASER;

        frame.getCanvasPanel().replaceMouseListener(this);
        frame.getCanvasPanel().replaceMouseMotionListener(this);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (( canvasPanel.mouseDraggedX != (int) (e.getX() /  canvasPanel.widthScale) ||
                canvasPanel.mouseDraggedY != (int) (e.getY() /  canvasPanel.widthScale))) {
            canvasPanel.shapes.add(new Shape((int) (e.getX() /  canvasPanel.widthScale),
                    (int) (e.getY() /  canvasPanel.widthScale),
                    canvasPanel.canvasColor,
                    canvasPanel.shapeThickness,
                    canvasPanel.shapeType));

        }
        canvasPanel.mouseDraggedX = (int) (e.getX() /  canvasPanel.widthScale);
        canvasPanel.mouseDraggedY = (int) (e.getY() /  canvasPanel.widthScale);
        canvasPanel.mouseDragged = true;
        canvasPanel.repaint();
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        canvasPanel.shapesRedo.clear();
        canvasPanel.filledTempsRedo.clear();
        canvasPanel.filledTempDelayRedo.clear();

        canvasPanel.mousePressedX = (int) (e.getX() / canvasPanel.widthScale);
        canvasPanel.mousePressedY = (int) (e.getY() / canvasPanel.widthScale);

        canvasPanel.shapes.add(new Shape(canvasPanel.mousePressedX,
                canvasPanel.mousePressedY,
                canvasPanel.canvasColor,
                canvasPanel.shapeThickness,
                canvasPanel.shapeType));
        canvasPanel.filledTempDelay.add(true);
        canvasPanel.shapes.get(canvasPanel.shapes.size() - 1).pressed = true;
        canvasPanel.repaint();

        canvasPanel.mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        canvasPanel.mousePressed = false;
        if (!canvasPanel.shapes.isEmpty()) {
            canvasPanel.shapes.get(canvasPanel.shapes.size() - 1).setEndOfShape();
            canvasPanel.filledTempDelay.add(true);
        }
        if (canvasPanel.mouseDragged) {
            canvasPanel.mouseDragged = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

}
