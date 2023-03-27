/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import com.myproject.conf.Utils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.VeXe;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.TicketService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class MainStaffScreenController implements Initializable {
    static ChuyenDiService c = new ChuyenDiService();
    static TicketService ticket = new TicketService();
    @FXML private TableView<ChuyenDi> tableChuyenDi;
    @FXML private TableView<VeXe> tableVeXe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            this.loadTableColumns();
            this.loadTableData();
        } catch (SQLException ex) {
            Logger.getLogger(ListTourAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void loadTableColumns() {
        TableColumn colMaChuyen = new TableColumn("Mã chuyến");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        
        TableColumn colGiaVe = new TableColumn("Giá vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        
        TableColumn colNgayKhoiHanh = new TableColumn("Ngày khởi hành");
        colNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("ngayKhoiHanh"));
        
        TableColumn colGioKhoiHanh = new TableColumn("Giờ khởi hành");
        colGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        
        TableColumn colDiemKhoiHanh = new TableColumn("Điểm khởi hành");
        colDiemKhoiHanh.setCellValueFactory(new PropertyValueFactory("diemKhoiHanh"));
        
        TableColumn colDiemKetThuc = new TableColumn("Điểm kết thúc");
        colDiemKetThuc.setCellValueFactory(new PropertyValueFactory("diemKetThuc"));
        
        TableColumn colSoGheTrong = new TableColumn("Ghế trống");
        colSoGheTrong.setCellValueFactory(new PropertyValueFactory("soGheTrong"));
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        
        TableColumn colXemVe = new TableColumn();
              colXemVe.setCellFactory(evt -> {
            Button btn = new Button("Xem vé");
            btn.setOnAction(e -> {
                try {
                    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//                    App.setRoot("ListTicket");
                    TableCell cell = (TableCell) btn.getParent();
                    ChuyenDi q = (ChuyenDi) cell.getTableRow().getItem();
                    try {   
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("ListTicket.fxml"));
                        Parent ticketView = loader.load();
                        Scene scene = new Scene(ticketView);
                        ListTicketController controller = loader.getController();
                        int id = q.getMaChuyenDi();
                        controller.loadTableDataTicket(id);
                        stage.setScene(scene);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            TableCell cellXem = new TableCell();
            cellXem.setGraphic(btn);
            return cellXem;
        });
        
        TableColumn colDatVe = new TableColumn();
            colDatVe.setCellFactory(evt -> {
                Button btn2 = new Button("Đặt vé");
                btn2.setOnAction(e -> {
                    try {
                        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                        TableCell cell = (TableCell) btn2.getParent();
                        ChuyenDi q = (ChuyenDi) cell.getTableRow().getItem();
                        try {   
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("Booking.fxml"));
                            Parent bookingView = loader.load();
                            Scene scene = new Scene(bookingView);
                            BookingController controller = loader.getController();
                            int id = q.getMaChuyenDi();
                            controller.loadBookingForm(id);
                            stage.setScene(scene);
                        } catch (SQLException ex) {
                            Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            TableCell cellXem = new TableCell();
            cellXem.setGraphic(btn2);
            return cellXem;
        });
        
        this.tableChuyenDi.getColumns().addAll(colMaChuyen ,colGiaVe, 
                colNgayKhoiHanh, colGioKhoiHanh, colDiemKhoiHanh, colDiemKetThuc,
                colSoGheTrong, colTrangThai,colXemVe, colDatVe);
    }
    
    private void loadTableData() throws SQLException {
        List<ChuyenDi> chuyendi = c.getChuyenDi();
        
        this.tableChuyenDi.getItems().clear();
        this.tableChuyenDi.setItems(FXCollections.observableList(chuyendi));
    }
    
    public void ActionXemVe(ActionEvent ac) throws IOException {
        App.setRoot("ListTicket");
    }
    
    
    public void ActionDatVe(ActionEvent ac) throws IOException {
        App.setRoot("Booking");
    }
    
//    private void loadTableDataTicket(int id) throws SQLException {
//        List<VeXe> ve = ticket.getVeTheoMa(id);
//        
//        this.tableVeXe.setItems(FXCollections.observableList(ve));
//    }
                
//    private void loadTableTicketColumns() {
//        TableColumn colTen = new TableColumn("Ten");
//        colTen.setCellValueFactory(new PropertyValueFactory("tenKhachHang"));
//        
//        TableColumn colSdt = new TableColumn("Số điện thoạt");
//        colSdt.setCellValueFactory(new PropertyValueFactory("sdt"));
//        
//        TableColumn colNgayDat = new TableColumn("Ngày đặt");
//        colNgayDat.setCellValueFactory(new PropertyValueFactory("ngayDat"));
//        
//        TableColumn colViTriGhe = new TableColumn("Số ghế");
//        colViTriGhe.setCellValueFactory(new PropertyValueFactory("viTriGhe"));
//        
//        TableColumn colTrangThai = new TableColumn("Trạng thái");
//        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
//        
//        tableVeXe.getColumns().addAll(colTen, colSdt, colNgayDat, colViTriGhe, colTrangThai);
//    }
}
