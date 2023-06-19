package toolbars;

import ui.PaintGui;
import utils.IconSourcePath;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveFileButton extends JButton implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.SAVE));
    private JFileChooser fc;
    public SaveFileButton(PaintGui frame) {
        super("Save file");
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
        if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            frame.getCanvasPanel().setFile(new File(fc.getSelectedFile() + ".png"));
            try {
                saveFile(frame.getCanvasPanel().getFile());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    private void saveFile(File f) throws IOException {

        // ----------------
        // Take all the contents of the jpanel and save them to a png
        // 		destination is the file they selected via the filechooser
        // ----------------
        BufferedImage im = makePanel(frame.getCanvasPanel());
        ImageIO.write(im, "png", f);
    }
    private BufferedImage makePanel(JPanel panel)
    {
        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.print(g);
        return bi;
    }
}
