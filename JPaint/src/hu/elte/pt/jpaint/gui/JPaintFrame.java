package hu.elte.pt.jpaint.gui;

import hu.elte.pt.jpaint.GlobalConstants;
import static hu.elte.pt.jpaint.gui.constants.WindowConstants.*;
import hu.elte.pt.jpaint.logic.canvas.CanvasPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
 * @version 0.1
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
    private JButton paintBucketUtilityButton = new JButton(BUCKET_UTILITY_TEXT);
    private JButton rectangleUtilityButton = new JButton(RECTANGLE_UTILITY_TEXT);
    private JButton circleUtilityButton = new JButton(CIRCLE_UTILITY_TEXT);
    private JButton lineUtilityButton = new JButton(LINE_UTILITY_TEXT);
    
    private ActionListener createCanvas;
    private ActionListener clearCanvas;
    private ActionListener saveImage;
    private ActionListener closeImage;
    private ActionListener closeWindow;

    private ActionListener pencilAction;
    private ActionListener brushAction;
    private ActionListener eraserAction;
    private ActionListener bucketAction;
    private ActionListener rectangleAction;
    private ActionListener circleAction;
    private ActionListener lineAction;
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

    public void initFrame() {
        setTitle(WINDOW_TITLE);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

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

    private void setPageCenter() {
        JPanel canvasHolder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        canvasHolder.setBackground(Color.DARK_GRAY);

        add(canvasHolder, BorderLayout.CENTER);
    }

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

        paintBucketUtilityButton.setFont(JPAINT_UTILITY_FONT);
        paintBucketUtilityButton.setBackground(UTILITY_BUTTON_COLOR);

        rectangleUtilityButton.setFont(JPAINT_UTILITY_FONT);
        rectangleUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        rectangleUtilityButton.addActionListener(rectangleAction);

        circleUtilityButton.setFont(JPAINT_UTILITY_FONT);
        circleUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        circleUtilityButton.addActionListener(circleAction);

        lineUtilityButton.setFont(JPAINT_UTILITY_FONT);
        lineUtilityButton.setBackground(UTILITY_BUTTON_COLOR);
        lineUtilityButton.addActionListener(lineAction);

        //disabling these buttons by default
        pencilUtilityButton.setEnabled(false);
        brushUtilityButton.setEnabled(false);
        eraserUtilityButton.setEnabled(false);
        paintBucketUtilityButton.setEnabled(false);
        rectangleUtilityButton.setEnabled(false);
        circleUtilityButton.setEnabled(false);
        lineUtilityButton.setEnabled(false);

        utilityButtonHolder.add(pencilUtilityButton);
        utilityButtonHolder.add(brushUtilityButton);
        utilityButtonHolder.add(eraserUtilityButton);
        utilityButtonHolder.add(paintBucketUtilityButton);
        utilityButtonHolder.add(rectangleUtilityButton);
        utilityButtonHolder.add(circleUtilityButton);
        utilityButtonHolder.add(lineUtilityButton);

        utilitiesPanel.add(utilityButtonHolder);
        add(utilitiesPanel, BorderLayout.WEST);
    }

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
                    wildcard1ColorButton.setBackground(
                        colorChooser.showDialog(JPaintFrame.this, "Color picker", wildcard1ColorButton.getBackground())
                    );
                } else if(SwingUtilities.isLeftMouseButton(e)) {
                    setSelectedColorButton(wildcard1ColorButton);
                }
            }
        });
        
        wildcard2ColorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    wildcard2ColorButton.setBackground(
                        colorChooser.showDialog(JPaintFrame.this, "Color picker", wildcard2ColorButton.getBackground())
                    );
                } else if(SwingUtilities.isLeftMouseButton(e)) {
                    setSelectedColorButton(wildcard2ColorButton);
                }
            }
        });
        
        wildcard3ColorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    wildcard3ColorButton.setBackground(
                        colorChooser.showDialog(JPaintFrame.this, "Color picker", wildcard3ColorButton.getBackground())
                    );
                } else if(SwingUtilities.isLeftMouseButton(e)) {
                    setSelectedColorButton(wildcard3ColorButton);
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

    public CanvasPanel getCanvas() {
        return canvas;
    }

    // MOVE TO LOGIC 
    private void createDrawableCanvas() {

        if (getCanvas() != null) {
            int createCanvasConfirmation = JOptionPane.showConfirmDialog(null, CLEAR_IMAGE_WINDOW_MESSAGE, CLEAR_IMAGE_WINDOW_TITLE, JOptionPane.YES_NO_OPTION);
            if (createCanvasConfirmation == JOptionPane.YES_OPTION) {
                this.getContentPane().remove(canvas);
            }
        }

        imageTitle = JOptionPane.showInputDialog(IMAGE_NAME_MESSAGE);
        try {
            if (imageTitle.equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name");
                createDrawableCanvas();
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
        paintBucketUtilityButton.setEnabled(true);
        rectangleUtilityButton.setEnabled(true);
        circleUtilityButton.setEnabled(true);
        lineUtilityButton.setEnabled(true);

        add(canvas, FlowLayout.CENTER);
        revalidate();
    }

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
                    paintBucketUtilityButton.setEnabled(false);
                    rectangleUtilityButton.setEnabled(false);
                    circleUtilityButton.setEnabled(false);
                    lineUtilityButton.setEnabled(false);

                    repaint();
                }
            }
        }
    }
    
    private void setSelectedColorButton(JButton selectedColorButton) {
        //Resets previously selected border
        if(this.selectedColorButton != null)
            this.selectedColorButton.setBorder(BorderFactory.createEmptyBorder());
        //Sets a new selected color button
        this.selectedColorButton = selectedColorButton;
        this.selectedColorButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.WHITE));
        if(canvas != null)
            canvas.setSelectedColor(this.selectedColorButton.getBackground());
    }

    private void drawWithPencil() {
        //this is only for testing
        BufferedImage mae;
        try {
            mae = ImageIO.read(new File("src/hu/elte/pu/jpaint/gui/constants/mae.png"));
            JLabel maePic = new JLabel(new ImageIcon(mae));
            canvas.add(maePic);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (NullPointerException ex) {
            System.out.println("there is no canvas to draw on :c");
        }
        revalidate();
    }

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

    // ACTION LISTENERS
    private void hookActionListeners() {
        createCanvas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                createDrawableCanvas();
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
                canvas.setSelectedTool(GlobalConstants.PaintTool.PENCIL);
            }
        };
        
        brushAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.setSelectedTool(GlobalConstants.PaintTool.BRUSH);
            }
        };
        
        eraserAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.setSelectedTool(GlobalConstants.PaintTool.ERASER);
            }
        };

        rectangleAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.setSelectedTool(GlobalConstants.PaintTool.RECTANGLE);
            }
        };

        circleAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.setSelectedTool(GlobalConstants.PaintTool.CIRCLE);
            }
        };

        lineAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.setSelectedTool(GlobalConstants.PaintTool.LINE);
            }
        };
        
        colorBlackAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //canvas.setSelectedColor(Color.BLACK);
                setSelectedColorButton(blackButton);
            }
        };
        
        colorRedAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //canvas.setSelectedColor(Color.RED);
                setSelectedColorButton(redButton);
            }
        };

        colorBlueAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //canvas.setSelectedColor(Color.BLUE);
                setSelectedColorButton(blueButton);
            }
        };

        colorYellowAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //canvas.setSelectedColor(Color.YELLOW);
                setSelectedColorButton(yellowButton);
            }
        };

        colorGreenAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //canvas.setSelectedColor(Color.GREEN);
                setSelectedColorButton(greenButton);
            }
        };
        
        brushRadiusAction = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                JSlider source = (JSlider)ce.getSource();
                if(!source.getValueIsAdjusting()){
                    brushRadius = (int)source.getValue();
                    if(canvas != null){
                        canvas.setBrushRadius(brushRadius);
                    }
                }
            }
        };

    }
}
