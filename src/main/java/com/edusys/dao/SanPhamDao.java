package com.edusys.dao;

import com.edusys.entity.SanPham;
import com.edusys.utils.JdbcHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao extends MainDao<SanPham, String> {

    final String INSERT_SQL = "INSERT INTO SanPham(MaSanPham,TenSanPham,SoLuong,GiaBan,MoTa,Anh,GiaNhap,MaLoai) VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE SanPham SET TenSanPham = ?, SoLuong = ?, GiaBan = ?, MoTa = ?, Anh = ?, GiaNhap = ?, MaLoai = ? WHERE MaSanPham = ?";
    final String DELETE_SQL = "DELETE FROM SanPham WHERE MaSanPham = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSanPham = ?";
    final String SELECT_BY_KEYWORD = "SELECT * FROM SanPham WHERE TenSanPham LIKE ?";

    @Override
    public void insert(SanPham entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaSanPham(), entity.getTenSanPham(), entity.getSoLuong(), entity.getGiaBan(), entity.getMoTa(), entity.getHinh(), entity.getGiaNhap(), entity.getLoaiSanPham());
    }

    @Override
    public void update(SanPham entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenSanPham(), entity.getSoLuong(), entity.getGiaBan(), entity.getMoTa(), entity.getHinh(), entity.getGiaNhap(), entity.getLoaiSanPham(), entity.getMaSanPham());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SanPham selectById(String id) {
        List<SanPham> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham();
                entity.setMaSanPham(rs.getString("MaSanPham"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGiaBan(rs.getFloat("GiaBan"));
                entity.setLoaiSanPham(rs.getInt("MaLoai"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setGiaNhap(rs.getFloat("GiaNhap"));
                entity.setHinh(rs.getString("Anh"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<SanPham> selectByKeyword(String keyword) {
        return selectBySql(SELECT_BY_KEYWORD, "%" + keyword + "%");
    }
    
   public List<Object[]> getThongKeByThoiGian(String tenSanPham) {
    List<Object[]> thongKeList = new ArrayList<>();
    String sql = "{call sp_GetThongKeByThoiGian(?)}";

    try (Connection conn = JdbcHelper.getConnection();
         CallableStatement cstmt = conn.prepareCall(sql)) {

        cstmt.setString(1, tenSanPham);

        try (ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[6]; // Number of columns returned by the procedure
                row[0] = rs.getString("MaSanPham");
                row[1] = rs.getString("TenSanPham");
                row[2] = rs.getInt("LoaiSanPham");
                row[3] = rs.getInt("TongSanPham");
                row[4] = rs.getInt("SoLuongBan");
                row[5] = rs.getInt("SoLuongCon");
                thongKeList.add(row);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Handle SQL exception here, possibly log or show error to user
    }

    return thongKeList;
}


}
