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
public class HocVienTest {
    
    public HocVienTest() {
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
        assertEquals(expResult, result,0.0);
       
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
        assertEquals(expected, instance.getDiem(),0.0);
       
    }

    /**
     * Test of toString method, of class HocVien.
     */
   
    
}
