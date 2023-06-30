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
        JMenuItem resizeCanvas = new ResizeCanvasMenuItem(frame);
        JMenuItem exit = new ExitMenuItem(frame);

        menuFile.add(newFile);
        menuFile.add(resizeCanvas);
        menuFile.add(openFile);
        menuFile.add(saveFile);
        menuFile.add(exit);

        add(menuFile);

        setSize(500, 500);
        setVisible(true);
    }
}
