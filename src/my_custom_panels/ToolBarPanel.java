package my_custom_panels;

import buttons.ColorChooserButton;
import buttons.IconButton;
import buttons.PenButton;
import buttons.ResetCanvasButton;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class ToolBarPanel extends JPanel {
    private final CanvasPanel canvasPanel;

    public ToolBarPanel(CanvasPanel canvasPanel) {
        super();
        this.canvasPanel = canvasPanel;

        this.setPreferredSize(new Dimension(Utils.TOOLBAR_WIDTH, Utils.TOOLBAR_HEIGHT));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        initLayout();
    }

    private void initLayout() {
        FlowLayout flowLayout = new FlowLayout();
        this.setLayout(flowLayout);
        this.add(initTest());
    }

    private JPanel initTest() {
        ItemToolsContainer item = new ItemToolsContainer();
        return new ItemToolBarPanel(item, "Tools");
    }
}
