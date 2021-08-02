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
public class KhoaHocTest {
    
    public KhoaHocTest() {
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
    public void testGetMaKH() {
      
        KhoaHoc instance = new KhoaHoc();
        int expResult = 0;
        int result = instance.getMaKH();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setMaKH method, of class KhoaHoc.
     */
    @Test
    public void testSetMaKH() {
    
        int maKH = 01;
        KhoaHoc instance = new KhoaHoc();
        instance.setMaKH(maKH);
        int expected = 01;
        assertEquals(expected, instance.getMaKH());
        
    }

    /**
     * Test of getMaCD method, of class KhoaHoc.
     */
    @Test
    public void testGetMaCD() {
      
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.getMaCD();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setMaCD method, of class KhoaHoc.
     */
    @Test
    public void testSetMaCD() {
      
        String maCD = "CD01";
        KhoaHoc instance = new KhoaHoc();
        instance.setMaCD(maCD);
        String expected = "CD01";
        assertEquals(expected, instance.getMaCD());
    }

    /**
     * Test of getHocPhi method, of class KhoaHoc.
     */
    @Test
    public void testGetHocPhi() {
      
        KhoaHoc instance = new KhoaHoc();
        double expResult = 0.0;
        double result = instance.getHocPhi();
        assertEquals(expResult, result,0.0);
       
    }

    /**
     * Test of setHocPhi method, of class KhoaHoc.
     */
    @Test
    public void testSetHocPhi() {
      
        double hocPhi = 400;
        KhoaHoc instance = new KhoaHoc();
        instance.setHocPhi(hocPhi);
       double expected = 400;
        double result =instance.getHocPhi();
        assertEquals(expected,  result,0.0 );
    }
    @Test(expected = Exception.class)
    public void testSetHocPhiWithNegative() {
        
        double hocPhi = -400;
        KhoaHoc instance = new KhoaHoc();
      instance.setHocPhi(hocPhi);
        
        
        // TODO review the generated test code and remove the default call to fail.
      
    }
          @Test(expected = Exception.class)
    public void testSetHocPhiWithLarge() {
        
        double hocPhi = 400000000000d;
       KhoaHoc instance = new KhoaHoc();
            instance.setHocPhi(hocPhi);
        
        
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of getThoiLuong method, of class KhoaHoc.
     */
    @Test
    public void testGetThoiLuong() {
      
        KhoaHoc instance = new KhoaHoc();
        int expResult = 0;
        int result = instance.getThoiLuong();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setThoiLuong method, of class KhoaHoc.
     */
    @Test
    public void testSetThoiLuong() {
      
        int thoiLuong = 24;
        KhoaHoc instance = new KhoaHoc();
        instance.setThoiLuong(thoiLuong);
         int expected = 24;
        assertEquals(expected, instance.getThoiLuong());
        
    }
      @Test(expected = Exception.class)
    public void testSetThoiLuongWithNegative() {
      
        int thoiLuong = -24;
        ChuyenDe instance = new ChuyenDe();
      instance.setThoiLuong(thoiLuong);
    }

    /**
     * Test of getNgayKG method, of class KhoaHoc.
     */
    @Test
    public void testGetNgayKG() {
      
        KhoaHoc instance = new KhoaHoc();
        Date expResult = null;
        Date result = instance.getNgayKG();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setNgayKG method, of class KhoaHoc.
     */
    @Test
    public void testSetNgayKG() {
      
        Date ngayKG = null;
        KhoaHoc instance = new KhoaHoc();
        instance.setNgayKG(ngayKG);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getGhiChu method, of class KhoaHoc.
     */
    @Test
    public void testGetGhiChu() {
       
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.getGhiChu();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setGhiChu method, of class KhoaHoc.
     */
    @Test
    public void testSetGhiChu() {
       
        String ghiChu = "ghichu";
        KhoaHoc instance = new KhoaHoc();
        instance.setGhiChu(ghiChu);
        String expected ="ghichu";
        assertEquals(expected, instance.getGhiChu());
       
    }

    /**
     * Test of getMaNV method, of class KhoaHoc.
     */
    @Test
    public void testGetMaNV() {
      
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.getMaNV();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMaNV method, of class KhoaHoc.
     */
    @Test
    public void testSetMaNV() {
      
        String maNV = "NV01";
        KhoaHoc instance = new KhoaHoc();
        instance.setMaNV(maNV);
        String expected ="NV01";
        assertEquals(expected, instance.getMaNV());
        
    }

    /**
     * Test of getNgayTao method, of class KhoaHoc.
     */
    @Test
    public void testGetNgayTao() {
     
        KhoaHoc instance = new KhoaHoc();
        Date expResult = null;
        Date result = instance.getNgayTao();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNgayTao method, of class KhoaHoc.
     */
    @Test
    public void testSetNgayTao() throws ParseException {
     String dateString ="22/01/2021";
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date ngayTao = df.parse(dateString) ;
        KhoaHoc instance = new KhoaHoc();
        instance.setNgayTao(ngayTao);
        Date epected = df.parse(dateString);
        assertEquals(epected, ngayTao);
        
        // TODO review the generated test code and remove the default call to fail.
      
    }

    /**
     * Test of toString method, of class KhoaHoc.
     */
    @Test
    public void testToString() {
       
        KhoaHoc instance = new KhoaHoc();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      
    }
}
