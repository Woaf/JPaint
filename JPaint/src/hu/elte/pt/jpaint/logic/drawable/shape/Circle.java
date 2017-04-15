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
    
    public Circle(Point startingPoint, Point currentPoint) {
        super(startingPoint, currentPoint);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);
        //Anti-aliasing to remove jagged edges from the line
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /**
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
         */
        
        g2d.drawOval((startingPoint.x > currentPoint.x ? currentPoint.x : startingPoint.x),
                (startingPoint.y > currentPoint.y ? currentPoint.y : startingPoint.y), 
                Math.abs(currentPoint.x-startingPoint.x),
                Math.abs(currentPoint.y-startingPoint.y));
    }

}
