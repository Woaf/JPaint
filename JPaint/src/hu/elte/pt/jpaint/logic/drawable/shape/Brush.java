package hu.elte.pt.jpaint.logic.drawable.shape;

import hu.elte.pt.jpaint.logic.drawable.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author retech
 */
public class Brush implements Drawable {
    
    private final List<Point> points = new ArrayList<>();
    private int radius;
    private Color selectedColor;
    
    /**
     * Constructs and initializes a basic brush with a centerpoint of 
     * currentPoint, brush radius and color, that is given in the parameters.
     *
     * @param currentPoint the center point where the brush will be painted.
     * @param radius the brush tools radius, that will be filled with a color.
     * @param selectedColor brush's painting color.
     */
    public Brush(Point currentPoint, int radius, Color selectedColor){
        points.add(currentPoint);
        this.radius = radius;
        this.selectedColor = selectedColor;
    }
    
    /**
     * Implementation of the Drawable interface's function. This will define 
     * what will be painted on the graphical component. The function will 
     * iterate through the points ArrayList and draw a filled oval in the 
     * radius of the points, with the color that is set by the constructor.
     *
     * @param g the graphical object, that paints the brush shapes.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(selectedColor);
        for(Point poi : points) {
            g2d.fillOval((int)poi.x - (int)radius, 
                        (int)poi.y - (int)radius, 
                        (int)radius * 2, 
                        (int)radius * 2);
        }
    }
    
    /**
     * Adds a new point to the ArrayList of points, that will be painted.
     *
     * @param newPoint the Point that will be added to the list.
     */
    public void addPoint(Point newPoint) {
        points.add(newPoint);
    }
    
}
