package hu.elte.pt.jpaint.logic.drawable.shape;

import java.awt.*;

/**
 *
 * @author Bálint
 */
public class Rectangle extends PaintShape {
    public Rectangle(Point startingPoint, Point currentPoint) {
        super(startingPoint, currentPoint);
    }

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
    
    private int returnStartingPointValue(int start, int current) {
        return (start > current ? current : start);
    }

    private int returnDistanceBetweenTwoPoints(int start, int current) {
        return (start > current ? (start-current) : (current-start));
    }
    
}
