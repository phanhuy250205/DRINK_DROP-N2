package com.edusys.dao;

import com.edusys.entity.HoaDonChiTiet;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO extends MainDao<HoaDonChiTiet, Integer> {
    final String INSERT_SQL = "INSERT INTO HoaDonChiTiet(MaHDCT, MaHoaDon, MaSanPham, SoLuong, DonGia, ThanhTien) VALUES(?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE HoaDonChiTiet SET MaHoaDon = ?, MaSanPham = ?, SoLuong = ?, DonGia = ?, ThanhTien = ? WHERE MaHDCT = ?";
    final String DELETE_SQL = "DELETE FROM HoaDonChiTiet WHERE MaHDCT = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HoaDonChiTiet";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDonChiTiet WHERE MaHDCT = ?";
    final String SELECT_BY_MAHD = "SELECT * FROM HoaDonChiTiet WHERE MaHoaDon = ?";

    @Override
    public void insert(HoaDonChiTiet entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHDCT(), entity.getMaHD(), entity.getMaSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien());
    }

    @Override
    public void update(HoaDonChiTiet entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaHD(), entity.getMaSanPham(), entity.getSoLuong(), entity.getDonGia(), entity.getThanhTien(), entity.getMaHDCT());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDonChiTiet selectById(Integer id) {
        List<HoaDonChiTiet> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            try {
                while (rs.next()) {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setMaHDCT(rs.getInt("MaHDCT")); // Changed to int
                    hdct.setMaHD(rs.getInt("MaHoaDon")); // Changed to int
                    hdct.setMaSanPham(rs.getInt("MaSanPham")); // Changed to int
                    hdct.setSoLuong(rs.getInt("SoLuong"));
                    hdct.setDonGia(rs.getDouble("DonGia")); // Changed to double
                    hdct.setThanhTien(rs.getDouble("ThanhTien")); // Changed to double
                    list.add(hdct);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<HoaDonChiTiet> selectByMaHD(Integer maHoaDon) {
        return selectBySql(SELECT_BY_MAHD, maHoaDon);
    }
}
