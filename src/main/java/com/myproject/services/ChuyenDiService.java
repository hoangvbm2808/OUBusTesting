/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.ChuyenDi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thanh
 */
public class ChuyenDiService {
     public List<ChuyenDi> getChuyenDi() throws SQLException {
        List<ChuyenDi> chuyendi = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM chuyendi");
            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"), 
                        rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"),
                        rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
                chuyendi.add(c);
            }
        }
        return chuyendi;
    }
     
     public ChuyenDi getChuyenDiByMaChuyenDi(int MaChuyenDi) throws SQLException{
        ChuyenDi result = null;
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE  id = ?");
            stm.setInt(1, MaChuyenDi);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                
                result  = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"),  rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                       rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"), rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
            }
        }
        return result; 
    }
     
     public boolean addTour(ChuyenDi c) throws SQLException {
        try ( Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO chuyendi(giaVe, ngayKhoiHanh, gioKhoiHanh, diemKhoiHanh, diemKetThuc, maXe) VALUES(?, ?, ?, ?, ?, ?)"; // sql injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, c.getGiaVe());
            stm.setDate(2, c.getNgayKhoiHanh());
            stm.setTime(3,c.getGioKhoiHanh());
            stm.setString(4, c.getDiemKhoiHanh());
            stm.setString(5, c.getDiemKetThuc());
            stm.setInt(6, c.getMaXe());
            int r = stm.executeUpdate();

            try {
                conn.commit();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
    }
    
}
