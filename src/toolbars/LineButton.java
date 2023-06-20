package toolbars;

//import listeners.ColorPickerListener;
import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LineButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private PaintGui frame;
    private CanvasPanel canvasPanel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.LINE));

    public LineButton(PaintGui frame) {
        super("Line");
        this.setIcon(ICON);
        this.frame = frame;
        canvasPanel = frame.getCanvasPanel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().shapeType = Shape.LINE;

        frame.getCanvasPanel().replaceMouseListener(this);
        frame.getCanvasPanel().replaceMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //We have to clear redo if shapetype not equal color picker
        canvasPanel.shapesRedo.clear();
        canvasPanel.filledTempsRedo.clear();
        canvasPanel.filledTempDelayRedo.clear();

        canvasPanel.mousePressedX = (int) (e.getX() /  canvasPanel.widthScale);
        canvasPanel.mousePressedY = (int) (e.getY() /  canvasPanel.widthScale);

        canvasPanel.mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasPanel.mousePressed = false;
        canvasPanel.shapes.add(new Shape(canvasPanel.startX,
                canvasPanel.startY,
                canvasPanel.width,
                canvasPanel.height,
                canvasPanel.shapeColor,
                canvasPanel.shapeThickness,
                canvasPanel.shapeType));

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

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        canvasPanel.mouseDraggedX = (int) (e.getX() / canvasPanel.widthScale);
        canvasPanel.mouseDraggedY = (int) (e.getY() / canvasPanel.widthScale);
        canvasPanel.mouseDragged = true;
        canvasPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
