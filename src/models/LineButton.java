package models;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.TOOLS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class LineButton extends JButton implements ActionListener, MouseMotionListener, MouseListener {
    private PaintGui paintGui;
    public  LineButton(ImageIcon icon, PaintGui paintGui) {
        super("Line", icon);
        this.paintGui = paintGui;
        this.addActionListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paintGui.getCanvasPanel().setTool(TOOLS.LINE);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        CanvasPanel canvasPanel = paintGui.getCanvasPanel();

        Color currentColor = canvasPanel.getCurrentColor();
        Color fillColor = canvasPanel.getFillColor();
        int x1 =canvasPanel.getX1();
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
            canvasPanel.pushStackForShapes(new Shape(x1, y1, x2, y2,primary,stroke, TOOLS.LINE,fillColor,transparent));
            canvasPanel.repaint();
            //graphics2D.drawLine(x1, y1, x2, y2);
        }

        canvasPanel.setDragged(false);
        canvasPanel.removedALl();
        canvasPanel.repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        CanvasPanel canvasPanel = paintGui.getCanvasPanel();

        BufferedImage canvas = canvasPanel.getCanvas();

        if(canvas == null){
            canvasPanel.setCanvas(new BufferedImage(canvasPanel.getPanelWidth(), canvasPanel.getPanelHeight(),BufferedImage.TYPE_INT_ARGB));

            canvasPanel.setGraphics2D(canvas.createGraphics());
            canvasPanel.clear();
        }
        g.drawImage(canvas, 0, 0, null);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(Shape s : canvasPanel.getShapes()){
            g2.setColor(s.getColor());
            g2.setStroke(s.getStroke());
            g2.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
        }
        if (canvasPanel.getPreview().size() > 0){
            Shape s = canvasPanel.popPreview();
            g2.setColor(s.getColor());
            g2.setStroke(s.getStroke());
            g2.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
        }
    }

    public void mouseDragged(MouseEvent e) {
        CanvasPanel canvasPanel = paintGui.getCanvasPanel();

        Color currentColor = canvasPanel.getCurrentColor();
        Color fillColor = canvasPanel.getFillColor();
        int x1 =canvasPanel.getX1();
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

        canvasPanel.pushStackForPreview(new Shape(x1, y1, x2, y2,primary,stroke,TOOLS.LINE,secondary,transparent));
        canvasPanel.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
