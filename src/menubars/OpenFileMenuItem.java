package menubars;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.CanvasModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class OpenFileMenuItem extends JMenuItem implements ActionListener {
    private final PaintGui frame;

    public OpenFileMenuItem(PaintGui frame) {
        super("Open file");
        ImageIcon ICON = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(IconSourcePath.OPEN)));
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open();
    }

    public void open() {
        CanvasPanel canvasPanel = frame.getCanvasPanel();
        CanvasModel canvas = canvasPanel.getCanvasModel();
        JFileChooser fileChooser = new JFileChooser(new File("."));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
        int val = fileChooser.showOpenDialog(this);
        String filePath;
        if (val == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return;
        }
        try {
            canvas.setImageDefault(ImageIO.read(new File(filePath)));
            canvas.setImageTemp(canvas.getImageDefault());

            int newWidth = canvas.getImageDefault().getWidth(canvasPanel);
            int newHeight = canvas.getImageDefault().getHeight(canvasPanel);
            canvas.setImageOpened(true);
            canvas.setSizeHeight(newHeight);
            canvas.setSizeWidth(newWidth);
            canvasPanel.scaleCanvas();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
