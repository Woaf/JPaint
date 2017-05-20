package hu.elte.pt.jpaint.logic.drawable.shape;

import java.awt.*;

/**
 *
 * @author Bálint Fazekas
 */
public class Rectangle extends PaintShape {

    /**
     * This constructor initializes a basic Rectangle with two points and 
     * sets the drawing color. The rectangle's opposite corners will be at the 
     * coordinates of the startingPoint and the currentPoint.
     * 
     * Note, that none of the parameters should be negative, otherwise the rectangle
     * will be created with negative dimensional properties which makes it practically
     * invisible for the user, but still creating an object within the memory. 
     * 
     * @param startingPoint one of the opposing corners' coordinate of the rectangle.
     * @param currentPoint the coordinates of the opposite corner to the startingPoint.
     * @param selectedColor the rectangle's color parameter.
     */
    public Rectangle(Point startingPoint, Point currentPoint, Color selectedColor) {
        super(startingPoint, currentPoint, selectedColor);
    }

    /**
     * 
     * @param g the graphical object that draws the shapes.
     */
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(selectedColor);
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
