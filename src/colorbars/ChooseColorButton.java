package colorbars;

import utils.IconSourcePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ChooseColorButton extends JButton implements ActionListener {
    private final ColorChooser colorChooser;
    private Color currentColor = Color.black;

    public ChooseColorButton(ColorChooser colorChooser) {
        super("Choose color");
        ImageIcon ICON = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(IconSourcePath.CHOOSE_COLOR)));
        setIcon(ICON);
        addActionListener(this);

        this.colorChooser = colorChooser;
        this.colorChooser.getResultPanel().setBackground(currentColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentColor = JColorChooser.showDialog(null, "Select a color", Color.BLACK);

        colorChooser.getFrame().getCanvasPanel().getCanvasModel().setShapeColor(currentColor);
        colorChooser.getResultPanel().setBackground(currentColor);
    }
}
