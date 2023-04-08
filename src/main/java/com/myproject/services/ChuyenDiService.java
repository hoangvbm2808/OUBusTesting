/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.ChuyenDi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thanh
 */
public class ChuyenDiService {
    public List<ChuyenDi> getChuyenDi(String diemKH) throws SQLException {
        List<ChuyenDi> chuyendi = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM chuyendi";
            if (diemKH != null && !diemKH.isEmpty())
                sql += " WHERE diemKhoiHanh like concat('%', ?, '%')";
            PreparedStatement stm = conn.prepareCall(sql);
            if (diemKH != null && !diemKH.isEmpty())
                stm.setString(1, diemKH);
            ResultSet rs = stm.executeQuery();

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

    public ChuyenDi getChuyenDiByMaChuyenDi(int MaChuyenDi) throws SQLException {
        ChuyenDi result = null;
        try (Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE  id = ?");
            stm.setInt(1, MaChuyenDi);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                result = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"), rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"), rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
            }
        }
        return result;
    }

    public boolean addTour(ChuyenDi c) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO chuyendi(giaVe, ngayKhoiHanh, gioKhoiHanh, diemKhoiHanh, diemKetThuc, maXe) VALUES(?, ?, ?, ?, ?, ?)"; // sql injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, c.getGiaVe());
            stm.setDate(2, c.getNgayKhoiHanh());
            stm.setTime(3, c.getGioKhoiHanh());
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


    public boolean deleteTour(int id) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "DELETE FROM vexe WHERE maChuyenDi=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            String sql1 = "DELETE FROM chuyendi WHERE id=?";
            PreparedStatement stm1 = conn.prepareCall(sql1);
            stm1.setInt(1, id);

            return stm1.executeUpdate() > 0;
        }
    }

    public boolean updateTour(int maChuyenDi, int giaVe, String noiDi, String noiDen, Date ngayKH, String tg) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "UPDATE chuyendi SET giaVe=?, diemKhoiHanh=?, diemKetThuc=?, ngayKhoiHanh=?,gioKhoiHanh=? WHERE id=?"; // sql injection
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, giaVe);
            stm.setString(2, noiDi);
            stm.setString(3, noiDen);
            stm.setDate(4, ngayKH);
            stm.setString(5, tg);
            stm.setInt(6, maChuyenDi);
            int r = stm.executeUpdate();
//            return r;
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
