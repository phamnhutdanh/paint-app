package models;

import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineButton extends JButton implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.LINE));

    public LineButton(PaintGui frame) {
        super("Line");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().setTool(SHAPES.LINE);
    }
}
