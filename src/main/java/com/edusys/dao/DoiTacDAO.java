/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.DoiTac;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DoiTacDAO extends MainDao<DoiTac, String>{
    final String INSERT_SQL = "INSERT INTO NhaCungCap(MaNhaCungCap, TenNhaCungCap, DiaChi, SDT, Email, GhiChu, TrangThai) VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NhaCungCap SET TenNhaCungCap = ?, DiaChi = ?, SDT = ?, Email = ?, GhiChu = ?, TrangThai = ? WHERE MaNhaCungCap = ?";
    final String DELETE_SQL = "DELETE FROM NhaCungCap WHERE MaNhaCungCap = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhaCungCap";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhaCungCap WHERE MaNhaCungCap = ?";
    final String SELECT_BY_KEYWORD_SQL = "SELECT * FROM NhaCungCap WHERE MaNhaCungCap LIKE ?";
    
    
    @Override
    public void insert(DoiTac entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNCC(), entity.getTenNCC(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.getGhiChu(), entity.isTrangThai());
    }

    @Override
    public void update(DoiTac entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenNCC(), entity.getDiaChi(), entity.getSDT(), entity.getEmail(), entity.getGhiChu(), entity.isTrangThai(), entity.getMaNCC());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<DoiTac> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public DoiTac selectById(String id) {
        List<DoiTac> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DoiTac> selectBySql(String sql, Object... args) {
        List<DoiTac> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            try {
                rs = JdbcHelper.query(sql, args);
                while(rs.next()){
                    DoiTac dt = new DoiTac();
                    dt.setMaNCC(rs.getString("MaNhaCungCap"));
                    dt.setTenNCC(rs.getString("TenNhaCungCap"));
                    dt.setDiaChi(rs.getString("DiaChi"));
                    dt.setSDT(rs.getString("SDT"));
                    dt.setEmail(rs.getString("Email"));
                    dt.setGhiChu(rs.getString("GhiChu"));
                    dt.setTrangThai(rs.getBoolean("TrangThai"));
                    list.add(dt);
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<DoiTac> selectByKeyword(String keyword) {
        return selectBySql(SELECT_BY_KEYWORD_SQL,"%" + keyword + "%");
    }
}
