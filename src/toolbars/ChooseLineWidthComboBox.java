package toolbars;

import ui.PaintGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseLineWidthComboBox extends JComboBox implements ActionListener {
    private final PaintGui frame;

    public ChooseLineWidthComboBox(PaintGui frame) {
        super();
        this.frame = frame;
        this.addActionListener(this);

        String[] items = {"Line Width", "1", "2", "4", "6", "8", "12", "14", "16", "20", "24", "28", "32", "40"};
        addAllItems(items);
        this.setMaximumSize(new Dimension(100, 25));
    }

    private void addAllItems(String[] items) {
        for (String item : items) {
            this.addItem(item);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String current = (String) this.getSelectedItem();
            assert current != null;

            frame.getCanvasPanel().getCanvasModel().setShapeThickness(Integer.parseInt(current));
        } catch (NumberFormatException exception) {

        }
    }
}
