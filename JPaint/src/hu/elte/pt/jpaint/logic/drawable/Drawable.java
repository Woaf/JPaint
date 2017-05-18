package hu.elte.pt.jpaint.logic.drawable;

import java.awt.Graphics;

/**
 *
 * @author Kornél Konkolics
 * @version 0.0.1
 */
public abstract class Drawable {
    boolean isMousePressed = false;
    public abstract void draw(Graphics g);
    
}
