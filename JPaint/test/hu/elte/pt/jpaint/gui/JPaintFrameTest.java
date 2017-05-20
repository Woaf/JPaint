/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.pt.jpaint.gui;

import hu.elte.pt.jpaint.logic.canvas.CanvasPanel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bálint
 */
public class JPaintFrameTest {
    
    public JPaintFrameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of initFrame method, of class JPaintFrame.
     */
    @Test
    public void testInitFrame() {
        System.out.println("initFrame");
        JPaintFrame instance = new JPaintFrame();
        instance.initFrame();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(instance.getBlackButton().getBackground(), java.awt.Color.BLACK);
    }

    /**
     * Test of getCanvas method, of class JPaintFrame.
     */
    @Test
    public void testGetCanvas() {
        System.out.println("getCanvas");
        JPaintFrame instance = new JPaintFrame();
        CanvasPanel expResult = null;
        CanvasPanel result = instance.getCanvas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
