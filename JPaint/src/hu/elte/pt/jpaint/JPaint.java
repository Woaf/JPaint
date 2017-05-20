package hu.elte.pt.jpaint;

import hu.elte.pt.jpaint.gui.JPaintFrame;

/**
 *
 * @author Bálint Fazekas
 */
public class JPaint {

    /**
     * The applications main method. Initializes a JPaintFrame and sets it 
     * visible.
     *
     * @param args the input parameters given to the main method.
     */
    public static void main(String[] args) {
        JPaintFrame window = new JPaintFrame();
        window.setVisible(true);
    }
    
}
