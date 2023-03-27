/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import static com.myproject.oubus.MainStaffScreenController.ticket;
import com.myproject.pojo.Account;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.NhanVien;
import com.myproject.pojo.VeXe;
import com.myproject.pojo.XeKhach;
import com.myproject.services.AccountService;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.NhanVienService;
import com.myproject.services.XeKhachService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class BookingController implements Initializable {

    private int id;
    @FXML private Label maChuyenDi;
    @FXML private Label diemKhoiHanh;
    @FXML private Label diemKetThuc;
    @FXML private Label gioKhoiHanh;
    @FXML private Label bienSoXe;
    @FXML private Label nhanVien;
    @FXML private Label giaVe;
    
    private static final ChuyenDiService cd = new ChuyenDiService();
    private static final XeKhachService xk = new XeKhachService();
    private static final NhanVienService nv = new NhanVienService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
         
    public void loadBookingForm(int id) throws SQLException {
        ChuyenDi a = cd.getChuyenDiByMaChuyenDi(id);
        this.maChuyenDi.setText(String.valueOf(a.getMaChuyenDi()));
        this.diemKhoiHanh.setText(a.getDiemKhoiHanh());
        this.diemKetThuc.setText(a.getDiemKetThuc());
        this.gioKhoiHanh.setText(a.getGioKhoiHanh());
        this.giaVe.setText(String.valueOf(a.getGiaVe()));
        XeKhach b = xk.getXeKhachByMaXe(String.valueOf(a.getMaXe()));
        this.bienSoXe.setText(b.getBienSoXe());
        NhanVien c = nv.getNhanVienByMaNV(String.valueOf(b.getMaNhanVien()));
        this.nhanVien.setText(c.getTenNhanVien());
        
    }
    
}
