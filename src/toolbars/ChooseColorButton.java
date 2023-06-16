package toolbars;

import toolbars.ColorChooser;
import ui.PaintGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseColorButton extends JButton implements ActionListener {
    private ColorChooser colorChooser;
    private Color currentColor = Color.black;

    public ChooseColorButton(ImageIcon icon, ColorChooser colorChooser) {
        super("Choose color", icon);
        addActionListener(this);

        this.colorChooser = colorChooser;
        this.colorChooser.getResultPanel().setBackground(currentColor);
        this.colorChooser.setCurrentColor(currentColor);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        currentColor = JColorChooser.showDialog(null, "Select a color", Color.BLACK);

        colorChooser.getFrame().getCanvasPanel().setColor(currentColor);
        colorChooser.getResultPanel().setBackground(currentColor);
        colorChooser.setCurrentColor(currentColor);
    }
    public Color getCurrentColor() {
        return currentColor;
    }
}
