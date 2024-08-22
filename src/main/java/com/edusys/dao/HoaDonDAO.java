package com.edusys.dao;

import com.edusys.entity.HoaDon;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO extends MainDao<HoaDon, Integer> {
    final String INSERT_SQL = "INSERT INTO HoaDon(MaHoaDon, MaKhachHang, TongTien, ThoiGian, MaNhanVien) VALUES(?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE HoaDon SET MaKhachHang = ?, TongTien = ?, ThoiGian = ?, MaNhanVien = ? WHERE MaHoaDon = ?";
    final String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
    final String SELECT_BY_KEYWORD_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon LIKE ?";

    @Override
    public void insert(HoaDon entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHD(), entity.getMaKhachHang(), entity.getTongTien(), entity.getThoiGian(), entity.getMaNhanVien());
    }

    @Override
    public void update(HoaDon entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaKhachHang(), entity.getTongTien(), entity.getThoiGian(), entity.getMaNhanVien(), entity.getMaHD());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HoaDon selectById(Integer id) {
        List<HoaDon> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
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
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHD(rs.getInt("MaHoaDon"));
                    hd.setMaKhachHang(rs.getInt("MaKhachHang"));
                    hd.setTongTien(rs.getDouble("TongTien"));
                    hd.setThoiGian(rs.getTimestamp("ThoiGian"));
                    hd.setMaNhanVien(rs.getInt("MaNhanVien"));
                    list.add(hd);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<HoaDon> selectByKeyword(String keyword) {
        return selectBySql(SELECT_BY_KEYWORD_SQL, "%" + keyword + "%");
    }
}
