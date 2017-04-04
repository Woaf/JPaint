package hu.elte.pt.jpaint.logic.canvas;

import hu.elte.pt.jpaint.logic.drawable.Drawable;
import hu.elte.pt.jpaint.logic.drawable.shape.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Kornél Konkolics
 * @version 0.0.1
 */
public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener {
    private ArrayList canvasElements;
        
    public CanvasPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        canvasElements = new ArrayList();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(200, 200);
        this.setBackground(Color.WHITE);
    }
    
    public void beginDrawing() {
        if(true) {
            Line drawnObject = new Line(getMousePosition(), getMousePosition());
            canvasElements.add(drawnObject);
        }
    }
    
    public void draw() {
        if(true) {
            Drawable currentObject = ((Drawable)(canvasElements.get(canvasElements.size()-1)));
            ((PaintShape)currentObject).setCurrentPoint(getMousePosition());            
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        for(Object element : canvasElements) {
            ((Drawable)element).draw(g);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        beginDrawing();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }  

    @Override
    public void mouseDragged(MouseEvent e) {
        if(getMousePosition() != null){
            draw();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
