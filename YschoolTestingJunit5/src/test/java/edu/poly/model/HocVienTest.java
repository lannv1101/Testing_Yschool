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
public class HocVienTest {
    
    public HocVienTest() {
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
     * Test of getMaHV method, of class HocVien.
     */
  /**
     * Test of getMaHV method, of class HocVien.
     */
    @Test
    public void testGetMaHV() {
       
        HocVien instance = new HocVien();
        int expResult = 0;
        int result = instance.getMaHV();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMaHV method, of class HocVien.
     */
    @Test
    public void testSetMaHV() {
       
        int maHV = 01;
        HocVien instance = new HocVien();
        instance.setMaHV(maHV);
        int expected=01;
        assertEquals(expected, instance.getMaHV());
       
    }

    /**
     * Test of getMaKH method, of class HocVien.
     */
    @Test
    public void testGetMaKH() {
       
        HocVien instance = new HocVien();
        int expResult = 0;
        int result = instance.getMaKH();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setMaKH method, of class HocVien.
     */
    @Test
    public void testSetMaKH() {
       
        int maKH = 001;
        HocVien instance = new HocVien();
        instance.setMaKH(maKH);
        int expected = 001;
        assertEquals(expected, instance.getMaKH());
       
    }

    /**
     * Test of getMaNH method, of class HocVien.
     */
    @Test
    public void testGetMaNH() {
       
        HocVien instance = new HocVien();
        String expResult = "";
        String result = instance.getMaNH();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setMaNH method, of class HocVien.
     */
    @Test
    public void testSetMaNH() {
      
        String maNH = "CNTT";
        HocVien instance = new HocVien();
        instance.setMaNH(maNH);
        String expected ="CNTT";
        assertEquals(expected, instance.getMaNH());
        
    }

    /**
     * Test of getDiem method, of class HocVien.
     */
    @Test
    public void testGetDiem() {
       
        HocVien instance = new HocVien();
        double expResult = -1;
        double result = instance.getDiem();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setDiem method, of class HocVien.
     */
    @Test
    public void testSetDiem() {
      
        double diem = 10;
        HocVien instance = new HocVien();
        instance.setDiem(diem);
        double expected = 10;
        assertEquals(expected, instance.getDiem());
       
    }

    /**
     * Test of toString method, of class HocVien.
     */
    @Test
    public void testToString() {
       
        HocVien instance = new HocVien();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
}
