package com.edusys.entity;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTiet {
    private int maHDCT;         // Changed to int
    private int maHD;           // Changed to int
    private int maSanPham;      // Changed to int
    private int soLuong;
    private double donGia;      // Changed to double for better precision
    private double thanhTien;   // Changed to double for better precision

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, int maHD, int maSanPham, int soLuong, double donGia, double thanhTien) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
