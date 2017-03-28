package hu.elte.pu.jpaint.gui;

import static hu.elte.pu.jpaint.gui.constants.WindowConstants.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class JPaintFrame extends JFrame {

    private JPanel workspace = new JPanel();
    
    private ActionListener createCanvas;
    private ActionListener closeWindow;

    public JPaintFrame() throws HeadlessException {
        initFrame();
        hookActionListeners();
        setMenu();
        setPageStart();
        setTools();
        setPageCenter();
        setColorButtons();
        //setPageEnd();
    }

    public void initFrame() {
        setTitle(WINDOW_TITLE);
        setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jpaintMenu = new JMenu(MENU_TEXT);
        JMenuItem newCanvas = new JMenuItem(NEW_CANVAS);
        JMenuItem closeApp = new JMenuItem(CLOSE_APP);
        
        newCanvas.addActionListener(createCanvas);
        closeApp.addActionListener(closeWindow);
        
        jpaintMenu.add(newCanvas);
        jpaintMenu.addSeparator();
        jpaintMenu.add(closeApp);

        menuBar.add(jpaintMenu);
        setJMenuBar(menuBar);
    }

    private void setPageStart() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel frameHeader = new JLabel(WINDOW_TITLE);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 26);

        frameHeader.setFont(font);
        frameHeader.setForeground(Color.DARK_GRAY);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(frameHeader);

        add(panel, BorderLayout.PAGE_START);
    }

    private void setPageCenter() {
        workspace.setBackground(Color.DARK_GRAY);
        workspace.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(workspace, BorderLayout.CENTER);
    }

    // not used anymore
    private void setPageEnd() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("v.0.1");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel.setPreferredSize(new Dimension(getWidth(), 24));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(label);
        add(panel, BorderLayout.SOUTH);
    }

    private void setTools() {
        JPanel utilitiesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel utilityButtonHolder = new JPanel(new GridLayout(0, 1, 5, 5));
        utilitiesPanel.setSize(new Dimension(100, getHeight()));
        
        JButton pencilUtilityButton = new JButton("Pencil");
        pencilUtilityButton.setBackground(Color.yellow);
        JButton brushUtilityButton = new JButton("Brushy");
        brushUtilityButton.setBackground(Color.MAGENTA);
        JButton eraserUtilityButton = new JButton("Eraser");
        eraserUtilityButton.setBackground(Color.WHITE);
        JButton paintBucketButton = new JButton("Bucket");
        paintBucketButton.setBackground(Color.ORANGE);
        
        utilityButtonHolder.add(pencilUtilityButton);
        utilityButtonHolder.add(brushUtilityButton);
        utilityButtonHolder.add(eraserUtilityButton);
        utilityButtonHolder.add(paintBucketButton);
        
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

    private void hookActionListeners() {
        createCanvas = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("New canvas clicked.");
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
    }

}
