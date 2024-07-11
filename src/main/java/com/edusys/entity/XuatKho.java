package com.edusys.entity;

import java.util.Date;

public class XuatKho {
    private String maPhieuXuat;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private Date ngayXuat;
    private String maNguoiXuat;
    private String loaiSanPham;
    private String ghiChu;

    public XuatKho() {
    }

    public XuatKho(String maPhieuXuat, String maSanPham, String tenSanPham, int soLuong, Date ngayXuat, String maNguoiXuat, String loaiSanPham, String ghiChu) {
        this.maPhieuXuat = maPhieuXuat;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.ngayXuat = ngayXuat;
        this.maNguoiXuat = maNguoiXuat;
        this.loaiSanPham = loaiSanPham;
        this.ghiChu = ghiChu;
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
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

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public String getMaNguoiXuat() {
        return maNguoiXuat;
    }

    public void setMaNguoiXuat(String maNguoiXuat) {
        this.maNguoiXuat = maNguoiXuat;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
