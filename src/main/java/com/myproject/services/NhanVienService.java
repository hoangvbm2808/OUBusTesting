/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vbmho
 */
public class NhanVienService {

    public NhanVien getNhanVienByMaNV(String maNV) throws SQLException {
        NhanVien results = null;
        try (Connection conn = jdbcUtils.getConn()) {
//                String sql = "SELECT * FROM nhanvien WHERE id LIKE concat('%', '" + maNV + "', '%')";
//                PreparedStatement stm = conn.prepareStatement(sql);
//                ResultSet rs = stm.executeQuery(sql);
                    PreparedStatement stm = conn.prepareCall("SELECT * FROM nhanvien WHERE id LIKE concat('%',?,'%')");
                    stm.setString(1, maNV);

                    ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                results = new NhanVien(rs.getInt("id"),
                        rs.getString("TenNhanVien"), rs.getString("MaLoaiNhanVien"),
                        rs.getDate("NgaySinh"), rs.getString("SoDienThoai"),
                        rs.getString("CMND"), rs.getString("QueQuan"), rs.getInt("MaAccount"));
            }
        }
        return results;
    }
}
