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
public class NhanVienNGTest {
    
    public NhanVienNGTest() {
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
    public void testGetMaNV() {
        System.out.println("getMaNV");
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.getMaNV();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetMaNV() {
        System.out.println("setMaNV");
        String maNV = "NV01";
        NhanVien instance = new NhanVien();
        instance.setMaNV(maNV);
     
        String actual = instance.getMaNV();
        
        assertEquals(actual, maNV);
    }

    @Test
    public void testGetMatKhau() {
        System.out.println("getMatKhau");
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.getMatKhau();
        assertEquals(result, expResult);
        
    }

    @Test
    public void testSetMatKhau() {
        System.out.println("setMatKhau");
        String matKhau = "matkhau0123";
        NhanVien instance = new NhanVien();
        instance.setMatKhau(matKhau);
        
        String actual = instance.getMatKhau();
        
        assertEquals(actual, matKhau);
    }

    @Test
    public void testGetHoTen() {
        System.out.println("getHoTen");
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.getHoTen();
        assertEquals(result, expResult);
      
    }

    @Test
    public void testSetHoTen() {
        System.out.println("setHoTen");
        String hoTen = "nguyen vu lan";
        NhanVien instance = new NhanVien();
        instance.setHoTen(hoTen);
        
        String actual = instance.getHoTen();
        
        assertEquals(actual, hoTen);
       
    }

    @Test
    public void testGetVaiTro() {
        System.out.println("getVaiTro");
        NhanVien instance = new NhanVien();
        boolean expResult = false;
        boolean result = instance.getVaiTro();
        assertEquals(result, expResult);
        
    }

    @Test
    public void testSetVaiTro() {
        System.out.println("setVaiTro");
        boolean vaiTro = true;
        NhanVien instance = new NhanVien();
        instance.setVaiTro(vaiTro);
        
        boolean actual = instance.getVaiTro();
        
        assertEquals(actual, vaiTro);
       
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        NhanVien instance = new NhanVien();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }
    
}
