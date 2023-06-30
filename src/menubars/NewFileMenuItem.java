package menubars;

import models.CanvasModel;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFileMenuItem extends JMenuItem implements ActionListener {
    private CanvasPanel canvasPanel;
    private CanvasModel canvasModel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.NEW_FILE));
    public NewFileMenuItem(PaintGui frame) {
        super("New file");
        this.setIcon(ICON);
        canvasPanel = frame.getCanvasPanel();
        canvasModel = frame.getCanvasPanel().getCanvasModel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!canvasModel.isBlank()) {
            String[] options = new String[] {"Save", "Don't save", "Cancel"};
            int response = JOptionPane.showOptionDialog(null,
                    "Your current file isn't save?", "Warning!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response == 0) {
                SaveFileMenuItem.saveFile(canvasPanel, this);
            }
            else if (response == 2) {
                return;
            }
        }
        canvasPanel.resetCanvas();
    }
}
