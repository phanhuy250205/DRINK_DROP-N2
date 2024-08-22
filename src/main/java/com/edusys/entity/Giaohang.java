package com.edusys.entity;

/**
 * Represents a delivery order with additional product and customer details.
 */
public class Giaohang {
    private String tenKhachHang; // Tên khách hàng
    private String tenSanPham;   // Tên sản phẩm
    private String maNhanVien;      // Mã nhân viên
    private String maSanPham;  
    private  int  Soluong;
    private float donGia;        // Đơn giá
    private float tongTien;      // Tổng tiền

    // Default constructor

    public Giaohang() {
    }

    public Giaohang(String tenKhachHang, String tenSanPham, String maNhanVien, String maSanPham, int Soluong, float donGia, float tongTien) {
        this.tenKhachHang = tenKhachHang;
        this.tenSanPham = tenSanPham;
        this.maNhanVien = maNhanVien;
        this.maSanPham = maSanPham;
        this.Soluong = Soluong;
        this.donGia = donGia;
        this.tongTien = tongTien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }
    
}