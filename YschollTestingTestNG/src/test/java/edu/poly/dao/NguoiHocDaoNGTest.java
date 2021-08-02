/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.NguoiHoc;
import java.util.ArrayList;
import java.util.Date;
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
@PrepareForTest({JdbcHelper.class, NguoiHocDao.class})
public class NguoiHocDaoNGTest {
     NguoiHocDao nguoihocDao;
    NguoiHocDao nguoihocDaoSpy;
    
    public NguoiHocDaoNGTest() {
        
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
            nguoihocDao = new NguoiHocDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        nguoihocDaoSpy = PowerMockito.spy(new NguoiHocDao());
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test(expectedExceptions = Exception.class)
    public void testInsertWithNullModel() {
        System.out.println("insert");
        NguoiHoc model = null;
        NguoiHocDao instance = new NguoiHocDao();
        instance.insert(model);
       
    }
    
     @Test(expectedExceptions = Exception.class)
    public void testInsertWithEmptyModel() {
        System.out.println("insert");
        NguoiHoc model = new NguoiHoc();
        NguoiHocDao instance = new NguoiHocDao();
        instance.insert(model);
       
    }
     @Test
    public void testInsertWithValidModel() {
        System.out.println("insert");
      NguoiHoc model = new NguoiHoc();
      
       model.setMaNH("NH01");
        model.setMaNV("NV01");
        model.setHoTen("Nguyen vu lan");
        model.setDienThoai("0378795129");
        model.setEmail("lannvpd03981@fpt.edu.vn");
        model.setGhiChu("ghi chu");
        model.setGioiTinh(true);
        model.setNgayDK(new Date());
        model.setNgaySinh(new Date());
        NguoiHocDao instance = new NguoiHocDao();
        instance.insert(model);
       
    }



    @Test(expectedExceptions = Exception.class)
    public void testUpdateWithNullModel() {
        System.out.println("update");
        NguoiHoc model = null;
        NguoiHocDao instance = new NguoiHocDao();
        instance.update(model);
      
    }
    
    @Test(expectedExceptions = Exception.class)
    public void testUpdateWithEmptyModel() {
        System.out.println("update");
        NguoiHoc model = new NguoiHoc();
        NguoiHocDao instance = new NguoiHocDao();
        instance.update(model);
      
    }
    
    @Test
    public void testUpdateWithValidModel() {
        System.out.println("update");
        NguoiHoc model = new NguoiHoc();
        
         model.setMaNH("NH01");
        model.setMaNV("NV01");
        model.setHoTen("Nguyen vu lan");
        model.setDienThoai("0378795129");
        model.setEmail("lannvpd03981@fpt.edu.vn");
        model.setGhiChu("ghi chu");
        model.setGioiTinh(true);
        model.setNgayDK(new Date());
        model.setNgaySinh(new Date());
        NguoiHocDao instance = new NguoiHocDao();
        instance.update(model);
      
    }

    @Test(expectedExceptions = Exception.class)
    public void testDeleteWithNullId() {
        System.out.println("delete");
        String id = "";
        NguoiHocDao instance = new NguoiHocDao();
        instance.delete(id);
      
    }
     @Test
    public void testDeleteWithValidId() {
        System.out.println("delete");
        String id = "01";
        NguoiHocDao instance = new NguoiHocDao();
        instance.delete(id);
      
    }

    @Test
    public void testSelect() throws Exception {
         System.out.println("select");
        NguoiHoc nguoiHoc = new NguoiHoc();
        List<NguoiHoc> expResult = new ArrayList<>();

        expResult.add(nguoiHoc);

        PowerMockito.doReturn(expResult)
                .when(nguoihocDaoSpy, "select", ArgumentMatchers.anyString());

        List<NguoiHoc> result = nguoihocDaoSpy.select();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testSelectByKeyword() throws Exception {
        System.out.println("selectByKeyword");
        String keyword = "aaaa";
          NguoiHoc nguoiHoc = new NguoiHoc();
        List<NguoiHoc> expResult = new ArrayList<>();

        expResult.add(nguoiHoc);

        PowerMockito.doReturn(expResult)
                .when(nguoihocDaoSpy, "select", ArgumentMatchers.anyString(),ArgumentMatchers.any());

        List<NguoiHoc> result = nguoihocDaoSpy.selectByKeyword(keyword);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testSelectByCourseWithFound() throws Exception  {
        System.out.println("selectByCourse");
        Integer makh = 01;
        
        NguoiHoc nguoiHoc = new NguoiHoc();
        List<NguoiHoc> expResult = new ArrayList<>();
        
        expResult.add(nguoiHoc);

        

        PowerMockito.doReturn(expResult)
                .when(nguoihocDaoSpy, "select", ArgumentMatchers.anyString(),ArgumentMatchers.any());
        List<NguoiHoc> result =  nguoihocDaoSpy.selectByCourse(makh);
        assertThat(result, CoreMatchers.is(expResult));
    }
     @Test
    public void testSelectByCourseWithNotFound() throws Exception  {
        System.out.println("selectByCourse");
        Integer makh = null;
        
        NguoiHoc nguoiHoc =null;
        List<NguoiHoc> expResult = new ArrayList<>();
        
        expResult.add(nguoiHoc);

        

        PowerMockito.doReturn(expResult)
                .when(nguoihocDaoSpy, "select", ArgumentMatchers.anyString(),ArgumentMatchers.any());
        List<NguoiHoc> result =  nguoihocDaoSpy.selectByCourse(makh);
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        System.out.println("findById");
        String manh = "";
        NguoiHoc expResult = null;
        List<NguoiHoc> resultList = new ArrayList<>();

       

        PowerMockito.doReturn(resultList)
                .when(nguoihocDaoSpy, "select", ArgumentMatchers.anyString(),ArgumentMatchers.any());

       NguoiHoc result = nguoihocDaoSpy.findById(manh);
        assertThat(result, CoreMatchers.is(expResult));
    }
    
     @Test
    public void testFindByIdWithFound() throws Exception {
        System.out.println("findById");
        String manh = "NH01";
        NguoiHoc expResult = new NguoiHoc();
        
        List<NguoiHoc> resultList = new ArrayList<>();
        resultList.add(expResult);
       

        PowerMockito.doReturn(resultList)
                .when(nguoihocDaoSpy, "select", ArgumentMatchers.anyString(),ArgumentMatchers.any());

       NguoiHoc result = nguoihocDaoSpy.findById(manh);
        assertThat(result, CoreMatchers.is(expResult));
    }
    
    
}
