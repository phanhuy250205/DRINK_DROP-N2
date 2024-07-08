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
public class Giaohangdao extends MainDao<Giaohang , String>{
    final String insert_SQl="INSERT into GIAOHANG(MaGiaoHang ,LoaiNuoc, MaSP, DiaChiGiao, TenNguoiGiao, SDTNguoiGiao,"
	    + " TenNguoiNhan, SDTNguoiNhan ,TinhTrangGiaoHang) VALUES (?,?,?,?,?,?,?,?,?)";
    final String update_SQl="UPDATE GIAOHANG set LoaiNuoc = ?, MaSP = ? ,DiaChiGiao = ?, TenNguoiGiao = ?,"
	    + " SDTNguoiGiao = ?, TenNguoiNhan = ?,SDTNguoiNhan = ?,TinhTrangGiaoHang = ?\n" +
"WHERE MaGiaoHang = ?";
    final String delete_SQl="DELETE from GIAOHANG WHERE MaGiaoHang = ?";
    final String all_SQl="select * from GIAOHANG";
    final String select_By_id_SQl="select * from GIAOHANG WHERE MaGiaoHang = ?";


    @Override
    public void insert(Giaohang entity) {
        JdbcHelper.update(insert_SQl, entity.getMaGiaoHang(), entity.getLoaiNuoc(),entity.getMaSP(),entity.getDiaChiGiao(), entity.getTenNguoiGiao(),entity.getSDTNguoiGiao(),entity.getTenNguoiNhan(),entity.getSDTNguoiNhan(),entity.getTinhTrang());
    }

    @Override
    public void update(Giaohang entity) {
      JdbcHelper.update(update_SQl, entity.getLoaiNuoc(),entity.getMaSP(),entity.getDiaChiGiao(),entity.getTenNguoiGiao(),entity.getSDTNguoiGiao(),entity.getTenNguoiNhan(),entity.getSDTNguoiNhan(),entity.getTinhTrang(),entity.getMaGiaoHang());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(delete_SQl, id);
    }

    @Override
    public List<Giaohang> selectAll() {
       return  selectBySql(all_SQl);
    }

    @Override
    public Giaohang selectById(String id) {
         List<Giaohang> list =selectBySql(select_By_id_SQl, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Giaohang> selectBySql(String sql, Object... args) {
        List<Giaohang> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.querry(sql, args);
            while (rs.next()) {                
                Giaohang ennity = new Giaohang();
                ennity.setMaGiaoHang(rs.getString("MaGiaoHang"));
                ennity.setLoaiNuoc(rs.getString("LoaiNuoc"));
                ennity.setMaSP(rs.getString("MaSP"));
                ennity.setDiaChiGiao(rs.getString("DiaChiGiao"));
                ennity.setTenNguoiGiao(rs.getString("TenNguoiGiao"));
                ennity.setSDTNguoiGiao(rs.getString("SDTNguoiGiao"));
                ennity.setTenNguoiNhan(rs.getString("TenNguoiNhan"));
                ennity.setSDTNguoiNhan(rs.getString("SDTNguoiNhan"));
                ennity.setTinhTrang(rs.getString("TinhTrangGiaoHang"));
                list.add(ennity);
            }
        } catch (Exception e) {
            System.out.println("lo" + e);
        }
         return  list;
    }
    
}
