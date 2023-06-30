package coordinatebars;

import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

public class CoordinateBar extends JToolBar implements MouseListener, MouseMotionListener {
    private JLabel coordinates;
    private JLabel frameSize;
    private final PaintGui frame;
    private final ImageIcon ICON_COORDINATES = new ImageIcon(Objects.requireNonNull(getClass().getResource(IconSourcePath.COORDINATES)));
    private final ImageIcon ICON_SIZE_PIC = new ImageIcon(Objects.requireNonNull(getClass().getResource(IconSourcePath.SIZE_PIC)));

    public CoordinateBar(PaintGui frame) {
        super(JToolBar.HORIZONTAL);
        this.frame = frame;
        initUI();
        setListeners();
        printPaintPanelSize(frame.getCanvasPanel().getCanvasModel().getSizeWidth(), frame.getCanvasPanel().getCanvasModel().getSizeHeight());
    }

    private void initUI() {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(flowLayout);

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

        ZoomPanel zoomPanel = new ZoomPanel(frame);
        this.add(zoomPanel);
    }

    private void setListeners() {
        frame.getCanvasPanel().addMouseListener(this);
        frame.getCanvasPanel().addMouseMotionListener(this);
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