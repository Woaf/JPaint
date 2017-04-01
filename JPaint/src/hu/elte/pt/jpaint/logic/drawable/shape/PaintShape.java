package hu.elte.pt.jpaint.logic.drawable.shape;

import hu.elte.pt.jpaint.logic.drawable.Drawable;
import java.awt.Point;

/**
 *
 * @author Kornél Konkolics
 * @version 0.0.1
 */
public abstract class PaintShape extends Drawable {
    protected Point startingPoint;
    protected Point currentPoint;
    
    public PaintShape(Point startingPoint, Point currentPoint) {
        this.startingPoint = startingPoint;
        this.currentPoint = currentPoint;
    }
    
    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }
}
