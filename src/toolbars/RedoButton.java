package toolbars;

import models.FilledTemp;
import models.Shape;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class RedoButton extends JButton implements ActionListener {
    private PaintGui frame;
    private CanvasPanel canvasPanel;

    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.REDO));

    public RedoButton(PaintGui frame) {
        super("Redo");
        this.setIcon(ICON);
        this.frame = frame;
        canvasPanel = frame.getCanvasPanel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       redo();
    }

    private void redo() {
        if (!canvasPanel.filledTempDelayRedo.isEmpty() && !canvasPanel.filledTempsRedo.isEmpty() && !canvasPanel.filledTempDelayRedo.get(canvasPanel.filledTempDelayRedo.size() - 1)) {
            canvasPanel.filledTempDelay.add(canvasPanel.filledTempDelayRedo.get(canvasPanel.filledTempDelayRedo.size() - 1));
            canvasPanel.filledTemps.add(canvasPanel.filledTempsRedo.get(canvasPanel.filledTempsRedo.size() - 1));

            canvasPanel.filledTemps.get(canvasPanel.filledTemps.size() - 1).color = returnToPreviousColor(canvasPanel.filledTempsRedo.get(canvasPanel.filledTempsRedo.size() - 1));

            canvasPanel.filledTempDelayRedo.remove(canvasPanel.filledTempDelayRedo.size() - 1);
            canvasPanel.filledTempsRedo.remove(canvasPanel.filledTempsRedo.size() - 1);

            canvasPanel.repaint();
            return;
        }
        if (!canvasPanel.filledTempDelayRedo.isEmpty() && canvasPanel.filledTempDelayRedo.get(canvasPanel.filledTempDelayRedo.size() - 1)) {
            canvasPanel.filledTempDelay.add(canvasPanel.filledTempDelayRedo.get(canvasPanel.filledTempDelayRedo.size() - 1));
            canvasPanel.filledTempDelayRedo.remove(canvasPanel.filledTempDelayRedo.size() - 1);
        }
        for (int endOfCount = 0, i = canvasPanel.shapesRedo.size() - 1; i >= 0; i--) {
            if (canvasPanel.shapesRedo.get(i).isEndOfShape()) {
                endOfCount++;
            }
            //In btnUndo case we added shapes when endOfCount equals 2 because of that we have to check shapes for not add them twice
            if (!canvasPanel.shapes.isEmpty() && canvasPanel.shapes.get(canvasPanel.shapes.size() - 1) == canvasPanel.shapesRedo.get(i)) {
                canvasPanel.shapesRedo.remove(i);
                if (endOfCount == 2) {
                    break;
                } else {
                    continue;
                }
            }
            canvasPanel.shapes.add(canvasPanel.shapesRedo.get(i));
            canvasPanel.shapesRedo.remove(i);
            if (endOfCount == 2) {
                break;
            }
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
