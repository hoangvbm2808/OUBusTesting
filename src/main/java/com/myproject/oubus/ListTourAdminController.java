package com.myproject.oubus;

import com.myproject.pojo.ChuyenDi;
import com.myproject.services.ChuyenDiService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.*;
import java.time.format.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.collections.FXCollections.*;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Thanh
 */
public class ListTourAdminController implements Initializable {
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
    static ChuyenDiService c = new ChuyenDiService();
    @FXML private TableView<ChuyenDi> tableChuyenDi;
    
//    @FXML private TableColumn<ChuyenDi, String> maColumn;
//    
//    @FXML private TableColumn<ChuyenDi, String> noiDiColumn;
//    
//    @FXML private TableColumn<ChuyenDi, String> noiDenColumn;
//    
//    @FXML private TableColumn<ChuyenDi, String> ngayKhoiHanhColumn;
//    
//    @FXML private TableColumn<ChuyenDi, Double> giaColumn;
    
//    private ObservableList<ChuyenDi> tourList;
//    
//    @FXML private TextField maField;
    
    @FXML private TextField maXeField;
    
    @FXML private TextField noiDiField;
    
    @FXML private TextField noiDenField;
    
    @FXML private DatePicker ngayKhoiHanhField;
    
    @FXML private TextField tgKhoiHanhField;
    
    @FXML private TextField giaVeField; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
        TableColumn colMaXe = new TableColumn("maXe");
        colMaXe.setCellValueFactory(new PropertyValueFactory("maXe"));
        
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
        
        TableColumn colSoGheDat = new TableColumn("soGheDat");
        colSoGheDat.setCellValueFactory(new PropertyValueFactory("soGheDat"));
        
        TableColumn colTrangThai = new TableColumn("trangThai");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        
        this.tableChuyenDi.getColumns().addAll(colMaChuyen, colMaXe ,colGiaVe, 
                colNgayKhoiHanh, colGioKhoiHanh, colDiemKhoiHanh, colDiemKetThuc,
                colSoGheTrong, colSoGheDat, colTrangThai);
    }
    
    private void loadTableData() throws SQLException {
        List<ChuyenDi> chuyendi = c.getChuyenDi();
        
        this.tableChuyenDi.getItems().clear();
        this.tableChuyenDi.setItems(FXCollections.observableList(chuyendi));
    }
    
    
//    quay ve trang GD Nguoiquanly
    public void actionQuayVe() throws IOException {
        App.setRoot("MainAdminScreen");
    }
    
    //them chuyen di
    public void add(ActionEvent e) throws SQLException {
        ChuyenDi tour = new ChuyenDi(Integer.parseInt(giaVeField.getText()), 
                noiDiField.getText(),noiDenField.getText(), Date.valueOf(ngayKhoiHanhField.getValue()),
                tgKhoiHanhField.getText(), Integer.parseInt(maXeField.getText()));
//         tour.setMaChuyenDi(maField.getText());
//        tour.setMaXe(Integer.parseInt(maXeField.getText()));
//        tour.setDiemKhoiHanh(noiDiField.getText());
//        tour.setDiemKetThuc(noiDenField.getText());
//        tour.setNgayKhoiHanh(Date.valueOf(ngayKhoiHanhField.getValue()));
////        LocalTime lt = LocalTime.parse(tgKhoiHanhField.getText(),formatter);
////        tour.setGioKhoiHanh(lt);
//        tour.getGioKhoiHanh(tgKhoiHanhField.getText());
//        tour.setGiaVe(Integer.parseInt(giaVeField.getText()));      
        try {
            c.addTour(tour);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Add successful");
            alert.show();
            loadTableData();
//            tableChuyenDi.ad;
            maXeField.setText("");
            noiDiField.setText("");
            noiDenField.setText("");
    //        ngayKhoiHanhField.setTime("");
            tgKhoiHanhField.setText("");
            giaVeField.setText("");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Add failed" + ex.getMessage());
            alert.show();
        }
        
    }
    
    
    //xoa chuyen di
    public void delete(ActionEvent e) {
//        ChuyenDi t = table.getSelectionModel().getSelectedItem();
//            tourList.remove(t);
    }
    
    //cap nhat chuyen di
    public void update(ActionEvent e) {
//        ChuyenDi tour = new ChuyenDi();
//        ChuyenDi t = table.getSelectionModel().getSelectedItem();
//        tour.setMaChuyenDi(t.getMaChuyenDi());
//        tour.setDiemKhoiHanh(t.getDiemKhoiHanh());
//        tour.setDiemKetThuc(t.getDiemKetThuc());
//        tour.setNgayKhoiHanh(t.getNgayKhoiHanh());
//        tour.setGioKhoiHanh(t.getGioKhoiHanh());
//        tour.setGiaVe(t.getGiaVe());
    }
    
}