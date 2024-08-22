package com.edusys.dao;

import com.edusys.utils.JdbcHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThongKedao {

    /**
     * Retrieves revenue data between the specified date range using the stored procedure.
     * 
     * @param dateFrom Start date of the range.
     * @param dateTo End date of the range.
     * @return List of Object arrays containing revenue data.
     */
   public List<Object[]> getDoanhthu1(java.util.Date dateFrom, java.util.Date dateTo) {
        List<Object[]> doanhThuList = new ArrayList<>();
        String sql = "{call sp_GetThongKe(?, ?)}";

        try (Connection conn = JdbcHelper.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            // Convert java.util.Date to java.sql.Date
            Date sqlDateFrom = new Date(dateFrom.getTime());
            Date sqlDateTo = new Date(dateTo.getTime());

            // In ra để kiểm tra giá trị
            System.out.println("SQL Date From: " + sqlDateFrom);
            System.out.println("SQL Date To: " + sqlDateTo);

            cstmt.setDate(1, sqlDateFrom);
            cstmt.setDate(2, sqlDateTo);

            try (ResultSet rs = cstmt.executeQuery()) {
                // Kiểm tra xem ResultSet có kết quả không
                if (!rs.isBeforeFirst()) {
                    System.out.println("No data found for the given date range.");
                }
                while (rs.next()) {
                    Object[] row = new Object[5]; // Number of columns in the result set
                    row[0] = rs.getDate("ThoiGianLap");
                    row[1] = rs.getInt("TongSoSanPham");
                    row[2] = rs.getFloat("TongTien");
                    row[3] = rs.getString("MaNhanVien");
                    row[4] = rs.getString("NhanVienLapHoadon");
                    doanhThuList.add(row);
                }
            }

        } catch (SQLException ex) {
            // Handle SQL exceptions
            ex.printStackTrace();
            // Additional error handling (logging, user feedback, etc.) can be added here
        }

        return doanhThuList;
    }

}

