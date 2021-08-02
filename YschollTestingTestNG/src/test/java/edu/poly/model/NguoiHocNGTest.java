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
public class NguoiHocNGTest {
    
    String dateString ="11/01/1998";
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    public NguoiHocNGTest() {
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
    public void testGetMaNH() {
        System.out.println("getMaNH");
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getMaNH();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetMaNH() {
        System.out.println("setMaNH");
        String maNH = "NH01";
        NguoiHoc instance = new NguoiHoc();
        instance.setMaNH(maNH);
       
        String actual = instance.getMaNH();
        
        assertEquals(actual, maNH);
    }

    @Test
    public void testGetHoTen() {
        System.out.println("getHoTen");
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getHoTen();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetHoTen() {
        System.out.println("setHoTen");
        String hoTen = "nguyen vu lan";
        NguoiHoc instance = new NguoiHoc();
        instance.setHoTen(hoTen);
       
        String actual = instance.getHoTen();
        
        assertEquals(actual, hoTen);
    }

    @Test
    public void testGetNgaySinh() {
        System.out.println("getNgaySinh");
        NguoiHoc instance = new NguoiHoc();
        Date expResult = null;
        Date result = instance.getNgaySinh();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetNgaySinh() throws ParseException {
        System.out.println("setNgaySinh");
        Date ngaySinh = df.parse(dateString);
        NguoiHoc instance = new NguoiHoc();
        instance.setNgaySinh(ngaySinh);
       
        Date actual = instance.getNgaySinh();
        
        assertEquals(actual, ngaySinh);
        
    }

    @Test
    public void testGetGioiTinh() {
        System.out.println("getGioiTinh");
        NguoiHoc instance = new NguoiHoc();
        boolean expResult = false;
        boolean result = instance.getGioiTinh();
        assertEquals(result, expResult);
     
    }

    @Test
    public void testSetGioiTinh() {
        System.out.println("setGioiTinh");
        boolean gioiTinh = true;
        NguoiHoc instance = new NguoiHoc();
        instance.setGioiTinh(gioiTinh);
        
        boolean actual = instance.getGioiTinh();
        
          assertEquals(actual, gioiTinh);
       
    }

    @Test
    public void testGetDienThoai() {
        System.out.println("getDienThoai");
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getDienThoai();
        assertEquals(result, expResult);
     
    }

    @Test
    public void testSetDienThoai() {
        System.out.println("setDienThoai");
        String dienThoai = "0378795129";
        NguoiHoc instance = new NguoiHoc();
        instance.setDienThoai(dienThoai);
       
        String actual = instance.getDienThoai();
        
        assertEquals(actual, dienThoai);
    }

    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "lannvpd03981@fpt.edu.vn";
        NguoiHoc instance = new NguoiHoc();
        instance.setEmail(email);
        
        String actual = instance.getEmail();
        
        assertEquals(actual, email);
      
    }

    @Test
    public void testGetGhiChu() {
        System.out.println("getGhiChu");
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getGhiChu();
        assertEquals(result, expResult);
       
    }

    @Test
    public void testSetGhiChu() {
        System.out.println("setGhiChu");
        String ghiChu = "ghichu";
        NguoiHoc instance = new NguoiHoc();
        instance.setGhiChu(ghiChu);
      
        String actual = instance.getGhiChu();
        
        assertEquals(actual, ghiChu);
    }

    @Test
    public void testGetMaNV() {
        System.out.println("getMaNV");
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getMaNV();
        assertEquals(result, expResult);
        
    }

    @Test
    public void testSetMaNV() {
        System.out.println("setMaNV");
        String maNV = "NV01";
        NguoiHoc instance = new NguoiHoc();
        instance.setMaNV(maNV);
     
        
        String actual = instance.getMaNV();
        
        assertEquals(actual, maNV);
    }

    @Test
    public void testGetNgayDK() {
        System.out.println("getNgayDK");
        NguoiHoc instance = new NguoiHoc();
        Date expResult = null;
        Date result = instance.getNgayDK();
        assertEquals(result, expResult);
        
    }

    @Test
    public void testSetNgayDK() throws ParseException {
        System.out.println("setNgayDK");
        Date ngayDK = df.parse(dateString);
        NguoiHoc instance = new NguoiHoc();
        instance.setNgayDK(ngayDK);
      
        
        Date actual = instance.getNgayDK();
        
        assertEquals(actual, ngayDK);
    }

    @Test
    public void testToString() {
        System.out.println("toString");
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }
    
}
