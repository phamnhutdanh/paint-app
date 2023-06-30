package menubars;

import models.CanvasModel;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExitMenuItem extends  JMenuItem implements ActionListener{
    private CanvasModel canvasModel;
    private CanvasPanel canvasPanel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.EXIT));
    public ExitMenuItem(PaintGui frame) {
        super("Exit");
        canvasModel = frame.getCanvasPanel().getCanvasModel();
        canvasPanel = frame.getCanvasPanel();
        this.setIcon(ICON);
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (canvasModel.isBlank()) {
            System.exit(0);
        }
        else {
            String[] options = new String[] {"Save", "Don't save", "Cancel"};
            int response = JOptionPane.showOptionDialog(null,
                    "Are you sure you want to close", "Warning!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response == 1)
            {
                System.exit(0);
            }
            else if (response == 0) {
               SaveFileMenuItem.saveFile(canvasPanel, this);
            }
        }
    }

}
