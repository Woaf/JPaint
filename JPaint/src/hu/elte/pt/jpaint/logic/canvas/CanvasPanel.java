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
        
    /**
     * Constructs and initializes a canvas object, where the drawables will be 
     * painted. Adds levent listeners for the mouse and its motion.
     *
     * @param selectedColor drawing Color.
     * @param brushRadius brush's or eraser's radius.
     */
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
    
    /**
     * Initializes the drawables depending on the selected tool and adds them 
     * to the canvasElements list.
     *
     */
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
    
    /**
     * Modifies the last drawable object in the canvasElements list. Sets new 
     * current position for the second parameter for shapes using a two 
     * parameters, or adds a new point to the list of points. The modification 
     * depends on the selected tool.
     *
     * @param isRegular true if drawing a regular shape (if supported).
     */
    public void draw(boolean isRegular) {
        if(getMousePosition() != null && this.selectedTool != null) {
            Drawable currentObject = ((Drawable)(canvasElements.get(canvasElements.size()-1)));
            if(currentObject instanceof Pencil) {
                ((Pencil) currentObject).addPoint(getMousePosition());
            } else if(currentObject instanceof Brush) {
                ((Brush) currentObject).addPoint(getMousePosition());
            } else {
                if(isRegular) {
                    ((PaintShape)currentObject).setCurrentPoint(getMousePosition(), true);
                } else {
                    ((PaintShape)currentObject).setCurrentPoint(getMousePosition(), false);
                }
            }
            repaint();
        }
    }
    
    /**
     * This function will paint all the drawable shapes, and objects present in 
     * the canvasElements currently. Also draws a circle representing the 
     * current brush size, if Brush or Eraser is selected, around the cursor.
     *
     * @param g the graphical object, that paints the drawables.
     */
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

    /**
     * Sets the active painting tool.
     *
     * @param selectedTool the new tool to use for drawing.
     */
    public void setSelectedTool(PaintTool selectedTool) {
        this.selectedTool = selectedTool;
    }
    
    /**
     * Sets the drawing color to the given Color value.
     *
     * @param selectedColor new Color vlae for painting.
     */
    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
    
    /**
     * Sets the radius for the Brush and Eraser tools. Negative values for the 
     * parameter should work, but now conventional.
     *
     * @param brushRadius new radius value.
     */
    public void setBrushRadius(int brushRadius) {
        this.brushRadius = brushRadius;
    }
    
    /**
     * Returns the currently set tool for drawing shapes.
     *
     * @return the PaintTool object of the selected tool.
     */
    public PaintTool getSelectedTool() {
        return this.selectedTool;
    }
    
    /**
     * Returns the currently set color for drawing objects.
     *
     * @return the Color value of the drawing color.
     */
    public Color getSelectedColor() {
        return this.selectedColor;
    }
    
    /**
     * Returns a drawable object list of the canvas property.
     *
     * @return the canvas ArrayList of drawables.
     */
    public ArrayList getCanvasElements() {
        return this.canvasElements;
    }
    
    //Mouse events

    /**
     * Whenever the mouse is clicked on the canvas object, that event will be 
     * processed with this function.
     *
     * @param e mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Whenever the mouse is dragged on the canvas object, that event will be 
     * processed with this function. This function checks if the mouse is 
     * within boundaries of the canvas and if the user selected a tool, any of 
     * these are false, the function ends and does nothing. If they are true 
     * it will call the beginDrawing function.
     *
     * @param e mouse event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(getMousePosition() != null && this.selectedTool != null)
            beginDrawing();
    }

    /**
     * Whenever the mouse is released on the canvas object, that event will be 
     * processed with this function.
     *
     * @param e mouse event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Whenever the mouse is entered the canvas object, that event will be 
     * processed with this function.
     *
     * @param e mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Whenever the mouse is exited the canvas object, that event will be 
     * processed with this function.
     *
     * @param e mouse event.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }  

    /**
     * Whenever the mouse is dragged on the canvas object, that event will be 
     * processed with this function. This function checks if the mouse is 
     * within boundaries of the canvas and if the user selected a tool, any of 
     * these are false, the function ends and does nothing. If they are true, 
     * it further checks if the SHIFT key is being pressed. If true, then the 
     * object chapes that support regular drawing, will be painted like that.
     *
     * @param e mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(getMousePosition() != null && this.selectedTool != null){
            if((e.getModifiers() & e.SHIFT_MASK) != 0){
                draw(true);
            } else {
                draw(false);
            }
        }
    }

    /**
     * Repaints the canvas whenever the mouse is moved on the canvas.
     *
     * @param e mouse event.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    }
}
