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
                        rs.getDate("ngayKhoiHanh"), rs.getString("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"),
                        rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
                chuyendi.add(c);
            }
        }
        return chuyendi;
    }
}
