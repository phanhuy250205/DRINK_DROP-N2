
package com.edusys.dao;

import com.edusys.entity.KhoHang;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KhoHangDao extends DrinkdropDao<KhoHang, String>{
    final String INSERT_SQL = "INSERT INTO KhoHang(MaSanPham,TenSanPham,NCC,SoLuong,GiaNhap,GiaBan,SLTonKho,NgayNhap,NgayXuat,LoaiSanPham) VALUES(?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhoHang SET TenSanPham = ? ,NCC = ? ,SoLuong = ? ,GiaNhap = ? ,GiaBan= ? ,SLTonKho = ? ,NgayNhap = ? ,NgayXuat = ? ,LoaiSanPham = ? WHERE MaSanPham =? ";
    final String DELETE_SQL = "DELETE FROM KhoHang Where MaSanPham= ?";
    final String SELECT_ALL_SQL = "SELECT * From KhoHang";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KhoHang where MaSanPham = ?";
    @Override
    public void insert(KhoHang entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaSanPham(),entity.getTenSanPham(),entity.getNCC(),entity.getSoLuong(),entity.getGiaNhap(),entity.getGiaBan(),entity.getSLTonKho(),entity.getNgayNhap(),entity.getNgayXuat(),entity.getLoaiSanPham());
    }

    @Override
    public void update(KhoHang entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenSanPham(),entity.getNCC(),entity.getSoLuong(),entity.getGiaNhap(),entity.getGiaBan(),entity.getSLTonKho(),entity.getNgayNhap(),entity.getNgayXuat(),entity.getLoaiSanPham(),entity.getMaSanPham());
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
                entity.setMaSanPham(rs.getString("MaSanPham"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setNCC(rs.getString("NCC"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGiaNhap(rs.getFloat("GiaNhap"));
                entity.setGiaBan(rs.getFloat("GiaBan"));
                entity.setSLTonKho(rs.getInt("SLTonKho"));
                entity.setNgayNhap(rs.getDate("NgayNhap"));
                entity.setNgayXuat(rs.getDate("NgayXuat"));
                entity.setLoaiSanPham(rs.getString("LoaiSanPham"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
