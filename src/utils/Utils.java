package utils;

import ui.PaintGui;

import java.awt.*;

public class Utils {
    public static void setDimensions(PaintGui frame, int width, int height) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if (height > dim.height - 200 && width > dim.width - 200) {
            frame.getScrollPane().setSize(dim.width - 200, dim.height - 200);
        } else if (width > dim.width - 200) {
            frame.getScrollPane().setSize(dim.width - 200, height);
        } else frame.getScrollPane().setSize(width, Math.min(height, dim.height - 200));
    }
}
