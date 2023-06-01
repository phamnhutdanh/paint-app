package utils;

import buttons.IconButton;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 960;

    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 400;

    public static final int ICON_WIDTH = 32;
    public static final int ICON_HEIGHT = 32;

    public static final int TOOLBAR_WIDTH = 1280;
    public static final int TOOLBAR_HEIGHT = 160;

    public static final int ITEM_TOOLBAR_WIDTH = 150;
    public static final int ITEM_TOOLBAR_HEIGHT = 120;

    public static final int ITEM_TOOLS_WIDTH = 120;
    public static final int ITEM_TOOLS_HEIGHT = 80;

    public static final int ITEM_TITLE_WIDTH = 120;
    public static final int ITEM_TITLE_HEIGHT = 30;


    public static ImageIcon getIconFromPath(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(IconButton.class.getResource(path));
        Image newImg = icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        return icon;
    }
}
