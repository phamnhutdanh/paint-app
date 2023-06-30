package toolbars;

import models.CanvasModel;
import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPE_TYPE;

import javax.swing.*;
import java.awt.event.*;

public class BrushButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private CanvasPanel canvasPanel;
    private CanvasModel canvasModel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.BRUSH));

    public BrushButton(PaintGui frame) {
        super("Pencil");
        this.setIcon(ICON);
        canvasPanel = frame.getCanvasPanel();
        canvasModel = frame.getCanvasPanel().getCanvasModel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvasModel.setShapeType(SHAPE_TYPE.BRUSH);
        canvasPanel.replaceMouseListener(this);
        canvasPanel.replaceMouseMotionListener(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if ((canvasModel.getMouseDraggedX() != (int) (e.getX() / canvasModel.getWidthScale()) ||
                canvasModel.getMouseDraggedY() != (int) (e.getY() / canvasModel.getWidthScale()))) {

            canvasModel.addShape(new Shape(
                    (int) (e.getX() / canvasModel.getWidthScale()),
                    (int) (e.getY() / canvasModel.getWidthScale()),
                    canvasModel.getShapeColor(),
                    canvasModel.getShapeThickness(),
                    canvasModel.getShapeType()));

        }

        canvasModel.setMouseDraggedX((int) (e.getX() / canvasModel.getWidthScale()));
        canvasModel.setMouseDraggedY((int) (e.getY() / canvasModel.getWidthScale()));
        canvasModel.setMouseDragged(true);
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
        canvasModel.clearShapeRedo();
        canvasModel.clearFilledTempsRedo();
        canvasModel.clearFilledTempDelayRedo();

        canvasModel.setMousePressedX((int) (e.getX() / canvasModel.getWidthScale()));
        canvasModel.setMousePressedY((int) (e.getY() / canvasModel.getWidthScale()));

        canvasModel.addShape(new Shape(canvasModel.getMousePressedX(),
                canvasModel.getMousePressedY(),
                canvasModel.getShapeColor(),
                canvasModel.getShapeThickness(),
                canvasModel.getShapeType()));

        canvasModel.addFilledTempDelay(true);
        canvasModel.setShapesPressedAtPosition(canvasModel.getShapes().size() - 1, true);

        canvasPanel.repaint();
        canvasModel.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasModel.setMousePressed(false);

        if (!canvasModel.getShapes().isEmpty()) {
            canvasModel.getShapes().get(canvasModel.getShapes().size() - 1).setEndOfShape();
            canvasModel.addFilledTempDelay(true);
        }
        if (canvasModel.isMouseDragged()) {
            canvasModel.setMouseDragged(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }
}
