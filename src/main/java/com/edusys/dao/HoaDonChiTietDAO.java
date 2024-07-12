/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.HoaDonChiTiet;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTietDAO extends MainDao<HoaDonChiTiet, String>{
    final String SELECT_ALL_SQL = "SELECT * FROM HoaDonChiTiet";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDonChiTiet WHERE MaHDCT = ?";
    final String SELECT_BY_MAHD = "SELECT * FROM HoaDonChiTiet WHERE MaHD = ?";
        
    
    @Override
    public void insert(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDonChiTiet selectById(String id) {
        List<HoaDonChiTiet> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try{
            ResultSet rs = JdbcHelper.query(sql, args);
            try {
                rs = JdbcHelper.query(sql, args);
                while(rs.next()){
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setMaHDCT(rs.getString("MaHDCT"));
                    hdct.setMaHD(rs.getString("MaHoaDon"));
                    hdct.setMaSanPham(rs.getString("MaSanPham"));
                    hdct.setSoLuong(rs.getInt("SoLuong"));
                    hdct.setDonGia(rs.getFloat("DonGia"));
                    hdct.setThanhTien(rs.getFloat("ThanhTien"));
                    list.add(hdct);
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<HoaDonChiTiet> selectByMaHD(String maHoaDon, Object ... args) throws SQLException {
    List<HoaDonChiTiet> list = new ArrayList<>();
    String sql = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon = ?";
    try(PreparedStatement ps = JdbcHelper.getStmt(sql, args)){
        ps.setString(1, maHoaDon);

        try (ResultSet rs = ps.executeQuery()) {
            while(rs.next()){
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHDCT(rs.getString("MaHDCT"));
                hdct.setMaHD(rs.getString("MaHoaDon"));
                hdct.setMaSanPham(rs.getString("MaSanPham"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDonGia(rs.getFloat("DonGia"));
                hdct.setThanhTien(rs.getFloat("ThanhTien"));
                list.add(hdct);
            }
        }
    }
    return list;
}
    
}
