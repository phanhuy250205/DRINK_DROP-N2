package com.edusys.dao;

import com.edusys.entity.NhanVien;
import com.edusys.utils.JdbcHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDao extends MainDao<NhanVien, String> {

    final String INSERT_SQL = "INSERT INTO NhanVien(MaNhanVien,TenNhanVien,DiaChi,GioiTinh,SDT,VaiTro,Anh) VALUES(?, ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET TenNhanVien = ?,DiaChi = ?,GioiTinh = ?, SDT = ?, VaiTro = ?, Anh =? WHERE MaNhanVien = ?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
    final String SELECT_BY_KEYWORD = "SELECT * FROM NhanVien WHERE TenNhanVien LIKE ?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getTenNV(), entity.getDiaChi(), entity.isGioiTinh(), entity.getSdt(), entity.isVaiTro(), entity.getAnh());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenNV(), entity.getDiaChi(), entity.isGioiTinh(), entity.getSdt(), entity.isVaiTro(), entity.getAnh(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectbySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectbySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }


    public List<NhanVien> selectbySql(String sql, Object... args) {
         List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNhanVien"));
                entity.setTenNV(rs.getString("TenNhanVien"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setSdt(rs.getString("SDT"));
                entity.setAnh(rs.getString("Anh"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<NhanVien> selectByKeyword(String keyword) {
        return selectbySql(SELECT_BY_KEYWORD, "%" + keyword + "%");
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public List<NhanVien> selectall() {
        return selectbySql(SELECT_ALL_SQL);
    } 

//   public List<Object[]> getThongKeByNhanVien(String tenNV) {
//    String sql = "{call sp_GetThongKeByNhanVien(?)}";
//    String[] cols = {"ThoiGian", "SoHoaDon", "TongHD", "TongTienBanRa", "TongTienThuLai", "MaNhanVien", "NhanVienLapHoaDon", "SanPham", "GiaNhap", "GiaBan", "SoLuong"}; // Các cột trong kết quả truy vấn
//
//    return getListOfArray(sql, cols, tenNV);
//}

private List<Object[]> getListOfArray(String sql, String[] cols, Object... params) {
    List<Object[]> list = new ArrayList<>();
    try {
        ResultSet rs = JdbcHelper.query(sql, params);
        while (rs.next()) {
            Object[] vals = new Object[cols.length];
            for (int i = 0; i < cols.length; i++) {
                vals[i] = rs.getObject(cols[i]);
            }
            list.add(vals);
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}



     public float getTongTienVaLoi(String tenNhanVien) {
        float tongLoiNhuan = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = JdbcHelper.getConnection(); // Lấy kết nối từ JdbcHelper
            
            // Câu truy vấn tính tổng lợi nhuận cho nhân viên cụ thể
            String sql = "SELECT SUM(ct.ThanhTien - sp.GiaNhap * ct.SoLuong) AS TongLoiNhuan " +
                         "FROM HoaDonChiTiet ct " +
                         "JOIN SanPham sp ON ct.MaSanPham = sp.MaSanPham " +
                         "JOIN HoaDon hd ON ct.MaHoaDon = hd.MaHoaDon " +
                         "JOIN NhanVien nv ON hd.MaNhanVien = nv.MaNhanVien " +
                         "WHERE nv.TenNhanVien = ?";
            
            // Chuẩn bị câu lệnh SQL
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenNhanVien);
            
            // Thực thi câu lệnh SQL
            rs = stmt.executeQuery();
            
            // Lấy kết quả
            if (rs.next()) {
                tongLoiNhuan = rs.getFloat("TongLoiNhuan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và các đối tượng liên quan
//            JdbcHelper.close(rs, stmt, conn);
        }
        
        // Trả về kết quả tổng lợi nhuận
        return tongLoiNhuan;
    }
     
     public List<Object[]> getThongKeByNhanVien(String maNhanVien) {
    List<Object[]> thongKeList = new ArrayList<>();
    
    String sql = "{call sp_GetThongKeByNhanVien(?)}";
    
    try (
        Connection conn = JdbcHelper.getConnection();
        CallableStatement cstmt = conn.prepareCall(sql);
    ) {
        cstmt.setString(1, maNhanVien);
        
        try (ResultSet rs = cstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[11]; // Điều chỉnh kích thước dựa trên số cột của kết quả truy vấn của bạn
                row[0] = rs.getDate("ThoiGian");
                row[1] = rs.getString("SoHoaDon");
                row[2] = rs.getInt("TongHD");
                row[3] = rs.getFloat("TongTienBanRa");
                row[4] = rs.getFloat("TongTienThuLai");
                row[5] = rs.getString("MaNhanVien");
                row[6] = rs.getString("NhanVienLapHoaDon");
                row[7] = rs.getString("SanPham");
                row[8] = rs.getFloat("GiaNhap");
                row[9] = rs.getFloat("GiaBan");
                row[10] = rs.getInt("SoLuong");
                thongKeList.add(row);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Xử lý ngoại lệ SQL theo nhu cầu
    }
    
    return thongKeList;
}


       
}
