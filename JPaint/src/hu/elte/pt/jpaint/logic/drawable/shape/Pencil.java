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
public class Pencil implements Drawable {
    private final List<Point> points = new ArrayList<>();
    private Color pencilColor;
    
    /**
     * This constructor sets the drawing color for the Pencil drawings and adds 
     * a new point to the points list. The list elements will be drawn in the 
     * draw function.
     *
     * @param currentPoint
     * @param selectedColor
     */
    public Pencil(Point currentPoint, Color selectedColor) {
        points.add(currentPoint);
        pencilColor = selectedColor;
    }
    
    /**
     * Implementation of the Drawable interface's function. This function will 
     * draw lines between the coordinates of the points ArrayList with 
     * drawPolyline function of the graphical object that is given as parameter.
     *
     * @param g the graphical objet, that paints the points.
     */
    @Override
    public void draw(Graphics g) {
        int[] xCoords = new int[points.size()];
        int[] yCoords = new int[points.size()];
        for(int i = 0; i < points.size(); i++){
            xCoords[i] = points.get(i).x;
            yCoords[i] = points.get(i).y;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(pencilColor);
        g2d.drawPolyline(xCoords, yCoords, points.size());
    }
    
    /**
     * Adds a new point to the list of points that will be drawn.
     *
     * @param point
     */
    public void addPoint(Point point){
        points.add(point);
    }
    
}
