/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myproject.pojo;

/**
 *
 * @author dell
 */
public class XeKhach {
    private String maXe;
    private String bienSoXe;
    private int soGhe;

    public XeKhach() {
    }

    public XeKhach(String maXe, String BienSoXe, int soGhe) {
        this.maXe = maXe;
        this.bienSoXe = BienSoXe;
        this.soGhe = soGhe;
    }
    
    /**
     * @return the maXe
     */
    public String getMaXe() {
        return maXe;
    }

    /**
     * @param maXe the maXe to set
     */
    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    /**
     * @return the BienSoXe
     */
    public String getBienSoXe() {
        return bienSoXe;
    }

    /**
     * @param BienSoXe the BienSoXe to set
     */
    public void setBienSoXe(String BienSoXe) {
        this.bienSoXe = BienSoXe;
    }

    /**
     * @return the soGhe
     */
    public int getSoGhe() {
        return soGhe;
    }

    /**
     * @param soGhe the soGhe to set
     */
    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }
    
    @Override
    public String toString(){
        return this.maXe;
    }
}
