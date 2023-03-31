/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.VeXe;
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
public class TicketService {
    public List<VeXe> getVeTheoMa(int id) throws SQLException {
        List<VeXe> vexe = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM vexe WHERE maChuyenDi=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                VeXe ve = new VeXe( rs.getString("id"),rs.getString("tenKhachHang"), 
                        rs.getDate("ngayDat"), rs.getString("sdt"),
                        rs.getInt("maChuyenDi"), rs.getString("viTriGhe"),
                        rs.getString("trangThai"), 
                        rs.getInt("maNhanVien"), rs.getInt("maDoanhThu"), rs.getString("diemDon"));
                    vexe.add(ve);
            }
        }
        return vexe;
    }
    
    public VeXe getVeTheoMaVe(String id) throws SQLException {
        VeXe vexe = null;
        try (Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM vexe WHERE id=? ");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                vexe = new VeXe( rs.getString("id"),rs.getString("tenKhachHang"), 
                        rs.getDate("ngayDat"), rs.getString("sdt"),
                        rs.getInt("maChuyenDi"), rs.getString("viTriGhe"),
                        rs.getString("trangThai"), 
                        rs.getInt("maNhanVien"), rs.getInt("maDoanhThu"), rs.getString("diemDon"));
                    
            }
        }
        return vexe;
    }
    
    
    public void exportTicket(VeXe ve) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            String sql = "UPDATE vexe SET trangThai='Đã xuất' WHERE id =?;";     
            PreparedStatement stm1 = conn.prepareCall(sql);
            stm1.setString(1, ve.getMaVe());
            stm1.executeUpdate();
        
            conn.commit();
        }
    }
    
    public void deleteTicket(VeXe ve) throws SQLException {
        try (Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            
            PreparedStatement stm = conn.prepareCall("DELETE FROM vexe WHERE id = ?");
            stm.setString(1, ve.getMaVe());
            stm.executeUpdate();
            
            String sql = "UPDATE chuyendi SET SoGheTrong = SoGheTrong+1, SoGheDat = SoGheDat-1 WHERE id = ?";     
            PreparedStatement stm2 = conn.prepareCall(sql);
            stm2.setInt(1, ve.getMaChuyenDi());
            stm2.executeUpdate();
        
            conn.commit();
        }
    }
}

