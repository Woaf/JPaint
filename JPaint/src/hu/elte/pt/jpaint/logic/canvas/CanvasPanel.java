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
 * @version 0.0.2
 */
public class CanvasPanel extends JPanel implements MouseMotionListener, MouseListener {
    private ArrayList canvasElements;
    private PaintTool selectedTool;
    private Color selectedColor;
    private int brushRadius;
        
    public CanvasPanel(Color selectedColor, int brushRadius) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.brushRadius = brushRadius;
        
        canvasElements = new ArrayList();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setSize(200, 200);
        this.setBackground(Color.WHITE);
        this.selectedColor = selectedColor;
        
        //Change cursor type on the canvas for better visibility
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
    
    public void beginDrawing() {
        Drawable drawnObject;
        switch (this.selectedTool) {
            case RECTANGLE: 
                drawnObject = new hu.elte.pt.jpaint.logic.drawable.shape.Rectangle(getMousePosition(), getMousePosition(), selectedColor);
                break;
            case CIRCLE: 
                drawnObject = new Circle(getMousePosition(), getMousePosition(),  selectedColor);
                break;
            case LINE:
                drawnObject = new Line(getMousePosition(), getMousePosition(), selectedColor);
                break;
            case PENCIL:
                drawnObject = new Pencil(getMousePosition(), selectedColor);
                break;
            case BRUSH:
                drawnObject = new Brush(getMousePosition(), brushRadius, selectedColor);
                break;
            case ERASER:
                drawnObject = new Brush(getMousePosition(), brushRadius, Color.WHITE);
                break;
            default:
                drawnObject = null; 
        }
        canvasElements.add(drawnObject);
    }
    
    public void draw() {
        if(getMousePosition() != null && this.selectedTool != null) {
            Drawable currentObject = ((Drawable)(canvasElements.get(canvasElements.size()-1)));
            if(currentObject instanceof Pencil){
                ((Pencil) currentObject).addPoint(getMousePosition());
            } else if(currentObject instanceof Brush) {
                ((Brush) currentObject).addPoint(getMousePosition());
            } else {
                ((PaintShape)currentObject).setCurrentPoint(getMousePosition());
            }
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Print canvas elements
        for(Object element : canvasElements) {
            ((Drawable)element).draw(g);
        }   
        
        //Store the mouse position so it doesn't change during the drawing, which could result in NullPointerException if outside of canvas
        Point lastMousePosition = getMousePosition();
        if( lastMousePosition != null) {
            //For brush and eraser tools display circle around the cursor
            if(selectedTool == PaintTool.BRUSH || selectedTool == PaintTool.ERASER) {
                Graphics2D g2d = (Graphics2D)g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if(selectedTool == PaintTool.ERASER)
                    g2d.setColor(Color.WHITE);
                else 
                    g2d.setColor(selectedColor);
                
                g2d.fillOval(lastMousePosition.x-brushRadius, lastMousePosition.y-brushRadius, 2*brushRadius, 2*brushRadius);
            }
        }
    }
    
    //Setters and getters
    public void setSelectedTool(PaintTool selectedTool) {
        this.selectedTool = selectedTool;
    }
    
    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
    
    public void setBrushRadius(int brushRadius) {
        this.brushRadius = brushRadius;
    }
    
    public PaintTool getSelectedTool() {
        return this.selectedTool;
    }
    
    public Color getSelectedColor() {
        return this.selectedColor;
    }
    
    public ArrayList getCanvasElements() {
        return this.canvasElements;
    }
    
    //Mouse events
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(getMousePosition() != null && this.selectedTool != null)
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
        repaint();
    }
}
