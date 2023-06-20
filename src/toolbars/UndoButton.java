package toolbars;

import models.FilledTemp;
import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class UndoButton extends JButton implements ActionListener {
    private PaintGui frame;
    private CanvasPanel canvasPanel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.UNDO));

    public UndoButton(PaintGui frame) {
        super("Undo");
        this.setIcon(ICON);
        this.frame = frame;
        canvasPanel = frame.getCanvasPanel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        undo();
    }


    private void undo() {
        if (!canvasPanel.filledTempDelay.isEmpty() && !canvasPanel.filledTemps.isEmpty() && !canvasPanel.filledTempDelay.get(canvasPanel.filledTempDelay.size() - 1)) {
            canvasPanel.filledTempDelayRedo.add(canvasPanel.filledTempDelay.get(canvasPanel.filledTempDelay.size() - 1));
            canvasPanel.filledTempsRedo.add(canvasPanel.filledTemps.get(canvasPanel.filledTemps.size() - 1));

            canvasPanel.filledTempsRedo.get(canvasPanel.filledTempsRedo.size() - 1).color = returnToPreviousColor(
                    canvasPanel.filledTemps.get(canvasPanel.filledTemps.size() - 1));

            canvasPanel.filledTempDelay.remove(canvasPanel.filledTempDelay.size() - 1);
            canvasPanel.filledTemps.remove(canvasPanel.filledTemps.size() - 1);
            canvasPanel.repaint();
            return;
        }
        if (!canvasPanel.filledTempDelay.isEmpty() && canvasPanel.filledTempDelay.get(canvasPanel.filledTempDelay.size() - 1)) {
            canvasPanel.filledTempDelayRedo.add(canvasPanel.filledTempDelay.get(canvasPanel.filledTempDelay.size() - 1));
            canvasPanel.filledTempDelay.remove(canvasPanel.filledTempDelay.size() - 1);
        }
        for (int endOfCount = 0, i = canvasPanel.shapes.size() - 1; i >= 0; i--) {
            if (canvasPanel.shapes.get(i).isEndOfShape()) {
                endOfCount++;
            }
            //We have to add shapes to shapesRedo when endOfCount equals 2 too
            canvasPanel.shapesRedo.add(canvasPanel.shapes.get(i));
            if (endOfCount == 2) {
                break;
            }
            canvasPanel.shapes.remove(i);
        }
        canvasPanel.repaint();
    }

    public Color returnToPreviousColor(FilledTemp filledTemp) {
        Color previousColor;
        int endOfCount = 0;
        if (filledTemp.startIndex == FilledTemp.CANVAS_INDEX) {
            previousColor = canvasPanel.canvasColor;
            canvasPanel.canvasColor = filledTemp.color;
            canvasPanel.setBackground(filledTemp.color);
            return previousColor;
        }
        previousColor = canvasPanel.shapes.get(filledTemp.endIndex).color;
        for (int i = filledTemp.endIndex; i >= filledTemp.startIndex; i--) {
            if (canvasPanel.shapes.get(i).isEndOfShape()) {
                endOfCount++;
            }
            if (endOfCount == 2) {
                return previousColor;
            }
            canvasPanel.shapes.get(i).color = filledTemp.color;
        }
        return previousColor;
    }
}


