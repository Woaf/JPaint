package hu.elte.pt.jpaint.logic.drawable.shape;

import hu.elte.pt.jpaint.logic.drawable.Drawable;
import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Kornél Konkolics
 * @version 0.0.1
 */
public abstract class PaintShape implements Drawable {

    /**
     * This field identifies the starting point of the shape's bounding box. Also implements the Drawable interface, to enable this class for being painted on a canvas.
     *
     */
    protected Point startingPoint;

    /**
     * This field identifies the ending point of the shape's bounding box
     *
     */
    protected Point currentPoint;

    /**
     * This field holds the drawing color.
     *
     */
    protected Color selectedColor;
    
    /**
     * Constructs and initializes a simple shape between rectangle with opposite corners of startingPoint and currentPoint, with the color selectedColor.
     *
     * @param startingPoint one of the opposing corners of a bounding rectangle.
     * @param currentPoint other one of the opposing corners of a bounding rectangle.
     * @param selectedColor the color of the shape.
     */
    public PaintShape(Point startingPoint, Point currentPoint, Color selectedColor) {
        this.startingPoint = startingPoint;
        this.currentPoint = currentPoint;
        this.selectedColor = selectedColor;
    }

    /**
     * Sets the endpoint of the shape to the point it gets in the first parameter. The second parameter tells the function, if the shape's bounding box will be regular or not.
     *
     * @param currentPoint the shape's new end point.
     * @param isRegular true if the shape is regular.
     */
    public void setCurrentPoint(Point currentPoint, boolean isRegular){
        if(!isRegular) {
            this.currentPoint = currentPoint;
        } else {
            int diffX = currentPoint.x - startingPoint.x;
            int diffY = currentPoint.y - startingPoint.y;
            if(Math.abs(diffX) < Math.abs(diffY)) {
                if(diffX > 0 && diffY > 0) {
                    this.currentPoint = new Point(currentPoint.x, startingPoint.y + diffX);
                } else if(diffX < 0 && diffY > 0) {
                    this.currentPoint = new Point(currentPoint.x, startingPoint.y - diffX);
                } else if(diffX < 0 && diffY < 0) {
                    this.currentPoint = new Point(currentPoint.x, startingPoint.y + diffX);
                } else if(diffX > 0 && diffY < 0) {
                    this.currentPoint = new Point(currentPoint.x, startingPoint.y - diffX);
                }
            } else if(Math.abs(diffX) > Math.abs(diffY)) {
                if(diffX > 0 && diffY > 0) {
                   this.currentPoint = new Point(startingPoint.x + diffY, currentPoint.y); 
                } else if(diffX < 0 && diffY > 0) {
                    this.currentPoint = new Point(startingPoint.x - diffY, currentPoint.y);
                } else if(diffX < 0 && diffY < 0) {
                    this.currentPoint = new Point(startingPoint.x + diffY, currentPoint.y);
                } else if(diffX > 0 && diffY < 0) {
                    this.currentPoint = new Point(startingPoint.x - diffY, currentPoint.y);
                }
            } else {
                this.currentPoint = currentPoint;
            }
        }
    }
}
