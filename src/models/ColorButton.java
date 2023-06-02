package models;

import panels.CanvasPanel;
import toolbars.ColorChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorButton extends JButton implements ActionListener {
    private Color color;

    public ColorButton(Color color, Dimension buttonSize) {
        super("");
        this.color = color;

        setPreferredSize(buttonSize);
        setBackground(color);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton b = (JButton) e.getSource();

        /*secondaryColor.setBackground(b.getBackground());

        frame.getInkPanel().setColor(primaryColor.getBackground());
        frame.getInkPanel().setFillColor(secondaryColor.getBackground());*/

    }
}
