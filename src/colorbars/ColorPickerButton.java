package colorbars;

import panels.CanvasPanel;
import utils.IconSourcePath;
import utils.SHAPE_TYPE;

import javax.swing.*;

import models.CanvasModel;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ColorPickerButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private final ColorChooser colorChooser;
    private final CanvasPanel canvasPanel;
    private final CanvasModel canvasModel;

    public ColorPickerButton(ColorChooser colorChooser) {
        super("Color Picker");
        ImageIcon ICON = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(IconSourcePath.COLOR_PICKER)));
        setIcon(ICON);
        this.colorChooser = colorChooser;
        canvasPanel = colorChooser.getFrame().getCanvasPanel();
        canvasModel = colorChooser.getFrame().getCanvasPanel().getCanvasModel();
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvasModel.setShapeType(SHAPE_TYPE.COLOR_PICKER);
        canvasPanel.replaceMouseListener(this);
        canvasPanel.replaceMouseMotionListener(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
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
        canvasModel.setMousePressedX((int) (e.getX() / canvasModel.getWidthScale()));
        canvasModel.setMousePressedY((int) (e.getY() / canvasModel.getWidthScale()));
        canvasModel.setMousePressed(true);

        Color color = canvasModel.getRobot().getPixelColor(MouseInfo.getPointerInfo().getLocation().x,
                MouseInfo.getPointerInfo().getLocation().y);
        canvasModel.setShapeColor(color);
        colorChooser.getResultPanel().setBackground(canvasModel.getShapeColor());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasModel.setMousePressed(false);
        if (canvasModel.isMouseDragged()) {
            canvasModel.setMouseDragged(false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

}
