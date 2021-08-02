/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.NhanVien;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *
 * @author DELL
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcHelper.class, NhanVienDao.class})
public class NhanVienDaoTest {
    NhanVienDao nhanvienDao;
     NhanVienDao nhanvienDaoSpy;
    
    public NhanVienDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        nhanvienDao = new NhanVienDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        nhanvienDaoSpy = PowerMockito.spy(new NhanVienDao());
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void testInsertWithNullModel() {
     
        NhanVien model = null;
        nhanvienDao.insert(model);
        
    }
     @Test(expected = Exception.class)
    public void testInsertWithEmptyModel() {
     
        NhanVien model = new NhanVien();
        nhanvienDao.insert(model);
        
    }
    @Test
    public void testInsertWithValiModel() {
     
        NhanVien model = new NhanVien();
        model.setHoTen("Nguyen Vu lan");
        model.setMaNV("NV01");
        model.setMatKhau("matkhau");
        model.setVaiTro(true);
        nhanvienDao.insert(model);
        
    }

    @Test(expected = Exception.class)
    public void testUpdateWithNullModel() {
      
        NhanVien model = null;
        
        nhanvienDao.update(model);
        
    }
    
     @Test(expected = Exception.class)
    public void testUpdateWithEmptyModel() {
      
        NhanVien model = new NhanVien();
        
        nhanvienDao.update(model);
        
    }
      @Test
    public void testUpdateWithValiModel() {
      
        NhanVien model = new NhanVien();
        
        model.setHoTen("Nguyen Vu lan");
        model.setMaNV("NV01");
        model.setMatKhau("matkhau");
        model.setVaiTro(true);
        nhanvienDao.update(model);
        
    }

    @Test(expected = Exception.class)
    public void testDeleteWithNullMaNV() {
      
        String MaNV = "";
      
        nhanvienDao.delete(MaNV);
        
    }
     @Test(expected = Exception.class)
    public void testDeleteWithEmptyMaNV() {
      
        String MaNV = null;
      
        nhanvienDao.delete(MaNV);
        
    }
     @Test
    public void testDeleteWithMaNV() {
      
        String MaNV = "NV01";
      
        nhanvienDao.delete(MaNV);
        
    }

    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        NhanVien nhanVien = new NhanVien();
        List<NhanVien> expResult = new ArrayList<>();
        expResult.add(nhanVien);
        
          PowerMockito.doReturn(expResult)
                .when(nhanvienDaoSpy, "select", ArgumentMatchers.anyString());
          
          List <NhanVien> result = nhanvienDaoSpy.select();
          assertThat(result, CoreMatchers.is(expResult));
          
     
       
    }

    @Test
    public void testFindByIdWithFound() throws Exception {
        System.out.println("findById");
        String manv = "NV01";
        NhanVien expResult = new NhanVien();
       
        List<NhanVien> resultList = new ArrayList<>();
        
        resultList.add(expResult);
         PowerMockito.doReturn(resultList)
                .when(nhanvienDaoSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());
         
         NhanVien result = nhanvienDaoSpy.findById(manv);
          assertThat(result, CoreMatchers.is(expResult));
         
    }
    
       @Test
    public void testFindByIdWithNotFound() throws Exception {
        System.out.println("findById");
        String manv = "";
        NhanVien expResult = null;
       
        List<NhanVien> resultList = new ArrayList<>();
        
      
         PowerMockito.doReturn(resultList)
                .when(nhanvienDaoSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());
         
         NhanVien result = nhanvienDaoSpy.findById(manv);
          assertThat(result, CoreMatchers.is(expResult));
         
    }
    
}
