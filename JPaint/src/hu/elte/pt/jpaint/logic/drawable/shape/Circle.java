/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.pt.jpaint.logic.drawable.shape;

import java.awt.*;

/**
 *
 * @author Bálint
 */
public class Circle extends PaintShape {
    
    /**
     * Constructs and initializes a basic circle shape, within a bounding box
     * with the opposite corner points of startingPoint and currentPoint. The 
     * shape is painted with the color of selectedColor.
     * If initialized with negative starting and current point the shape won't 
     * be shown, but still creating an object in the memory.
     *
     * @param startingPoint one of the corners of the shapes bounding box.
     * @param currentPoint the other corner, that is opposite of the startingPoint
     * @param selectedColor the circle's color.
     */
    public Circle(Point startingPoint, Point currentPoint, Color selectedColor) {
        super(startingPoint, currentPoint, selectedColor);
    }

    /**
     * Implementation of the Drawable interface's function. It draws an oval 
     * based on the given parameters of the shape. 
     * This function creates an oval with the following parameters: 
     * - x coordinate of the top left corner of the circle
     * - y coordinate of the top left corner
     * - height of the circle
     * - width of the circle
     * 
     * If the starting point sets below, or right of the ending points,
     * then the start and the endpoints should be switched; the 
     * absolute value of the dimensions (height and width) should be 
     * calculated in order to avoid negative dimensional values.
     *
     * @param g the graphical object that draws the shapes.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(selectedColor);
        //Anti-aliasing to remove jagged edges from the line
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawOval((startingPoint.x > currentPoint.x ? currentPoint.x : startingPoint.x),
                (startingPoint.y > currentPoint.y ? currentPoint.y : startingPoint.y), 
                Math.abs(currentPoint.x-startingPoint.x),
                Math.abs(currentPoint.y-startingPoint.y));
    }

}
