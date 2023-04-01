/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.VeXe;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author vbmho
 */
public class BookingService {
    public boolean checkTextField(String Ten, String SDT, String DiemDon){
        if(Ten == "" || SDT == "" || DiemDon == "")
            return false;
        return true;
    }
    public boolean checkSDT(String sdt){
        Integer b = Integer.parseInt(sdt.substring(1));
        if(sdt.length() == 10 && b % 1 == 0)
            return true;
        return false;
    }
    
    public void addVeXe(VeXe v) throws SQLException {
        
          try ( Connection conn = jdbcUtils.getConn()) {
            conn.setAutoCommit(false);
            String sql = "INSERT INTO vexe(id, tenKhachHang, ngayDat, sdt, viTriGhe, trangThai ,maChuyenDi ,maNhanVien, maDoanhThu, diemDon)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, v.getMaVe());
            stm.setString(2, v.getTenKhachHang());
            stm.setDate(3, v.getNgayDat());
            stm.setString(4, v.getSdt());
            stm.setString(5, v.getViTriGhe());
            stm.setString(6, v.getTrangThai());
            stm.setInt(7, v.getMaChuyenDi());
            stm.setInt(8, v.getMaNhanVien());
            stm.setInt(9, v.getMaDoanhThu());
            stm.setString(10,v.getDiemDon());
            stm.executeUpdate();
            
            //khi thêm thì số ghế trống của chuyến đi sẽ giảm 1 và số ghế đặt tăng 1
            String sql2 = "UPDATE chuyendi SET soGheTrong = soGheTrong-1, soGheDat = SoGheDat +1 WHERE id = ?";
            PreparedStatement stm2 = conn.prepareCall(sql2);
            stm2.setInt(1, v.getMaChuyenDi());
            stm2.executeUpdate();
            
            conn.commit();
        }
    }
}