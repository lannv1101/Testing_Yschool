/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.KhoaHoc;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.MatcherAssert.assertThat;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.Assert;
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
@PrepareForTest({JdbcHelper.class, KhoaHocDao.class})
public class KhoaHocDaoNGTest {
      String dateString ="22/01/2021";
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    
        KhoaHocDao khoahocDao;
          KhoaHocDao khoahocDaoSpy;
    
    public KhoaHocDaoNGTest() {
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
        khoahocDao = new KhoaHocDao();
        
        PowerMockito.mockStatic(JdbcHelper.class);
        khoahocDaoSpy = PowerMockito.spy(new KhoaHocDao());
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(expectedExceptions = Exception.class)
    public void testInsertWithNullModel() {
        System.out.println("insert");
        KhoaHoc model = null;
        KhoaHocDao instance = new KhoaHocDao();
        instance.insert(model);
       
    }
      @Test(expectedExceptions = Exception.class)
    public void testInsertWithEmptyModel() {
        System.out.println("insert");
        KhoaHoc model = new KhoaHoc();
        KhoaHocDao instance = new KhoaHocDao();
        instance.insert(model);
       
    }
    
     @Test
    public void testInsertWithValid() throws ParseException {
        System.out.println("insert");
        KhoaHoc model = new KhoaHoc();
        
        model.setMaCD("CD01");
        model.setMaKH(2);
        model.setMaNV("NV01");
        model.setHocPhi(20000);
        model.setNgayKG(df.parse(dateString));
        model.setNgayTao(df.parse(dateString));
        model.setGhiChu("ghichu");
        model.setThoiLuong(24);
        
        KhoaHocDao instance = new KhoaHocDao();
        
        instance.insert(model);
       
    }
    

    @Test(expectedExceptions = Exception.class)
    public void testUpdateWithNullModel() {
        System.out.println("update");
        KhoaHoc model = null;
        KhoaHocDao instance = new KhoaHocDao();
        instance.update(model);
       
    }
     @Test(expectedExceptions = Exception.class)
    public void testUpdateWithEmptyModel() {
        System.out.println("update");
        KhoaHoc model = new KhoaHoc();
        KhoaHocDao instance = new KhoaHocDao();
        instance.update(model);
       
    }
    
     @Test
    public void testUpdateWithValidModel() {
        System.out.println("update");
        KhoaHoc model = new KhoaHoc();
        KhoaHocDao instance = new KhoaHocDao();
          model.setMaCD("CD01");
        model.setMaKH(2);
        model.setMaNV("NV01");
        model.setHocPhi(20000);
        model.setNgayKG(new Date());
        model.setNgayTao(new Date());
        model.setGhiChu("ghichu");
        model.setThoiLuong(24);
        instance.update(model);
       
    }

    @Test(expectedExceptions = Exception.class)
    public void testDeleteWithNull() {
        System.out.println("delete");
        Integer MaKH = null;
        KhoaHocDao instance = new KhoaHocDao();
        instance.delete(MaKH);
      
    }
    @Test
    public void testDeleteWithValid() {
        System.out.println("delete");
        Integer MaKH = 1;
        KhoaHocDao instance = new KhoaHocDao();
        instance.delete(MaKH);
        
      
    }

    @Test
    public void testSelect() throws Exception {
         System.out.println("select");
        KhoaHoc expResult = new KhoaHoc();
        List<KhoaHoc> resultList = new ArrayList<>();
        resultList.add(expResult);

        PowerMockito.doReturn(resultList)
                .when(khoahocDaoSpy, "select", ArgumentMatchers.anyString());
        List<KhoaHoc> result = khoahocDaoSpy.select();
        assertThat(result, CoreMatchers.is(resultList));
    }

    @Test
    public void testFindByIdWithValidID() throws Exception {
        System.out.println("findById");
        Integer makh = 12;
        
        KhoaHoc expResult = new KhoaHoc();
        
        List<KhoaHoc> resultList = new ArrayList<>();
        
        resultList.add(expResult);
        
        PowerMockito.doReturn(resultList).when(khoahocDaoSpy,"select",
                ArgumentMatchers.anyString(),  ArgumentMatchers.any()
                );
        
        KhoaHoc result = khoahocDaoSpy.findById(makh);
        
      assertThat(result, CoreMatchers.is(expResult));
       
    }
    
     @Test
    public void testFindByIdWithNotFoundID() throws Exception {
        System.out.println("findById");
        Integer makh = -12;
        
        KhoaHoc expResult = null;
        
        List<KhoaHoc> resultList = new ArrayList<>();
        
//        resultList.add(expResult);
        
        PowerMockito.doReturn(resultList).when(khoahocDaoSpy,"select",
                ArgumentMatchers.anyString(),  ArgumentMatchers.any()
                );
        
        KhoaHoc result = khoahocDaoSpy.findById(makh);
        
       assertThat(result, CoreMatchers.is(expResult));
       
    }
    
}
