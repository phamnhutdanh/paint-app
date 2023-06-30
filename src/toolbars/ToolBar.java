package toolbars;

import ui.PaintGui;
import utils.IconSourcePath;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    private BrushButton pencilButton;
    private LineButton lineButton;
    private RectangleButton rectangleButton;
    private CircleButton circleButton;
    private ClearButton clearButton;
    private UndoButton undoButton;
    private RedoButton redoButton;
    private EraserButton eraserButton;
    private ChooseLineWidthComboBox chooseLineWidthComboBox;
    private TextButton textButton;
    private PaintGui frame;

    public ToolBar(PaintGui frame) {
        super(JToolBar.HORIZONTAL);
        this.frame = frame;
        initializeToolBar();
    }

    private void initializeToolBar() {
        // ----------------
        // create buttons for the tool bar
        // ----------------
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        setFloatable(false);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);

        pencilButton = new BrushButton(frame);
        lineButton = new LineButton(frame);
        rectangleButton = new RectangleButton(frame);
        circleButton = new CircleButton(frame);
        textButton = new TextButton(frame);
        eraserButton = new EraserButton(frame);

        undoButton = new UndoButton(frame);
        redoButton = new RedoButton(frame);
        clearButton = new ClearButton(frame);

        chooseLineWidthComboBox = new ChooseLineWidthComboBox(frame);

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