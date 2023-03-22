package com.myproject.OUBus;

import com.myproject.pojo.ChuyenDi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.*;
import java.time.format.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.collections.FXCollections.*;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Thanh
 */
public class ListTourAdminController implements Initializable {
    static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         
        // TODO
//        tourList = FXCollections.observableArrayList(
//                new ChuyenDi("A01","TP Ho Chi Minh","Da Nang", "28/02/2023", "12h", 120000),
//                new ChuyenDi("A02","TP Ho Chi Minh","Dong Nai", "28/02/2023", "12h", 140000)
//        );
//        maColumn.setCellValueFactory(new PropertyValueFactory<ChuyenDi, String>("maChuyen"));
//        noiDiColumn.setCellValueFactory(new PropertyValueFactory<ChuyenDi, String>("noiDi"));
//        noiDenColumn.setCellValueFactory(new PropertyValueFactory<ChuyenDi, String>("noiDen"));
//        ngayKhoiHanhColumn.setCellValueFactory(new PropertyValueFactory<ChuyenDi, String>("ngayKhoiHanh"));
//        tgKhoiHanhColumn.setCellValueFactory(new PropertyValueFactory<ChuyenDi, String>("tgKhoiHanh"));
//        giaColumn.setCellValueFactory(new PropertyValueFactory<ChuyenDi, Double>("giaVe"));
//        table.setItems(tourList);
        
    }    
    
    
    //quay ve trang GD Nguoiquanly
    public void ActionQuayVe() throws IOException {
//        App.setRoot("MainAdminScreen");
    }
    
    //them chuyen di
    public void add(ActionEvent e) {
        ChuyenDi tour = new ChuyenDi();
        tour.setMaChuyenDi(maField.getText());
        tour.setMaXe(maXeField.getText());
        tour.setDiemKhoiHanh(noiDiField.getText());
        tour.setDiemKetThuc(noiDenField.getText());
        tour.setNgayKhoiHanh(ngayKhoiHanhField.getValue());
        LocalTime lt = LocalTime.parse(tgKhoiHanhField.getText(),formatter);
        tour.setGioKhoiHanh(lt);
        tour.setGiaVe(Integer.parseInt(giaVeField.getText()));      
        
        tourList.add(tour);
        maField.setText("");
        noiDiField.setText("");
        noiDenField.setText("");
//        ngayKhoiHanhField.setTime("");
        tgKhoiHanhField.setText("");
        giaVeField.setText("");
    }
    
    
    //xoa chuyen di
    public void delete(ActionEvent e) {
        ChuyenDi t = table.getSelectionModel().getSelectedItem();
            tourList.remove(t);
    }
    
    //cap nhat chuyen di
    public void update(ActionEvent e) {
        ChuyenDi tour = new ChuyenDi();
        ChuyenDi t = table.getSelectionModel().getSelectedItem();
        tour.setMaChuyenDi(t.getMaChuyenDi());
        tour.setDiemKhoiHanh(t.getDiemKhoiHanh());
        tour.setDiemKetThuc(t.getDiemKetThuc());
        tour.setNgayKhoiHanh(t.getNgayKhoiHanh());
        tour.setGioKhoiHanh(t.getGioKhoiHanh());
        tour.setGiaVe(t.getGiaVe());
    }
    
    @FXML 
    private TableView<ChuyenDi> table;
    
    @FXML
    private TableColumn<ChuyenDi, String> maColumn;
    
    @FXML
    private TableColumn<ChuyenDi, String> noiDiColumn;
    
    @FXML
    private TableColumn<ChuyenDi, String> noiDenColumn;
    
    @FXML
    private TableColumn<ChuyenDi, String> ngayKhoiHanhColumn;
    
    @FXML
    private TableColumn<ChuyenDi, Double> giaColumn;
    
    private ObservableList<ChuyenDi> tourList;
    
    @FXML
    private TextField maField;
    
    @FXML
    private TextField maXeField;
    
    @FXML
    private TextField noiDiField;
    
    @FXML
    private TextField noiDenField;
    
    @FXML
    private DatePicker ngayKhoiHanhField;
    
    @FXML    
    private TextField tgKhoiHanhField;
    
    @FXML
    private TextField giaVeField; 
    
}