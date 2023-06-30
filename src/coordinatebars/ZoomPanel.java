package coordinatebars;

import models.CanvasModel;
import models.Slider;
import panels.CanvasPanel;
import ui.PaintGui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomPanel extends JPanel implements ActionListener, ChangeListener {
    private final JLabel zoomVal;
    private final Slider slider;
    private final JButton zoomIn;
    private final JButton zoomOut;
    private final PaintGui frame;

    public ZoomPanel(PaintGui frame) {
        super();
        this.frame = frame;

        zoomOut = new JButton("-");
        add(zoomOut);

        slider = new Slider(7, 30, 5, true);
        add(slider);
        slider.addChangeListener(this);
        slider.setBackground(null);

        zoomVal = new JLabel("");
        add(zoomVal);

        zoomIn = new JButton("+");
        add(zoomIn);

        this.setBackground(null);
        zoomIn.addActionListener(this);
        zoomOut.addActionListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        zoom(slider.getValue());
        zoomVal.setText("%" + (int) (frame.getCanvasPanel().getCanvasModel().getWidthScale() * 100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        JButton btn = (JButton) eventSource;

        if (btn == zoomIn) {
            slider.setValue(slider.getValue() + 2);
        } else if (btn == zoomOut) {
            slider.setValue(slider.getValue() - 2);
        }
    }

    private void zoom(float val) {
        CanvasPanel canvasPanel = frame.getCanvasPanel();
        CanvasModel canvasModel = canvasPanel.getCanvasModel();
        canvasModel.setWidthScale(val / 20);
        canvasModel.setHeightScale(val / 20);
        if (canvasModel.getWidthScale() == 0) {
            canvasModel.setWidthScale(0.05);
        }
        if (canvasModel.getHeightScale() == 0) {
            canvasModel.setHeightScale(0.05);
        }
        canvasPanel.scaleCanvas();
    }


}
