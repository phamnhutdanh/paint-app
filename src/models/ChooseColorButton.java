package models;

import panels.CanvasPanel;
import ui.PaintGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseColorButton extends JButton implements ActionListener {
    private PaintGui frame;
    private JPanel resultPanel;

    public ChooseColorButton(String text, ImageIcon icon, PaintGui frame, JPanel resultPanel) {
        super(text, icon);
        addActionListener(this);
        this.frame = frame;
        this.resultPanel = resultPanel;
        this.resultPanel.setBackground(Color.black);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Color c = JColorChooser.showDialog(null, "Select a color", Color.BLACK);
        frame.getCanvasPanel().setColor(c);
        resultPanel.setBackground(c);
    }
}
