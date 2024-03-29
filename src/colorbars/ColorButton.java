package colorbars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorButton extends JButton implements ActionListener {
    private final Color color;
    private final ColorChooser colorChooser;

    public ColorButton(Color color, Dimension buttonSize, ColorChooser colorChooser) {
        super("");
        this.color = color;
        this.colorChooser = colorChooser;

        setPreferredSize(buttonSize);
        setBackground(color);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        colorChooser.getFrame().getCanvasPanel().getCanvasModel().setShapeColor(color);

        colorChooser.getResultPanel().setBackground(color);
    }

    public Color getColor() {
        return color;
    }
}
