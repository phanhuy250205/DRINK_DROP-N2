package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.JdbcHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao extends MainDao<NhanVien, String> {

    final String INSERT_SQL = "INSERT INTO NhanVien(MaNhanVien,TenNhanVien,DiaChi,GioiTinh,SDT,VaiTro,Anh) VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET TenNhanVien = ?,DiaChi = ?,GioiTinh = ?, SDT = ?, VaiTro = ?, Anh =? WHERE MaNhanVien = ?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
    final String SELECT_BY_KEYWORD = "SELECT * FROM NhanVien WHERE TenNhanVien LIKE ?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.getDiaChi(), entity.isGioiTinh(), entity.getSdt(), entity.isVaiTro(), entity.getAnh());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenNV(), entity.getDiaChi(), entity.isGioiTinh(), entity.getSdt(), entity.isVaiTro(), entity.getAnh(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    public List<NhanVien> selectbySql(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setTenNV(rs.getString("TenNhanVien"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setSdt(rs.getString("SDT"));
                entity.setAnh(rs.getString("Anh"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<NhanVien> selectByKeyword(String keyword) {
        return selectbySql(SELECT_BY_KEYWORD, "%" + keyword + "%");
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
