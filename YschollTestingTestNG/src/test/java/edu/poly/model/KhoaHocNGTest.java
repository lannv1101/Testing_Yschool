/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class KhoaHocNGTest {
    
     String dateString ="22/01/2021";
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    
    public KhoaHocNGTest() {
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
    public void testGetMaKH() {
        System.out.println("getMaKH");
        KhoaHoc instance = new KhoaHoc();
        int expResult = 0;
        int result = instance.getMaKH();
        assertEquals(result, expResult);
      
    }

    @Test(expectedExceptions = Exception.class)
    public void testSetMaKHWithNegative() {
        System.out.println("setMaKH");
        int maKH = -1;
        KhoaHoc instance = new KhoaHoc();
        instance.setMaKH(maKH);
       
    }
    @Test
    public void testSetMaKHWithValid() {
        System.out.println("setMaKH");
        int maKH = 10;
        KhoaHoc instance = new KhoaHoc();
        instance.setMaKH(maKH);
        
        int actual = instance.getMaKH();
        assertEquals(actual, maKH);
        
       
    }

    @Test
    public void testGetMaCD() {
        System.out.println("getMaCD");
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.getMaCD();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetMaCD() {
        System.out.println("setMaCD");
        String maCD = "CD01";
        KhoaHoc instance = new KhoaHoc();
        instance.setMaCD(maCD);
        String actual = instance.getMaCD();
        
        assertEquals(actual, maCD);
    }

    @Test
    public void testGetHocPhi() {
        System.out.println("getHocPhi");
        KhoaHoc instance = new KhoaHoc();
        double expResult = 0.0;
        double result = instance.getHocPhi();
        assertEquals(result, expResult, 0.0);
      
    }

    @Test
    public void testSetHocPhiWithZero() {
        System.out.println("setHocPhi");
        double hocPhi = 0.0;
        KhoaHoc instance = new KhoaHoc();
        instance.setHocPhi(hocPhi);
       double actual = instance.getHocPhi();
        assertEquals(actual, hocPhi, 0.0);
    }
     @Test
    public void testSetHocPhiWithValid() {
        System.out.println("setHocPhi");
        double hocPhi = 1600000.0;
        KhoaHoc instance = new KhoaHoc();
        instance.setHocPhi(hocPhi);
       double actual = instance.getHocPhi();
        assertEquals(actual, hocPhi, 0.0);
    }
    
     @Test(expectedExceptions = Exception.class)
    public void testSetHocPhiWithNegative() {
        System.out.println("setHocPhi");
        double hocPhi = 400.0;
        KhoaHoc instance = new KhoaHoc();
        instance.setHocPhi(hocPhi);
       double actual = instance.getHocPhi();
        assertEquals(actual, hocPhi, 0.0);
    }

    @Test
    public void testGetThoiLuong() {
        System.out.println("getThoiLuong");
        KhoaHoc instance = new KhoaHoc();
        int expResult = 0;
        int result = instance.getThoiLuong();
        assertEquals(result, expResult);
       
    }

    @Test(expectedExceptions = Exception.class)
    public void testSetThoiLuongWithZero() {
        System.out.println("setThoiLuong");
        int thoiLuong = 0;
        KhoaHoc instance = new KhoaHoc();
        instance.setThoiLuong(thoiLuong);
      
    }
    @Test(expectedExceptions = Exception.class)
    public void testSetThoiLuongWithNegative() {
        System.out.println("setThoiLuong");
        int thoiLuong = -12;
        KhoaHoc instance = new KhoaHoc();
        instance.setThoiLuong(thoiLuong);
      
    }
    
    @Test
    public void testSetThoiLuongWithValid() {
        System.out.println("setThoiLuong");
        int thoiLuong = 12;
        KhoaHoc instance = new KhoaHoc();
        instance.setThoiLuong(thoiLuong);
        
        int actual= instance.getThoiLuong();
        
        assertEquals(actual, thoiLuong);
      
    }

    @Test
    public void testGetNgayKG() {
        System.out.println("getNgayKG");
        KhoaHoc instance = new KhoaHoc();
        Date expResult = null;
        Date result = instance.getNgayKG();
        assertEquals(result, expResult);
      
    }

    @Test
    public void testSetNgayKG() throws ParseException {
        System.out.println("setNgayKG");
        Date ngayKG = df.parse(dateString);
        KhoaHoc instance = new KhoaHoc();
        instance.setNgayKG(ngayKG);
        
        Date actual = instance.getNgayKG();
        
        assertEquals(actual, ngayKG);
    }

    @Test
    public void testGetGhiChu() {
        System.out.println("getGhiChu");
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.getGhiChu();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetGhiChu() {
        System.out.println("setGhiChu");
        String ghiChu = "ghichu";
        KhoaHoc instance = new KhoaHoc();
        instance.setGhiChu(ghiChu);
        
        String actual = instance.getGhiChu();
        
        assertEquals(actual, ghiChu);
    }

    @Test
    public void testGetMaNV() {
        System.out.println("getMaNV");
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.getMaNV();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetMaNV() {
        System.out.println("setMaNV");
        String maNV = "NV01";
        KhoaHoc instance = new KhoaHoc();
        instance.setMaNV(maNV);
        
        String actual = instance.getMaNV();
        
        assertEquals(actual, maNV);
       
    }

    @Test
    public void testGetNgayTao() {
        System.out.println("getNgayTao");
        KhoaHoc instance = new KhoaHoc();
        Date expResult = null;
        Date result = instance.getNgayTao();
        assertEquals(result, expResult);
      
    }

    @Test
    public void testSetNgayTao() throws ParseException {
        System.out.println("setNgayTao");
        Date ngayTao = df.parse(dateString);
        KhoaHoc instance = new KhoaHoc();
        instance.setNgayTao(ngayTao);
     
        Date actual = instance.getNgayTao();
        
        assertEquals(actual, ngayTao);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
    
    }
    
}
