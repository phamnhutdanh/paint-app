package models;

import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CircleButton extends JButton implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.CIRCLE));

    public CircleButton(PaintGui frame) {
        super("Circle");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.getCanvasPanel().setTool(SHAPES.CIRCLE);
    }
}