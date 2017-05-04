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
public class Brush extends Drawable {
    
    private final List<Point> points = new ArrayList<>();
    private int radius;
    private Color selectedColor;
    
    public Brush(Point currentPoint, int radius, Color selectedColor){
        points.add(currentPoint);
        this.radius = radius;
        this.selectedColor = selectedColor;
    }
    
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
    
    public void addPoint(Point newPoint) {
        points.add(newPoint);
    }
    
}
