package hu.elte.pt.jpaint.gui;

import hu.elte.pt.jpaint.GlobalConstants;
import static hu.elte.pt.jpaint.gui.constants.WindowConstants.*;
import hu.elte.pt.jpaint.logic.canvas.CanvasPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.*;

/**
 * This "JPaintFrame" class defines the main outline of the JPaint application,
 * and extends the java.swing.JFrame class for simple implementation.
 *
 * @author Bálint Fazekas
 * @version 1.1
 */
public class JPaintFrame extends JFrame {

    /**
     *
     */
    private String imageTitle = "JPaint";
    private CanvasPanel canvas = null;
    private JLabel frameHeader = new JLabel();
    private int brushRadius = BRUSH_RADIUS_INIT;

    /*
     * These menu items had to be moved outside of the setup function in 
     * order to manually set their visibility (enabled/disabled property).
     */
    private JMenuItem clearCanvasMenuItem = new JMenuItem(CLEAR_CANVAS);
    private JMenuItem saveImageMenuItem = new JMenuItem(SAVE_IMAGE);
    private JMenuItem closeImageMenuItem = new JMenuItem(CLOSE_IMAGE);

    private JButton pencilUtilityButton = new JButton(PENCIL_UTILITY_TEXT);
    private JButton brushUtilityButton = new JButton(BRUSH_UTILITY_TEXT);
    private JButton eraserUtilityButton = new JButton(ERASER_UTILITY_TEXT);
    private JButton rectangleUtilityButton = new JButton(RECTANGLE_UTILITY_TEXT);
    private JButton circleUtilityButton = new JButton(CIRCLE_UTILITY_TEXT);
    private JButton lineUtilityButton = new JButton(LINE_UTILITY_TEXT);
    private JButton undoUtilityButton = new JButton(UNDO_UTILITY_TEXT);
    
    private ActionListener createCanvas;
    private ActionListener clearCanvas;
    private ActionListener saveImage;
    private ActionListener closeImage;
    private ActionListener closeWindow;

    private ActionListener pencilAction;
    private ActionListener brushAction;
    private ActionListener eraserAction;
    private ActionListener rectangleAction;
    private ActionListener circleAction;
    private ActionListener lineAction;
    private ActionListener undoAction;
    private ChangeListener brushRadiusAction;
    
    private ActionListener colorBlackAction;
    private ActionListener colorRedAction;
    private ActionListener colorBlueAction;
    private ActionListener colorYellowAction;
    private ActionListener colorGreenAction;
    
    private JButton blackButton = new JButton();
    private JButton redButton = new JButton();
    private JButton blueButton = new JButton();
    private JButton yellowButton = new JButton();
    private JButton greenButton = new JButton();
    private JButton wildcard1ColorButton = new JButton();
    private JButton wildcard2ColorButton = new JButton();
    private JButton wildcard3ColorButton = new JButton();
    
    private JButton selectedColorButton; 
    private JButton selectedToolButton;
    
    /**
     * Returns the canvas element of the window. Returns null if it has none.
     *
     * @return the canvas element of the window.
     */
    public CanvasPanel getCanvas() {
        return canvas;
    }
    
    /**
     * Returns the currently selected brush radius in pixels.
     * @return brush radius in pixels (integers)
     */
    public int getBrushRadius() {
        return brushRadius;
    }

    /**
     * Returns the Pencil button entity.
     * @return pencil button
     */
    public JButton getPencilUtilityButton() {
        return pencilUtilityButton;
    }

    /**
     * Returns the Utility button entity.
     * @return utility button
     */
    public JButton getBrushUtilityButton() {
        return brushUtilityButton;
    }

    /**
     * Returns the Eraser button entity.
     * @return eraser button
     */
    public JButton getEraserUtilityButton() {
        return eraserUtilityButton;
    }

    /**
     * Returns the Rectangle drawing button entity.
     * @return rectangle button
     */
    public JButton getRectangleUtilityButton() {
        return rectangleUtilityButton;
    }

    /**
     * Returns the Circle drawing button entity.
     * @return circle button
     */
    public JButton getCircleUtilityButton() {
        return circleUtilityButton;
    }
 
    /**
     * Returns the Line drawing button entity.
     * @return line button
     */
    public JButton getLineUtilityButton() {
        return lineUtilityButton;
    }

    /**
     * Returns the Black color button entity.
     * @return black button
     */
    public JButton getBlackButton() {
        return blackButton;
    }

    /**
     * Returns the Red color button entity.
     * @return red button
     */
    public JButton getRedButton() {
        return redButton;
    }

    /**
     * Returns the Blue color button entity.
     * @return blue button
     */
    public JButton getBlueButton() {
        return blueButton;
    }

    /**
     * Returns the Yellow color button entity.
     * @return yellow button
     */
    public JButton getYellowButton() {
        return yellowButton;
    }

    /**
     * Returns the Green color button entity.
     * @return green button
     */
    public JButton getGreenButton() {
        return greenButton;
    }

    /**
     * Returns the first custom color button entity.
     * @return first custom color button
     */
    public JButton getWildcard1ColorButton() {
        return wildcard1ColorButton;
    }

    /**
     * Returns the second custom color button entity.
     * @return second custom color button
     */
    public JButton getWildcard2ColorButton() {
        return wildcard2ColorButton;
    }

    /**
     * Returns the third custom color button entity.
     * @return third custom color button
     */
    public JButton getWildcard3ColorButton() {
        return wildcard3ColorButton;
    }

    /**
     * Returns the title of the image, which was given by the user upon 
     * the creation of a new canvas. 
     * @return image title
     */
    public String getImageTitle() {
        return imageTitle;
    }

    /**
     * Returns the label of the JPaintFrame window. By default, its
     * text is set to "JPaint". When a new canvas is created, the new 
     * given name is displayed in this label. 
     * @return frame header
     */
    public JLabel getFrameHeader() {
        return frameHeader;
    }

    /**
     * Returns the 'Clear canvas' menu item.
     * @return JMenut item
     */
    public JMenuItem getClearCanvasMenuItem() {
        return clearCanvasMenuItem;
    }

    /**
     * Returns the 'Save image' menu item. 
     * @return JMenu item
     */
    public JMenuItem getSaveImageMenuItem() {
        return saveImageMenuItem;
    }

    /**
     * Returns the 'Close image' menu item.
     * @return JMenu item
     */
    public JMenuItem getCloseImageMenuItem() {
        return closeImageMenuItem;
    }

    /**
     * Returns the undo utility button entity.
     * @return undo button
     */
    public JButton getUndoUtilityButton() {
        return undoUtilityButton;
    }

    /**
     * Returns the canvas creator action listener. 
     * @return canvas creation action listener
     */
    public ActionListener getCreateCanvas() {
        return createCanvas;
    }

    /**
     * Returns the clear canvas action listener. 
     * @return clear canvas action listener
     */
    public ActionListener getClearCanvas() {
        return clearCanvas;
    }

    /**
     * Returns the save image action listener. 
     * @return save image action listener
     */
    public ActionListener getSaveImage() {
        return saveImage;
    }

    /**
     * Returns the close image action listener. 
     * @return close image action listener
     */
    public ActionListener getCloseImage() {
        return closeImage;
    }

    /**
     * Returns the close window action listener. 
     * @return close window action listener
     */
    public ActionListener getCloseWindow() {
        return closeWindow;
    }

    /**
     * Returns the pencil utility tool action listener. 
     * @return pencil tool action listener
     */
    public ActionListener getPencilAction() {
        return pencilAction;
    }

    /**
     * Returns the brush utility tool action listener. 
     * @return brush tool action listener
     */
    public ActionListener getBrushAction() {
        return brushAction;
    }

    /**
     * Returns the eraser utility tool action listener. 
     * @return eraser tool action listener
     */
    public ActionListener getEraserAction() {
        return eraserAction;
    }

    /**
     * Returns the rectangle drawing utility tool action listener. 
     * @return rectangle tool action listener
     */
    public ActionListener getRectangleAction() {
        return rectangleAction;
    }

    /**
     * Returns the circle drawing utility tool action listener. 
     * @return circle tool action listener
     */
    public ActionListener getCircleAction() {
        return circleAction;
    }

    /**
     * Returns the straight line drawing utility tool action listener. 
     * @return straight line tool action listener
     */
    public ActionListener getLineAction() {
        return lineAction;
    }

    /**
     * Returns the undo action listener. 
     * @return undo action listener
     */
    public ActionListener getUndoAction() {
        return undoAction;
    }

    /**
     * Returns the brush radius action listener. 
     * @return brush radius action listener
     */
    public ChangeListener getBrushRadiusAction() {
        return brushRadiusAction;
    }

    /**
     * Returns the black color action listener. 
     * @return black color action listener
     */
    public ActionListener getColorBlackAction() {
        return colorBlackAction;
    }

    /**
     * Returns the red color action listener. 
     * @return red color action listener
     */
    public ActionListener getColorRedAction() {
        return colorRedAction;
    }

    /**
     * Returns the blue color action listener. 
     * @return blue color action listener
     */
    public ActionListener getColorBlueAction() {
        return colorBlueAction;
    }

    /**
     * Returns the yellow color action listener. 
     * @return yellow color action listener
     */
    public ActionListener getColorYellowAction() {
        return colorYellowAction;
    }

    /**
     * Returns the green color action listener. 
     * @return green color action listener
     */
    public ActionListener getColorGreenAction() {
        return colorGreenAction;
    }

    /**
     * Returns the custom color button entity. 
     * @return custom color button entity
     */
    public JButton getSelectedColorButton() {
        return selectedColorButton;
    }

    /**
     * Returns the currently selected utility tool button entity. 
     * @return currently selected tool button entity
     */
    public JButton getSelectedToolButton() {
        return selectedToolButton;
    }
    
    
    /**
     * The constructor of this class sets up the window of the JPaint
     * application. It uses several methods to set up each component of the user
     * interface.
     *
     * @throws HeadlessException
     */
    public JPaintFrame() throws HeadlessException {
        initFrame();
        hookActionListeners();
        setMenu();
        setPageStart();
        setPageCenter();
        setTools();
        setColorButtons();
        setSelectedColorButton(blackButton);
    }

    /**
     * Initializes the main window.
     *
     */
    public void initFrame() {
        setTitle(WINDOW_TITLE);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Sets up the menu items of the main window of the JPaint application.
     */
    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jpaintMenu = new JMenu(MENU_TEXT);
        jpaintMenu.setFont(JPAINT_MENU_FONT);
        JMenuItem newCanvasMenuItem = new JMenuItem(NEW_CANVAS);
        JMenuItem closeAppMenuItem = new JMenuItem(CLOSE_APP);

        newCanvasMenuItem.setFont(JPAINT_SUBMENU_FONT);
        clearCanvasMenuItem.setFont(JPAINT_SUBMENU_FONT);
        saveImageMenuItem.setFont(JPAINT_SUBMENU_FONT);
        closeImageMenuItem.setFont(JPAINT_SUBMENU_FONT);
        closeAppMenuItem.setFont(JPAINT_SUBMENU_FONT);

        newCanvasMenuItem.addActionListener(createCanvas);
        clearCanvasMenuItem.addActionListener(this.clearCanvas);
        saveImageMenuItem.addActionListener(this.saveImage);
        closeImageMenuItem.addActionListener(this.closeImage);
        closeAppMenuItem.addActionListener(closeWindow);

        // disabling these buttons by default
        clearCanvasMenuItem.setEnabled(false);
        saveImageMenuItem.setEnabled(false);
        closeImageMenuItem.setEnabled(false);

        jpaintMenu.add(newCanvasMenuItem);
        jpaintMenu.add(clearCanvasMenuItem);
        jpaintMenu.add(saveImageMenuItem);
        jpaintMenu.add(closeImageMenuItem);
        jpaintMenu.addSeparator();
        jpaintMenu.add(closeAppMenuItem);

        menuBar.add(jpaintMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Sets up the header of the JPaint main window. The header consists of a
     * JLabel, where the title of the canvas is displayed. If there is no 
     * open canvas, then "JPaint" is displayed.
     */
    private void setPageStart() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        frameHeader.setText(imageTitle);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 26);

        frameHeader.setFont(font);
        frameHeader.setForeground(Color.DARK_GRAY);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(frameHeader);

        add(panel, BorderLayout.PAGE_START);
    }

    /**
     * Sets up the space for the image canvas in the main window of the 
     * JPain application.
     */
    private void setPageCenter() {
        JPanel canvasHolder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        canvasHolder.setBackground(Color.DARK_GRAY);

        add(canvasHolder, BorderLayout.CENTER);
    }

    /**
     * Sets up the space and position of the utility buttons on the left side of
     * JPaint's main window. The buttons are disabled by default. They are only 
     * enabled when there is an open canvas in the main window. 
     */
    private void setTools() {
        JPanel utilitiesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel utilityButtonHolder = new JPanel(new GridLayout(0, 1, 5, 5));
        utilitiesPanel.setSize(new Dimension(100, getHeight()));

        pencilUtilityButton.setFont(JPAINT_UTILITY_FONT);
        pencilUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        pencilUtilityButton.addActionListener(pencilAction);

        brushUtilityButton.setFont(JPAINT_UTILITY_FONT);
        brushUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        brushUtilityButton.addActionListener(brushAction);

        eraserUtilityButton.setFont(JPAINT_UTILITY_FONT);
        eraserUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        eraserUtilityButton.addActionListener(eraserAction);

        rectangleUtilityButton.setFont(JPAINT_UTILITY_FONT);
        rectangleUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        rectangleUtilityButton.addActionListener(rectangleAction);

        circleUtilityButton.setFont(JPAINT_UTILITY_FONT);
        circleUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        circleUtilityButton.addActionListener(circleAction);

        lineUtilityButton.setFont(JPAINT_UTILITY_FONT);
        lineUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        lineUtilityButton.addActionListener(lineAction);
        
        undoUtilityButton.setFont(JPAINT_UTILITY_FONT);
        undoUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        undoUtilityButton.addActionListener(undoAction);

        //disabling these buttons by default
        pencilUtilityButton.setEnabled(false);
        brushUtilityButton.setEnabled(false);
        eraserUtilityButton.setEnabled(false);
        rectangleUtilityButton.setEnabled(false);
        circleUtilityButton.setEnabled(false);
        lineUtilityButton.setEnabled(false);
        undoUtilityButton.setEnabled(false);

        utilityButtonHolder.add(pencilUtilityButton);
        utilityButtonHolder.add(brushUtilityButton);
        utilityButtonHolder.add(eraserUtilityButton);
        utilityButtonHolder.add(rectangleUtilityButton);
        utilityButtonHolder.add(circleUtilityButton);
        utilityButtonHolder.add(lineUtilityButton);
        utilityButtonHolder.add(undoUtilityButton);

        utilitiesPanel.add(utilityButtonHolder);
        add(utilitiesPanel, BorderLayout.WEST);
    }

    /**
     * Sets the default and custom color buttons of the bottom of the main 
     * window. 
     */
    private void setColorButtons() {
        JPanel colorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JColorChooser colorChooser = new JColorChooser();
        
        blackButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        blackButton.setBackground(Color.BLACK);
        blackButton.addActionListener(colorBlackAction);
        
        redButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        redButton.setBackground(Color.RED);
        redButton.addActionListener(colorRedAction);
        
        blueButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        blueButton.setBackground(Color.BLUE);
        blueButton.addActionListener(colorBlueAction);
        
        yellowButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        yellowButton.setBackground(Color.yellow);
        yellowButton.addActionListener(colorYellowAction);
        
        greenButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        greenButton.setBackground(Color.GREEN);
        greenButton.addActionListener(colorGreenAction);
        
        wildcard1ColorButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        wildcard2ColorButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        wildcard3ColorButton.setPreferredSize(COLOR_BUTTON_DIMENSIONS);
        wildcard1ColorButton.setBackground(Color.WHITE);
        wildcard2ColorButton.setBackground(Color.WHITE);
        wildcard3ColorButton.setBackground(Color.WHITE);
        
        /**
         * Left and right mouse click events are required for the "wild card" colors
         * for JColorChooser dialog to pop
         */
        wildcard1ColorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    getWildcard1ColorButton().setBackground(colorChooser.showDialog(JPaintFrame.this, "Color picker", getWildcard1ColorButton().getBackground())
                    );
                } else if(SwingUtilities.isLeftMouseButton(e)) {
                    setSelectedColorButton(getWildcard1ColorButton());
                }
            }
        });
        
        wildcard2ColorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    getWildcard2ColorButton().setBackground(colorChooser.showDialog(JPaintFrame.this, "Color picker", getWildcard2ColorButton().getBackground())
                    );
                } else if(SwingUtilities.isLeftMouseButton(e)) {
                    setSelectedColorButton(getWildcard2ColorButton());
                }
            }
        });
        
        wildcard3ColorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    getWildcard3ColorButton().setBackground(colorChooser.showDialog(JPaintFrame.this, "Color picker", getWildcard3ColorButton().getBackground())
                    );
                } else if(SwingUtilities.isLeftMouseButton(e)) {
                    setSelectedColorButton(getWildcard3ColorButton());
                }
            }
        });
        
        colorsPanel.add(blackButton);
        colorsPanel.add(redButton);
        colorsPanel.add(blueButton);
        colorsPanel.add(yellowButton);
        colorsPanel.add(greenButton);
        colorsPanel.add(new JSeparator(JSeparator.VERTICAL));
        colorsPanel.add(wildcard1ColorButton);
        colorsPanel.add(wildcard2ColorButton);
        colorsPanel.add(wildcard3ColorButton);
        
        colorsPanel.add(new JSeparator(JSeparator.VERTICAL));
        colorsPanel.add(brushRadiusSliderCreator());

        add(colorsPanel, BorderLayout.PAGE_END);
    }
    
    /**
     * Sets and positions a slider on the bottom of the main window, beside the 
     * color selection buttons. Returns the slider. The value of the slider sets 
     * the radius of the brush drawing tool.
     * @return JSlider brush tool radius
     */
    private JSlider brushRadiusSliderCreator(){
        JSlider brushRadiusSlider = new JSlider(JSlider.HORIZONTAL, 
                                                BRUSH_RADIUS_MIN, 
                                                BRUSH_RADIUS_MAX, 
                                                BRUSH_RADIUS_INIT);
        
        brushRadiusSlider.setMajorTickSpacing(20);
        brushRadiusSlider.setMinorTickSpacing(5);
        brushRadiusSlider.setPaintTicks(true);
        brushRadiusSlider.setPaintLabels(true);
        
        brushRadiusSlider.addChangeListener(brushRadiusAction);
        
        return brushRadiusSlider;
    }

    /**
     * Creates and positions a canvas which can be used as a drawing surface.
     * Sets the title of the image in the image header. 
     * @param title 
     */
    private void createDrawableCanvas(String title) {

        if (getCanvas() != null) {
            int createCanvasConfirmation = JOptionPane.showConfirmDialog(null, CLEAR_IMAGE_WINDOW_MESSAGE, CLEAR_IMAGE_WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
            if (createCanvasConfirmation == JOptionPane.YES_OPTION) {
                this.getContentPane().remove(canvas);
            }
        }

        imageTitle = title;
        try {
            if (imageTitle.equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name");
                createDrawableCanvas(JOptionPane.showInputDialog(IMAGE_NAME_MESSAGE));
            }
        } catch(NullPointerException npe){
            return;
        }

        frameHeader.setText(imageTitle);
        canvas = new CanvasPanel(selectedColorButton.getBackground(), brushRadius);
        
        // enabling disabled buttons
        clearCanvasMenuItem.setEnabled(true);
        saveImageMenuItem.setEnabled(true);
        closeImageMenuItem.setEnabled(true);

        pencilUtilityButton.setEnabled(true);
        brushUtilityButton.setEnabled(true);
        eraserUtilityButton.setEnabled(true);
        rectangleUtilityButton.setEnabled(true);
        circleUtilityButton.setEnabled(true);
        lineUtilityButton.setEnabled(true);
        undoUtilityButton.setEnabled(true);

        add(canvas, FlowLayout.CENTER);
        revalidate();
    }

    /**
     * Clears the canvas of its contents by replacing the currently used canvas
     * with a new one. 
     */
    private void clearCanvas() {
        if (canvas != null) {
            int clearCanvasConfirmation = JOptionPane.showConfirmDialog(null, CLEAR_IMAGE_WINDOW_MESSAGE, CLEAR_IMAGE_WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
            if (clearCanvasConfirmation == JOptionPane.YES_OPTION) {
                this.getContentPane().remove(canvas);
                canvas = new CanvasPanel(selectedColorButton.getBackground(), brushRadius);
                add(canvas, FlowLayout.CENTER);
                revalidate();
                repaint();
            }
        }
    }

    /**
     * Closes a currently open canvas. 
     */
    private void closeCanvas() {
        if (canvas != null) {
            if (imageTitle != "JPaint") {
                int clearCanvasConfirmation = JOptionPane.showConfirmDialog(null, CLOSE_IMAGE_WINDOW_MESSAGE, CLOSE_IMAGE_WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
                if (clearCanvasConfirmation == JOptionPane.YES_OPTION) {
                    this.getContentPane().remove(canvas);
                    canvas = null;
                    imageTitle = "JPaint";
                    frameHeader.setText(imageTitle);
                    // disabling enabled buttons
                    clearCanvasMenuItem.setEnabled(false);
                    saveImageMenuItem.setEnabled(false);
                    closeImageMenuItem.setEnabled(false);

                    pencilUtilityButton.setEnabled(false);
                    brushUtilityButton.setEnabled(false);
                    eraserUtilityButton.setEnabled(false);
                    rectangleUtilityButton.setEnabled(false);
                    circleUtilityButton.setEnabled(false);
                    lineUtilityButton.setEnabled(false);
                    undoUtilityButton.setEnabled(false);

                    repaint();
                }
            }
        }
    }
    
    /**
     * Sets the currently selected custom color button. Changes the attributes 
     * of the button given in the parameter.
     * @param selectedColorButton 
     */
    private void setSelectedColorButton(JButton selectedColorButton) {
        //Resets previously selected border
        if(this.selectedColorButton != null)
            this.selectedColorButton.setBorder(BorderFactory.createEmptyBorder());
        //Sets a new selected color button
        this.selectedColorButton = selectedColorButton;
        this.selectedColorButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.WHITE));
        if(canvas != null) {
            canvas.setSelectedColor(this.selectedColorButton.getBackground());
        }
    }
    
    /**
     * Changes the attributes of a button based on which tool is selected by 
     * the user. 
     * @param selectedToolButton
     * @param selectedTool 
     */
    private void setSelectedToolButton(JButton selectedToolButton, GlobalConstants.PaintTool selectedTool) {
        if(this.selectedToolButton != null)
            this.selectedToolButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //Sets a new selected tool button
        this.selectedToolButton = selectedToolButton;
        this.selectedToolButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.WHITE));
        if(canvas != null) {
            canvas.setSelectedTool(selectedTool);
        }   
    }

    /**
     * Saves the contents of the canvas on the hard drive. Both the name and 
     * the format of the image can be customized. 
     */
    private void saveImage() {
        if (canvas != null) {

            BufferedImage imageToSave = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D tempGraphics = imageToSave.createGraphics();
            canvas.paintAll(tempGraphics);

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "PNG Images", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    ImageIO.write(imageToSave, "PNG", new File(chooser.getSelectedFile() + ".png"));
                    System.out.println("Save complete! " + chooser.getSelectedFile() + ".png");
                } catch (IOException ioe) {
                    System.err.println("Error occurred during saving the Image!");
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * Connects the action listeners with their corresponding GUI component on 
     * the JPaint main window. 
     */
    private void hookActionListeners() {
        createCanvas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                createDrawableCanvas(JOptionPane.showInputDialog(IMAGE_NAME_MESSAGE));
            }
        };

        clearCanvas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearCanvas();
            }
        };

        saveImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                saveImage();
            }
        };

        closeImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                closeCanvas();
            }
        };

        closeWindow = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (Frame frame : Frame.getFrames()) {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
            }
        };

        pencilAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setSelectedToolButton(getPencilUtilityButton(), GlobalConstants.PaintTool.PENCIL);
            }
        };
        
        brushAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setSelectedToolButton(getBrushUtilityButton(), GlobalConstants.PaintTool.BRUSH);
            }
        };
        
        eraserAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setSelectedToolButton(getEraserUtilityButton(), GlobalConstants.PaintTool.ERASER);
            }
        };

        rectangleAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setSelectedToolButton(getRectangleUtilityButton(), GlobalConstants.PaintTool.RECTANGLE);
            }
        };

        circleAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setSelectedToolButton(getCircleUtilityButton(), GlobalConstants.PaintTool.CIRCLE);
            }
        };

        lineAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setSelectedToolButton(getLineUtilityButton(), GlobalConstants.PaintTool.LINE);
            }
        };
        
        undoAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList canvasElements = canvas.getCanvasElements();
                if(!canvasElements.isEmpty())
                    canvasElements.remove(canvasElements.size() - 1);
                canvas.repaint();
            }
        };
        
        colorBlackAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedColorButton(getBlackButton());
            }
        };
        
        colorRedAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedColorButton(getRedButton());
            }
        };

        colorBlueAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedColorButton(getBlueButton());
            }
        };

        colorYellowAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedColorButton(getYellowButton());
            }
        };

        colorGreenAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedColorButton(getGreenButton());
            }
        };
        
        brushRadiusAction = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting()){
                    brushRadius = (int)source.getValue();
                    if(canvas != null){
                        canvas.setBrushRadius(getBrushRadius());
                    }
                }
            }
        };

    }
}
