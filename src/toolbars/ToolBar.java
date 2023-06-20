package toolbars;

import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    private PencilButton pencilButton;
    private LineButton lineButton;
    private RectangleButton rectangleButton;
    private CircleButton circleButton;
    private ClearButton clearButton;
    private UndoButton undoButton;
    private RedoButton redoButton;
    private EraserButton eraserButton;
    private ChooseLineWidthComboBox chooseLineWidthComboBox;


    private JButton text;
    private JButton fill;
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
        //toolBar = new JToolBar(JToolBar.HORIZONTAL);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        setFloatable(false);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        setLayout(flowLayout);

        //toolBar.setBackground( new Color(0, 153, 204));

        pencilButton = new PencilButton(frame);
        lineButton = new LineButton(frame);
        rectangleButton = new RectangleButton(frame);
        circleButton = new CircleButton(frame);
        text = new JButton("Text", new ImageIcon(this.getClass().getResource(IconSourcePath.TEXT)));
        eraserButton = new EraserButton(frame);

        undoButton = new UndoButton(frame);
        redoButton = new RedoButton(frame);
        clearButton = new ClearButton(frame);

        chooseLineWidthComboBox = new ChooseLineWidthComboBox(frame);
        // ----------------
        // add buttons to the tool bar
        // ----------------
        addSeparator();
        add(pencilButton);
        add(lineButton);
        add(rectangleButton);
        add(circleButton);
        addSeparator();
        add(text);
        add(eraserButton);
        add(clearButton);
        addSeparator();
        add(undoButton);
        add(redoButton);
        addSeparator();
        add(chooseLineWidthComboBox);

        frame.getCanvasPanel().setTool(SHAPES.PENCIL);
    }
}