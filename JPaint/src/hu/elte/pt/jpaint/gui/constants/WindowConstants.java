/**
 * @author Bálint Fazekas
 * 
 * This class consists of constants for the components of the JPaint window, 
 * such as its width and height, and button texts. 
 */
package hu.elte.pt.jpaint.gui.constants;

import java.awt.*;

/**
 * Defines the static constant variables that are used in the graphical user 
 * interface of the JPaint main window.
 * @author Bálint
 */
public class WindowConstants {

    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 600;
    
    public static final String WINDOW_TITLE = "JPaint";
    public static final String MENU_TEXT = "Menu";
    public static final String NEW_CANVAS = "New Canvas";
    public static final String CLEAR_CANVAS = "Clear Canvas";
    public static final String SAVE_IMAGE = "Save...";
    public static final String CLOSE_IMAGE = "Close";
    public static final String CLOSE_APP = "Quit";
    
    public static final Font JPAINT_MENU_FONT = new Font(Font.SANS_SERIF, Font.ITALIC, 16);
    public static final Font JPAINT_SUBMENU_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    public static final Font JPAINT_UTILITY_FONT = new Font(Font.DIALOG, Font.PLAIN, 12);
    public static final Color UTILITY_BUTTON_COLOR = new Color(255, 222, 173);
    public static final Dimension COLOR_BUTTON_DIMENSIONS = new Dimension(20, 20);
    
    public static final String PENCIL_UTILITY_TEXT = "Pencil";
    public static final String BRUSH_UTILITY_TEXT = "Brush";
    public static final String ERASER_UTILITY_TEXT = "Eraser";
    public static final String RECTANGLE_UTILITY_TEXT = "Rectangle";
    public static final String CIRCLE_UTILITY_TEXT = "Circle";
    public static final String LINE_UTILITY_TEXT = "Line";
    public static final String UNDO_UTILITY_TEXT = "Undo";
    
    public static final String IMAGE_NAME_MESSAGE = "Please enter a name for your image:";
    public static final String CLEAR_IMAGE_WINDOW_TITLE = "You seem to have an open canvas.";
    public static final String CLEAR_IMAGE_WINDOW_MESSAGE = "Are you sure you want to clear it?";
    public static final String CLOSE_IMAGE_WINDOW_TITLE = "You seem to have an open canvas.";
    public static final String CLOSE_IMAGE_WINDOW_MESSAGE = "Are you sure you want to close it?";
    
    public static final int BRUSH_RADIUS_MIN = 0;
    public static final int BRUSH_RADIUS_MAX = 100;
    public static final int BRUSH_RADIUS_INIT = 15;
}
