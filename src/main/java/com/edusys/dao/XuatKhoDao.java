package com.edusys.dao;

import com.edusys.entity.XuatKho;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class XuatKhoDao extends DrinkdropDao<XuatKho, String> {

    final String INSERT_SQL = "INSERT INTO XuatKho(MaPhieuXuat,MaSanPham,TenSanPham,SoLuongXuat,NgayXuat,MaNguoiXuat,LoaiSanPham,GhiChu)VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE XuatKho SET MaSanPham = ? ,TenSanPham = ? ,SoLuongXuat = ? ,NgayXuat = ? ,MaNguoiXuat = ? ,LoaiSanPham = ? ,GhiChu = ? WHERE MaPhieuXuat = ?";
    final String DELETE_SQL = "DELETE FROM XuatKho WHERE MaPhieuXuat = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM XuatKho";
    final String SELECT_BY_ID_SQL = "SELECT * FROM XuatKho WHERE MaPhieuXuat = ?";

    @Override
    public void insert(XuatKho entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaPhieuXuat(), entity.getMaSanPham(), entity.getTenSanPham(), entity.getSoLuong(), entity.getNgayXuat(), entity.getMaNguoiXuat(), entity.getLoaiSanPham(),entity.getGhiChu());
    }

    @Override
    public void update(XuatKho entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaSanPham(), entity.getTenSanPham(), entity.getSoLuong(), entity.getNgayXuat(), entity.getMaNguoiXuat(), entity.getLoaiSanPham(),entity.getGhiChu(), entity.getMaPhieuXuat());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<XuatKho> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public XuatKho selectById(String id) {
        List<XuatKho> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<XuatKho> selectBySql(String sql, Object... args) {
        List<XuatKho> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                XuatKho entity = new XuatKho();
                entity.setMaPhieuXuat(rs.getString("MaPhieuXuat"));
                entity.setMaSanPham(rs.getString("MaSanPham"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setSoLuong(rs.getInt("SoLuongXuat"));
                entity.setNgayXuat(rs.getDate("NgayXuat"));
                entity.setMaNguoiXuat(rs.getString("MaNguoiXuat"));
                entity.setLoaiSanPham(rs.getString("LoaiSanPham"));
                entity.setGhiChu(rs.getString("GhiChu"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
