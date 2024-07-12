package com.edusys.dao;

import com.edusys.entity.SanPham;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao extends MainDao<SanPham, String> {

    final String INSERT_SQL = "INSERT INTO SanPham(MaSanPham,TenSanPham,SoLuong,GiaBan,LoaiSanPham,MoTa,Anh,GiaNhap,TongSanPham) VALUES(?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE SanPham SET TenSanPham = ? ,SoLuong= ?,GiaBan= ? ,LoaiSanPham = ? ,MoTa = ?, Anh = ? ,GiaNhap = ?  ,TongSanPham = ?  WHERE MaSanPham =? ";
    final String DELETE_SQL = "DELETE FROM SanPham Where MaSanPham= ?";
    final String SELECT_ALL_SQL = "SELECT * From SanPham";
    final String SELECT_BY_ID_SQL = "SELECT * FROM SanPham where MaSanPham = ?";
    final String SELECT_BY_KEYWORD = "SELECT * FROM SanPham WHERE MaSanPham LIKE ?";

    @Override
    public void insert(SanPham entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaSanPham(), entity.getTenSanPham(), entity.getSoLuong(), entity.getGiaBan(), entity.getLoaiSanPham(), entity.getMoTa(),entity.getHinh(),entity.getGiaNhap(),entity.getTongSanPham());
    }

    @Override
    public void update(SanPham entity) {
        JdbcHelper.update(UPDATE_SQL,  entity.getTenSanPham(), entity.getSoLuong(), entity.getGiaBan(), entity.getLoaiSanPham(), entity.getMoTa(),entity.getHinh(),entity.getGiaNhap(),entity.getTongSanPham(), entity.getMaSanPham());
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
                entity.setLoaiSanPham(rs.getString("LoaiSanPham"));
                entity.setMoTa(rs.getString("MoTa"));
                entity.setGiaNhap(rs.getFloat("GiaNhap"));
                entity.setHinh(rs.getString("Anh"));
                entity.setTongSanPham(rs.getInt("TongSanPham")); 
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<SanPham> selectByKeyword(String keyword) {
        return selectBySql(SELECT_BY_KEYWORD, "%" + keyword + "%");
    }

}
