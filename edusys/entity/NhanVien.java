/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entily;

/**
 *
 * @author hoang
 */
public class NhanVien {

    private String MaNhanVien;
    private String TenNhanVien;
    private String DiaChi;
    private boolean GioiTinh;
    private String SDT;
    private boolean VaiTro;
    private String Anh;

    

    public NhanVien() {
    }
   @Override
    public String toString() {
        return this.TenNhanVien;
       
    }
    
    public NhanVien(String MaNhanVien, String TenNhanVien, String DiaChi, boolean GioiTinh, String SDT, boolean VaiTro, String Anh) {
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.DiaChi = DiaChi;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.VaiTro = VaiTro;
        this.Anh = Anh;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String Anh) {
        this.Anh = Anh;
    }

}
