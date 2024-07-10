
package com.edusys.entity;

import java.util.Date;

public class NhapKho {
    private int maPhieuNhap;
    private String NCC;
    private String maNhanVien;
    private int soLuong;
    private Date ngayNhap;
    private float TienNhap;
    private String loaiSanPham;
    private String maSanPham;

    public NhapKho() {
    }

    public NhapKho(int maPhieuNhap, String NCC, String maNhanVien,int soLuong ,Date ngayNhap, float TienNhap, String loaiSanPham ,String maSanPham) {
        this.maPhieuNhap = maPhieuNhap;
        this.NCC = NCC;
        this.maNhanVien = maNhanVien;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.TienNhap = TienNhap;
        this.loaiSanPham = loaiSanPham;
        this.maSanPham = maSanPham;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getNCC() {
        return NCC;
    }

    public void setNCC(String NCC) {
        this.NCC = NCC;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public float getTienNhap() {
        return TienNhap;
    }

    public void setTienNhap(float TienNhap) {
        this.TienNhap = TienNhap;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }
    
}
