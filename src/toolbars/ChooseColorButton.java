package toolbars;

import toolbars.ColorChooser;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseColorButton extends JButton implements ActionListener {
    private ColorChooser colorChooser;
    private Color currentColor = Color.black;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.CHOOSE_COLOR));

    public ChooseColorButton(ColorChooser colorChooser) {
        super("Choose color");
        setIcon(ICON);
        addActionListener(this);

        this.colorChooser = colorChooser;
        this.colorChooser.getResultPanel().setBackground(currentColor);
        this.colorChooser.setCurrentColor(currentColor);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        currentColor = JColorChooser.showDialog(null, "Select a color", Color.BLACK);

        // TODO: fix here
        colorChooser.getFrame().getCanvasPanel().shapeColor = currentColor;

        colorChooser.getResultPanel().setBackground(currentColor);
        colorChooser.setCurrentColor(currentColor);
    }
    public Color getCurrentColor() {
        return currentColor;
    }
}
