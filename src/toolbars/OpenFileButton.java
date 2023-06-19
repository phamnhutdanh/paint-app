package toolbars;

import ui.PaintGui;
import utils.IconSourcePath;
import utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OpenFileButton extends JButton implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.OPEN));
    private JFileChooser fc;
    private Dimension newDimensions = new Dimension(700, 500);
    public OpenFileButton(PaintGui frame) {
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
        if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            frame.getCanvasPanel().setFile(fc.getSelectedFile());
            openFile(frame.getCanvasPanel().getFile());
        }
    }
    private void openFile(File f) {

        // ----------------
        // update the contents of the jlabel to be the image from the selected file
        // ----------------

        //	Image image = Toolkit.getDefaultToolkit().getImage(f.getPath());
        try {
            frame.getCanvasPanel().setImage(ImageIO.read(f));
            newDimensions = new Dimension(ImageIO.read(f).getWidth(), ImageIO.read(f).getHeight());
            Utils.setDimensions(frame,newDimensions.width,newDimensions.height);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
