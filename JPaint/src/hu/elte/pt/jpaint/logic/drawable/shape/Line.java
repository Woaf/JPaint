package hu.elte.pt.jpaint.logic.drawable.shape;

import java.awt.*;
/**
 *
 * @author Kornél Konkolics
 * @version 0.0.2
 */

public class Line extends PaintShape {
    public Line(Point startingPoint, Point currentPoint, Color selectedColor) {
        super(startingPoint, currentPoint, selectedColor);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(selectedColor);
        //Anti-aliasing to remove jagged edges from the line
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        /**
         * This method draws between two points using the current color set for the graphics object.
         * @param startingPoint.x This is the x coordinate of the point where the line is drawn from.
         * @param startingPoint.y This is the y coordinate of the point where the line is drawn from.
         * @param currentPoint.x This is the x coordinate of the point where the line is drawn to.
         * @param currentPoint.y This is the y coordinate of the point where the line is drawn to.
         * @return Nothing
         */
        g2d.drawLine(startingPoint.x, startingPoint.y, 
                     currentPoint.x , currentPoint.y );
    }
}
