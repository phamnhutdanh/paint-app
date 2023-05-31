package my_custom_panels;

import buttons.ColorChooserButton;
import buttons.ResetCanvasButton;

import javax.swing.*;
import java.awt.*;

public class ToolBarPanel extends JPanel {
    private final CanvasPanel canvasPanel;

    public ToolBarPanel(CanvasPanel canvasPanel) {
        super();
        this.canvasPanel = canvasPanel;
        init();
    }
    private void init() {
        FlowLayout flowLayout = new FlowLayout();

        this.add(initChooseColorButton());
        this.add(initResetButton());
        this.setLayout(flowLayout);

        this.setPreferredSize(new Dimension(1000, 50));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private JButton initChooseColorButton() {
        return new ColorChooserButton(canvasPanel);
    }
    private JButton initResetButton() {
        return new ResetCanvasButton(canvasPanel);
    }
}
