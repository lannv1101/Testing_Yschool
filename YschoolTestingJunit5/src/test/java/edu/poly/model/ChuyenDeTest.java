/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DELL
 */
public class ChuyenDeTest {
    
    public ChuyenDeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMaCD method, of class ChuyenDe.
     */
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
        assertEquals(expResult, result);
       
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
        assertEquals(expected,  result );
        // TODO review the generated test code and remove the default call to fail.
      
    }
      @Test
    public void testSetHocPhiWithNegative() {
        
        double hocPhi = -400;
        ChuyenDe instance = new ChuyenDe();
       Exception exception = assertThrows(Exception.class, ()-> instance.setHocPhi(hocPhi));
        
        
        // TODO review the generated test code and remove the default call to fail.
      
    }
        @Test
    public void testSetHocPhiWithLarge() {
        
        double hocPhi = 400000000000d;
        ChuyenDe instance = new ChuyenDe();
       Exception exception = assertThrows(Exception.class, ()-> instance.setHocPhi(hocPhi));
        
        
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
       @Test
    public void testSetThoiLuongWithNegative() {
      
        int thoiLuong = -24;
        ChuyenDe instance = new ChuyenDe();
        Exception ex = assertThrows(Exception.class, ()->instance.setThoiLuong(thoiLuong));
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
