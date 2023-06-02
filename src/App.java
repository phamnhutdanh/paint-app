import java.awt.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
   /*     SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
               new PaintGui(Utils.WINDOW_WIDTH,Utils.WINDOW_HEIGHT).setVisible(true);
            }
        });
*/
        PaintGui frame = new PaintGui();
        frame.setTitle("Draw");
        frame.setBackground( new Color(39,174,96));
        frame.pack();

        // put the frame in the middle of the display
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);

    }
}