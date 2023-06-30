package ui;

import menubars.MenuBar;
import menubars.SaveFileMenuItem;
import models.CanvasModel;
import panels.CanvasPanel;
import colorbars.ColorChooser;
import coordinatebars.CoordinateBar;
import toolbars.ToolBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintGui extends JFrame {
    private MenuBar menuBar;
    private ToolBar toolBar;
    private ColorChooser colorChooser;
    private CoordinateBar coordinateBar;

    private JPanel contentPane;
    private JScrollPane scrollPane;
    private CanvasPanel canvasPanel;

    private final int CONTENT_PANE_WIDTH = 900;
    private final int CONTENT_PANE_HEIGHT = 600;

    private int canvasPanelWidth;
    private int inkPanelHeight;
    private final Color background = Color.GRAY;

    public PaintGui() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        canvasPanelWidth = dim.width - 200;
        inkPanelHeight = dim.height - 200;
        canvasPanel = new CanvasPanel(canvasPanelWidth,inkPanelHeight);

        canvasPanelWidth = canvasPanel.getCanvasModel().getSizeWidth();
        inkPanelHeight = canvasPanel.getCanvasModel().getSizeHeight();

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
        this.setSize(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT);
        this.setPreferredSize(new Dimension(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT));
        this.add(contentPane);
    }

    private void initCanvas() {
        scrollPane = new JScrollPane();
        scrollPane.setLocation(10, 10);
        scrollPane.setSize(canvasPanelWidth, inkPanelHeight);
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
            }
            else {
                String[] options = new String[] {"Save", "Don't save", "Cancel"};
                int response = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to close", "Warning!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if (response == 1)
                {
                    System.exit(0);
                }
                else if (response == 0) {
                    SaveFileMenuItem.saveFile(canvasPanel, getContentPane());
                    System.exit(0);

                }
            }
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    };
    public ToolBar getToolBar() {
        return toolBar;
    }
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