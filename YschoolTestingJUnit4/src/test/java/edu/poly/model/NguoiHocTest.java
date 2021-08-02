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
public class NguoiHocTest {
    
    public NguoiHocTest() {
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
    public void testGetMaNH() {
       
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getMaNH();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setMaNH method, of class NguoiHoc.
     */
    @Test
    public void testSetMaNH() {
       
        String maNH = "NG01";
        NguoiHoc instance = new NguoiHoc();
        instance.setMaNH(maNH);
        
        String expected = "NG01";
        assertEquals(expected, instance.getMaNH());
       
    }

    /**
     * Test of getHoTen method, of class NguoiHoc.
     */
    @Test
    public void testGetHoTen() {
       
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getHoTen();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setHoTen method, of class NguoiHoc.
     */
    @Test
    public void testSetHoTen() {
      
        String hoTen = "Nguyễn Vũ Lân";
        NguoiHoc instance = new NguoiHoc();
        instance.setHoTen(hoTen);
        String expected = "Nguyễn Vũ Lân";
        assertEquals(expected, instance.getHoTen());
       
    }

    /**
     * Test of getNgaySinh method, of class NguoiHoc.
     */
    @Test
    public void testGetNgaySinh() {
       
        NguoiHoc instance = new NguoiHoc();
        Date expResult = null;
        Date result = instance.getNgaySinh();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNgaySinh method, of class NguoiHoc.
     */
    @Test
    public void testSetNgaySinh() throws ParseException {
        String dateString ="22/01/2021";
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date ngaySinh = df.parse(dateString);
        NguoiHoc instance = new NguoiHoc();
        instance.setNgaySinh(ngaySinh);
        
        Date expected = df.parse(dateString);
        
        assertEquals(expected, instance.getNgaySinh());

    }

    /**
     * Test of getGioiTinh method, of class NguoiHoc.
     */
    @Test
    public void testGetGioiTinh() {
      
        NguoiHoc instance = new NguoiHoc();
        boolean expResult = false;
        boolean result = instance.getGioiTinh();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setGioiTinh method, of class NguoiHoc.
     */
    @Test
    public void testSetGioiTinh() {
       
        boolean gioiTinh = false;
        NguoiHoc instance = new NguoiHoc();
        instance.setGioiTinh(gioiTinh);
        
        boolean expected = false;
        
        assertEquals(expected, instance.getGioiTinh());
    }

    /**
     * Test of getDienThoai method, of class NguoiHoc.
     */
    @Test
    public void testGetDienThoai() {
       
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getDienThoai();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setDienThoai method, of class NguoiHoc.
     */
    @Test
    public void testSetDienThoai() {
      
        String dienThoai = "0378795129";
        NguoiHoc instance = new NguoiHoc();
        instance.setDienThoai(dienThoai);
      
        String expected = "0378795129";
        
        assertEquals(expected, instance.getDienThoai());
      
    }

    /**
     * Test of getEmail method, of class NguoiHoc.
     */
    @Test
    public void testGetEmail() {
      
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of setEmail method, of class NguoiHoc.
     */
    @Test
    public void testSetEmail() {
     
        String email = "lannvpd03981@fpt.edu.vn";
        NguoiHoc instance = new NguoiHoc();
        instance.setEmail(email);
        String expected = "lannvpd03981@fpt.edu.vn";
        
        assertEquals(expected, instance.getEmail());
    }

    /**
     * Test of getGhiChu method, of class NguoiHoc.
     */
    @Test
    public void testGetGhiChu() {
      
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getGhiChu();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of setGhiChu method, of class NguoiHoc.
     */
    @Test
    public void testSetGhiChu() {
   
        String ghiChu = "ghichu";
        NguoiHoc instance = new NguoiHoc();
        instance.setGhiChu(ghiChu);
        String expected = "ghichu";
        
        assertEquals(expected, instance.getGhiChu());
    }

    /**
     * Test of getMaNV method, of class NguoiHoc.
     */
    @Test
    public void testGetMaNV() {
       
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.getMaNV();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setMaNV method, of class NguoiHoc.
     */
    @Test
    public void testSetMaNV() {
       
        String maNV = "NV01";
        NguoiHoc instance = new NguoiHoc();
        instance.setMaNV(maNV);
        
         String expected = "NV01";
        
        assertEquals(expected, instance.getMaNV());
       
    }

    /**
     * Test of getNgayDK method, of class NguoiHoc.
     */
    @Test
    public void testGetNgayDK() {
     
        NguoiHoc instance = new NguoiHoc();
        Date expResult = null;
        Date result = instance.getNgayDK();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setNgayDK method, of class NguoiHoc.
     */
    @Test
    public void testSetNgayDK() throws ParseException {
       String dateString ="22/01/2021";
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date ngayDK = df.parse(dateString);
        NguoiHoc instance = new NguoiHoc();
        instance.setNgayDK(ngayDK);
        
        Date expected = df.parse(dateString);
        
        assertEquals(expected, instance.getNgayDK());
        
      
    }

    /**
     * Test of toString method, of class NguoiHoc.
     */
    @Test
    public void testToString() {
      
        NguoiHoc instance = new NguoiHoc();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
       
    }
    
}
