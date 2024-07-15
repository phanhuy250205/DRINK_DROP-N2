/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.DoiTac;
import com.edusys.entity.HoaDon;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonDAO extends MainDao<HoaDon, String>{
    final String INSERT_SQL = "INSERT INTO HoaDon(MaHoaDon, KhachHang, DiaChiKhach, SDTKhach, TongTien, ThoiGian) VALUES(?, ?, ?, ?, ?,?)";
    final String UPDATE_SQL = "UPDATE HoaDon SET KhachHang = ?, DiaChiKhach = ?, SDTKhach = ?, TongTien = ?, ThoiGian = ? WHERE MaHoaDon = ?";
    final String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
    final String SELECT_BY_KEYWORD_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon LIKE ?";
    
    @Override
    public void insert(HoaDon entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHD(), entity.getKhachHang(), entity.getDiaChiKhach(), entity.getSDTKhach(), entity.getTongTien(), entity.getThoiGian());
    }

    @Override
    public void update(HoaDon entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getKhachHang(), entity.getDiaChiKhach(), entity.getSDTKhach(), entity.getTongTien(), entity.getThoiGian(), entity.getMaHD());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectById(String id) {
        List<HoaDon> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            try {
                rs = JdbcHelper.query(sql, args);
                while(rs.next()){
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getString("MaHoaDon"));
                    hd.setKhachHang(rs.getString("KhachHang"));
                    hd.setDiaChiKhach(rs.getString("DiaChiKhach"));
                    hd.setSDTKhach(rs.getString("SDTKhach"));
                    hd.setTongTien(rs.getFloat("TongTien"));
                    hd.setThoiGian(rs.getDate("ThoiGian"));
                    hd.setMaNhanVien("MaNhanVien");
                    list.add(hd);
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    public List<HoaDon> selectByKeyword(String keyword) {
        return selectBySql(SELECT_BY_KEYWORD_SQL,"%" + keyword + "%");
    }
    
}
