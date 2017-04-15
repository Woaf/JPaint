/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.pt.jpaint.logic.keylisteners;

/**
 *
 * @author Bálint
 */
public class ShiftButtonListener {
    
    private static boolean shiftPressed = false;
    
    public static boolean isShiftPressed() {
        synchronized (ShiftButtonListener.class) {
            return shiftPressed;
        }
    }
}
