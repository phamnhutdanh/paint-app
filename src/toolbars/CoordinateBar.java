package toolbars;

import panels.CanvasPanel;
import ui.PaintGui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class CoordinateBar extends JToolBar implements MouseListener, MouseMotionListener {
    private JLabel coordinates;
    private JLabel frameSize;
    private Separator separator;
    private CanvasPanel canvasPanel;

    public CoordinateBar(CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;
        initUI();
        setListeners();
        printPaintPanelSize(canvasPanel.getWidth(), canvasPanel.getHeight());
    }

    private void initUI() {
        JLabel coordinatePic = new JLabel(new ImageIcon(getClass().getResource("/icons/coordinates.png")));
        this.add(coordinatePic);
        coordinates = new JLabel();
        coordinates.setText("  0 x 0  ");
        this.add(coordinates);

        separator = new Separator();
        this.add(separator);

        JLabel sizePic = new JLabel(new ImageIcon(getClass().getResource("/icons/size.png")));
        this.add(sizePic);
        frameSize = new JLabel();
        frameSize.setText("  0 x 0  ");
        this.add(frameSize);
        this.setFloatable(false);
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    private void setListeners() {
        canvasPanel.addMouseListener(this);
        canvasPanel.addMouseMotionListener(this);
    }

    public JLabel getCoordinates() {
        return coordinates;
    }

    public JLabel getFrameSize() {
        return frameSize;
    }

    public JToolBar getCoordinateBar() {
        return this;
    }

    public void printCoordinates(MouseEvent e) {
        String posX = String.valueOf((int) e.getPoint().getX());
        String posY = String.valueOf((int) e.getPoint().getY());
        coordinates.setText(posX + ",  " + posY + " px");
    }
    // print drawer panel size at status tool bar
    public void printPaintPanelSize(int width, int height) {
        frameSize.setText(width + ",  " + height + " px");
    }
    @Override
    public void mouseClicked(MouseEvent e) {

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
    public void mousePressed(MouseEvent e) {
        printCoordinates(e);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        printCoordinates(e);
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        printCoordinates(e);
    }
}