/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.pt.jpaint.gui;

import hu.elte.pt.jpaint.logic.canvas.CanvasPanel;
import hu.elte.pt.jpaint.logic.drawable.shape.Pencil;
import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Bálint
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JPaintFrameTest {
    
    private static JPaintFrame instance;
    private Method method;
    
    public JPaintFrameTest() {
    }
    
    /**
     * Initalizing JPaintFrame in setUpClass(), so it can be accessed from every tests.
     */
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Initializing the frame.");
        instance = new JPaintFrame();
        instance.initFrame();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test the colors of the color buttons.
     */
    @Test
    public void testButtonColors() {
        System.out.println("Testing the colors of the color buttons.");
        assertEquals(instance.getBlackButton().getBackground(), java.awt.Color.BLACK);
        assertEquals(instance.getRedButton().getBackground(), java.awt.Color.RED);
        assertEquals(instance.getYellowButton().getBackground(), java.awt.Color.YELLOW);
        assertEquals(instance.getBlueButton().getBackground(), java.awt.Color.BLUE);
        assertEquals(instance.getGreenButton().getBackground(), java.awt.Color.GREEN);
        assertEquals(instance.getWildcard1ColorButton().getBackground(), java.awt.Color.WHITE);
        assertEquals(instance.getWildcard2ColorButton().getBackground(), java.awt.Color.WHITE);
        assertEquals(instance.getWildcard3ColorButton().getBackground(), java.awt.Color.WHITE);
    }
    
    /**
     * Test the availablility of the buttons.
     */
    @Test
    public void testBeforeCanvasButtonAvailability() {
        System.out.println("Testing the availability of the tool buttons before canvas creation.");
        assertEquals(false, instance.getPencilUtilityButton().isEnabled());
        assertEquals(false, instance.getBrushUtilityButton().isEnabled());
        assertEquals(false, instance.getEraserUtilityButton().isEnabled());
        assertEquals(false, instance.getRectangleUtilityButton().isEnabled());
        assertEquals(false, instance.getCircleUtilityButton().isEnabled());
        assertEquals(false, instance.getLineUtilityButton().isEnabled());
        assertEquals(false, instance.getUndoUtilityButton().isEnabled());
        
        System.out.println("Testing the availability of the color buttons before canvas creation.");
        assertEquals(true, instance.getBlackButton().isEnabled());
        assertEquals(true, instance.getRedButton().isEnabled());
        assertEquals(true, instance.getYellowButton().isEnabled());
        assertEquals(true, instance.getBlueButton().isEnabled());
        assertEquals(true, instance.getGreenButton().isEnabled());
        assertEquals(true, instance.getWildcard1ColorButton().isEnabled());
        assertEquals(true, instance.getWildcard2ColorButton().isEnabled());
        assertEquals(true, instance.getWildcard3ColorButton().isEnabled());
        
        System.out.println("Testing the availability of the menu items before canvas creation.");
        assertEquals(true, instance.getJMenuBar().getMenu(0).getItem(0).isEnabled());
        assertEquals(false, instance.getSaveImageMenuItem().isEnabled());
        assertEquals(false, instance.getClearCanvasMenuItem().isEnabled());
        assertEquals(false, instance.getCloseImageMenuItem().isEnabled());
        assertEquals(true, instance.getJMenuBar().getMenu(0).getItem(instance.getJMenuBar().getMenu(0).getMenuComponentCount() - 1).isEnabled());
    }

    /**
     * Test of getCanvas method, of class JPaintFrame, before canvas gets created.
     */
    @Test
    public void testBeforeCanvasIfCanvasExists() {
        System.out.println("Testing the getCanvas() method before canvas creation.");
        CanvasPanel expResult = null;
        CanvasPanel result = instance.getCanvas();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setting the canvas.
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    @Test
    public void testCanvasCreation() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("Setting the canvas and calling getCanvas().");
        Class[] parameterTypes = new Class[1];
        parameterTypes[0] = java.lang.String.class;
        Object[] parameters = new Object[1];
        parameters[0] = "Test";
        
        //Access the createDrawableCanvas private method.
        method = instance.getClass().getDeclaredMethod("createDrawableCanvas", parameterTypes);
        method.setAccessible(true);
        method.invoke(instance, parameters);
      
        //Canvas got created, it should not be null.
        assertNotNull(instance.getCanvas());
    }
    
    /**
     * Testing the frame parameters after creating the canvas.
     */
    @Test
    public void testCanvasParamteres() {
        System.out.println("Checking the header, selected color and selected tool (all being defaults) after canvas creation.");
        //JLabel had been set to "Test".
        assertEquals("Test", instance.getFrameHeader().getText());
        
        //No changes should have been made in the button settings so far.
        assertEquals(java.awt.Color.BLACK, instance.getSelectedColorButton().getBackground());
        assertEquals(null, instance.getSelectedToolButton());
    }
    
    /**
     * Testing the availability of the buttons after canvas creation.
     */
    @Test
    public void testCreatedCanvasButtonAvailability() {
        System.out.println("Testing the availability of the tool buttons before after creation.");
        assertEquals(true, instance.getPencilUtilityButton().isEnabled());
        assertEquals(true, instance.getBrushUtilityButton().isEnabled());
        assertEquals(true, instance.getEraserUtilityButton().isEnabled());
        assertEquals(true, instance.getRectangleUtilityButton().isEnabled());
        assertEquals(true, instance.getCircleUtilityButton().isEnabled());
        assertEquals(true, instance.getLineUtilityButton().isEnabled());
        assertEquals(true, instance.getUndoUtilityButton().isEnabled());
        
        System.out.println("Testing the availability of the color buttons after canvas creation.");
        assertEquals(true, instance.getBlackButton().isEnabled());
        assertEquals(true, instance.getRedButton().isEnabled());
        assertEquals(true, instance.getYellowButton().isEnabled());
        assertEquals(true, instance.getBlueButton().isEnabled());
        assertEquals(true, instance.getGreenButton().isEnabled());
        assertEquals(true, instance.getWildcard1ColorButton().isEnabled());
        assertEquals(true, instance.getWildcard2ColorButton().isEnabled());
        assertEquals(true, instance.getWildcard3ColorButton().isEnabled());
        
        System.out.println("Testing the availability of the menu items after canvas creation.");
        assertEquals(true, instance.getJMenuBar().getMenu(0).getItem(0).isEnabled());
        assertEquals(true, instance.getSaveImageMenuItem().isEnabled());
        assertEquals(true, instance.getClearCanvasMenuItem().isEnabled());
        assertEquals(true, instance.getCloseImageMenuItem().isEnabled());
        assertEquals(true, instance.getJMenuBar().getMenu(0).getItem(instance.getJMenuBar().getMenu(0).getMenuComponentCount() - 1).isEnabled());
    }
    
    /**
     * Testing the tool button selection.
     */
    @Test
    public void testCreatedCanvasButtonSetting() {
        System.out.println("Checking if clicking a tool will result in it being selected.");
        instance.getPencilUtilityButton().doClick();
        
        assertNotEquals(instance.getBrushUtilityButton(), instance.getSelectedToolButton());
        assertEquals(instance.getPencilUtilityButton(), instance.getSelectedToolButton());
    }
    
    /**
     * Testing the color button selection.
     */
    @Test
    public void testCreatedCanvasColorButtonSetting() {
        System.out.println("Checking if clicking a color will result in it being selected.");
        instance.getBlueButton().doClick();
        
        assertNotEquals(instance.getBlackButton(), instance.getSelectedColorButton());
        assertEquals(instance.getBlueButton(), instance.getSelectedColorButton());
    }
    
    /**
     * Adding an element to the canvas then deleting it - Undo test.
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    @Test
    public void testCreatedCanvasElementDeleting() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        System.out.println("Adding an element to the canvas and then clicking Undo.");
        //Accessing the CanvasPanel private field called canvasElements
        Field field = instance.getCanvas().getClass().getDeclaredField("canvasElements");
        field.setAccessible(true);
        field.set(instance.getCanvas(), new ArrayList<>(Arrays.asList(new Pencil(new Point(0, 0), java.awt.Color.BLUE))));
        
        //Check if canvas contains the elements
        assertNotNull(instance.getCanvas().getCanvasElements());
        assertFalse(instance.getCanvas().getCanvasElements().isEmpty());
        
        //Press Undo button
        instance.getUndoUtilityButton().doClick();
        assertTrue(instance.getCanvas().getCanvasElements().isEmpty());
    }
    
    /**
     * Testing that Undo remains unselected after clicking it.
     */
    @Test
    public void testCreatedCanvasElementDeletingUndoNotSelected() {
        System.out.println("Test if Undo is still being unselected.");
        assertNotEquals(instance.getSelectedColorButton(), instance.getUndoUtilityButton());
    }
    
}
