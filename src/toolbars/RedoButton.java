package toolbars;

import models.CanvasModel;
import models.FilledTemp;
import panels.CanvasPanel;
import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedoButton extends JButton implements ActionListener {
    private CanvasPanel canvasPanel;
    private CanvasModel canvasModel;

    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.REDO));

    public RedoButton(PaintGui frame) {
        super("Redo");
        this.setIcon(ICON);
        canvasPanel = frame.getCanvasPanel();
        canvasModel = frame.getCanvasPanel().getCanvasModel();

        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        redo();
    }

    private void redo() {
        if (!canvasModel.getFilledTempDelayRedo().isEmpty()
                && !canvasModel.getFilledTempsRedo().isEmpty()
                && !canvasModel.getFilledTempDelayRedo().get(canvasModel.getFilledTempDelayRedo().size() - 1)) {

            canvasModel.addFilledTempDelay(canvasModel.getFilledTempDelayRedo().get(canvasModel.getFilledTempDelayRedo().size() - 1));
            canvasModel.addFilledTemp(canvasModel.getFilledTempsRedo().get(canvasModel.getFilledTempsRedo().size() - 1));
            canvasModel.setColorFilledTempsAtPosition(canvasModel.getFilledTemps().size() - 1,
                    returnToPreviousColor(canvasModel.getFilledTempsRedo().get(canvasModel.getFilledTempsRedo().size() - 1)));
            canvasModel.removeFilledTempDelayRedo(canvasModel.getFilledTempDelayRedo().size() - 1);
            canvasModel.removeFilledTempsRedo(canvasModel.getFilledTempsRedo().size() - 1);
            canvasPanel.repaint();
            return;
        }
        if (!canvasModel.getFilledTempDelayRedo().isEmpty()
                && canvasModel.getFilledTempDelayRedo().get(canvasModel.getFilledTempDelayRedo().size() - 1)) {
            canvasModel.addFilledTempDelay(canvasModel.getFilledTempDelayRedo().get(
                    canvasModel.getFilledTempDelayRedo().size() - 1
            ));
            canvasModel.removeFilledTempDelayRedo(canvasModel.getFilledTempDelayRedo().size() - 1);
        }
        for (int endOfCount = 0, i = canvasModel.getShapesRedo().size() - 1; i >= 0; i--) {
            if (canvasModel.getShapesRedo().get(i).isEndOfShape()) {
                endOfCount++;
            }
            if (!canvasModel.getShapes().isEmpty() && canvasModel.getShapes().get(canvasModel.getShapes().size() - 1) == canvasModel.getShapesRedo().get(i)) {
                canvasModel.removeShapeRedoAtPosition(i);
                if (endOfCount == 2) {
                    break;
                } else {
                    continue;
                }
            }
            canvasModel.addShape(canvasModel.getShapesRedo().get(i));
            canvasModel.removeShapeRedoAtPosition(i);
            if (endOfCount == 2) {
                break;
            }
        }
        canvasPanel.repaint();
    }

    public Color returnToPreviousColor(FilledTemp filledTemp) {
        Color previousColor;
        int endOfCount = 0;
        if (filledTemp.getStartIndex() == FilledTemp.getCanvasIndex()) {
            previousColor = canvasModel.getCanvasColor();
            canvasModel.setCanvasColor(filledTemp.getColor());
            canvasPanel.setBackground(filledTemp.getColor());
            return previousColor;
        }

        previousColor = canvasModel.getShapes().get(filledTemp.getEndIndex()).getColor();
        for (int i = filledTemp.getEndIndex(); i >= filledTemp.getStartIndex(); i--) {
            if (canvasModel.getShapes().get(i).isEndOfShape()) {
                endOfCount++;
            }
            if (endOfCount == 2) {
                return previousColor;
            }
            canvasModel.setColorShapesAtPosition(i, filledTemp.getColor());
        }
        return previousColor;
    }
}
