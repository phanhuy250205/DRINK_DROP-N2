/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.ul;

import com.edusys.dao.NhanVienDao;
import com.edusys.entity.NhanVien;
import java.util.List;

/**
 *
 * @author TranTrongDi
 */
public class Test {
    public static void main(String[] args) {
      NhanVienDao dao = new NhanVienDao();
      dao.insert(new NhanVien("001", "Nguyen Van A", "password001", "82phamnhuxuong", true, "0123456789", true, "NV1", "image1"));
      List<NhanVien> ls = dao.selectAll();
      for (NhanVien nv : ls) {
          System.out.println("=>"+nv.toString());
      }
    }
}

