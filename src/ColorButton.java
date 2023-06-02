import javax.swing.*;
import java.awt.*;

public class ColorButton extends JButton {

    public ColorButton(Color color, Dimension buttonSize) {
        super("");
        setPreferredSize(buttonSize);
        setBackground(color);
    }
}
