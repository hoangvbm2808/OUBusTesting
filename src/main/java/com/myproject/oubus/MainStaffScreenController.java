/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import com.myproject.pojo.ChuyenDi;
import com.myproject.services.ChuyenDiService;
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
import javafx.fxml.Initializable;
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


/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class MainStaffScreenController implements Initializable {
    static ChuyenDiService c = new ChuyenDiService();
    @FXML private TableView<ChuyenDi> tableChuyenDi;
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
        TableColumn colMaChuyen = new TableColumn("maChuyenDi");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        
        TableColumn colGiaVe = new TableColumn("giaVe");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        
        TableColumn colNgayKhoiHanh = new TableColumn("ngayKhoiHanh");
        colNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("ngayKhoiHanh"));
        
        TableColumn colGioKhoiHanh = new TableColumn("gioKhoiHanh");
        colGioKhoiHanh.setCellValueFactory(new PropertyValueFactory("gioKhoiHanh"));
        
        TableColumn colDiemKhoiHanh = new TableColumn("diemKhoiHanh");
        colDiemKhoiHanh.setCellValueFactory(new PropertyValueFactory("diemKhoiHanh"));
        
        TableColumn colDiemKetThuc = new TableColumn("diemKetThuc");
        colDiemKetThuc.setCellValueFactory(new PropertyValueFactory("diemKetThuc"));
        
        TableColumn colSoGheTrong = new TableColumn("soGheTrong");
        colSoGheTrong.setCellValueFactory(new PropertyValueFactory("soGheTrong"));
        
        TableColumn colTrangThai = new TableColumn("trangThai");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        
//        TableColumn colDel = new TableColumn();
//        colDel.setCellFactory(e -> {
//            Button btn = new Button("Đặt vé");
//            
//            btn.setOnAction(evt -> { try {
//                App.setRoot("ListTicket");
//                } catch (IOException ex) {
//                    Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//});
//            return true;
//                Alert a = MessageBox.getBox("Question", "Are you sure to delete this question?", Alert.AlertType.CONFIRMATION);
//                a.showAndWait().ifPresent(res -> {
//                    if (res == ButtonType.OK) {
//                        Button b = (Button) evt.getSource();
//                        TableCell cell = (TableCell) b.getParent();
//                        Question q = (Question) cell.getTableRow().getItem();
//                        
//                        try {
//                            if (s.deleteQuestion(q.getId()) == true) {
//                                MessageBox.getBox("Question", "Delete successful", Alert.AlertType.INFORMATION).show();
//                                this.loadTableData(null);
//                            } else
//                                MessageBox.getBox("Question", "Delete failed", Alert.AlertType.ERROR).show();
//                        } catch (SQLException ex) {
//                            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                });
//        });
        
        this.tableChuyenDi.getColumns().addAll(colMaChuyen ,colGiaVe, 
                colNgayKhoiHanh, colGioKhoiHanh, colDiemKhoiHanh, colDiemKetThuc,
                colSoGheTrong, colTrangThai);
    }
    
    private void loadTableData() throws SQLException {
        List<ChuyenDi> chuyendi = c.getChuyenDi();
        
        this.tableChuyenDi.getItems().clear();
        this.tableChuyenDi.setItems(FXCollections.observableList(chuyendi));
    }
}
