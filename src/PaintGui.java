import my_custom_panels.CanvasPanel;
import my_custom_panels.ToolBarPanel;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class PaintGui extends JFrame {
    public PaintGui(int width, int height){
        super("Paint GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        pack();
        setLocationRelativeTo(null);

        addGuiComponents();
    }

    private void addGuiComponents(){
        // JPanel Configs
        JPanel container = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        container.setLayout(springLayout);

        // 1. Canvas
        CanvasPanel canvas = new CanvasPanel(Utils.CANVAS_WIDTH, Utils.CANVAS_HEIGHT);
        container.add(canvas);
        // 2. Toolbar
        ToolBarPanel tool = new ToolBarPanel(canvas);
        container.add(tool);

        springLayout.putConstraint(SpringLayout.NORTH, tool, 10, SpringLayout.NORTH, container);
        springLayout.putConstraint(SpringLayout.NORTH, canvas, 20, SpringLayout.SOUTH, tool);
        springLayout.putConstraint(SpringLayout.WEST, canvas,20,SpringLayout.WEST,container);

        this.getContentPane().add(container);
    }
}