package panels;

import models.Shape;
import ui.PaintGui;
import utils.TOOLS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class CanvasPanel extends JPanel implements MouseListener,MouseMotionListener
{
   // private TextDialog td;
    private BasicStroke stroke = new BasicStroke((float) 2);
    BufferedImage canvas;
    Graphics2D graphics2D;
    private TOOLS activeTool = TOOLS.PENCIL;
    //private JLabel label;
    private boolean eraser;
    private PaintGui frame;

    private Stack<Shape> shapes;
    private Stack<Shape> removed;
    private Stack<Shape> preview;

    private int grouped;

    int x1, y1, x2, y2;

    private boolean dragged = false;
    private Color currentColor;
    private Color fillColor;
    private boolean transparent;

    private int inkPanelWidth;
    private int inkPanelHeight;

    public CanvasPanel()
    {
        // construct components
        // contentPane > outerPanel > inkPanel > (Components: clearButton, stroke)
        // configure components
        // add listeners
        // set components into the contentPane
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(250, 250));
        //this.eraser = false;
        this.shapes = new Stack<Shape>();
    }

    //Now for the constructors
    public CanvasPanel(int f, PaintGui frame, int width, int height){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        inkPanelWidth = dim.width - 150;
        inkPanelHeight = dim.height- 160;
        this.setSize(inkPanelWidth - 3, inkPanelHeight - 3); //the 3 accounts for the sp scroller
        this.setPreferredSize(new Dimension(inkPanelWidth - 3,inkPanelHeight - 3));
        this.setLayout(null);
        setDoubleBuffered(true);
        setLocation(10, 10);
        setBackground(Color.WHITE);
        currentColor = Color.BLACK;
        this.fillColor = Color.white;
        setFocusable(true);
        requestFocus();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.frame = frame;
        //this.printPaintPanelSize(inkPanelWidth, inkPanelHeight);
        this.shapes = new Stack<Shape>();
        this.removed = new Stack<Shape>();
        this.grouped = 1;
        this.preview = new Stack<Shape>();
        this.transparent = true;
        //td = new TextDialog(frame);

        //if the mouse is pressed it sets the oldX & oldY
        //coordinates as the mouses x & y coordinates

        //while the mouse is dragged it sets currentX & currentY as the mouses x and y
        //then it draws a line at the coordinates
        //it repaints it and sets oldX and oldY as currentX and currentY
    }

    @Override
    public void paintComponent(Graphics g){
        if(canvas == null){
            canvas = new BufferedImage(inkPanelWidth, inkPanelHeight,BufferedImage.TYPE_INT_ARGB);
            graphics2D = canvas.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(canvas, 0, 0, null);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(Shape s : shapes){

            g2.setColor(s.getColor());
            g2.setStroke(s.getStroke());

            if (s.getShape() == TOOLS.LINE){
                g2.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());
            }
            else if (s.getShape() == TOOLS.RECTANGLE){

                g2.drawRect(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                if(s.getTransparency() == false){
                    g2.setColor(s.getfillColor());
                    g2.fillRect(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                }
            }
            else if (s.getShape() == TOOLS.CIRCLE){
                g2.drawOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                if(s.getTransparency() == false){
                    g2.setColor(s.getfillColor());
                    g2.fillOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                }
            }
            else if (s.getShape() == TOOLS.TEXT) {
                //g2.setFont(s.getFont());
                g2.drawString(s.getMessage(), s.getX1(), s.getY1());
            }
        }
        if (preview.size() > 0){
            Shape s = preview.pop();
            g2.setColor(s.getColor());
            g2.setStroke(s.getStroke());
            if (s.getShape() == TOOLS.LINE){
                g2.drawLine(s.getX1(), s.getY1(), s.getX2(), s.getY2());

            }
            else if (s.getShape() == TOOLS.RECTANGLE){

                g2.drawRect(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                if(s.getTransparency() == false){
                    g2.setColor(s.getfillColor());
                    g2.fillRect(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                }
            }
            else if (s.getShape() == TOOLS.CIRCLE){
                g2.drawOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                if(s.getTransparency() == false){
                    g2.setColor(s.getfillColor());
                    g2.fillOval(s.getX1(), s.getY1(), s.getX2(), s.getY2());
                }
            }

        }

    }

    public void setTool(TOOLS tool) {
        this.activeTool = tool;
    }

    public void setImage(BufferedImage image) {
        graphics2D.dispose();
        this.setInkPanel(image.getWidth(), image.getHeight());
        canvas = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics2D = canvas.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void clear(){
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        shapes.removeAllElements();
        removed.removeAllElements();
        repaint();
        graphics2D.setColor(currentColor);
    }


    public void undo(){
        if(shapes.size()> 0 && shapes.peek().getGroup() == 0){
            removed.push(shapes.pop());
            repaint();
        }
        else if (shapes.size() > 0 && shapes.peek().getGroup() != 0){

            Shape lastRemoved = shapes.pop();
            removed.push(lastRemoved);

            while (shapes.isEmpty() == false && shapes.peek().getGroup() == lastRemoved.getGroup() ){
                removed.push(shapes.pop());
                repaint();

            }

        }
    }
    public void redo(){
        if(removed.size()>0 && removed.peek().getGroup() == 0){
            shapes.push(removed.pop());
            repaint();
        }else if (removed.size() > 0 && removed.peek().getGroup() != 0){

            Shape lastRemoved = removed.pop();
            shapes.push(lastRemoved);

            while (removed.isEmpty() == false && removed.peek().getGroup() == lastRemoved.getGroup() ){
                shapes.push(removed.pop());
                repaint();

            }

        }
    }
    public void setColor(Color c){
        currentColor = c;
        graphics2D.setColor(c);

    }
    public void setFillColor(Color c){
        this.fillColor = c;
    }
    public void setThickness(float f){
        stroke = new BasicStroke(f);
        graphics2D.setStroke(stroke);
    }
    public void setTransparency(Boolean b){
        this.transparent = b;
    }
    public void floodFill(Point2D.Double point, Color fillColor) {
        Color targetColor = new Color(canvas.getRGB((int)point.getX(), (int)point.getY()));
        Queue<Point2D.Double> queue = new LinkedList<Point2D.Double>();
        queue.add(point);
        if (!targetColor.equals(fillColor));
        while (!queue.isEmpty()) {
            Point2D.Double p = queue.remove();

            if ((int)p.getX() >= 0 && (int)p.getX() < canvas.getWidth() &&
                    (int)p.getY() >= 0 && (int)p.getY() < canvas.getHeight())
                if (canvas.getRGB((int)p.getX(), (int)p.getY()) == targetColor.getRGB()) {
                    canvas.setRGB((int)p.getX(), (int)p.getY(), fillColor.getRGB());
                    queue.add(new Point2D.Double(p.getX() - 1, p.getY()));
                    queue.add(new Point2D.Double(p.getX() + 1, p.getY()));
                    queue.add(new Point2D.Double(p.getX(), p.getY() - 1));
                    queue.add(new Point2D.Double(p.getX(), p.getY() + 1));
                    //System.out.println("0");
                }
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        Color primary = currentColor;
        Color secondary = fillColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = currentColor;
        }
       // printCoordinates(e);
        x2 = e.getX();
        y2 = e.getY();
        dragged = true;
        if (activeTool == TOOLS.ERASER){
            shapes.push(new Shape(x1, y1, x2, y2,Color.white,stroke,TOOLS.LINE,grouped));
            repaint();
            x1 = x2;
            y1 = y2;
        }
        if (activeTool == TOOLS.PENCIL) {
            shapes.push(new Shape(x1, y1, x2, y2,primary,stroke,TOOLS.LINE,grouped));
            repaint();
            x1 = x2;
            y1 = y2;
        }
        else if (activeTool == TOOLS.LINE){
            preview.push(new Shape(x1, y1, x2, y2,primary,stroke,TOOLS.LINE,secondary,transparent));
            repaint();
        }
        else if (activeTool == TOOLS.RECTANGLE){
            if (x1 < x2 && y1 < y2) {
                preview.push(new Shape(x1, y1, x2 - x1, y2 - y1,primary,stroke,TOOLS.RECTANGLE,secondary,transparent));
                //graphics2D.draw(new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1));
            }
            else if (x2 < x1 && y1 < y2) {
                preview.push(new Shape(x2, y1, x1 - x2, y2 - y1,primary,stroke,TOOLS.RECTANGLE,secondary,transparent));
                //graphics2D.draw(new Rectangle2D.Double(x2, y1, x1 - x2, y2 - y1));
            }
            else if (x1 < x2 && y2 < y1) {
                preview.push(new Shape(x1, y2, x2 - x1, y1 - y2,primary,stroke,TOOLS.RECTANGLE,secondary,transparent));
                //graphics2D.draw(new Rectangle2D.Double(x1, y2, x2 - x1, y1 - y2));
            }
            else if (x2 < x1 && y2 < y1) {
                preview.push(new Shape(x2, y2, x1 - x2, y1 - y2,primary,stroke,TOOLS.RECTANGLE,secondary,transparent));
                //graphics2D.draw(new Rectangle2D.Double(x2, y2, x1 - x2, y1 - y2));
            }
            repaint();
        }
        else if (activeTool == TOOLS.CIRCLE) {
            if (x1 < x2 && y1 < y2) {
                preview.push(new Shape(x1, y1, x2 - x1, y2 - y1,primary,stroke,TOOLS.CIRCLE,secondary,transparent));
                //graphics2D.draw(new Ellipse2D.Double(x1, y1, x2 - x1, y2 - y1));
            }
            else if (x2 < x1 && y1 < y2) {
                preview.push(new Shape(x2, y1, x1 - x2, y2 - y1,primary,stroke,TOOLS.CIRCLE,secondary,transparent));
                //graphics2D.draw(new Ellipse2D.Double(x2, y1, x1 - x2, y2 - y1));
            }
            else if (x1 < x2 && y2 < y1) {
                preview.push(new Shape(x1, y2, x2 - x1, y1 - y2,primary,stroke,TOOLS.CIRCLE,secondary,transparent));
                //graphics2D.draw(new Ellipse2D.Double(x1, y2, x2 - x1, y1 - y2));
            }
            else if (x2 < x1 && y2 < y1) {
                preview.push(new Shape(x2, y2, x1 - x2, y1 - y2,primary,stroke,TOOLS.CIRCLE,secondary,transparent));
                //graphics2D.draw(new Ellipse2D.Double(x2, y2, x1 - x2, y1 - y2));
            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //printCoordinates(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //printCoordinates(e);
        x1 = e.getX();
        y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        grouped++;
        // TODO Auto-generated method stub
        Color primary = currentColor;
        Color secondary = fillColor;
        if (SwingUtilities.isRightMouseButton(e)) {
            primary = secondary;
            secondary = currentColor;
        }

        if (activeTool == TOOLS.LINE && dragged) {
            shapes.push(new Shape(x1, y1, x2, y2,primary,stroke,TOOLS.LINE,fillColor,transparent));
            repaint();
            //graphics2D.drawLine(x1, y1, x2, y2);
        }
        else if (activeTool == TOOLS.RECTANGLE && dragged) {

            if (x1 < x2 && y1 < y2) {
                shapes.push(new Shape(x1, y1, x2 - x1, y2 - y1,primary,stroke,TOOLS.RECTANGLE,secondary, transparent));
                //graphics2D.draw(new Rectangle2D.Double(x1, y1, x2 - x1, y2 - y1));
            }
            else if (x2 < x1 && y1 < y2) {
                shapes.push(new Shape(x2, y1, x1 - x2, y2 - y1,primary,stroke,TOOLS.RECTANGLE,secondary, transparent));
                //	graphics2D.draw(new Rectangle2D.Double(x2, y1, x1 - x2, y2 - y1));
            }
            else if (x1 < x2 && y2 < y1) {
                shapes.push(new Shape(x1, y2, x2 - x1, y1 - y2,primary,stroke,TOOLS.RECTANGLE,secondary, transparent));
                //graphics2D.draw(new Rectangle2D.Double(x1, y2, x2 - x1, y1 - y2));
            }
            else if (x2 < x1 && y2 < y1) {
                shapes.push(new Shape(x2, y2, x1 - x2, y1 - y2,primary,stroke,TOOLS.RECTANGLE,secondary, transparent));
                //	graphics2D.draw(new Rectangle2D.Double(x2, y2, x1 - x2, y1 - y2));
            }

        }
        else if (activeTool == TOOLS.CIRCLE && dragged) {
            if (x1 < x2 && y1 < y2) {
                shapes.push(new Shape(x1, y1, x2 - x1, y2 - y1,primary,stroke,TOOLS.CIRCLE,secondary, transparent));
                //	graphics2D.draw(new Ellipse2D.Double(x1, y1, x2 - x1, y2 - y1));
            }
            else if (x2 < x1 && y1 < y2) {
                shapes.push(new Shape(x2, y1, x1 - x2, y2 - y1,primary,stroke,TOOLS.CIRCLE,secondary, transparent));
                //graphics2D.draw(new Ellipse2D.Double(x2, y1, x1 - x2, y2 - y1));
            }
            else if (x1 < x2 && y2 < y1) {
                shapes.push(new Shape(x1, y2, x2 - x1, y1 - y2,primary,stroke,TOOLS.CIRCLE,secondary, transparent));
                //graphics2D.draw(new Ellipse2D.Double(x1, y2, x2 - x1, y1 - y2));
            }
            else if (x2 < x1 && y2 < y1) {
                shapes.push(new Shape(x2, y2, x1 - x2, y1 - y2,primary,stroke,TOOLS.CIRCLE,secondary, transparent));
                //	graphics2D.draw(new Ellipse2D.Double(x2, y2, x1 - x2, y1 - y2));
            }
        }
        else if (activeTool == TOOLS.SELECT){

        }
        else if (activeTool == TOOLS.TEXT){
           /* int i = td.showCustomDialog(frame);
            if (i == TextDialog.APPLY_OPTION) {
                shapes.push(new Shape(x1, y1, td.getInputSize(), td.getFont(), primary, stroke, 5, td.getText())); //int x1, int y1, int fontSize, Color color, Font font
            }
*/

        }
        else if (activeTool == TOOLS.FILL) {
            floodFill(new Point2D.Double(x1, y1), fillColor);
        }
        dragged = false;
        removed.removeAllElements();
        repaint();
    }

    public void setInkPanelWidth(int width)
    {
        this.inkPanelWidth = width;
    }
    public void setInkPanelHeight(int height)
    {
        this.inkPanelHeight = height;
    }
    public Color getCurrentColor() {
        return currentColor;
    }
    public Color getFillColor() {
        return fillColor;
    }
    public int getX1() {
        return x1;
    }
    public int getX2() {
        return x2;
    }
    public int getY1() {
        return y1;
    }
    public int getY2() {
        return y2;
    }
    public boolean isDragged() {
        return dragged;
    }
    public void setDragged(boolean dragged){
        this.dragged = dragged;
    }
    public void setX1(int x1){
        this.x1 = x1;
    }
    public void setX2(int x2){
        this.x2 = x2;
    }
    public void setY1(int Y1){
        this.y1 = y1;
    }
    public void setY2(int y2){
        this.y2 = y2;
    }
    public BasicStroke getStroke() {
        return stroke;
    }
    public boolean isTransparent() {
        return transparent;
    }
    public void pushStackForPreview(Shape shape){
        preview.push(shape);
    }
    public void pushStackForShapes(Shape shape){
        shapes.push(shape);
    }
    public void addGroup(int number){
        grouped +=number;
    }
    public void removedALl(){
        removed.removeAllElements();
    }
    public BufferedImage getCanvas() {
        return canvas;
    }
    public void setCanvas(BufferedImage canvas){
        this.canvas = canvas;
    }
    public int getPanelWidth() {
        return inkPanelWidth;
    }
    public int getPanelHeight() {
        return inkPanelHeight;
    }
    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        this.graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public Stack<Shape> getShapes() {
        return shapes;
    }

    public Stack<Shape> getPreview() {
        return preview;
    }
    public Shape popPreview() {
        return preview.pop();
    }

    public void setInkPanel(int width, int height)
    {
        canvas = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        graphics2D = canvas.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //this.printPaintPanelSize(width, height);
        this.setSize(width-3, height-3);
        this.setPreferredSize(new Dimension(width - 3, height - 3));
        clear();

    }

}