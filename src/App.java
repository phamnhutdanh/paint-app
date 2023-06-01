import utils.Utils;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
               new PaintGui(Utils.WINDOW_WIDTH,Utils.WINDOW_HEIGHT).setVisible(true);
            }
        });
    }
}