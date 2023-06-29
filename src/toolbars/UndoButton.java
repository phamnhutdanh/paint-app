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

public class UndoButton extends JButton implements ActionListener {
    private CanvasPanel canvasPanel;
    private CanvasModel canvasModel;
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.UNDO));

    public UndoButton(PaintGui frame) {
        super("Undo");
        this.setIcon(ICON);
        canvasPanel = frame.getCanvasPanel();
        canvasModel = frame.getCanvasPanel().getCanvasModel();
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        undo();
    }

    private void undo() {
        if (!canvasModel.getFilledTempDelay().isEmpty()
                && !canvasModel.getFilledTemps().isEmpty()
                && !canvasModel.getFilledTempDelay().get(canvasModel.getFilledTempDelay().size() - 1)) {

            canvasModel.addFilledTempDelayRedo(canvasModel.getFilledTempDelay().get(canvasModel.getFilledTempDelay().size() - 1));
            canvasModel.addFilledTempsRedo(canvasModel.getFilledTemps().get(canvasModel.getFilledTemps().size() - 1));

            canvasModel.setColorFilledTempsRedoAtPosition(canvasModel.getFilledTempsRedo().size() - 1,
                    returnToPreviousColor(
                            canvasModel.getFilledTemps().get(canvasModel.getFilledTemps().size() - 1)
                    ));

            canvasModel.removeFilledTempDelay(canvasModel.getFilledTempDelay().size() - 1);
            canvasModel.removeFilledTemps(canvasModel.getFilledTemps().size() - 1);
            canvasPanel.repaint();
            return;
        }
        if (!canvasModel.getFilledTempDelay().isEmpty()
                && canvasModel.getFilledTempDelay().get(canvasModel.getFilledTempDelay().size() - 1)) {

            canvasModel.addFilledTempDelayRedo(canvasModel.getFilledTempDelay().get(canvasModel.getFilledTempDelay().size() - 1));
            canvasModel.removeFilledTempDelay(canvasModel.getFilledTempDelay().size() - 1);
        }
        for (int endOfCount = 0, i = canvasModel.getShapes().size() - 1; i >= 0; i--) {
            if (canvasModel.getShapes().get(i).isEndOfShape()) {
                endOfCount++;
            }
            canvasModel.addShapeRedo(canvasModel.getShapes().get(i));
            if (endOfCount == 2) {
                break;
            }
            canvasModel.removeShapeAtPosition(i);
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


