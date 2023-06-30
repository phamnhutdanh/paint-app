package toolbars;

import models.CanvasModel;
import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPE_TYPE;

import javax.swing.*;
import java.awt.event.*;

public class TextButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private PaintGui frame;
    private CanvasPanel canvasPanel;
    private CanvasModel canvasModel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.TEXT));

    public TextButton(PaintGui frame) {
        super("Text");
        this.setIcon(ICON);
        this.frame = frame;
        canvasPanel = frame.getCanvasPanel();
        canvasModel = frame.getCanvasPanel().getCanvasModel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvasModel.setShapeType(SHAPE_TYPE.TEXT);
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

        TextDialog textDialog =new TextDialog(frame);
        int i = textDialog.showCustomDialog(frame);
        if (i == TextDialog.APPLY_OPTION) {
            canvasModel.addShape(new Shape(
                    canvasModel.getMousePressedX(),
                    canvasModel.getMousePressedY(),
                    textDialog.getFont(),
                    canvasModel.getShapeColor(),
                    canvasModel.getShapeThickness(),
                    textDialog.getText(),
                    canvasModel.getShapeType()));
        }

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
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
