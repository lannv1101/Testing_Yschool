/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DELL
 */
public class ChuyenDeTest {
    
    public ChuyenDeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   @Test
    public void testGetMaCD() {
        
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
       
        
        String result = instance.getMaCD();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMaCD method, of class ChuyenDe.
     */
    @Test
    public void testSetMaCD() {
       
        String maCD = "CD01";
        
        ChuyenDe instance = new ChuyenDe();
        instance.setMaCD(maCD);
        String expected="CD01";
        assertEquals(expected, instance.getMaCD());
        
    }

    /**
     * Test of getTenCD method, of class ChuyenDe.
     */
    @Test
    public void testGetTenCD() {
       
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.getTenCD();
        
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setTenCD method, of class ChuyenDe.
     */
    @Test
    public void testSetTenCD() {
      
        String tenCD = "Lập trình";
        ChuyenDe instance = new ChuyenDe();
        instance.setTenCD(tenCD);
         String expected="Lập trình";
      
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expected,   instance.getTenCD());
    }

    /**
     * Test of getHocPhi method, of class ChuyenDe.
     */
    @Test
    public void testGetHocPhi() {
       
        ChuyenDe instance = new ChuyenDe();
        double expResult = 0.0;
        double result = instance.getHocPhi();
        
         assertEquals(expResult, result,0.0);
       
    }

    /**
     * Test of setHocPhi method, of class ChuyenDe.
     */
    @Test
    public void testSetHocPhi() {
        
        double hocPhi = 400;
        ChuyenDe instance = new ChuyenDe();
        instance.setHocPhi(hocPhi);
        
        double expected = 400;
        double result =instance.getHocPhi();
        assertEquals(expected,  result,0.0 );
        // TODO review the generated test code and remove the default call to fail.
      
    }
      @Test(expected = Exception.class)
    public void testSetHocPhiWithNegative() {
        
        double hocPhi = -400;
        ChuyenDe instance = new ChuyenDe();
        instance.setHocPhi(hocPhi);
        
        
        // TODO review the generated test code and remove the default call to fail.
      
    }
        @Test(expected = Exception.class)
    public void testSetHocPhiWithLarge() {
        
        double hocPhi = 400000000000d;
        ChuyenDe instance = new ChuyenDe();
           instance.setHocPhi(hocPhi);
        
        
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of getThoiLuong method, of class ChuyenDe.
     */
    @Test
    public void testGetThoiLuong() {
      
        ChuyenDe instance = new ChuyenDe();
        int expResult = 0;
        int result = instance.getThoiLuong();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setThoiLuong method, of class ChuyenDe.
     */
    @Test
    public void testSetThoiLuong() {
      
        int thoiLuong = 24;
        ChuyenDe instance = new ChuyenDe();
        instance.setThoiLuong(thoiLuong);
       int expected = 24;
        assertEquals(expected, instance.getThoiLuong());
    }
       @Test(expected = Exception.class)
    public void testSetThoiLuongWithNegative() {
      
        int thoiLuong = -24;
        ChuyenDe instance = new ChuyenDe();
        instance.setThoiLuong(thoiLuong);
    }
    
   

    /**
     * Test of getHinh method, of class ChuyenDe.
     */
    @Test
    public void testGetHinh() {
         ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.getHinh();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setHinh method, of class ChuyenDe.
     */
    @Test
    public void testSetHinh() {
        System.out.println("setHinh");
        String hinh = "Hinh.jpg";
        ChuyenDe instance = new ChuyenDe();
        instance.setHinh(hinh);
        String expected = "Hinh.jpg";
        
        assertEquals(expected, instance.getHinh());
        // TODO review the generated test code and remove the default call to fail.
        
      
    }

    /**
     * Test of getMoTa method, of class ChuyenDe.
     */
    @Test
    public void testGetMoTa() {
       
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.getMoTa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of setMoTa method, of class ChuyenDe.
     */
    @Test
    public void testSetMoTa() {
       
        String moTa = "Mota";
        ChuyenDe instance = new ChuyenDe();
        instance.setMoTa(moTa);
        String expected = "Mota";
        assertEquals(expected, instance.getMoTa());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of toString method, of class ChuyenDe.
     */
    @Test
    public void testToString() {
      
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
