/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.dao;

import edu.poly.helper.JdbcHelper;
import edu.poly.model.KhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class KhoaHocDao {
      public void insert (KhoaHoc model){
        String sql="insert into KhoaHoc (MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV) values (?,?,?,?,?,?)";
        JdbcHelper.executeUpdate(sql, 
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV());
    }
    public void update(KhoaHoc model) {
        String sql="update KhoaHoc set MaCD=?,HocPhi=?,ThoiLuong=?,NgayKG=?,GhiChu=?,MaNV=? where MaKH=?";
        JdbcHelper.executeUpdate(sql, 
                model.getMaCD(),
                model.getHocPhi(),
                model.getThoiLuong(),
                model.getNgayKG(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getMaKH());
    }
    public void delete(Integer MaKH){
        String sql="delete from KhoaHoc where MaKH=?";
        JdbcHelper.executeUpdate(sql, MaKH);
    }
    public List<KhoaHoc> select(){
        String sql="select * from KhoaHoc";
        return select(sql);
    }
    public KhoaHoc findById(Integer makh){
        String sql="select * from KhoaHoc where MaKH=?";
        List<KhoaHoc> list=select(sql,makh);
        return list.size()>0 ? list.get(0):null;
    }
    private List<KhoaHoc> select(String sql,Object...args){
        List<KhoaHoc> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            try {
                rs=JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                    KhoaHoc model=readFromResultSet(rs);
                    list.add(model);
                }
            }
            finally{
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    } 
    private KhoaHoc readFromResultSet(ResultSet rs) throws SQLException{
        KhoaHoc model=new KhoaHoc();
        model.setMaKH(rs.getInt("MaKH"));
        model.setHocPhi(rs.getDouble("HocPhi"));
        model.setThoiLuong(rs.getInt("ThoiLuong"));
        model.setNgayKG(rs.getDate("NgayKG"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setMaNV(rs.getString("MaNV"));
        model.setNgayTao(rs.getDate("NgayTao"));
        model.setMaCD(rs.getString("MaCD"));
        return model;
    }

}
