package buttons;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IconButton extends JButton {

    public IconButton(String title) {
        super(title);
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(Utils.ICON_WIDTH, Utils.ICON_HEIGHT));
        this.setBackground(null);
        //this.setBorder(null);

        this.setBorderPainted(false);
        this.setFocusPainted(true);
        this.setContentAreaFilled(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setListeners();
    }

    private void setListeners() {
        changeBorderMousePressed();
    }
    private void changeBorderMousePressed() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setBorder(null);
            }
        });
    }
}
