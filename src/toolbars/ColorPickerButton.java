package toolbars;

//import listeners.ColorPickerListener;
import models.Shape;
import panels.CanvasPanel;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorPickerButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private ColorChooser colorChooser;
    private Color currentColor;
    private CanvasPanel canvasPanel;

    public ColorPickerButton(ColorChooser colorChooser) {
        super("Color Picker");
        this.colorChooser = colorChooser;
        canvasPanel = colorChooser.getFrame().getCanvasPanel();
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvasPanel.shapeType = Shape.COLOR_PICKER;
        canvasPanel.replaceMouseListener(this);
        canvasPanel.replaceMouseMotionListener(this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        canvasPanel.mouseDraggedX = (int) (e.getX() / canvasPanel.widthScale);
        canvasPanel.mouseDraggedY = (int) (e.getY() / canvasPanel.widthScale);
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
        canvasPanel.mousePressedX = (int) (e.getX() / canvasPanel.widthScale);
        canvasPanel.mousePressedY = (int) (e.getY() / canvasPanel.widthScale);
        canvasPanel.mousePressed = true;

        canvasPanel.shapeColor = canvasPanel.robot.getPixelColor(MouseInfo.getPointerInfo().getLocation().x,
                MouseInfo.getPointerInfo().getLocation().y);
        colorChooser.getResultPanel().setBackground(canvasPanel.shapeColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canvasPanel.mousePressed = false;
        if (canvasPanel.mouseDragged) {
            canvasPanel.mouseDragged = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

}
