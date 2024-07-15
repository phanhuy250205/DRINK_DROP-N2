/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.entity;

/**
 *
 * @author admin
 */
public class Giaohang {
  private String MaGiaoHang ;
  private String LoaiNuoc ;
  private String MaSP ;
  private  String DiaChiGiao ;
  private  String TenNguoiGiao ;
  private  String SDTNguoiGiao ;
  private  String TenNguoiNhan;
  private  String SDTNguoiNhan;
  private String TinhTrang ;

    public Giaohang() {
    }

	public Giaohang(String MaGiaoHang, String LoaiNuoc, String MaSP, String DiaChiGiao, String TenNguoiGiao, String SDTNguoiGiao, String TenNguoiNhan, String SDTNguoiNhan, String TinhTrang) {
		this.MaGiaoHang = MaGiaoHang;
		this.LoaiNuoc = LoaiNuoc;
		this.MaSP = MaSP;
		this.DiaChiGiao = DiaChiGiao;
		this.TenNguoiGiao = TenNguoiGiao;
		this.SDTNguoiGiao = SDTNguoiGiao;
		this.TenNguoiNhan = TenNguoiNhan;
		this.SDTNguoiNhan = SDTNguoiNhan;
		this.TinhTrang = TinhTrang;
	}

	public String getMaGiaoHang() {
		return MaGiaoHang;
	}

	public void setMaGiaoHang(String MaGiaoHang) {
		this.MaGiaoHang = MaGiaoHang;
	}

	public String getLoaiNuoc() {
		return LoaiNuoc;
	}

	public void setLoaiNuoc(String LoaiNuoc) {
		this.LoaiNuoc = LoaiNuoc;
	}

	public String getMaSP() {
		return MaSP;
	}

	public void setMaSP(String MaSP) {
		this.MaSP = MaSP;
	}

	public String getDiaChiGiao() {
		return DiaChiGiao;
	}

	public void setDiaChiGiao(String DiaChiGiao) {
		this.DiaChiGiao = DiaChiGiao;
	}

	public String getTenNguoiGiao() {
		return TenNguoiGiao;
	}

	public void setTenNguoiGiao(String TenNguoiGiao) {
		this.TenNguoiGiao = TenNguoiGiao;
	}

	public String getSDTNguoiGiao() {
		return SDTNguoiGiao;
	}

	public void setSDTNguoiGiao(String SDTNguoiGiao) {
		this.SDTNguoiGiao = SDTNguoiGiao;
	}

	public String getTenNguoiNhan() {
		return TenNguoiNhan;
	}

	public void setTenNguoiNhan(String TenNguoiNhan) {
		this.TenNguoiNhan = TenNguoiNhan;
	}

	public String getSDTNguoiNhan() {
		return SDTNguoiNhan;
	}

	public void setSDTNguoiNhan(String SDTNguoiNhan) {
		this.SDTNguoiNhan = SDTNguoiNhan;
	}

	public String getTinhTrang() {
		return TinhTrang;
	}

	public void setTinhTrang(String TinhTrang) {
		this.TinhTrang = TinhTrang;
	}

    
    
}
