/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.ChuyenDe;
import edu.poly.model.KhoaHoc;
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
@PrepareForTest({JdbcHelper.class, ChuyenDeDao.class})
public class ChuyenDeDaoNGTest {
    
    ChuyenDeDao ChuyenDeDao;
     ChuyenDeDao ChuyenDeDaoSpy;
    
    public ChuyenDeDaoNGTest() {
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
        
        ChuyenDeDao = new ChuyenDeDao();
         PowerMockito.mockStatic(JdbcHelper.class);
        ChuyenDeDaoSpy = PowerMockito.spy(new ChuyenDeDao());
        
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(expectedExceptions = Exception.class)
    public void testInsertWithNullModel() {
        System.out.println("insert");
        ChuyenDe model = null;
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.insert(model);
       
    }
    @Test(expectedExceptions = Exception.class)
    public void testInsertWithEmptyModel() {
        System.out.println("insert");
        ChuyenDe model = new ChuyenDe();
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.insert(model);
       
    }
    
    @Test
    public void testInsertWithValidModel() {
        System.out.println("insert");
        ChuyenDe model = new ChuyenDe();
        model.setTenCD("java");
        model.setMaCD("CD01");
        model.setThoiLuong(24);
        model.setHocPhi(2400000);
        model.setHinh("hinh.jpg");
        model.setMoTa("mota");
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.insert(model);
       
    }

    @Test(expectedExceptions = Exception.class)
    public void testUpdateWithNullModel() {
        System.out.println("update");
        ChuyenDe model = null;
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.update(model);
     
    }
      @Test(expectedExceptions = Exception.class)
    public void testUpdateWithEmptyModel() {
        System.out.println("update");
        ChuyenDe model = new ChuyenDe();
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.update(model);
     
    }
      @Test
    public void testUpdateWithValidModel() {
        System.out.println("update");
        ChuyenDe model = new ChuyenDe();
        model.setTenCD("java");
        model.setMaCD("CD01");
        model.setThoiLuong(24);
        model.setHocPhi(2400000);
        model.setHinh("hinh.jpg");
        model.setMoTa("mota");
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.update(model);
     
    }

    @Test
    public void testDeleteWithValidMacd() {
        System.out.println("delete");
        String MaCD = "CD01";
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.delete(MaCD);
       
    }
    
     @Test(expectedExceptions = Exception.class)
    public void testDeleteWithNullMacd() {
        System.out.println("delete");
        String MaCD = "";
        ChuyenDeDao instance = new ChuyenDeDao();
        instance.delete(MaCD);
       
    }

    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        ChuyenDe chuyenDe = new ChuyenDe();
        List<ChuyenDe> expResult = new ArrayList<>();
        expResult.add(chuyenDe);

        PowerMockito.doReturn(expResult)
                .when(ChuyenDeDaoSpy, "select", ArgumentMatchers.anyString());
        List<ChuyenDe> result = ChuyenDeDaoSpy.select();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testFindByIdWithValidMacd() throws Exception {
        System.out.println("findById");
        String macd = "CD01";
        
        ChuyenDe expResult = new ChuyenDe();
        List<ChuyenDe> resultList = new ArrayList<>();
        resultList.add(expResult);

        PowerMockito.doReturn(resultList)
                .when(ChuyenDeDaoSpy, "select", ArgumentMatchers.anyString(),ArgumentMatchers.any());
      ChuyenDe result = ChuyenDeDaoSpy.findById(macd);
        assertThat(result, CoreMatchers.is(expResult));
       
    }
    
       @Test
    public void testFindByIdWithNotFoundMacd() throws Exception {
        System.out.println("findById");
        String macd = "";
        
        ChuyenDe expResult = null;
        List<ChuyenDe> resultList = new ArrayList<>();
//        resultList.add(expResult);

        PowerMockito.doReturn(resultList)
                .when(ChuyenDeDaoSpy, "select", ArgumentMatchers.anyString(),ArgumentMatchers.any());
      ChuyenDe result = ChuyenDeDaoSpy.findById(macd);
        assertThat(result, CoreMatchers.is(expResult));
       
    }
    
}
