
package com.edusys.entity;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private float giaNhap;
    private float giaBan;
    private String loaiSanPham;
    private String moTa;
    private int tongSanPham;
    private String Hinh;

    public SanPham() {
    }

    public SanPham(String maSanPham, String tenSanPham, int soLuong, float giaNhap, float giaBan, String loaiSanPham, String moTa, int tongSanPham, String Hinh) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.loaiSanPham = loaiSanPham;
        this.moTa = moTa;
        this.tongSanPham = tongSanPham;
        this.Hinh = Hinh;
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

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTongSanPham() {
        return tongSanPham;
    }

    public void setTongSanPham(int tongSanPham) {
        this.tongSanPham = tongSanPham;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    
}

