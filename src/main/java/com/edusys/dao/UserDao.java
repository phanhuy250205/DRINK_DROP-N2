/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.User;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author TranTrongDi
 */
public class UserDao extends MainDao<User, String>{
    final String INSERT_SQL = "INSERT INTO Users(TenDN, MatKhau, VaiTro) VALUES(?,?,?)";
    final String UPDATE_SQL = "UPDATE Users SET MatKhau=?, VaiTro=? WHERE TenDN =? ";
    final String DELETE_SQL = "DELETE FROM Users Where TenDN= ?";
    final String SELECT_ALL_SQL = "SELECT * From Users";
    final String SELECT_BY_ID_SQL = "SELECT * FROM Users where TenDN = ?";
    
    @Override
    public void insert(User entity) {
        JdbcHelper.update(INSERT_SQL, entity.getTenDN(),entity.getMatKhau(), entity.isVaiTro());
    }

    @Override
    public void update(User entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMatKhau(),entity.isVaiTro(),entity.getTenDN());
    }

    
    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<User> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public User selectById(String id) {
        List<User> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<User> selectBySql(String sqlString, Object... args) {
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sqlString, args);
            while (rs.next()) {
                User entity = new User();
                entity.setTenDN(rs.getString("TenDN"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
