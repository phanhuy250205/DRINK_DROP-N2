/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.khachhang;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//
/**
 *
 * @author hoang
 */
public class KhachHangDAO extends MainDao<khachhang, String> {

    final String INSERT_SQL = "INSERT INTO KhachHang (MaKhachHang, TenKhachHang, DiaChi, SDT, LoaiKhachHang, MaNhanVien) Values(?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhachHang SET TenKhachHang = ?,DiaChi= ?,SDT= ?,LoaiKhachHang= ? ,MaNhanVien = ?  WHERE MaKhachHang=? ";
    final String DELETE_SQL = "DELETE FROM KhachHang Where MaKhachHang= ?";
    final String SELECT_ALL_SQL = "SELECT * From KhachHang";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KhachHang where MaKhachHang = ?";
    final String SELECT_BY_KEYWORD ="SELECT * FROM KhachHang WHERE TenKhachHang LIKE ?";
    @Override
    public void insert(khachhang entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaKhachHang(), entity.getTenKhachHang(), entity.getDiaChi(), entity.getSDT(), entity.getLoaiKhachHang(), entity.getMaNhanVien());
    }

    @Override
    public void update(khachhang entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenKhachHang(), entity.getDiaChi(), entity.getSDT(), entity.getLoaiKhachHang(), entity.getMaNhanVien() ,entity.getMaKhachHang());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<khachhang> selectAll() {
        return selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public khachhang selectById(String id) {
        List<khachhang> list = selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<khachhang> selectbySql(String sqlString, Object... args) {
        List<khachhang> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sqlString, args);
            while (rs.next()) {
                khachhang entity = new khachhang();
                entity.setMaKhachHang(rs.getString("MaKhachHang"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setLoaiKhachHang(rs.getString("LoaiKhachHang"));
                entity.setMaNhanVien(rs.getString("MaNhanVien"));
                entity.setSDT(rs.getString("SDT"));
                entity.setTenKhachHang(rs.getString("TenKhachHang"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
     public List<khachhang> selectByKeyword(String keyword){
        return selectbySql(SELECT_BY_KEYWORD,"%" + keyword + "%");
    }

    @Override
    public List<khachhang> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
