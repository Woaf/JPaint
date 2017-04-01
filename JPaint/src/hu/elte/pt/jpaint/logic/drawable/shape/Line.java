package hu.elte.pt.jpaint.logic.drawable.shape;

import java.awt.*;
/**
 *
 * @author Kornél Konkolics
 * @version 0.0.1
 */

public class Line extends PaintShape {
    public Line(Point startingPoint, Point currentPoint) {
        super(startingPoint, currentPoint);
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        //Anti-aliasing to remove jagged edges from the line
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.drawLine(startingPoint.x, startingPoint.y, 
                     currentPoint.x , currentPoint.y );
    }
}
