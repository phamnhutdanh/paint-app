package menubars;

import ui.PaintGui;
import utils.IconSourcePath;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ResizeCanvasMenuItem extends JMenuItem implements ActionListener {
    private final PaintGui frame;
    private Dimension newDimensions = new Dimension(700, 500);

    public ResizeCanvasMenuItem(PaintGui frame) {
        super("New size");
        ImageIcon ICON = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(IconSourcePath.NEW_SIZE)));
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resizeCanvas();
    }

    private void resizeCanvas() {
        JFrame newFileFrame = new JFrame();
        newFileFrame.setTitle("New");
        newFileFrame.setBackground(Color.GRAY);
        newFileFrame.setSize(400, 200);
        newFileFrame.setPreferredSize(new Dimension(400, 200));
        newFileFrame.setLayout(null);
        newFileFrame.setResizable(false);
        newFileFrame.pack();

        // put the frame in the middle of the display
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        newFileFrame.setLocation(dim.width / 2 - newFileFrame.getSize().width / 2, dim.height / 2 - newFileFrame.getSize().height / 2);

        newFileFrame.setVisible(true);

        JTextField width = new JTextField();
        width.setLocation(100, 25);
        width.setSize(200, 25);

        JLabel widthLabel = new JLabel("Width (px):");
        widthLabel.setSize(75, 25);
        widthLabel.setLocation(25, 25);

        JLabel heightLabel = new JLabel("Height (px):");
        heightLabel.setSize(75, 25);
        heightLabel.setLocation(25, 75);

        JTextField height = new JTextField();
        height.setLocation(100, 75);
        height.setSize(200, 25);

        JButton okay = new JButton("OK");
        okay.setLocation(250, 125);
        okay.setSize(75, 25);
        okay.addActionListener(e -> {
                    try {
                        newDimensions = new Dimension(Integer.parseInt(width.getText()),
                                Integer.parseInt(height.getText()));
                        System.out.println(newDimensions);

                        frame.getCanvasPanel().resizeCanvas(newDimensions.width, newDimensions.height);
                        Utils.setDimensions(frame, newDimensions.width, newDimensions.height);
                        newFileFrame.dispose();
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid numeric entry. A proper integer is required.",
                                "New",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
        );

        JButton cancel = new JButton("Cancel");
        cancel.setSize(75, 25);
        cancel.setLocation(150, 125);
        cancel.addActionListener(e -> newFileFrame.dispose());

        newFileFrame.add(heightLabel);
        newFileFrame.add(widthLabel);
        newFileFrame.add(width);
        newFileFrame.add(height);
        newFileFrame.add(okay);
        newFileFrame.add(cancel);
    }
}
