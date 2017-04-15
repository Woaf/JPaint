package hu.elte.pt.jpaint.logic.drawable.shape;

import java.awt.*;

/**
 *
 * @author Bálint Fazekas
 */
public class Rectangle extends PaintShape {
    public Rectangle(Point startingPoint, Point currentPoint) {
        super(startingPoint, currentPoint);
    }

    /**
     * Using this method below, the user is now able to create a rectangle of any shape. 
     * 
     * The drawRect function requires the following parameters: 
     * - x coordinate of the starting point
     * - y coordinate of the starting point
     * - width of the rectangle
     * - height of the rectangle
     * 
     * Note, that none of the parameters should be negative, otherwise the rectangle
     * will be created with negative dimensional properties which makes it practically
     * invisible for the user, but still creating an object within the memory. 
     */
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        //Anti-aliasing to remove jagged edges from the line
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawRect(returnStartingPointValue(startingPoint.x, currentPoint.x),
                returnStartingPointValue(startingPoint.y, currentPoint.y),
                returnDistanceBetweenTwoPoints(startingPoint.x, currentPoint.x),
                returnDistanceBetweenTwoPoints(startingPoint.y, currentPoint.y));
    }
    
    /**
     * The following methods were created in order to avoid redundant and lengthy parameterization of the draw(...) function.
     */
    
    /**
     * This function returns the lesser of two values, as for the starting point coordinates 
     * the lesser should be given. This function makes sure that the starting point always sets
     * "below" the ending point, considering the screen coordinate system.
     * 
     * @param start
     * @param current 
     * @return lesser of the two values
     */
    private int returnStartingPointValue(int start, int current) {
        return (start > current ? current : start);
    }

    /**
     * This function returns the absolute difference between two integer values, 
     * and is used in the draw(...) function in order to avoid negative dimensions for the 
     * height and the width of the rectangle.
     * 
     * @param start
     * @param current
     * @return absolute difference between two values
     */
    private int returnDistanceBetweenTwoPoints(int start, int current) {
        return Math.abs(start-current);
    }
    
}
