package models;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class RedoButton extends JButton implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.REDO));

    public RedoButton(PaintGui frame) {
        super("Redo");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       redo();
    }

    public void redo() {
        CanvasPanel canvasPanel = frame.getCanvasPanel();
        Stack<Shape> removed = canvasPanel.getRemoved();

        if (removed.size() > 0 && removed.peek().getGroup() == 0) {
            canvasPanel.pushStackForShapes(canvasPanel.popRemoved());
            canvasPanel.repaint();
        } else if (removed.size() > 0 && removed.peek().getGroup() != 0) {
            Shape lastRemoved = canvasPanel.popRemoved();
            canvasPanel.pushStackForShapes(lastRemoved);
            while (!removed.isEmpty() && removed.peek().getGroup() == lastRemoved.getGroup()) {
                canvasPanel.pushStackForShapes(canvasPanel.popRemoved());
                canvasPanel.repaint();
            }
        }
    }
}
