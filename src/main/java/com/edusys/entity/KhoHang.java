
package com.edusys.entity;

import java.util.Date;

public class KhoHang {
    private String maSanPham;
    private String tenSanPham;
    private String NCC;
    private int soLuong;
    private float giaNhap;
    private float giaBan;
    private int SLTonKho;
    private Date ngayNhap;
    private Date ngayXuat;
    private String loaiSanPham;

    public KhoHang() {
    }

    public KhoHang(String maSanPham, String tenSanPham, String NCC, int soLuong, float giaNhap, float giaBan, int SLTonKho, Date ngayNhap, Date ngayXuat, String loaiSanPham) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.NCC = NCC;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.SLTonKho = SLTonKho;
        this.ngayNhap = ngayNhap;
        this.ngayXuat = ngayXuat;
        this.loaiSanPham = loaiSanPham;
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

    public String getNCC() {
        return NCC;
    }

    public void setNCC(String NCC) {
        this.NCC = NCC;
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

    public int getSLTonKho() {
        return SLTonKho;
    }

    public void setSLTonKho(int SLTonKho) {
        this.SLTonKho = SLTonKho;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
    
}
