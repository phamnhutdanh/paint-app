package menubars;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.CanvasModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveFileMenuItem extends JMenuItem implements ActionListener {
    private CanvasPanel canvasPanel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.SAVE));
    private JFileChooser fc;
    public SaveFileMenuItem(PaintGui frame) {
        super("Save file");
        canvasPanel =frame.getCanvasPanel();
        this.setIcon(ICON);
        initFileChooser();

        this.addActionListener(this);
    }
    private void initFileChooser() {
        fc = new JFileChooser(new File("."));
        fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       saveFile(canvasPanel, this);
    }

    public static void saveFile(CanvasPanel canvasPanel, Component parent) {
        CanvasModel canvas = canvasPanel.getCanvasModel();
        double oldWidthScale = canvas.getWidthScale();
        double oldHeightScale = canvas.getHeightScale();
        canvas.setWidthScale(1); 
        canvas.setHeightScale(1); 
        canvasPanel.scaleCanvas();
        JFileChooser fileChooser = new JFileChooser();
        int val = fileChooser.showSaveDialog(parent);
        String filePath;
        if (val == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return;
        }
        BufferedImage image = new BufferedImage(canvas.getSizeWidth(), canvas.getSizeHeight(), BufferedImage.TYPE_INT_RGB);
        canvasPanel.printAll(image.getGraphics());
        canvas.setWidthScale(oldWidthScale); 
        canvas.setHeightScale(oldHeightScale); 
        canvasPanel.scaleCanvas();
        try {
            ImageIO.write(image, "png", new File(filePath + ".png"));
            String[] options = new String[] {"Ok"};
            JOptionPane.showOptionDialog(null, "Save file successful", "Success",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            String[] options = new String[] {"Ok"};
            JOptionPane.showOptionDialog(null, ex.getMessage(), "Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
        }
    }

}
