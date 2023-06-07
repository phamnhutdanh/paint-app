package models;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LineButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.LINE));

    public LineButton(PaintGui frame) {
        super("Line");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().setTool(SHAPES.LINE);

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

        if (canvasPanel.isDragged()) {
            canvasPanel.pushStackForShapes(new Shape(
                    canvasPanel.getX1(),
                    canvasPanel.getY1(),
                    canvasPanel.getX2(),
                    canvasPanel.getY2(),
                    primary,
                    canvasPanel.getStroke(),
                    SHAPES.LINE,
                    fillColor,
                    canvasPanel.isTransparent()));
            canvasPanel.repaint();
            //graphics2D.drawLine(x1, y1, x2, y2);
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


        canvasPanel.pushStackForPreview(new Shape(canvasPanel.getX1(),
                canvasPanel.getY1(),
                canvasPanel.getX2(),
                canvasPanel.getY2(),
                primary,
                canvasPanel.getStroke(),
                SHAPES.LINE,
                secondary,
                canvasPanel.isTransparent()));

        canvasPanel.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
