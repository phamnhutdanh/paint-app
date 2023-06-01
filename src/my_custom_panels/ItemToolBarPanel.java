package my_custom_panels;

import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class ItemToolBarPanel extends JPanel {
    private JPanel itemsContainer;
    private String title;
    public ItemToolBarPanel(JPanel itemsContainer, String title) {
        super();
        this.itemsContainer=itemsContainer;
        this.title=title;

        this.setPreferredSize(new Dimension(Utils.ITEM_TOOLBAR_WIDTH, Utils.ITEM_TOOLBAR_HEIGHT));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        init();
    }
    private void init() {
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        // 1. Item
        this.add(itemsContainer);
        // 2. Title
        FlowLayout flowLayout = new FlowLayout();
        TextPanel textPanel=new TextPanel(title,Utils.ITEM_TITLE_WIDTH,Utils.ITEM_TITLE_HEIGHT);
        textPanel.setLayout(flowLayout);
        this.add(textPanel);

        // spring layout constraint
        springLayout.putConstraint(SpringLayout.NORTH, itemsContainer, 4, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, textPanel, 4, SpringLayout.SOUTH, itemsContainer);
    }
}
