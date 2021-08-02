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
public class NhanVienTest {
    
    public NhanVienTest() {
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
    public void testGetMaNV() {
     
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.getMaNV();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setMaNV method, of class NhanVien.
     */
    @Test
    public void testSetMaNV() {
     
        String maNV = "NV01";
        NhanVien instance = new NhanVien();
        instance.setMaNV(maNV);
        
        String expected= "NV01";
        
        assertEquals(expected, instance.getMaNV());
       
    }

    /**
     * Test of getMatKhau method, of class NhanVien.
     */
    @Test
    public void testGetMatKhau() {
      
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.getMatKhau();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setMatKhau method, of class NhanVien.
     */
    @Test
    public void testSetMatKhau() {
      
        String matKhau = "123";
        NhanVien instance = new NhanVien();
        instance.setMatKhau(matKhau);
        
        
        
         String expected= "123";
        
        assertEquals(expected, instance.getMatKhau());
    }

    /**
     * Test of getHoTen method, of class NhanVien.
     */
    @Test
    public void testGetHoTen() {
     
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.getHoTen();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setHoTen method, of class NhanVien.
     */
    @Test
    public void testSetHoTen() {
      
        String hoTen = "Nguyen Vu Lan";
        NhanVien instance = new NhanVien();
        instance.setHoTen(hoTen);
         String expected= "Nguyen Vu Lan";
        
        assertEquals(expected, instance.getHoTen());
    }

    /**
     * Test of getVaiTro method, of class NhanVien.
     */
    @Test
    public void testGetVaiTro() {
      
        NhanVien instance = new NhanVien();
        boolean expResult = false;
        boolean result = instance.getVaiTro();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of setVaiTro method, of class NhanVien.
     */
    @Test
    public void testSetVaiTro() {
    
        boolean vaiTro = false;
        NhanVien instance = new NhanVien();
        instance.setVaiTro(vaiTro);
       
        
         boolean expected= false;
        
        assertEquals(expected, instance.getVaiTro());
    }

    /**
     * Test of toString method, of class NhanVien.
     */
    @Test
    public void testToString() {
      
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
    
}
