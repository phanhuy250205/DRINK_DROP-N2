
package com.edusys.entity;

import java.util.Date;

public class KhoHang {
    private int ID;
    private String loaiGiaoDich;
    private String maPhieu;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private Date ngayGiaoDich;
    private String maNhanVien;
    private String ghiChu;

    public KhoHang() {
    }

    public KhoHang(int ID, String loaiGiaoDich, String maPhieu, String maSanPham, String tenSanPham, int soLuong, Date ngayGiaoDich, String maNhanVien, String ghiChu) {
        this.ID = ID;
        this.loaiGiaoDich = loaiGiaoDich;
        this.maPhieu = maPhieu;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.ngayGiaoDich = ngayGiaoDich;
        this.maNhanVien = maNhanVien;
        this.ghiChu = ghiChu;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(Date ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
 
}
