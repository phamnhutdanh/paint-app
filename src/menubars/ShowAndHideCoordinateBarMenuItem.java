package menubars;

import ui.PaintGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowAndHideCoordinateBarMenuItem extends JMenuItem implements ActionListener {
    private final PaintGui frame;
    private boolean isHide;

    public ShowAndHideCoordinateBarMenuItem(PaintGui frame) {
        super("Hide status bar");
        this.frame = frame;
        isHide = false;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCoordinateBar().setVisible(isHide);
        isHide = !isHide;
        if (isHide) {
            setText("Show status bar");
        } else {
            setText("Hide status bar");
        }
    }
}