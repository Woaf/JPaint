package hu.elte.pu.jpaint.gui;

import hu.elte.pu.jpaint.gui.constants.FunConstants;
import static hu.elte.pu.jpaint.gui.constants.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class JPaintFrame extends JFrame {

    private final FunConstants fun = new FunConstants();
    private final String TITLE = fun.returnOneFunString();
    private JPanel canvas;
    private final Font jPaintMenuFont = new Font(Font.SANS_SERIF, Font.ITALIC, 16);
    private final Font jPaintSubMenuFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private final Font jPaintUtilityFont = new Font(Font.DIALOG, Font.PLAIN, 12);
    private final Color utilityButtonColor = new Color(255, 222, 173);
    
    private ActionListener createCanvas;
    private ActionListener clearCanvas;
    private ActionListener saveImage;
    private ActionListener closeImage;
    private ActionListener closeWindow;
    
    private ActionListener pencilAction;

    public JPaintFrame() throws HeadlessException {
        initFrame();
        hookActionListeners();
        setMenu();
        setPageStart();
        setPageCenter();
        setTools();
        setColorButtons();
    }

    public void initFrame() {
        setTitle(TITLE);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jpaintMenu = new JMenu(MENU_TEXT);
        jpaintMenu.setFont(jPaintMenuFont);
        JMenuItem newCanvas = new JMenuItem(NEW_CANVAS);
        JMenuItem clearCanvas = new JMenuItem(CLEAR_CANVAS);
        JMenuItem saveImage = new JMenuItem(SAVE_IMAGE);
        JMenuItem closeImage = new JMenuItem(CLOSE_IMAGE);
        JMenuItem closeApp = new JMenuItem(CLOSE_APP);
        
        newCanvas.setFont(jPaintSubMenuFont);
        clearCanvas.setFont(jPaintSubMenuFont);
        saveImage.setFont(jPaintSubMenuFont);
        closeImage.setFont(jPaintSubMenuFont);
        closeApp.setFont(jPaintSubMenuFont);
        
        newCanvas.addActionListener(createCanvas);
        clearCanvas.addActionListener(this.clearCanvas);
        saveImage.addActionListener(this.saveImage);
        closeImage.addActionListener(this.closeImage);
        closeApp.addActionListener(closeWindow);
        
        jpaintMenu.add(newCanvas);
        jpaintMenu.add(clearCanvas);
        jpaintMenu.add(saveImage);
        jpaintMenu.add(closeImage);
        jpaintMenu.addSeparator();
        jpaintMenu.add(closeApp);

        menuBar.add(jpaintMenu);
        setJMenuBar(menuBar);
    }

    private void setPageStart() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel frameHeader = new JLabel(TITLE);
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
        
        JButton pencilUtilityButton = new JButton("Pencil");
        pencilUtilityButton.setFont(jPaintUtilityFont);
        pencilUtilityButton.setBackground(utilityButtonColor);
        pencilUtilityButton.addActionListener(pencilAction);
        JButton brushUtilityButton = new JButton("Brushy");
        brushUtilityButton.setFont(jPaintUtilityFont);
        brushUtilityButton.setBackground(utilityButtonColor);
        JButton eraserUtilityButton = new JButton("Eraser");
        eraserUtilityButton.setFont(jPaintUtilityFont);
        eraserUtilityButton.setBackground(utilityButtonColor);
        JButton paintBucketButton = new JButton("Bucket");
        paintBucketButton.setFont(jPaintUtilityFont);
        paintBucketButton.setBackground(utilityButtonColor);
        JButton rectangleUtilityTool = new JButton("Rectangle");
        rectangleUtilityTool.setFont(jPaintUtilityFont);
        rectangleUtilityTool.setBackground(utilityButtonColor);
        JButton circleUtilityButton = new JButton("Circle");
        circleUtilityButton.setFont(jPaintUtilityFont);
        circleUtilityButton.setBackground(utilityButtonColor);
        
        utilityButtonHolder.add(pencilUtilityButton);
        utilityButtonHolder.add(brushUtilityButton);
        utilityButtonHolder.add(eraserUtilityButton);
        utilityButtonHolder.add(paintBucketButton);
        utilityButtonHolder.add(rectangleUtilityTool);
        utilityButtonHolder.add(circleUtilityButton);
        
        utilitiesPanel.add(utilityButtonHolder);
        add(utilitiesPanel, BorderLayout.WEST);
        
    }

    private void setColorButtons() {
        JPanel colorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JButton redButton = new JButton("R");
        redButton.setBackground(Color.RED);
        JButton blueButton = new JButton("B");
        blueButton.setBackground(Color.BLUE);
        JButton yellowButton = new JButton("Y");
        yellowButton.setBackground(Color.yellow);
        JButton greenButton = new JButton("G");
        greenButton.setBackground(Color.GREEN);
        
        colorsPanel.add(redButton);
        colorsPanel.add(blueButton);
        colorsPanel.add(yellowButton);
        colorsPanel.add(greenButton);
        
        add(colorsPanel, BorderLayout.PAGE_END);
    }
    
    // MOVE TO LOGIC 
    private void createDrawableCanvas() {
        canvas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        canvas.setSize(200, 200);
        canvas.setBackground(Color.WHITE);
        
        add(canvas, FlowLayout.CENTER);
        revalidate();
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
    
    // check if canvas is null
    private void clearImage() {
        this.getContentPane().remove(canvas);
        canvas = null;
        repaint();
    }
    
    // ACTION LISTENERS
    private void hookActionListeners() {
        createCanvas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                createDrawableCanvas();
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
        
        clearCanvas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                canvas.removeAll();
            }
        };
        
        pencilAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                drawWithPencil();
            }
        };
        
        closeImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearImage();
            }
        };
    }

}
