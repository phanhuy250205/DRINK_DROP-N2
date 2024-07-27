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
    private int Magiaohang;
    private  String masp;
    private  String Tensp;
    private  int soluong;
    private  float dongia ;
    private  float tongtien ;

    public Giaohang() {
    }

    public Giaohang(int Magiaohang, String masp, String Tensp, int soluong, float dongia, float tongtien) {
        this.Magiaohang = Magiaohang;
        this.masp = masp;
        this.Tensp = Tensp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.tongtien = tongtien;
    }

    public int getMagiaohang() {
        return Magiaohang;
    }

    public void setMagiaohang(int Magiaohang) {
        this.Magiaohang = Magiaohang;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return Tensp;
    }

    public void setTensp(String Tensp) {
        this.Tensp = Tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    
    
}
