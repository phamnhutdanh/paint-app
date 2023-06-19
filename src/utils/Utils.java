package utils;

import ui.PaintGui;

import java.awt.*;

public class Utils {
    public static void setDimensions(PaintGui frame, int width, int height) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if (height > dim.height - 160 && width > dim.width - 200) {
            frame.getScrollPane().setSize(dim.width - 200, dim.height - 160);
        } else if (width > dim.width - 200) {
            frame.getScrollPane().setSize(dim.width - 200, height);
        } else if (height > dim.height - 160) {
            frame.getScrollPane().setSize(width, dim.height - 160);
        } else {
            frame.getScrollPane().setSize(width, height);
        }
    }
}
