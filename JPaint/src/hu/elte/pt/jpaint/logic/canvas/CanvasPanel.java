package hu.elte.pt.jpaint.logic.canvas;

import hu.elte.pt.jpaint.GlobalConstants.PaintTool;
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
    private PaintTool selectedTool;
        
    public CanvasPanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        canvasElements = new ArrayList();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(200, 200);
        this.setBackground(Color.WHITE);
    }
    
    public void beginDrawing() {
        Drawable drawnObject;
        switch (this.selectedTool) {
            case LINE:
               drawnObject = new Line(getMousePosition(), getMousePosition());
                break;
            case CIRCLE: 
                drawnObject = new Circle(getMousePosition(), getMousePosition());
                break;
            default:
                drawnObject = null; 
        }
        canvasElements.add(drawnObject);
    }
    
    public void draw() {
        if(this.selectedTool != null) {
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
    
    //Setters and getters
    public void setSelectedTool(PaintTool selectedTool) {
        this.selectedTool = selectedTool;
    }
    
    //Mouse events
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.selectedTool != null)
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
        if(getMousePosition() != null && this.selectedTool != null){
            draw();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
