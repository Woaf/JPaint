package hu.elte.pt.jpaint.logic.drawable;

import java.awt.Graphics;

/**
 *
 * @author Kornél Konkolics
 * @version 0.0.1
 */
public interface Drawable {

    /**
     * The class that implements this interface will be enabled for being drawn on a canvas, if it implements the draw method.
     * 
     * @param g graphical object, that will draw the shapes.
     */
    public abstract void draw(Graphics g);
    
}
