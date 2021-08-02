/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.ChuyenDe;
import edu.poly.model.KhoaHoc;
import edu.poly.model.NguoiHoc;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@PrepareForTest({JdbcHelper.class, NguoiHocDao.class})
public class NguoiHocDaoTest {
    
    NguoiHocDao nguoihocDao;
    NguoiHocDao nguoihocDaoSpy;
     String dateString = "22/01/2021";
    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
    public NguoiHocDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        nguoihocDao = new NguoiHocDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        nguoihocDaoSpy = PowerMockito.spy(new NguoiHocDao());
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void testInsertWithNullModel() {
     
        NguoiHoc model = null;
        nguoihocDao.insert(model);
      
    }
    @Test(expected = Exception.class)
    public void testInsertWithEmptyModel() {
     
        NguoiHoc model = new NguoiHoc();
        nguoihocDao.insert(model);
      
    }
    @Test
    public void testInsertWithValiModel() throws ParseException {
     
        NguoiHoc model = new NguoiHoc();
        model.setMaNH("NH01");
        model.setMaNV("NV01");
        model.setHoTen("Nguyen vu lan");
        model.setDienThoai("0378795129");
        model.setEmail("lannvpd03981@fpt.edu.vn");
        model.setGhiChu("ghi chu");
        model.setGioiTinh(true);
        model.setNgayDK(df.parse(dateString));
        model.setNgaySinh(df.parse(dateString));
        nguoihocDao.insert(model);
      
    }

    @Test(expected = Exception.class)
    public void testUpdateWithNullModel() {
       
        NguoiHoc model = null;
        nguoihocDao.update(model);
        
    }
    @Test(expected = Exception.class)
    public void testUpdateWithEmptyModel() {
       
        NguoiHoc model = new NguoiHoc();
        nguoihocDao.update(model);
        
    }
    @Test
    public void testUpdateWithValiModel() throws ParseException {
       
        NguoiHoc model = new NguoiHoc();
         model.setMaNH("NH01");
        model.setMaNV("NV01");
        model.setHoTen("Nguyen vu lan");
        model.setDienThoai("0378795129");
        model.setEmail("lannvpd03981@fpt.edu.vn");
        model.setGhiChu("ghi chu");
        model.setGioiTinh(true);
        model.setNgayDK(df.parse(dateString));
        model.setNgaySinh(df.parse(dateString));
        nguoihocDao.update(model);
        
    }

    @Test(expected = Exception.class)
    public void testDeleteWithNullId() {
       
        String id = "";
        nguoihocDao.delete(id);
      
    }
    @Test(expected = Exception.class)
    public void testDeleteWithEmptyId() {
       
        String id = null;
        nguoihocDao.delete(id);
      
    }
    @Test
    public void testDeleteWithId() {
       
        String id = "NH01";
        nguoihocDao.delete(id);
      
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
