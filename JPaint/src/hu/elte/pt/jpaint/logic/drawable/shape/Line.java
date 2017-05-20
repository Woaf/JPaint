package hu.elte.pt.jpaint.logic.drawable.shape;

import java.awt.*;
/**
 *
 * @author Kornél Konkolics
 * @version 0.0.2
 */

public class Line extends PaintShape {

    /**
     * Constructs and initializes a line object, that will be drawn between 
     * the startingPoint and the curentPoint with the selectedColor applied to it.
     *
     * @param startingPoint starting point of the line.
     * @param currentPoint endpoint of the line.
     * @param selectedColor color of the line.
     */
    public Line(Point startingPoint, Point currentPoint, Color selectedColor) {
        super(startingPoint, currentPoint, selectedColor);
    }
    
    /**
     * This method draws between two points using the current color set for the graphics object.
     * 
     * @param g the graphical object that draws the shape.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(selectedColor);
        //Anti-aliasing to remove jagged edges from the line
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawLine(startingPoint.x, startingPoint.y, 
                     currentPoint.x , currentPoint.y );
    }
}
