package my_custom_panels;

import buttons.PenButton;
import utils.Utils;

import javax.swing.*;
import java.awt.*;

public class ItemToolsContainer extends JPanel {

    public ItemToolsContainer() {
        super();
        this.setPreferredSize(new Dimension(Utils.ITEM_TOOLS_WIDTH,Utils.ITEM_TOOLS_HEIGHT));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        init();
    }

    private void init() {
        GridLayout gridLayout = new GridLayout(2,3,4,4);
        this.setLayout(gridLayout);

        PenButton p1 = new PenButton();
        PenButton p2 = new PenButton();
        PenButton p3 = new PenButton();
        PenButton p4 = new PenButton();
        PenButton p5 = new PenButton();
        PenButton p6 = new PenButton();
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);


    }
}
