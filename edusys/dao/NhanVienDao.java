package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao extends MainDao<NhanVien, String> {

    final String INSERT_SQL = "INSERT INTO NhanVien(MaNhanVien, TenNhanVien, MatKhau, DiaChi, GioiTinh, SDT, VaiTro, TenDN, Anh) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET TenNhanVien = ?, MatKhau =?, DiaChi = ?, GioiTinh = ?, SDT = ?, VaiTro = ?, TenDN = ?, Anh =? WHERE MaNhanVien = ?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.getMatKhau(), entity.getDiaChi(),entity.isGioiTinh(),entity.getSdt(), entity.isVaiTro(), entity.getTenDN(), entity.getAnh());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenNV(), entity.getMatKhau(), entity.getDiaChi(),entity.isGioiTinh(),entity.getSdt(), entity.isVaiTro(), entity.getTenDN(), entity.getAnh(),entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setTenNV(rs.getString("TenNhanVien"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setSdt(rs.getString("SDT"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setTenDN(rs.getString("TenDN"));
                entity.setAnh(rs.getString("Anh"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
