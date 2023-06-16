package models;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EraserButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.ERASE));

    public EraserButton(PaintGui frame) {
        super("Eraser");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().setTool(SHAPES.ERASER);

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
        canvasPanel.setX2(e.getX());
        canvasPanel.setY2(e.getY());
        canvasPanel.setDragged(true);

        int x1 = canvasPanel.getX1(), x2 = canvasPanel.getX2(),
                y1 = canvasPanel.getY1(), y2 = canvasPanel.getY2();
        BasicStroke stroke = canvasPanel.getStroke();
        boolean transparent = canvasPanel.isTransparent();
        int grouped = canvasPanel.getGrouped();
        canvasPanel.pushStackForShapes(new Shape(x1, y1, x2, y2,Color.white,stroke, SHAPES.LINE,grouped));
           canvasPanel.repaint();
           canvasPanel.setX1(x2);
           canvasPanel.setY1(y2);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
