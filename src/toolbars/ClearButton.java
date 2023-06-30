package toolbars;

import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClearButton extends JButton implements ActionListener {
    private final PaintGui frame;

    public ClearButton(PaintGui frame) {
        super("Clear");
        ImageIcon ICON = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(IconSourcePath.CLEAR)));
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int reply = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to clear all canvas?", "Clear?",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            frame.getCanvasPanel().resetCanvas();
        }
    }
}
