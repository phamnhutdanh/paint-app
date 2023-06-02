import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaintGui extends JFrame {
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JToolBar cc1;
    private CoordinateBar coordinateBar;
    private JScrollPane sp;

    private final int CONTENT_PANE_WIDTH = 1300;
    private final int CONTENT_PANE_HEIGHT = 700;

    private int inkPanelWidth;
    private int inkPanelHeight;
    private final Color background = Color.GRAY;

    public PaintGui(){
       init();
    }

    private void init() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        inkPanelWidth = dim.width - 150;
        inkPanelHeight = dim.height- 160;

        // construct layout manager.
        contentPane = new JPanel();
        contentPane.setLayout(null);

        initMenuBar();
        initToolBar();
        initColorChooser();
        initCoordinateBar();
        this.addWindowListener(windowCloser);
        this.setSize(CONTENT_PANE_WIDTH, CONTENT_PANE_HEIGHT);
        this.setPreferredSize(new Dimension(CONTENT_PANE_WIDTH,CONTENT_PANE_HEIGHT));
        this.add(contentPane);

    }

    private void initColorChooser() {
        cc1 = (new ColorChooser(this)).getToolBar();
        this.add(cc1, BorderLayout.WEST);
        sp = new JScrollPane();
        sp.setLocation(10, 10);
        sp.setSize(inkPanelWidth, inkPanelHeight);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        contentPane.add(sp);
        contentPane.setBackground(background);
    }
    private void initMenuBar() {
        this.setJMenuBar(menuBar);
    }
    private void initToolBar() {
        toolBar = (new ToolBar(this)).getToolBar();
        this.add(toolBar, BorderLayout.PAGE_START);

    }
    private void initCoordinateBar() {
        coordinateBar = new CoordinateBar();
        this.add(coordinateBar, BorderLayout.PAGE_END);
    }
    private final WindowAdapter windowCloser = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent event)
        {
            System.exit(0);
        }
    };

    public JScrollPane getSP() {
        return this.sp;
    }
}