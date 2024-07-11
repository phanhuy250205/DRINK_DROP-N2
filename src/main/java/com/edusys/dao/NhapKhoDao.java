package com.edusys.dao;

import com.edusys.dao.DrinkdropDao;
import com.edusys.entity.NhapKho;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhapKhoDao extends DrinkdropDao<NhapKho, String> {

    final String INSERT_SQL = "INSERT INTO NhapKho(MaPhieuNhap,MaNguoiNhap,MaNhaCungCap,NgayNhap,TenSanPham,SoLuong,TienNhap,LoaiSanPham,MaSanPham) VALUES(?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NhapKho SET MaNguoiNhap = ? ,MaNhaCungCap = ? ,NgayNhap = ?,TenSanPham = ? ,SoLuong = ? ,TienNhap = ? ,LoaiSanPham = ? ,MaSanPham = ?  WHERE MaPhieuNhap = ?";
    final String DELETE_SQL = "DELETE FROM NhapKho WHERE MaPhieuNhap = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhapKho";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhapKho WHERE MaPhieuNhap = ?";

    @Override
    public void insert(NhapKho entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaPhieuNhap(), entity.getMaNguoiNhap(), entity.getMaNhaCungCap(),entity.getNgayNhap(), entity.getTenSanPham(), entity.getSoLuong(), entity.getTienNhap(), entity.getLoaiSanPham(), entity.getMaSanPham());
    }

    @Override
    public void update(NhapKho entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaNguoiNhap(), entity.getMaNhaCungCap(),entity.getNgayNhap(), entity.getTenSanPham(), entity.getSoLuong(), entity.getTienNhap(), entity.getLoaiSanPham(), entity.getMaSanPham(), entity.getMaPhieuNhap());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhapKho> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhapKho selectById(String id) {
        List<NhapKho> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhapKho> selectBySql(String sql, Object... args) {
        List<NhapKho> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhapKho entity = new NhapKho();
                entity.setMaPhieuNhap(rs.getString("MaPhieuNhap"));
                entity.setMaNguoiNhap(rs.getString("MaNguoiNhap"));
                entity.setMaNhaCungCap(rs.getString("MaNhaCungCap"));
                entity.setNgayNhap(rs.getDate("NgayNhap"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setTienNhap(rs.getFloat("TienNhap"));
                entity.setLoaiSanPham(rs.getString("LoaiSanPham"));
                entity.setMaSanPham(rs.getString("MaSanPham"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
