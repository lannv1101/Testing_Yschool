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
import static org.hamcrest.MatcherAssert.assertThat;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import static org.testng.Assert.*;
import org.testng.IObjectFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

/**
 *
 * @author DELL
 */@PrepareForTest({JdbcHelper.class, NhanVienDao.class})
public class NhanVienDaoNGTest {
      NhanVienDao nhanvienDao;
     NhanVienDao nhanvienDaoSpy;
    public NhanVienDaoNGTest() {
    }
     @ObjectFactory
    public IObjectFactory getObjectFactory(){
        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
          nhanvienDao = new NhanVienDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        nhanvienDaoSpy = PowerMockito.spy(new NhanVienDao());
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(expectedExceptions = Exception.class)
    public void testInsertWithNullModel() {
        System.out.println("insert");
        NhanVien model = null;
        NhanVienDao instance = new NhanVienDao();
        instance.insert(model);
       
    }
     @Test(expectedExceptions = Exception.class)
    public void testInsertWithEmptyModel() {
        System.out.println("insert");
        NhanVien model = new NhanVien();
        NhanVienDao instance = new NhanVienDao();
        instance.insert(model);
       
    }
     @Test
    public void testInsertWithValidModel() {
       System.out.println("insert");
        NhanVien model = new NhanVien();
            model.setHoTen("Nguyen Vu lan");
        model.setMaNV("NV01");
        model.setMatKhau("matkhau");
        model.setVaiTro(true);
        NhanVienDao instance = new NhanVienDao();
        instance.insert(model);
       
    }

    @Test(expectedExceptions = Exception.class)
    public void testUpdateWithNullModel() {
        System.out.println("update");
        NhanVien model = null;
        NhanVienDao instance = new NhanVienDao();
        instance.update(model);
    
    }
    
     @Test(expectedExceptions = Exception.class)
    public void testUpdateWithEmptyModel() {
        System.out.println("update");
        NhanVien model = new NhanVien();
        NhanVienDao instance = new NhanVienDao();
        instance.update(model);
    
    }
    
     @Test
    public void testUpdateWithValidModel() {
        System.out.println("update");
          NhanVien model = new NhanVien();
              model.setHoTen("Nguyen Vu lan");
        model.setMaNV("NV01");
        model.setMatKhau("matkhau");
        model.setVaiTro(true);
        NhanVienDao instance = new NhanVienDao();
        instance.update(model);
    
    }

    @Test(expectedExceptions = Exception.class)
    public void testDeleteWithNullMahv() {
        System.out.println("delete");
        String MaNV = "";
        NhanVienDao instance = new NhanVienDao();
        instance.delete(MaNV);
     
    }
    @Test
    public void testDeleteWithValidMahv() {
        System.out.println("delete");
        String MaNV = "HV12";
        NhanVienDao instance = new NhanVienDao();
        instance.delete(MaNV);
     
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
