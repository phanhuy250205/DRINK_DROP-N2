/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

//import Edusys.ennity.Giaohang;
//import Edusys.untils.JDBChelper;
import com.edusys.dao.MainDao;
import com.edusys.entity.Giaohang;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class Giaohangdao extends MainDao<Giaohang, Integer> {
    // Câu lệnh SQL cho các thao tác CRUD
    final String insert_SQL = "INSERT INTO Banhang (MaGiaoHang, MaSanPham, TenSanPham, SoLuong, DonGia, TongTien) VALUES (?, ?, ?, ?, ?, ?)";
    final String update_SQL = "UPDATE Banhang SET MaSanPham = ?, TenSanPham = ?, SoLuong = ?, DonGia = ?, TongTien = ? WHERE MaGiaoHang = ?";
    final String delete_SQL = "DELETE FROM Banhang WHERE MaGiaoHang = ?";
    final String all_SQL = "SELECT * FROM Banhang";
    final String select_By_id_SQL = "SELECT * FROM Banhang WHERE MaGiaoHang = ?";

    @Override
    public void insert(Giaohang entity) {
        JdbcHelper.update(insert_SQL, 
            entity.getMagiaohang(), 
            entity.getMasp(), 
            entity.getTensp(), 
            entity.getSoluong(), 
            entity.getDongia(), 
            entity.getTongtien()
        );
    }

    @Override
    public void update(Giaohang entity) {
        JdbcHelper.update(update_SQL, 
            entity.getMasp(), 
            entity.getTensp(), 
            entity.getSoluong(), 
            entity.getDongia(), 
            entity.getTongtien(), 
            entity.getMagiaohang()
        );
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.update(delete_SQL, id);
    }

    @Override
    public List<Giaohang> selectAll() {
        return selectBySql(all_SQL);
    }

    @Override
    public Giaohang selectById(Integer id) {
        List<Giaohang> list = selectBySql(select_By_id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Giaohang> selectBySql(String sql, Object... args) {
        List<Giaohang> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                Giaohang entity = new Giaohang();
                entity.setMagiaohang(rs.getInt("MaGiaoHang"));
                entity.setMasp(rs.getString("MaSanPham"));
                entity.setTensp(rs.getString("TenSanPham"));
                entity.setSoluong(rs.getInt("SoLuong"));
                entity.setDongia(rs.getFloat("DonGia"));
                entity.setTongtien(rs.getFloat("TongTien"));
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
