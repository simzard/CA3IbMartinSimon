/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simon
 */
public class ExchangeRateFacadeTest {
    
    static ExchangeRateFacade xf = new ExchangeRateFacade();
    
   
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    

    /**
     * Test of convertCurrency method, of class ExchangeRateFacade.
     */
    @Test
    public void testConvertCurrency() {
        System.out.println("Converting 100 DKR to DKR");
        float amount = 100.0F;
        float expResult = 100.0F;
        String fromCurrency = "DKR";
        String toCurrency = "DKR";
                
        float result = xf.convertCurrency(amount, fromCurrency, toCurrency);
        System.out.println("expResult: " + expResult + ", result:" + result);
        assertTrue(expResult == result);
        
        System.out.println("Converting 100 EUR to DKR");
        float amount2 = 100.0F;
        float expResult2 = 746.0F;
        String fromCurrency2 = "EUR";
        String toCurrency2 = "DKR";
        float result2 = xf.convertCurrency(amount2, fromCurrency2, toCurrency2);
        System.out.println("expResult: " + expResult2 + ", result:" + result2);
        assertTrue(expResult2 == result2);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
