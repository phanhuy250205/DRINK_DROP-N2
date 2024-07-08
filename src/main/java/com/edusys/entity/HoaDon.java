/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.entity;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private String maHD;
    private String khachHang;
    private String diaChiKhach;
    private String SDTKhach;
    private float tongTien;
    private Date thoiGian = new Date();

    public HoaDon() {
    }

    public HoaDon(String maHD, String khachHang, String diaChiKhach, String SDTKhach, float tongTien) {
        this.maHD = maHD;
        this.khachHang = khachHang;
        this.diaChiKhach = diaChiKhach;
        this.SDTKhach = SDTKhach;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getDiaChiKhach() {
        return diaChiKhach;
    }

    public void setDiaChiKhach(String diaChiKhach) {
        this.diaChiKhach = diaChiKhach;
    }

    public String getSDTKhach() {
        return SDTKhach;
    }

    public void setSDTKhach(String SDTKhach) {
        this.SDTKhach = SDTKhach;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }
           
    
}
