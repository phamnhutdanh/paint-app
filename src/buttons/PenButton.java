package buttons;

import utils.Utils;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static utils.Utils.getIconFromPath;

public class PenButton extends IconButton implements ActionListener {
    private final ImageIcon ICON = getIconFromPath("/assets/ic_pen.png", Utils.ICON_WIDTH, Utils.ICON_HEIGHT);

    public PenButton() {
        super("");
        this.setIcon(ICON);
    }

    private void draw() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        draw();
    }
}
