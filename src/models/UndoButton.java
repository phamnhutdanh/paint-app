package models;

import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class UndoButton extends JButton implements ActionListener {
    private PaintGui frame;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.UNDO));

    public UndoButton(PaintGui frame) {
        super("Undo");
        this.setIcon(ICON);
        this.frame = frame;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        undo();
    }

    public void undo() {
        CanvasPanel canvasPanel = frame.getCanvasPanel();
        Stack<Shape> shapes = canvasPanel.getShapes();

        if (shapes.size() > 0 && shapes.peek().getGroup() == 0) {
            canvasPanel.pushStackForRemoved(canvasPanel.popShapes());
            canvasPanel.repaint();
        } else if (shapes.size() > 0 && shapes.peek().getGroup() != 0) {
            Shape lastRemoved = canvasPanel.popShapes();
            canvasPanel.pushStackForRemoved(lastRemoved);
            while (!shapes.isEmpty() && shapes.peek().getGroup() == lastRemoved.getGroup()) {
                canvasPanel.pushStackForRemoved(canvasPanel.popShapes());
                canvasPanel.repaint();
            }
        }
    }
}
