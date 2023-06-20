package coordinatebars;

import ui.PaintGui;
import utils.IconSourcePath;

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
    private PaintGui frame;
    private ImageIcon ICON_COORDINATES = new ImageIcon(getClass().getResource(IconSourcePath.COORDINATES));
    private ImageIcon ICON_SIZE_PIC = new ImageIcon(getClass().getResource(IconSourcePath.SIZE_PIC));


    public CoordinateBar(PaintGui frame) {
        this.frame = frame;
        initUI();
        setListeners();
        printPaintPanelSize(frame.getCanvasPanel().getWidth(), frame.getCanvasPanel().getHeight());
    }

    private void initUI() {
        JLabel coordinatePic = new JLabel(ICON_COORDINATES);
        this.add(coordinatePic);
        coordinates = new JLabel();
        coordinates.setText("  0 x 0  ");
        this.add(coordinates);

        Separator separator = new Separator();
        this.add(separator);

        JLabel sizePic = new JLabel(ICON_SIZE_PIC);
        this.add(sizePic);
        frameSize = new JLabel();
        frameSize.setText("  0 x 0  ");
        this.add(frameSize);
        this.setFloatable(false);
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    private void setListeners() {
        frame.getCanvasPanel().addMouseListener(this);
        frame.getCanvasPanel().addMouseMotionListener(this);
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