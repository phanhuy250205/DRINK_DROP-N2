/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.utils.JdbcHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ThongKedao {

    public List<Object[]> getDoanhthu1(java.util.Date dateFrom, java.util.Date dateTo) {
        List<Object[]> doanhThuList = new ArrayList<>();

        String sql = "{call sp_GetThongKe(?, ?)}";

        try (
                Connection conn = JdbcHelper.getConnection(); CallableStatement cstmt = conn.prepareCall(sql);) {
            // Chuyển đổi từ java.util.Date sang java.sql.Date
            java.sql.Date sqlDateFrom = new java.sql.Date(dateFrom.getTime());
            java.sql.Date sqlDateTo = new java.sql.Date(dateTo.getTime());

            cstmt.setDate(1, sqlDateFrom);
            cstmt.setDate(2, sqlDateTo);

            try (ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    Object[] row = new Object[5]; // Số cột của kết quả truy vấn
                    row[0] = rs.getDate("ThoiGianLap");
                    row[1] = rs.getInt("TongSoSanPham");
                    row[2] = rs.getFloat("TongTien");
                    row[3] = rs.getString("MaNhanVien");
                    row[4] = rs.getString("NhanVienLapHoadon");
                    doanhThuList.add(row);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý lỗi SQL tại đây (ví dụ: thông báo lỗi, ghi log, ...)
        }

        return doanhThuList;
    }

}
