/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.ChuyenDe;

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
@PrepareForTest({JdbcHelper.class, ChuyenDeDao.class})
public class ChuyenDeDaoTest {
       ChuyenDeDao chuyenDeDAO;
    ChuyenDeDao chuyenDeDaoSpy;

    
    
    public ChuyenDeDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         chuyenDeDAO = new ChuyenDeDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        chuyenDeDaoSpy = PowerMockito.spy(new ChuyenDeDao());

    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void testInsertWithNullModel() {

        ChuyenDe model = null;

        chuyenDeDAO.insert(model);

    }

    @Test(expected = Exception.class)
    public void testInsertWithEmptyModel() {

        ChuyenDe model = new ChuyenDe();

        chuyenDeDAO.insert(model);

    }

    @Test
    public void testInsertWithValiModel() {

        ChuyenDe model = new ChuyenDe();
        model.setHinh("hinh1.jpg");
        model.setHocPhi(10000);
        model.setMaCD("CD01");
        model.setMoTa("Test");
        model.setTenCD("Chuyen de 01");
        model.setThoiLuong(24);
        chuyenDeDAO.insert(model);

    }

    @Test(expected = Exception.class)
    public void testUpdateWithNullModel() {

        ChuyenDe model = null;
        chuyenDeDAO.update(model);

    }

    @Test(expected = Exception.class)
    public void testUpdateWithEmptyModel() {

        ChuyenDe model = new ChuyenDe();
        chuyenDeDAO.update(model);

    }

    @Test
    public void testUpdateWithValiModel() {

        ChuyenDe model = new ChuyenDe();
        model.setHinh("hinh1.jpg");
        model.setHocPhi(10000);
        model.setMaCD("CD01");
        model.setMoTa("Test");
        model.setTenCD("Chuyen de 01");
        model.setThoiLuong(24);
        chuyenDeDAO.update(model);

    }

    @Test
    public void testDeleteWithValiModel() {

        String MaCD = "CD01";
        chuyenDeDAO.delete(MaCD);

    }

    @Test(expected = Exception.class)
    public void testDeleteWithEmptyModel() {

        String MaCD = null;
        chuyenDeDAO.delete(MaCD);

    }

    @Test(expected = Exception.class)
    public void testDeleteWithNullModel() {

        String MaCD = "";
        chuyenDeDAO.delete(MaCD);

    }

    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        ChuyenDe chuyenDe = new ChuyenDe();
        List<ChuyenDe> expResult = new ArrayList<>();

        expResult.add(chuyenDe);

        PowerMockito.doReturn(expResult)
                .when(chuyenDeDaoSpy, "select", ArgumentMatchers.anyString());

        List<ChuyenDe> result = chuyenDeDaoSpy.select();
        assertThat(result, CoreMatchers.is(expResult));
        /**/
    }

    @Test
    public void testFindByIdWithNotFound() throws Exception {
        System.out.println("findById");
        String macd = "";

        ChuyenDe expResult = null;
        List<ChuyenDe> resultList = new ArrayList<>();

        PowerMockito.doReturn(resultList)
                .when(chuyenDeDaoSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());

        ChuyenDe result = chuyenDeDaoSpy.findById(macd);
        assertThat(result, CoreMatchers.is(expResult));
    }
    
     @Test
    public void testFindByIdWithFound() throws Exception {
        System.out.println("findById");
        String macd = "CD01";

        ChuyenDe expResult = new ChuyenDe();
        List<ChuyenDe> resultList = new ArrayList<>();
        resultList.add(expResult);

        PowerMockito.doReturn(resultList)
                .when(chuyenDeDaoSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());

        ChuyenDe result = chuyenDeDaoSpy.findById(macd);
        assertThat(result, CoreMatchers.is(expResult));
    }

    
}
