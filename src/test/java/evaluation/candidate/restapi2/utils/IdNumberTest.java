/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluation.candidate.restapi2.utils;

import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raymond
 */
public class IdNumberTest {
    
    public IdNumberTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of validateIdNumber method, of class IdNumber.
     */
    @Test
    public void testValidateIdNumberPositive() {
        System.out.println("validateIdNumber");
        IdNumber instance = new IdNumber("7605235084083");
        boolean expResult = true;
        boolean result = instance.validateIdNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of validateIdNumber method, of class IdNumber.
     */
    @Test
    public void testValidateIdNumberNegative() {
        System.out.println("validateIdNumber");
        IdNumber instance = new IdNumber("7605235184183");
        boolean expResult = false;
        boolean result = instance.validateIdNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAge method, of class IdNumber.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        IdNumber instance =  new IdNumber("7605235084083");
        int expResult = 45;
        instance.calcAge();
        int result = instance.getAge();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGender method, of class IdNumber.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        IdNumber instance =  new IdNumber("7605235084083");
        instance.calcGender();
        String expResult = "M";
        String result = instance.getGender();
        assertEquals(expResult, result);
    }

    
}
