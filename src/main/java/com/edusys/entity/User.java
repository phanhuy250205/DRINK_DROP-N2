/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.entity;

/**
 *
 * @author TranTrongDi
 */
public class User {
    private String TenDN;
    private String MatKhau;
    private boolean VaiTro;

    public User() {
    }

    public User(String TenDN, String MatKhau, boolean VaiTro) {
        this.TenDN = TenDN;
        this.MatKhau = MatKhau;
        this.VaiTro = VaiTro;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }
    
}
