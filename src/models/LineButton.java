package models;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LineButton extends JButton implements ActionListener{
    private CanvasPanel canvasPanel;

    public LineButton(ImageIcon icon, CanvasPanel canvasPanel) {
        super("Line", icon);
        this.canvasPanel = canvasPanel;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvasPanel.setTool(SHAPES.LINE);
    }

    public void handleMouseReleased(MouseEvent e) {
        Color currentColor = canvasPanel.getCurrentColor();
        Color fillColor = canvasPanel.getFillColor();
        int x1 = canvasPanel.getX1();
        int x2 = canvasPanel.getX2();
        int y1 = canvasPanel.getY1();
        int y2 = canvasPanel.getY2();
        BasicStroke stroke = canvasPanel.getStroke();
        boolean transparent = canvasPanel.isTransparent();
        boolean dragged = canvasPanel.isDragged();

        canvasPanel.addGroup(1);

        Color primary = currentColor;
        Color secondary = fillColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = currentColor;
        }


        if (dragged) {
            canvasPanel.pushStackForShapes(new Shape(x1, y1, x2, y2, primary, stroke, SHAPES.LINE, fillColor, transparent));
            canvasPanel.repaint();
            //graphics2D.drawLine(x1, y1, x2, y2);
        }

        canvasPanel.setDragged(false);
        canvasPanel.removedALl();
        canvasPanel.repaint();
    }

    public void handleMouseDragged(MouseEvent e) {
        Color currentColor = canvasPanel.getCurrentColor();
        Color fillColor = canvasPanel.getFillColor();
        int x1 = canvasPanel.getX1();
        int x2 = canvasPanel.getX2();
        int y1 = canvasPanel.getY1();
        int y2 = canvasPanel.getY2();
        BasicStroke stroke = canvasPanel.getStroke();
        boolean transparent = canvasPanel.isTransparent();

        Color primary = currentColor;
        Color secondary = fillColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = currentColor;
        }
        canvasPanel.setX2(e.getX());
        canvasPanel.setY2(e.getY());
        canvasPanel.setDragged(true);

        canvasPanel.pushStackForPreview(new Shape(x1, y1, x2, y2, primary, stroke, SHAPES.LINE, secondary, transparent));
        canvasPanel.repaint();
    }
}
