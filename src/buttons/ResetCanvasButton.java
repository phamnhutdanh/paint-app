package buttons;

import my_custom_panels.CanvasPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetCanvasButton extends JButton {
    private final CanvasPanel canvasPanel;
    public ResetCanvasButton(CanvasPanel canvasPanel) {
        super("Reset button");
        this.canvasPanel= canvasPanel;
        init();
    }
    private void init() {
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasPanel.resetCanvas();
            }
        });
    }
}
