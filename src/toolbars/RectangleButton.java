package toolbars;

import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RectangleButton extends JButton implements ActionListener, MouseListener, MouseMotionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.RECTANGLE));

    public RectangleButton(PaintGui frame) {
        super("Rectangle");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().setTool(SHAPES.RECTANGLE);

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


        int x1 = canvasPanel.getX1(), x2 = canvasPanel.getX2(),
                y1 = canvasPanel.getY1(), y2 = canvasPanel.getY2();
        BasicStroke stroke = canvasPanel.getStroke();
        boolean transparent = canvasPanel.isTransparent();
        if (canvasPanel.isDragged()) {
            if (x1 < x2 && y1 < y2) {
                canvasPanel.pushStackForShapes(new models.Shape(x1, y1, x2 - x1, y2 - y1, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
                //graphics2D.draw(new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1));
            } else if (x2 < x1 && y1 < y2) {
                canvasPanel.pushStackForShapes(new models.Shape(x2, y1, x1 - x2, y2 - y1, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
                //	graphics2D.draw(new Rectangle2D.Double(x2, y1, x1 - x2, y2 - y1));
            } else if (x1 < x2 && y2 < y1) {
                canvasPanel.pushStackForShapes(new models.Shape(x1, y2, x2 - x1, y1 - y2, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
                //graphics2D.draw(new Rectangle2D.Double(x1, y2, x2 - x1, y1 - y2));
            } else if (x2 < x1 && y2 < y1) {
                canvasPanel.pushStackForShapes(new models.Shape(x2, y2, x1 - x2, y1 - y2, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
                //	graphics2D.draw(new Rectangle2D.Double(x2, y2, x1 - x2, y1 - y2));
            }
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

        int x1 = canvasPanel.getX1(), x2 = canvasPanel.getX2(),
                y1 = canvasPanel.getY1(), y2 = canvasPanel.getY2();
        BasicStroke stroke = canvasPanel.getStroke();
        boolean transparent = canvasPanel.isTransparent();

        if (x1 < x2 && y1 < y2) {
            canvasPanel.pushStackForPreview(new models.Shape(x1, y1, x2 - x1, y2 - y1, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
            //graphics2D.draw(new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1));
        } else if (x2 < x1 && y1 < y2) {
            canvasPanel.pushStackForPreview(new models.Shape(x2, y1, x1 - x2, y2 - y1, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
            //graphics2D.draw(new Rectangle2D.Double(x2, y1, x1 - x2, y2 - y1));
        } else if (x1 < x2 && y2 < y1) {
            canvasPanel.pushStackForPreview(new models.Shape(x1, y2, x2 - x1, y1 - y2, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
            //graphics2D.draw(new Rectangle2D.Double(x1, y2, x2 - x1, y1 - y2));
        } else if (x2 < x1 && y2 < y1) {
            canvasPanel.pushStackForPreview(new Shape(x2, y2, x1 - x2, y1 - y2, primary, stroke, SHAPES.RECTANGLE, secondary, transparent));
            //graphics2D.draw(new Rectangle2D.Double(x2, y2, x1 - x2, y1 - y2));
        }
        canvasPanel.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
