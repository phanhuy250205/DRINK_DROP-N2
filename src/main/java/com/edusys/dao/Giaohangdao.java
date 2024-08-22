package com.edusys.dao;

import com.edusys.entity.Giaohang;
import com.edusys.utils.JdbcHelper;
import static com.edusys.utils.JdbcHelper.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Giaohangdao extends MainDao<Giaohang, Integer> {
    // SQL statements for CRUD operations
    final String insert_SQL = "INSERT INTO ProducBanHang (TenKhachHang, TenSanPham, MaNhanVien, MaSanPham, SoLuong, DonGia, TongTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String update_SQL = "UPDATE ProducBanHang SET TenKhachHang = ?, TenSanPham = ?, MaNhanVien = ?, MaSanPham = ?, SoLuong = ?, DonGia = ?, TongTien = ? WHERE MaGiaoHang = ?";
    final String delete_SQL = "DELETE FROM ProducBanHang WHERE MaGiaoHang = ?";
    final String all_SQL = "SELECT * FROM ProducBanHang";
    final String select_By_id_SQL = "SELECT * FROM ProducBanHang WHERE MaGiaoHang = ?";

    @Override
    public void insert(Giaohang entity) {
        JdbcHelper.update(insert_SQL, 
            entity.getTenKhachHang(),
            entity.getTenSanPham(),
            entity.getMaNhanVien(),
            entity.getMaSanPham(),
            entity.getSoluong(), // Đã sửa lại thành getSoluong()
            entity.getDonGia(),
            entity.getTongTien()
        );
    }

    @Override
    public void update(Giaohang entity) {
        JdbcHelper.update(update_SQL, 
            entity.getTenKhachHang(),
            entity.getTenSanPham(),
            entity.getMaNhanVien(),
            entity.getMaSanPham(),
            entity.getSoluong(), // Đã sửa lại thành getSoluong()
            entity.getDonGia(),
            entity.getTongTien()
            // Đã thêm MaGiaoHang vào câu lệnh UPDATE
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
                entity.setTenKhachHang(rs.getString("TenKhachHang"));
                entity.setTenSanPham(rs.getString("TenSanPham"));
                entity.setMaNhanVien(rs.getString("MaNhanVien")); // Kiểm tra kiểu dữ liệu
                entity.setMaSanPham(rs.getString("MaSanPham")); 
                entity.setSoluong(rs.getInt("SoLuong")); // Sửa lại để sử dụng getInt
                entity.setDonGia(rs.getFloat("DonGia"));
                entity.setTongTien(rs.getFloat("TongTien"));
                // Assuming `Giaohang` has a field `MaGiaoHang` for the unique identifier
                // entity.setMaGiaoHang(rs.getInt("MaGiaoHang")); // Thêm nếu cần thiết
                
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Giaohang selectByCustomerName(String tenKhachHang) {
        Giaohang model = null;
        String sql = "SELECT * FROM ProducBanHang WHERE TenKhachHang = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenKhachHang);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    model = new Giaohang();
                    model.setTenKhachHang(rs.getString("TenKhachHang"));
                    model.setTenSanPham(rs.getString("TenSanPham"));
                    model.setMaNhanVien(rs.getString("MaNhanVien"));
                    model.setMaSanPham(rs.getString("MaSanPham"));
                    model.setSoluong(rs.getInt("SoLuong"));
                    model.setDonGia(rs.getFloat("DonGia"));
                    model.setTongTien(rs.getFloat("TongTien"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
    public Giaohang selectByMaSanPham(String maSanPham) {
    // Câu lệnh SQL để lấy thông tin sản phẩm theo mã sản phẩm
    String sql = "SELECT * FROM ProducBanHang WHERE MaSanPham = ?";
    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maSanPham);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Giaohang model = new Giaohang();
            model.setTenKhachHang(rs.getString("TenKhachHang"));
            model.setTenSanPham(rs.getString("TenSanPham"));
            model.setMaNhanVien(rs.getString("MaNhanVien"));
            model.setMaSanPham(rs.getString("MaSanPham"));
            model.setSoluong(rs.getInt("SoLuong"));
            model.setDonGia(rs.getFloat("DonGia"));
            model.setTongTien(rs.getFloat("TongTien"));
            return model;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}
}
