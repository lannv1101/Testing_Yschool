/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import com.sun.jdi.connect.Connector;
import static org.hamcrest.MatcherAssert.assertThat;
import edu.poly.helper.JdbcHelper;
import edu.poly.model.KhoaHoc;
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

/**
 *
 * @author DELL
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcHelper.class, KhoaHocDao.class})
public class KhoaHocDaoTest {

    KhoaHocDao khoahocDAO;
    KhoaHocDao khoahocDAOSpy;

    String dateString = "22/01/2021";
    DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

    public KhoaHocDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        khoahocDAO = new KhoaHocDao();
        PowerMockito.mockStatic(JdbcHelper.class);
        khoahocDAOSpy = PowerMockito.spy(new KhoaHocDao());
    }

    @After
    public void tearDown() {
    }

    @Test(expected = Exception.class)
    public void testInsertWithNullModel() {

        KhoaHoc model = null;
        khoahocDAO.insert(model);
    }

    @Test(expected = Exception.class)
    public void testInsertWithEmptyModel() {

        KhoaHoc model = new KhoaHoc();
        khoahocDAO.insert(model);
    }

    @Test
    public void testInsertWithValiModel() throws ParseException {

        KhoaHoc model = new KhoaHoc();

        model.setMaCD("CD01");
        model.setMaKH(0);
        model.setGhiChu("ghichu");
        model.setHocPhi(20000);
        model.setMaNV("NV01");
        model.setNgayKG(df.parse(dateString));
        model.setNgayTao(df.parse(dateString));
        model.setThoiLuong(24);
        khoahocDAO.insert(model);
    }

    @Test(expected = Exception.class)
    public void testUpdateWithNullModel() {

        KhoaHoc model = null;
        khoahocDAO.update(model);
    }

    @Test(expected = Exception.class)
    public void testUpdateWithEmptyModel() {

        KhoaHoc model = new KhoaHoc();
        khoahocDAO.update(model);
    }

    @Test
    public void testUpdateWithValiModel() throws ParseException {

        KhoaHoc model = new KhoaHoc();

        model.setMaCD("CD01");
        model.setMaKH(0);
        model.setGhiChu("ghichu");
        model.setHocPhi(20000);
        model.setMaNV("NV01");
        model.setNgayKG(df.parse(dateString));
        model.setNgayTao(df.parse(dateString));
        model.setThoiLuong(24);
        khoahocDAO.update(model);
    }

    @Test(expected = Exception.class)
    public void testDeleteWithNullMaKH() {

        Integer MaKH = 0;
        khoahocDAO.delete(MaKH);

    }

    @Test(expected = Exception.class)
    public void testDeleteWithEmptyMaKH() {

        Integer MaKH = null;
        khoahocDAO.delete(MaKH);

    }

    @Test
    public void testDeleteWithValiMaNH() {

        Integer MaKH = 01;
        khoahocDAO.delete(MaKH);

    }

    @Test
    public void testSelect() throws Exception {
        System.out.println("select");
        KhoaHoc khoaHoc = new KhoaHoc();
        List<KhoaHoc> expResult = new ArrayList<>();
        expResult.add(khoaHoc);

        PowerMockito.doReturn(expResult)
                .when(khoahocDAOSpy, "select", ArgumentMatchers.anyString());
        List<KhoaHoc> result = khoahocDAOSpy.select();
        assertThat(result, CoreMatchers.is(expResult));
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
        System.out.println("findById");
        Integer makh = null;
        KhoaHoc expResult = null;
        List<KhoaHoc> resultList = new ArrayList<>();

        PowerMockito.doReturn(resultList)
                .when(khoahocDAOSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        KhoaHoc result = khoahocDAOSpy.findById(makh);
        assertThat(result, CoreMatchers.is(expResult));
    }
    
     @Test
    public void testFindByIdFound() throws Exception {
        System.out.println("findById");
        Integer makh = 01;
        KhoaHoc expResult = new KhoaHoc();
        List<KhoaHoc> resultList = new ArrayList<>();

        resultList.add(expResult);
        PowerMockito.doReturn(resultList)
                .when(khoahocDAOSpy, "select", ArgumentMatchers.anyString(), ArgumentMatchers.any());
        KhoaHoc result = khoahocDAOSpy.findById(makh);
        assertThat(result, CoreMatchers.is(expResult));
    }

}
