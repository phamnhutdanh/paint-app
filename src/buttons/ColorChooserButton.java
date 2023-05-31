package buttons;

import my_custom_panels.CanvasPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooserButton extends JButton {
    private final CanvasPanel canvasPanel;
    public ColorChooserButton(CanvasPanel canvasPanel) {
        super("Color chooser");
        this.canvasPanel = canvasPanel;
        init();
    }
    private void init() {
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null, "Select a color", Color.BLACK);
                setBackground(c);
                canvasPanel.setColor(c);
            }
        });
    }

}
