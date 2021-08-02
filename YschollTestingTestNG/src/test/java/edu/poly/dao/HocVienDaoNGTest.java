/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.HocVien;
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
 */
@PrepareForTest({JdbcHelper.class, HocVienDao.class})
public class HocVienDaoNGTest {
    HocVienDao hocvienDao;
      HocVienDao hocvienDaoSpy;
    
    public HocVienDaoNGTest() {
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
          hocvienDao = new HocVienDao();
        
        PowerMockito.mockStatic(JdbcHelper.class);
        hocvienDaoSpy = PowerMockito.spy(new HocVienDao());
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(expectedExceptions = Exception.class)
    public void testInsertWithNullModel() {
        System.out.println("insert");
        HocVien model = null;
        HocVienDao instance = new HocVienDao();
        instance.insert(model);
       
    }
    @Test(expectedExceptions = Exception.class)
    public void testInsertWithEmptyModel() {
        System.out.println("insert");
        HocVien model = new HocVien();
        HocVienDao instance = new HocVienDao();
        instance.insert(model);
       
    }
    @Test
    public void testInsertWithValidModel() {
        System.out.println("insert");
        HocVien model = new HocVien();
                
        model.setMaHV(01);
        model.setMaKH(01);
        model.setMaNH("NG01");
        model.setDiem(9);
        HocVienDao instance = new HocVienDao();
        instance.insert(model);
       
    }
    
    

    @Test(expectedExceptions = Exception.class)
    public void testUpdateWithNullModel() {
        System.out.println("update");
        HocVien model = null;
        HocVienDao instance = new HocVienDao();
        instance.update(model);
      
    }
    
     @Test(expectedExceptions = Exception.class)
    public void testUpdateWithEmptyModel() {
        System.out.println("update");
        HocVien model = new HocVien();
        HocVienDao instance = new HocVienDao();
        instance.update(model);
      
    }
    
     @Test
    public void testUpdateWithValidModel() {
        System.out.println("update");
      HocVien model = new HocVien();
      
        model.setMaHV(01);
        model.setMaKH(01);
        model.setMaNH("NG01");
        model.setDiem(9);
        HocVienDao instance = new HocVienDao();
        instance.update(model);
      
    }

    @Test(expectedExceptions = Exception.class)
    public void testDeleteWithNullMahv() {
        System.out.println("delete");
        Integer MaHV = null;
        HocVienDao instance = new HocVienDao();
        instance.delete(MaHV);
        
    }
     @Test
    public void testDeleteWithMahv() {
        System.out.println("delete");
        Integer MaHV = 12;
        HocVienDao instance = new HocVienDao();
        instance.delete(MaHV);
        
    }

    @Test
    public void testSelect() throws Exception {
         System.out.println("select");
        HocVien hocVien = new HocVien();
        List<HocVien> expResult = new ArrayList<>();

        expResult.add(hocVien);

        PowerMockito.doReturn(expResult)
                .when(hocvienDaoSpy, "select", ArgumentMatchers.anyString());

        List<HocVien> result = hocvienDaoSpy.select();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
   public void testFindByIdWithValidMahv() throws Exception {
        System.out.println("findById");
        Integer mahv = 01;
        
        
         HocVien expResult = new HocVien();
        List<HocVien> resultList = new ArrayList<>();
        resultList.add(expResult);
       

        PowerMockito.doReturn(resultList)
                .when(hocvienDaoSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());

       
       HocVien result = hocvienDaoSpy.findById(mahv);
        assertThat(result, CoreMatchers.is(expResult));
      
    }
   
    @Test
   public void testFindByIdWithNotFoundMahv() throws Exception {
        System.out.println("findById");
        Integer mahv = -22;
        
        
         HocVien expResult = null;
        List<HocVien> resultList = new ArrayList<>();
//        resultList.add(expResult);
       

        PowerMockito.doReturn(resultList)
                .when(hocvienDaoSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());

       
       HocVien result = hocvienDaoSpy.findById(mahv);
        assertThat(result, CoreMatchers.is(expResult));
      
    }
    
}
