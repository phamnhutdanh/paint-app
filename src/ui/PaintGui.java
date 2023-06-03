package ui;

import panels.CanvasPanel;
import toolbars.ColorChooser;
import toolbars.CoordinateBar;
import toolbars.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaintGui extends JFrame {
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JToolBar colorChooser;
    private CoordinateBar coordinateBar;

    private JPanel contentPane;
    private JScrollPane scrollPane;
    private CanvasPanel canvas;

    private final int CONTENT_PANE_WIDTH = 1200;
    private final int CONTENT_PANE_HEIGHT = 700;

    private int inkPanelWidth;
    private int inkPanelHeight;
    private final Color background = Color.GRAY;

    public PaintGui() {
        contentPane = new JPanel();
        canvas = new CanvasPanel(0, this, inkPanelWidth, inkPanelHeight);
        coordinateBar = new CoordinateBar(canvas);
        colorChooser = (new ColorChooser(this)).getToolBar();
        toolBar = (new ToolBar(this)).getToolBar();
        init();
    }

    public void drawUI() {
        setTitle("Draw");
        setBackground(new Color(39, 174, 96));
        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    private void init() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        inkPanelWidth = dim.width - 150;
        inkPanelHeight = dim.height - 160;

        contentPane.setLayout(null);

        initCanvas();
        initMenuBar();
        initToolBar();
        initColorChooser();
        initCoordinateBar();

        this.addWindowListener(windowCloser);
        this.setSize(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT);
        this.setPreferredSize(new Dimension(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT));
        this.add(contentPane);
    }

    private void initCanvas() {

        scrollPane = new JScrollPane();
        scrollPane.setLocation(10, 10);
        scrollPane.setSize(inkPanelWidth, inkPanelHeight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(canvas);

        contentPane.add(scrollPane);
        contentPane.setBackground(background);
    }

    private void initColorChooser() {
        add(colorChooser, BorderLayout.WEST);
    }

    private void initMenuBar() {
        setJMenuBar(menuBar);
    }

    private void initToolBar() {
        add(toolBar, BorderLayout.PAGE_START);
    }

    private void initCoordinateBar() {
        add(coordinateBar, BorderLayout.PAGE_END);
    }

    private final WindowAdapter windowCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event) {
            System.exit(0);
        }
    };

    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public CoordinateBar getCoordinateBar() {
        return coordinateBar;
    }

    public CanvasPanel getCanvasPanel() {
        return canvas;
    }
}