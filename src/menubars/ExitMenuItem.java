package menubars;

import models.CanvasModel;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ExitMenuItem extends JMenuItem implements ActionListener {
    private final CanvasModel canvasModel;
    private final CanvasPanel canvasPanel;

    public ExitMenuItem(PaintGui frame) {
        super("Exit");
        canvasModel = frame.getCanvasPanel().getCanvasModel();
        canvasPanel = frame.getCanvasPanel();
        ImageIcon ICON = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(IconSourcePath.EXIT)));
        this.setIcon(ICON);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (canvasModel.isBlank()) {
            System.exit(0);
        } else {
            String[] options = new String[]{"Save", "Don't save", "Cancel"};
            int response = JOptionPane.showOptionDialog(null,
                    "Are you sure you want to close", "Warning!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response == 1) {
                System.exit(0);
            } else if (response == 0) {
                SaveFileMenuItem.saveFile(canvasPanel, this);
            }
        }
    }

}
