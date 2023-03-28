/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.XeKhach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vbmho
 */
public class XeKhachService {
    public XeKhach getXeKhachByMaXe(String MaXe) throws SQLException {
        XeKhach results = null;
        try ( Connection conn = jdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM xekhach WHERE id = ?");
            stm.setString(1, MaXe);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                results = new XeKhach(rs.getInt("id"), rs.getString("BienSoXe"), rs.getInt("SoGhe"), rs.getInt("MaNhanVien"));
            }
        }
        return results;
    }
}