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
public class Pencil extends Drawable {
    private final List<Point> points = new ArrayList<>();
    private Color pencilColor;
    
    public Pencil(Point currentPoint, Color selectedColor) {
        points.add(currentPoint);
        pencilColor = selectedColor;
    }
    
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
    
    public void addPoint(Point point){
        points.add(point);
    }
    
}
