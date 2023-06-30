package toolbars;

import ui.PaintGui;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    private final PaintGui frame;

    public ToolBar(PaintGui frame) {
        super(JToolBar.HORIZONTAL);
        this.frame = frame;
        initializeToolBar();
    }

    private void initializeToolBar() {
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        setFloatable(false);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);

        BrushButton pencilButton = new BrushButton(frame);
        LineButton lineButton = new LineButton(frame);
        RectangleButton rectangleButton = new RectangleButton(frame);
        CircleButton circleButton = new CircleButton(frame);
        TextButton textButton = new TextButton(frame);
        EraserButton eraserButton = new EraserButton(frame);

        UndoButton undoButton = new UndoButton(frame);
        RedoButton redoButton = new RedoButton(frame);
        ClearButton clearButton = new ClearButton(frame);

        ChooseLineWidthComboBox chooseLineWidthComboBox = new ChooseLineWidthComboBox(frame);

        addSeparator();
        add(pencilButton);
        add(lineButton);
        add(rectangleButton);
        add(circleButton);
        addSeparator();
        add(textButton);
        add(eraserButton);
        add(clearButton);
        addSeparator();
        add(undoButton);
        add(redoButton);
        addSeparator();
        add(chooseLineWidthComboBox);
    }
}