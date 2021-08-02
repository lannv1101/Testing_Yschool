/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author DELL
 */
public class ChuyenDeNGTest {
    
    public ChuyenDeNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void testGetMaCD() {
       
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.getMaCD();
        assertEquals(result, expResult);
        
    }

    @Test
    public void testSetMaCD() {
        
        String maCD = "CD01";
        ChuyenDe instance = new ChuyenDe();
        instance.setMaCD(maCD);
        String expected ="CD01";
        assertEquals(expected, instance.getMaCD());
    }

    @Test
    public void testGetTenCD() {
       
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.getTenCD();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetTenCD() {
       
        String tenCD = "Java";
        ChuyenDe instance = new ChuyenDe();
        instance.setTenCD(tenCD);
        String expected ="Java";
        assertEquals(expected, instance.getTenCD());
      
    }

    @Test
    public void testGetHocPhi() {
       
        ChuyenDe instance = new ChuyenDe();
        double expResult = 0.0;
        double result = instance.getHocPhi();
        assertEquals(result, expResult, 0.0);
       
    }

    @Test
    public void testSetHocPhi() {
       
        double hocPhi = 40.0;
        ChuyenDe instance = new ChuyenDe();
        instance.setHocPhi(hocPhi);
        double actual = instance.getHocPhi();
        
        assertEquals(actual, hocPhi);
        
       
    }
    
      @Test(expectedExceptions = Exception.class)
    public void testSetHocPhiWithNegative() {
       
        double hocPhi = -40.0;
        ChuyenDe instance = new ChuyenDe();
        instance.setHocPhi(hocPhi);
        
    }
         @Test
    public void testSetHocPhiWithZero() {
       
        double hocPhi = 0.0;
        ChuyenDe instance = new ChuyenDe();
        instance.setHocPhi(hocPhi);
         double actual = instance.getHocPhi();
        
        assertEquals(actual, hocPhi);
        
    }

    @Test
    public void testGetThoiLuong() {
        System.out.println("getThoiLuong");
        ChuyenDe instance = new ChuyenDe();
        int expResult = 0;
        int result = instance.getThoiLuong();
        assertEquals(result, expResult);
       
    }

    @Test(expectedExceptions = Exception.class)
    public void testSetThoiLuongWithNegative() {
        System.out.println("setThoiLuong");
        int thoiLuong = -10;
        ChuyenDe instance = new ChuyenDe();
        instance.setThoiLuong(thoiLuong);
      
    }
    
      @Test(expectedExceptions = Exception.class)
    public void testSetThoiLuongWithZero() {
        System.out.println("setThoiLuong");
        int thoiLuong = 0;
        ChuyenDe instance = new ChuyenDe();
        instance.setThoiLuong(thoiLuong);
      
    }
     @Test
    public void testSetThoiLuong() {
        System.out.println("setThoiLuong");
        int thoiLuong = 24;
        ChuyenDe instance = new ChuyenDe();
          instance.setThoiLuong(thoiLuong);
          int actual = instance.getThoiLuong();
          assertEquals(actual, thoiLuong);
        
      
    }

    @Test
    public void testGetHinh() {
        System.out.println("getHinh");
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.getHinh();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetHinh() {
        System.out.println("setHinh");
        String hinh = "Hinh01,jpg";
        ChuyenDe instance = new ChuyenDe();
        instance.setHinh(hinh);
      String actual = instance.getHinh();
      
        assertEquals(actual, hinh);
    }

    @Test
    public void testGetMoTa() {
        System.out.println("getMoTa");
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.getMoTa();
        assertEquals(result, expResult);
      
    }

    @Test
    public void testSetMoTa() {
        System.out.println("setMoTa");
        String moTa = "Mota";
        ChuyenDe instance = new ChuyenDe();
        instance.setMoTa(moTa);
       String actual= instance.getMoTa();
       assertEquals(actual, moTa);
       
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        ChuyenDe instance = new ChuyenDe();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        
    }
    
}
