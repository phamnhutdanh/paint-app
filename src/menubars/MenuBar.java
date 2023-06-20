package menubars;

import ui.PaintGui;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar(PaintGui frame) {
        super();
        JMenu menuFile = new JMenu("File");
        JMenuItem newFile = new NewFileMenuItem(frame);
        JMenuItem openFile = new OpenFileMenuItem(frame);
        JMenuItem saveFile = new SaveFileMenuItem(frame);

        menuFile.add(newFile);
        menuFile.add(openFile);
        menuFile.add(saveFile);

        add(menuFile);

        setSize(500, 500);
        setVisible(true);
    }
}
