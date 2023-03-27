/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.pojo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import java.sql.Date;



/**
 *
 * @author dell
 */
public class ChuyenDi {
    private int maChuyenDi;
    private int maXe;
    private int giaVe;
    private Date ngayKhoiHanh;
    private String gioKhoiHanh;
    private String diemKhoiHanh;
    private String diemKetThuc;
    private int soGheTrong;
    private int soGheDat;
    private String trangThai;


    /**
     * @return the gioKhoiHanh
     */
    public String getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    /**
     * @param gioKhoiHanh the gioKhoiHanh to set
     */
    public void setGioKhoiHanh(String gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }
    
    public ChuyenDi() {
    }

    public ChuyenDi(int maChuyenDi, int maXe, int giaVe, Date ngayKhoiHanh, 
            String gioKhoiHanh, String diemKhoiHanh, String diemKetThuc, 
            int soGheTrong, int soGheDat, String trangThai) {
        this.maChuyenDi = maChuyenDi;
        this.maXe = maXe;
        this.giaVe = giaVe;
        this.ngayKhoiHanh = ngayKhoiHanh;
        this.gioKhoiHanh = gioKhoiHanh;
        this.diemKhoiHanh = diemKhoiHanh;
        this.diemKetThuc = diemKetThuc;
        this.soGheTrong = soGheTrong;
        this.soGheDat = soGheDat;
        this.trangThai = trangThai;
    }

    

    /**
     * @return the giaVe
     */
    public int getGiaVe() {
        return giaVe;
    }

    /**
     * @param giaVe the giaVe to set
     */
    public void setGiaVe(int giaVe) {
        this.giaVe = giaVe;
    }
    
    /**
     * @return the maChuyenDi
     */
    public int getMaChuyenDi() {
        return maChuyenDi;
    }

    /**
     * @param maChuyenDi the maChuyenDi to set
     */
    public void setMaChuyenDi(int maChuyenDi) {
        this.maChuyenDi = maChuyenDi;
    }

    /**
     * @return the maXe
     */
    public int getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    /**
     * @return the thoiGianKhoiHanh
     */
    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    /**
     * @param ngayKhoiHanh the ngayKhoiHanh to set
     */
    public void setNgayKhoiHanh(Date ngayKhoiHanh) {
        this.ngayKhoiHanh = ngayKhoiHanh;
    }

    /**
     * @return the diemKhoiHanh
     */
    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    /**
     * @param diemKhoiHanh the diemKhoiHanh to set
     */
    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    /**
     * @return the diemKetThuc
     */
    public String getDiemKetThuc() {
        return diemKetThuc;
    }

    /**
     * @param diemKetThuc the diemKetThuc to set
     */
    public void setDiemKetThuc(String diemKetThuc) {
        this.diemKetThuc = diemKetThuc;
    }

    /**
     * @return the soGheTrong
     */
    public int getSoGheTrong() {
        return soGheTrong;
    }

    /**
     * @param soGheTrong the soGheTrong to set
     */
    public void setSoGheTrong(int soGheTrong) {
        this.soGheTrong = soGheTrong;
    }

    /**
     * @return the soGheDat
     */
    public int getSoGheDat() {
        return soGheDat;
    }

    /**
     * @param soGheDat the soGheDat to set
     */
    public void setSoGheDat(int soGheDat) {
        this.soGheDat = soGheDat;
    }
    

    /**
     * @return the trangThai
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
