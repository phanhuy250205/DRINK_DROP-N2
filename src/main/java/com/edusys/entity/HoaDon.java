/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
   private int maHD;
    private int maKhachHang;
    private double tongTien;
    private Timestamp thoiGian;
    private int maNhanVien;

    // Getters and setters

    public HoaDon() {
    }

    public HoaDon(int maHD, int maKhachHang, double tongTien, Timestamp thoiGian, int maNhanVien) {
        this.maHD = maHD;
        this.maKhachHang = maKhachHang;
        this.tongTien = tongTien;
        this.thoiGian = thoiGian;
        this.maNhanVien = maNhanVien;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
    
}
