package menubars;

import utils.IconSourcePath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitMenuItem extends  JMenuItem implements ActionListener{
    private ImageIcon ICON = new ImageIcon(this.getClass().getResource(IconSourcePath.EXIT));
    public ExitMenuItem() {
        super("Exit");
        this.setIcon(ICON);
        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
