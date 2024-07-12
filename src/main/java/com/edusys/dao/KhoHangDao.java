
package com.edusys.dao;

import com.edusys.entity.KhoHang;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhoHangDao extends MainDao<KhoHang, String>{
    final String INSERT_SQL = "INSERT INTO QuanLyKho(ID,LoaiGiaoDich,MaPhieu,MaSanPham,TenSanPham,SoLuong,NgayGiaoDich,MaNhanVien,GhiChu) VALUES(?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE QuanLyKho SET LoaiGiaoDich = ? ,MaPhieu = ? ,MaSanPham = ? ,TenSanPham = ? ,SoLuong = ? ,NgayGiaoDich = ? ,MaNhanVien = ? ,GhiChu = ? WHERE ID = ? ";
    final String DELETE_SQL = "DELETE FROM QuanLyKho Where ID = ?";
    final String SELECT_ALL_SQL = "SELECT * From QuanLyKho";
    final String SELECT_BY_ID_SQL = "SELECT * FROM QuanLyKho where ID = ?";
    @Override
    public void insert(KhoHang entity) {
        JdbcHelper.update(INSERT_SQL, entity.getID(),entity.getLoaiGiaoDich(),entity.getMaPhieu(),entity.getMaSanPham(),entity.getTenSanPham(),entity.getSoLuong(),entity.getNgayGiaoDich(),entity.getMaNhanVien(),entity.getGhiChu());
    }

    @Override
    public void update(KhoHang entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getLoaiGiaoDich(),entity.getMaPhieu(),entity.getMaSanPham(),entity.getTenSanPham(),entity.getSoLuong(),entity.getNgayGiaoDich(),entity.getMaNhanVien(),entity.getGhiChu(),entity.getID());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<KhoHang> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhoHang selectById(String id) {
        List<KhoHang> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoHang> selectBySql(String sql, Object... args) {
        List<KhoHang> list = new ArrayList<>();
         try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoHang entity = new KhoHang();
                entity.setID(rs.getInt("ID"));
                entity.setLoaiGiaoDich(rs.getString("LoaiGiaoDich"));
                entity.setMaPhieu(rs.getString("MaPhieu"));
                entity.setMaSanPham(rs.getString("MaSanPham"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setNgayGiaoDich(rs.getDate("NgayGiaoDich"));
                entity.setMaNhanVien(rs.getString("MaNhanVien"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
