package menubars;

import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFileMenuItem extends JMenuItem implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.NEW_FILE));
    public NewFileMenuItem(PaintGui frame) {
        super("New file");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().resetCanvas();
    }
}
