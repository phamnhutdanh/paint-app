package models;

import ui.PaintGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseLineWidthComboBox extends JComboBox implements ActionListener {
    private PaintGui frame;
    private String[] items = {"Line Width", "1", "2", "4",  "6",  "8","12","14","16","20","24","28","32","40"};

    public ChooseLineWidthComboBox(PaintGui frame) {
        super();
        this.frame = frame;
        this.addActionListener(this);

        addAllItems(items);
        this.setMaximumSize(new Dimension(100, 25));
    }
    private void addAllItems(String[] items) {
        for (int i=0;i<items.length;i++) {
            this.addItem(items[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String current = (String) this.getSelectedItem();
            assert current != null;
            frame.getCanvasPanel().setThickness(Float.parseFloat(current));
        }
        catch (NumberFormatException exception) {

        }
    }
}
