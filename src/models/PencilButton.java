package models;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PencilButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.PENCIL));

    public PencilButton(PaintGui frame) {
        super("Pencil");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().setTool(SHAPES.PENCIL);

        frame.getCanvasPanel().replaceMouseListener(this);
        frame.getCanvasPanel().replaceMouseMotionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        frame.getCanvasPanel().setX1(e.getX());
        frame.getCanvasPanel().setY1(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        CanvasPanel canvasPanel = frame.getCanvasPanel();
        Color currentColor = canvasPanel.getCurrentColor();
        Color fillColor = canvasPanel.getFillColor();

        canvasPanel.addGroup(1);

        Color primary = currentColor;
        Color secondary = fillColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = currentColor;
        }

        canvasPanel.setDragged(false);
        canvasPanel.removedALl();
        canvasPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        CanvasPanel canvasPanel = frame.getCanvasPanel();
        Color currentColor = canvasPanel.getCurrentColor();
        Color fillColor = canvasPanel.getFillColor();

        Color primary = currentColor;
        Color secondary = fillColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = currentColor;
        }
        // printCoordinates(e);

        canvasPanel.setX2(e.getX());
        canvasPanel.setY2(e.getY());
        canvasPanel.setDragged(true);

        canvasPanel.pushStackForShapes(new Shape(
                canvasPanel.getX1(),
                canvasPanel.getY1(),
                canvasPanel.getX2(),
                canvasPanel.getY2(),
                primary,
                canvasPanel.getStroke(),
                SHAPES.LINE,
                canvasPanel.getGrouped()));
        canvasPanel.repaint();
        canvasPanel.setX1(canvasPanel.getX2());
        canvasPanel.setY1(canvasPanel.getY2());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
