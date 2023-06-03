package toolbars;

import ui.PaintGui;
import utils.IconSourcePath;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToolBar {
    private JToolBar toolBar;
    private JButton pencil;
    private JButton line;
    private JButton rectangle;
    private JButton circle;
    private JButton text;
    private JButton erase;
    private JButton fill;
    private JButton undo;
    private JButton redo;
    private JButton clear;
    private Dimension newDimensions = new Dimension(700,500);
    private JButton save;
    private JButton open;
    private JButton newFile;
    private JFileChooser fc;
    private JComboBox comboBox;
    private File f;
    private PaintGui frame;

    public ToolBar(PaintGui frame) {
        this.frame = frame;
        fc = new JFileChooser(new File("."));
        fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));
        this.initializeToolBar();
    }

    private void initializeToolBar() {
        // ----------------
        // create buttons for the tool bar
        // ----------------
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
        toolBar.setFloatable(false);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        toolBar.setLayout(flowLayout);


        //toolBar.setBackground( new Color(0, 153, 204));

        save = new JButton("Save",new ImageIcon(this.getClass().getResource(IconSourcePath.SAVE)));
        open = new JButton("Open",new ImageIcon(this.getClass().getResource(IconSourcePath.OPEN)));
        newFile = new JButton("New",new ImageIcon(this.getClass().getResource(IconSourcePath.NEW_FILE)));
        pencil = new JButton("Pencil",new ImageIcon(this.getClass().getResource(IconSourcePath.PENCIL)));
        line = new JButton("Line", new ImageIcon(this.getClass().getResource(IconSourcePath.LINE)));
        rectangle = new JButton("Rectangle", new ImageIcon(this.getClass().getResource(IconSourcePath.RECTANGLE)));
        circle = new JButton("Circle", new ImageIcon(this.getClass().getResource(IconSourcePath.CIRCLE)));
        text = new JButton("Text",new ImageIcon(this.getClass().getResource(IconSourcePath.TEXT)));
        erase = new JButton("Erase", new ImageIcon(this.getClass().getResource(IconSourcePath.ERASE)));
        undo = new JButton("Undo", new ImageIcon(this.getClass().getResource(IconSourcePath.UNDO)));
        redo = new JButton("Redo", new ImageIcon(this.getClass().getResource(IconSourcePath.REDO)));
        clear = new JButton("Clear",new ImageIcon(this.getClass().getResource(IconSourcePath.CLEAR)));

        String[] items = { "Line Width","1", "2", "3", "4", "5", "6", "7", "8" };

        comboBox = new JComboBox(items);
        comboBox.setMaximumSize(new Dimension(100,25));

        // ----------------
        // add buttons to the tool bar
        // ----------------
        toolBar.add(newFile);
        toolBar.add(open);
        toolBar.add(save);
        toolBar.addSeparator();
        toolBar.add(pencil);
        toolBar.add(line);
        toolBar.add(rectangle);
        toolBar.add(circle);
        toolBar.addSeparator();
        toolBar.add(text);
        toolBar.add(erase);
        toolBar.add(clear);
        toolBar.addSeparator();
        toolBar.add(undo);
        toolBar.add(redo);
        toolBar.addSeparator();
        toolBar.add(comboBox);
    }

    public JToolBar getToolBar() {
        return this.toolBar;
    }

    private void setDimensions(int width, int height)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if(height > dim.height - 160 && width > dim.width - 150)
        {
            frame.getScrollPane().setSize(dim.width - 150, dim.height - 160);
        }
        else if(width > dim.width - 150)
        {
            frame.getScrollPane().setSize(dim.width - 150, height);
        }
        else if(height > dim.height - 160)
        {
            frame.getScrollPane().setSize(width, dim.height - 160);
        }
        else
        {
            frame.getScrollPane().setSize(width, height);
        }
    }
}