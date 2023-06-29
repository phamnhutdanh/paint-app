package toolbars;

import models.CanvasModel;
import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPE_TYPE;

import javax.swing.*;
import java.awt.event.*;

public class LineButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private CanvasPanel canvasPanel;
    private CanvasModel canvasModel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.LINE));

    public LineButton(PaintGui frame) {
        super("Line");
        this.setIcon(ICON);
        canvasPanel = frame.getCanvasPanel();
        canvasModel = frame.getCanvasPanel().getCanvasModel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvasModel.setShapeType(SHAPE_TYPE.LINE);
        canvasPanel.replaceMouseListener(this);
        canvasPanel.replaceMouseMotionListener(this);
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
        canvasModel.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasModel.setMousePressed(false);

        Shape shape = new Shape(canvasModel.getStartX(),
                canvasModel.getStartY(),
                canvasModel.getWidth(),
                canvasModel.getHeight(),
                canvasModel.getShapeColor(),
                canvasModel.getShapeThickness(),
                canvasModel.getShapeType());
        canvasModel.addShape(shape);

        if (!canvasModel.getShapes().isEmpty()) {
            canvasModel.setListShapesEndOfShapeAtPosition(canvasModel.getShapes().size() - 1);
            canvasModel.addFilledTempDelay(true);
        }

        if (canvasModel.isMouseDragged()) {
            canvasModel.setMouseDragged(false);
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
        canvasModel.setMouseDraggedX((int) (e.getX() / canvasModel.getWidthScale()));
        canvasModel.setMouseDraggedY((int) (e.getY() / canvasModel.getWidthScale()));
        canvasModel.setMouseDragged(true);
        canvasPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
