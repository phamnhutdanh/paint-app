package ui;

import colorbars.ColorChooser;
import coordinatebars.CoordinateBar;
import menubars.MenuBar;
import menubars.SaveFileMenuItem;
import models.CanvasModel;
import panels.CanvasPanel;
import toolbars.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaintGui extends JFrame {
    private final MenuBar menuBar;
    private final ToolBar toolBar;
    private final ColorChooser colorChooser;
    private final CoordinateBar coordinateBar;

    private final JPanel contentPane;
    private JScrollPane scrollPane;
    private final CanvasPanel canvasPanel;

    private final int canvasPanelWidth;
    private final int canvasPanelHeight;
    private final Color background = Color.GRAY;

    public PaintGui() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        canvasPanelWidth = dim.width - 200;
        canvasPanelHeight = dim.height - 200;
        canvasPanel = new CanvasPanel(canvasPanelWidth, canvasPanelHeight);

        contentPane = new JPanel();
        toolBar = new ToolBar(this);
        coordinateBar = new CoordinateBar(this);
        colorChooser = new ColorChooser(this);
        menuBar = new MenuBar(this);

        init();
    }

    public void drawUI() {
        setTitle("Paint App");
        setBackground(new Color(39, 174, 96));
        pack();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    private void init() {
        contentPane.setLayout(null);

        initCanvas();
        initMenuBar();
        initToolBar();
        initColorChooser();
        initCoordinateBar();

        this.addWindowListener(windowCloser);
        int CONTENT_PANE_WIDTH = 900;
        int CONTENT_PANE_HEIGHT = 600;
        this.setSize(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT);
        this.setPreferredSize(new Dimension(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT));
        this.add(contentPane);
    }

    private void initCanvas() {
        scrollPane = new JScrollPane();
        scrollPane.setLocation(10, 10);
        scrollPane.setSize(canvasPanelWidth, canvasPanelHeight);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setViewportView(canvasPanel);

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
            CanvasModel canvasModel = canvasPanel.getCanvasModel();

            if (canvasModel.isBlank()) {
                System.exit(0);
            } else {
                String[] options = new String[]{"Save", "Don't save", "Cancel"};
                int response = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to close", "Warning!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response == 1) {
                    System.exit(0);
                } else if (response == 0) {
                    SaveFileMenuItem.saveFile(canvasPanel, getContentPane());
                    System.exit(0);
                }
            }
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    };

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public CoordinateBar getCoordinateBar() {
        return coordinateBar;
    }

    public CanvasPanel getCanvasPanel() {
        return canvasPanel;
    }
}