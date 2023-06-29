package toolbars;

import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButton extends JButton implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.CLEAR));

    public ClearButton(PaintGui frame) {
        super("Clear");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().resetCanvas();
    }
}
