/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import com.mockrunner.mock.jdbc.MockConnection;
import com.mockrunner.mock.jdbc.MockResultSet;
import com.mockrunner.mock.jdbc.MockStatement;
import edu.poly.helper.JdbcHelper;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 *
 * @author DELL
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JdbcHelper.class, ThongKeDao.class})
public class ThongKeDaoTest {

    ThongKeDao thongkeDaospy;

    @Mock
    MockConnection connection;

    @Mock
    MockStatement statement;

    @Spy
    @InjectMocks
    MockResultSet rs = new MockResultSet("myMock");

    public ThongKeDaoTest() {
    }

    @Before
    public void setUp() {

        PowerMockito.mockStatic(JdbcHelper.class);
        thongkeDaospy = PowerMockito.spy(new ThongKeDao());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetNguoiHoc() throws Exception {
        System.out.println("getNguoiHoc");

        rs = initMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

        List result = thongkeDaospy.getNguoiHoc();

        assertEquals(1, result.size());

    }
     @Test
    public void testGetNguoiHocWithMultipleDate() throws Exception {
        System.out.println("getNguoiHoc");

        rs = initMultipleDateMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

        List result = thongkeDaospy.getNguoiHoc();

        assertEquals(3, result.size());

    }

    @Test
    public void testGetBangDiem() throws Exception {
        System.out.println("getBangDiem");
          rs = initBangDiemDiemMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

        List result = thongkeDaospy.getBangDiem(24);

        assertEquals(1, result.size());
      
        

    }
    @Test
    public void testGetBangDiemWithMultipleData() throws Exception {
        System.out.println("getBangDiem");
          rs = initBangDiemDiemMultipleDataMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

        List result = thongkeDaospy.getBangDiem(24);

        assertEquals(3, result.size());
      
        

    }
    

    @Test
    public void testGetDiemTheoChuyenDe() throws Exception {
        System.out.println("getDiemTheoChuyenDe");
         rs = initDiemMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

        List result = thongkeDaospy.getDiemTheoChuyenDe();

        assertEquals(1, result.size());

    }
     @Test
    public void testGetDiemTheoChuyenDeWithMultipleDate() throws Exception {
        System.out.println("getDiemTheoChuyenDe");
         rs = initMultipleDataDiemMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

        List result = thongkeDaospy.getDiemTheoChuyenDe();

        assertEquals(3, result.size());

    }

    @Test
    public void testGetDoanhThu() throws Exception {
        System.out.println("getDoanhThu");
        rs = initDoanhThuMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

       
        List result = thongkeDaospy.getDoanhThu(2019);

        assertEquals(1, result.size());

    }
       @Test
    public void testGetDoanhThuWithMultipleData() throws Exception {
        System.out.println("getDoanhThu");
        rs = initDoanhThuWithMultipleDataMockResultSet();

        PowerMockito.when(JdbcHelper.executeQuery(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(rs);

        PowerMockito.when(rs.getStatement()).thenReturn(statement);

        PowerMockito.when(statement.getConnection()).thenReturn(connection);

       
        List result = thongkeDaospy.getDoanhThu(2019);///sai cho nay

        assertEquals(3, result.size());

    }

    private MockResultSet initMockResultSet() throws Exception {
        rs.addColumn("Nam", new Integer[]{1});
        rs.addColumn("SoLuong", new Integer[]{1});
        rs.addColumn("Dautien", new java.util.Date[]{
            new java.sql.Date(new java.util.Date().getTime()),
           
                
        });

        rs.addColumn("Cuoicung", new java.util.Date[]{
            new java.sql.Date(new java.util.Date().getTime()),
             
        });
        rs.beforeFirst();
        return rs;

    }

    private MockResultSet initMultipleDateMockResultSet() throws Exception {
  rs.addColumn("Nam", new Integer[]{1,2,3});
        rs.addColumn("SoLuong", new Integer[]{1,2,3});
        rs.addColumn("Dautien", new java.util.Date[]{
            new java.sql.Date(new java.util.Date().getTime()),
             new java.sql.Date(new java.util.Date().getTime()),
              new java.sql.Date(new java.util.Date().getTime())
                
        });

        rs.addColumn("Cuoicung", new java.util.Date[]{
            new java.sql.Date(new java.util.Date().getTime()),
             new java.sql.Date(new java.util.Date().getTime()),
              new java.sql.Date(new java.util.Date().getTime()),
        });
        rs.beforeFirst();
        return rs;    }

    private MockResultSet initDiemMockResultSet() throws Exception {
        
        rs.addColumn("ChuyenDe", new String[]{"Java"});
        rs.addColumn("SoHV", new Integer[]{1});
       rs.addColumn("ThapNhat", new Double []{});
        rs.addColumn("CaoNhat", new Double[]{});

     rs.addColumn("TrungBinh", new Double[]{});
     rs.beforeFirst();
     return rs;

        
    }

    private MockResultSet initMultipleDataDiemMockResultSet() throws Exception {
 rs.addColumn("ChuyenDe", new String[]{"Java","SQL","C++"});
        rs.addColumn("SoHV", new Integer[]{1,5,7});
       rs.addColumn("ThapNhat", new Double []{});
        rs.addColumn("CaoNhat", new Double[]{});

     rs.addColumn("TrungBinh", new Double[]{});
     rs.beforeFirst();
     return rs;    }

    private MockResultSet initDoanhThuMockResultSet() throws Exception {
         rs.addColumn("ChuyenDe", new String[]{"Java"});
           rs.addColumn("SoKH", new Integer[]{1});
           rs.addColumn("SoHV", new Integer[]{1});
             rs.addColumn("DoanhThu", new Double[]{});
             rs.addColumn("ThapNhat", new Double[]{});
             rs.addColumn("CaoNhat", new Double[]{});
             rs.addColumn("TrungBinh", new Double[]{});
             rs.beforeFirst();
             return rs;
             
    }
      private MockResultSet initDoanhThuWithMultipleDataMockResultSet() throws Exception {
         rs.addColumn("ChuyenDe", new String[]{"Java","SQL","Python"});
           rs.addColumn("SoKH", new Integer[]{1,2,3});
           rs.addColumn("SoHV", new Integer[]{1,2,3});
             rs.addColumn("DoanhThu", new Double[]{});
             rs.addColumn("ThapNhat", new Double[]{});
             rs.addColumn("CaoNhat", new Double[]{});
             rs.addColumn("TrungBinh", new Double[]{});
             rs.beforeFirst();
             return rs;
             
    }

    private MockResultSet initBangDiemDiemMockResultSet() throws Exception {
        rs.addColumn("MaNH", new String[]{"NG01"});
         rs.addColumn("HoTen", new String[]{"Nguyen Vu Lan"});
         rs.addColumn("Diem", new Double[]{});
         rs.beforeFirst();
         return rs;

    }
     private MockResultSet initBangDiemDiemMultipleDataMockResultSet() throws Exception {
        rs.addColumn("MaNH", new String[]{"NG01","NG02","NG03"});
         rs.addColumn("HoTen", new String[]{"Nguyen Vu Lan","Nguyen Vu Lan","Nguyen Vu Lan"});
         rs.addColumn("Diem", new Double[]{});
         rs.beforeFirst();
         return rs;

    }

}
