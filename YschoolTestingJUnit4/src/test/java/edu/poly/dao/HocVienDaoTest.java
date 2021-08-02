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
 */@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcHelper.class, HocVienDao.class})
public class HocVienDaoTest {
    
    HocVienDao hocvienDAO;
    HocVienDao hocvienDAOSpy;
    
    public HocVienDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hocvienDAO = new HocVienDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        hocvienDAOSpy = PowerMockito.spy(new HocVienDao());
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void testInsertWithNullModel() {
        
        HocVien model = null;
       hocvienDAO.insert(model);
    }
    
      @Test(expected = Exception.class)
    public void testInsertWithEmptyModel() {
        
        HocVien model = new HocVien();
       hocvienDAO.insert(model);
    }
    
      @Test
    public void testInsertWithValiModel() {
        
        HocVien model = new HocVien();
        
        model.setMaHV(01);
        model.setMaKH(01);
        model.setMaNH("NG01");
        model.setDiem(9);
       hocvienDAO.insert(model);
    }

    @Test(expected = Exception.class)
    public void testUpdateWithNullModel() {
      
        HocVien model = null;
        
        hocvienDAO.update(model);
        
    }
    
      @Test(expected = Exception.class)
    public void testUpdateWithEmptyModel() {
      
        HocVien model = new HocVien();
        
        hocvienDAO.update(model);
        
    }
    
      @Test
    public void testUpdateWithValiModel() {
      
        HocVien model = new HocVien();
        model.setMaHV(01);
        model.setMaKH(01);
        model.setMaNH("NG01");
        model.setDiem(9);
        hocvienDAO.update(model);
        
    }

    @Test(expected = Exception.class)
    public void testDeleteWithNullMaHV() {
        
        Integer MaHV =0;
        
        hocvienDAO.delete(MaHV);
        
    }
    
      @Test(expected = Exception.class)
    public void testDeleteWithEmptyMaHV() {
        
        Integer MaHV = null;
        
        hocvienDAO.delete(MaHV);
        
    }
      @Test
    public void testDeleteWithValiMaHV() {
        
        Integer MaHV = 01;
        
        hocvienDAO.delete(MaHV);
        
    }

    @Test
    public void testSelect() throws Exception {
         System.out.println("select");
        HocVien hocVien = new HocVien();
        List<HocVien> expResult = new ArrayList<>();

        expResult.add(hocVien);

        PowerMockito.doReturn(expResult)
                .when(hocvienDAOSpy, "select", ArgumentMatchers.anyString());

        List<HocVien> result = hocvienDAOSpy.select();
        assertThat(result, CoreMatchers.is(expResult));
    }

     @Test
    public void testFindByIdWithNotFound() throws Exception {
    System.out.println("findById");
    Integer mahv = 0;
    
    HocVien expResult = null;
    List<HocVien> resultList = new ArrayList<>();
    
    PowerMockito.doReturn(resultList)
    .when(hocvienDAOSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());
    
    HocVien result = hocvienDAOSpy.findById(mahv);
    assertThat(result, CoreMatchers.is(expResult));
    
    }
    
    @Test
    public void testFindByIdWithFound() throws Exception {
        System.out.println("findById");
        Integer mahv = 01;
        
        
         HocVien expResult = new HocVien();
        List<HocVien> resultList = new ArrayList<>();
        resultList.add(expResult);
       

        PowerMockito.doReturn(resultList)
                .when(hocvienDAOSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());

       
       HocVien result = hocvienDAOSpy.findById(mahv);
        assertThat(result, CoreMatchers.is(expResult));
      
    }
    
}
