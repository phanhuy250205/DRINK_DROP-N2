
package com.edusys.entity;

import java.util.Date;

public class NhapKho {
    private String maPhieuNhap;
    private String maNguoiNhap;
    private String maNhaCungCap;
    private String tenSanPham;
    private int soLuong;
    private Date ngayNhap;
    private float TienNhap;
    private String loaiSanPham;
    private String maSanPham;

    public NhapKho() {
    }

    public NhapKho(String maPhieuNhap, String maNguoiNhap, String maNhaCungCap, String tenSanPham, int soLuong, Date ngayNhap, float TienNhap, String loaiSanPham, String maSanPham) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNguoiNhap = maNguoiNhap;
        this.maNhaCungCap = maNhaCungCap;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.ngayNhap = ngayNhap;
        this.TienNhap = TienNhap;
        this.loaiSanPham = loaiSanPham;
        this.maSanPham = maSanPham;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaNguoiNhap() {
        return maNguoiNhap;
    }

    public void setMaNguoiNhap(String maNguoiNhap) {
        this.maNguoiNhap = maNguoiNhap;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
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
