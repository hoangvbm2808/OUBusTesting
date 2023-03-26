/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.services;

import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vbmho
 */
public class AccountService {
//    public List<Account> getAccounts() throws SQLException{
//        List<Account> results = new ArrayList<>();
//        try(Connection conn = jdbcUtils.getConn()){
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery("SELECT * FROM account");
//            
//            while(rs.next()){
//                Account a = new Account(rs.getInt("id"), rs.getString("TaiKhoan"), 
//                        rs.getString("MatKhau"), rs.getInt("MaQuyen"));
//                results.add(a);
//            }
//        }
//        return results;
//    }
    
    
    public Account getAccount(String tk) throws SQLException{
        Account results = null;
        try(Connection conn = jdbcUtils.getConn()){
            PreparedStatement stm = conn.prepareCall("SELECT * FROM account WHERE TaiKhoan = ?");
            stm.setString(1, tk);
                       
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                results = new Account(rs.getInt("id"), rs.getString("TaiKhoan"), 
                        rs.getString("MatKhau"), rs.getInt("MaQuyen"));
            }
        }
        return results;
    }
    
    
//    public Account checkAccount(){
//        return;
//    }
    
}
