package com.edusys.dao;

import com.edusys.dao.DrinkdropDao;
import com.edusys.entity.NhapKho;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhapKhoDao extends DrinkdropDao<NhapKho, String> {

    final String INSERT_SQL = "INSERT INTO NhapKho(MaPhieuNhap,NCC,Manv,SoLuong,NgayNhap,TongTien,LoaiSanPham,MaSanPham) VALUES(?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NhapKho SET NCC = ? ,Manv = ? ,SoLuong = ?,NgayNhap = ? ,TongTien = ? ,LoaiSanPham = ? ,MaSanPham = ?  WHERE MaPhieuNhap = ?";
    final String DELETE_SQL = "DELETE FROM NhapKho WHERE MaPhieuNhap = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhapKho";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhapKho WHERE MaPhieuNhap = ?";

    @Override
    public void insert(NhapKho entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaPhieuNhap(), entity.getNCC(), entity.getMaNhanVien(), entity.getSoLuong(), entity.getNgayNhap(), entity.getTienNhap(), entity.getLoaiSanPham(), entity.getMaSanPham());
    }

    @Override
    public void update(NhapKho entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getNCC(), entity.getMaNhanVien(), entity.getSoLuong(), entity.getNgayNhap(), entity.getTienNhap(), entity.getLoaiSanPham(), entity.getMaSanPham(), entity.getMaPhieuNhap());
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
                entity.setMaPhieuNhap(rs.getInt("MaPhieuNhap"));
                entity.setNCC(rs.getString("NCC"));
                entity.setMaNhanVien(rs.getString("Manv"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setNgayNhap(rs.getDate("NgayNhap"));
                entity.setTienNhap(rs.getFloat("TongTien"));
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
