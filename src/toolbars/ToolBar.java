package toolbars;

import models.*;
import ui.PaintGui;
import utils.IconSourcePath;
import utils.SHAPES;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

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


    private Dimension newDimensions = new Dimension(700, 500);
    private JButton save;
    private JButton open;
    private JButton newFile;
    private JFileChooser fc;
    private File f;
    private PaintGui frame;

    public ToolBar(PaintGui frame) {
        super(JToolBar.HORIZONTAL);
        this.frame = frame;
        fc = new JFileChooser(new File("."));
        fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
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

        save = new JButton("Save", new ImageIcon(this.getClass().getResource(IconSourcePath.SAVE)));
        open = new JButton("Open", new ImageIcon(this.getClass().getResource(IconSourcePath.OPEN)));
        newFile = new JButton("New", new ImageIcon(this.getClass().getResource(IconSourcePath.NEW_FILE)));

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
        add(newFile);
        add(open);
        add(save);
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
    public JToolBar getToolBar() {
        return this;
    }
    private void setDimensions(int width, int height) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if (height > dim.height - 160 && width > dim.width - 150) {
            frame.getScrollPane().setSize(dim.width - 150, dim.height - 160);
        } else if (width > dim.width - 150) {
            frame.getScrollPane().setSize(dim.width - 150, height);
        } else if (height > dim.height - 160) {
            frame.getScrollPane().setSize(width, dim.height - 160);
        } else {
            frame.getScrollPane().setSize(width, height);
        }
    }
}