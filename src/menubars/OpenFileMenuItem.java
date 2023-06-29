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

public class OpenFileMenuItem extends JMenuItem implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.OPEN));
    private JFileChooser fc;

    public OpenFileMenuItem(PaintGui frame) {
        super("Open file");
        this.setIcon(ICON);
        this.frame = frame;
        initFileChooser();

        this.addActionListener(this);
    }

    private void initFileChooser() {
        fc = new JFileChooser(new File("."));
        fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        open();
    }

    public void open() {
        CanvasPanel canvasPanel = frame.getCanvasPanel();
        CanvasModel canvas = canvasPanel.getCanvasModel();
        JFileChooser fileChooser = new JFileChooser();
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
            canvas.setSizeHeight(canvas.getImageDefault().getHeight(this)); 
            canvas.setSizeWidth(canvas.getImageDefault().getWidth(this)); 
            canvasPanel.scaleCanvas();
            canvas.setImageOpened(true); 
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
}
