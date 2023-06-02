import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ColorChooser extends JPanel{
    private final ArrayList<ColorButton> colorButtons = new ArrayList<ColorButton>();
    private final ArrayList<Color> colors = new ArrayList<Color>() {
        {
            add(new Color(235  , 51, 36));
            add(new Color(240  , 135, 132));
            add(new Color(119  , 67, 66));
            add(new Color(255  , 253, 85));
            add(new Color(255  , 254, 145));
            add(new Color(240  , 134, 80));
            add(new Color(115  , 251, 253));
            add(new Color(50  , 130, 246));
            add(new Color(115  , 43, 245));
            add(new Color(239  , 136, 190));
            add(new Color(238  , 138, 248));
            add(new Color(234  , 63, 247));
            add(new Color(55  , 125, 34));
            add(new Color(55  , 126, 71));
            add(new Color(161  , 251, 142));
            add(new Color(57  , 16, 123));
            add(new Color(128  , 128, 128));
            add(new Color(192  , 192, 192));
            add(new Color(0  , 0, 0));
            add(new Color(255  , 255, 255));
        }
    };

    private JToolBar cc;

    private PaintGui frame;

    public ColorChooser(PaintGui frame) {
        cc = new JToolBar(JToolBar.VERTICAL);

        cc.setLayout(new BorderLayout());
        cc.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        cc.setFloatable(false);
        cc.setBorder(new EmptyBorder(12, 12, 12, 12));
        this.frame = frame;

        initializeColorChooser(cc);
        //cd = new ColorDialog(frame, primaryColor.getBackground());
    }

    private void initializeColorChooser(JToolBar panel) {
        Dimension buttonSize = new Dimension(36,8);

        colors.forEach(color -> {
            colorButtons.add(new ColorButton(color,buttonSize));
        });

        // Button choose color
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(4,0));
        JButton button = new JButton("Choose colors");
        button.setMaximumSize(new Dimension(16,16));
        top.add(button);
        ColorButton button2 = new ColorButton(new Color(0,0,0), new Dimension(16,16));
        button.setMaximumSize(new Dimension(16,16));
        top.add(button2);
        JButton button3 = new JButton("Choose colors");
        button.setMaximumSize(new Dimension(16,16));
        top.add(button3);
        panel.add(top, BorderLayout.NORTH);

        // Grid color
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(15,2, 8, 8));

        colorButtons.forEach(colorPanel::add);
        panel.add(colorPanel, BorderLayout.CENTER);
    }
    public JToolBar getToolBar() {
        return this.cc;
    }
}