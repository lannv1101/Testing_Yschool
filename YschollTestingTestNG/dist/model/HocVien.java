/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.model;

/**
 *
 * @author DELL
 */
public class HocVien {
     private int maHV;
    private int maKH;
    private String maNH;
    private double diem = -1.0;

    public HocVien() {
    }

    public HocVien(int maHV, int maKH, String maNH) {
        this.maHV = maHV;
        this.maKH = maKH;
        this.maNH = maNH;
    }

    public int getMaHV() {
        return maHV;
    }

    public void setMaHV(int maHV) {
        this.maHV = maHV;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

     @Override
    public String toString() {
        return this.toString();
    }
}
