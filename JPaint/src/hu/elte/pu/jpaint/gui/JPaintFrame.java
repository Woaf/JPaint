package hu.elte.pu.jpaint.gui;

import static hu.elte.pu.jpaint.gui.constants.WindowConstants.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class JPaintFrame extends JFrame {

    private JPanel workspace = new JPanel();
    
    private ActionListener createCanvas;
    private ActionListener closeWindow;

    public JPaintFrame() throws HeadlessException {
        initFrame();
        hookActionListeners();
        setMenu();
        setPageStart();
        setPageCenter();
        setPageEnd();
        setTools();
        setColorButtons();
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

    }

    private void setColorButtons() {

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
