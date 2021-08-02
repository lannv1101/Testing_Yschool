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
public class HocVienNGTest {
    
    public HocVienNGTest() {
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
    public void testGetMaHV() {
        System.out.println("getMaHV");
        HocVien instance = new HocVien();
        int expResult = 0;
        int result = instance.getMaHV();
        assertEquals(result, expResult);
       
    }

    @Test(expectedExceptions = Exception.class)
    public void testSetMaHVWithNegative() {
        System.out.println("setMaHV");
        int maHV = -1;
        HocVien instance = new HocVien();
        instance.setMaHV(maHV);
        
    }
    
    @Test
    public void testSetMaHVWithValid() {
        System.out.println("setMaHV");
        int maHV = 2;
        HocVien instance = new HocVien();
        instance.setMaHV(maHV);
        
        int actual = instance.getMaHV();
        
        assertEquals(actual, maHV);
        
    }

 
      @Test
    public void testGetMaKH() {
        System.out.println("getMaKH");
        HocVien instance = new HocVien();
        int expResult = 0;
        int result = instance.getMaKH();
        assertEquals(result, expResult);
      
    }

    @Test(expectedExceptions = Exception.class)
    public void testSetMaKHWithNegative() {
        System.out.println("setMaKH");
        int maKH = -2;
        HocVien instance = new HocVien();
        instance.setMaKH(maKH);
      
    }
    
      @Test
    public void testSetMaKHWithValid() {
        System.out.println("setMaKH");
        int maKH = 2;
        HocVien instance = new HocVien();
        instance.setMaKH(maKH);
        
          int actual = instance.getMaKH();
        
        assertEquals(actual, maKH);
      
    }

    @Test
    public void testGetMaNH() {
        System.out.println("getMaNH");
        HocVien instance = new HocVien();
        String expResult = "";
        String result = instance.getMaNH();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetMaNH() {
        System.out.println("setMaNH");
        String maNH = "NH01";
        HocVien instance = new HocVien();
        instance.setMaNH(maNH);
       String actual = instance.getMaNH();
        assertEquals(actual, maNH);
    }

    @Test
    public void testGetDiem() {
        System.out.println("getDiem");
        HocVien instance = new HocVien();
        double expResult = -1;
        double result = instance.getDiem();
        assertEquals(result, expResult, 0.0);
      
    }

    @Test(expectedExceptions = Exception.class)
    public void testSetDiemWithNegative()  {
        System.out.println("setDiem");
        double diem = -9.0;
        HocVien instance = new HocVien();
        instance.setDiem(diem);
       
    }

    @Test(expectedExceptions = Exception.class)
    public void testSetDiemWithLarge(){
        System.out.println("setDiem");
        double diem = 11;
        HocVien instance = new HocVien();
        instance.setDiem(diem);
       
    }
    @Test
    public void testSetDiemWithValid() {
        System.out.println("setDiem");
        double diem = 8;
        HocVien instance = new HocVien();
        instance.setDiem(diem);
        
        double actual = instance.getDiem();
        
        assertEquals(actual, diem);
       
    }

    
}
