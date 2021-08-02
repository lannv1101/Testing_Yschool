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
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;


/**
 *
 * @author DELL
 */

@PrepareForTest({JdbcHelper.class, ChuyenDeDao.class})
public class ChuyenDeDaoTest {
      ChuyenDeDao chuyenDeDAO;
    ChuyenDeDao chuyenDeDaoSpy;
    
    public ChuyenDeDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
          chuyenDeDAO = new ChuyenDeDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        chuyenDeDaoSpy = PowerMockito.spy(new ChuyenDeDao());
    }
    
    @AfterEach
    public void tearDown() {
    }

   @Test
    public void testInsertWithNullModel() {

        ChuyenDe model = null;

        chuyenDeDAO.insert(model);
//        Exception exception = assertThrows(Exception.class, ()->   chuyenDeDAO.insert(model));

    }

   @Test
    public void testInsertWithEmptyModel() {

        ChuyenDe model = new ChuyenDe();

        chuyenDeDAO.insert(model);
         Exception exception = assertThrows(Exception.class, ()->   chuyenDeDAO.insert(model));

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

   @Test
    public void testUpdateWithNullModel() {

        ChuyenDe model = null;
        chuyenDeDAO.update(model);
          Exception exception = assertThrows(Exception.class, ()->   chuyenDeDAO.update(model));

    }

    @Test
    public void testUpdateWithEmptyModel() {

        ChuyenDe model = new ChuyenDe();
        chuyenDeDAO.update(model);
          Exception exception = assertThrows(Exception.class, ()->   chuyenDeDAO.update(model));

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

   @Test
    public void testDeleteWithEmptyModel() {

        String MaCD = null;
        chuyenDeDAO.delete(MaCD);
          Exception exception = assertThrows(Exception.class, ()->   chuyenDeDAO.delete(MaCD));

    }

    @Test
    public void testDeleteWithNullModel() {

        String MaCD = "";
        chuyenDeDAO.delete(MaCD);
                  Exception exception = assertThrows(Exception.class, ()->   chuyenDeDAO.delete(MaCD));


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
