package my_custom_panels;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {
    private String text;
    public TextPanel(String text, int width, int height){
        super();
        this.text=text;

        this.setPreferredSize(new Dimension(width, height));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        init();
    }

    private void init() {
        JLabel title = new JLabel(text);
        this.add(title);
    }
}
