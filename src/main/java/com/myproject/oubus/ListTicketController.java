/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.myproject.oubus;

import static com.myproject.oubus.MainStaffScreenController.ticket;
import com.myproject.pojo.VeXe;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author vbmho
 */
public class ListTicketController implements Initializable {
    private int id;
    @FXML private TableView<VeXe> tableVeXe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableTicketColumns();
    }    
    
    public void loadTableTicketColumns() {
        TableColumn colTen = new TableColumn("Ten");
        colTen.setCellValueFactory(new PropertyValueFactory("tenKhachHang"));
        colTen.setPrefWidth(200);
        
        TableColumn colSdt = new TableColumn("Số điện thoạt");
        colSdt.setCellValueFactory(new PropertyValueFactory("sdt"));
        colSdt.setPrefWidth(200);
        
        TableColumn colNgayDat = new TableColumn("Ngày đặt");
        colNgayDat.setCellValueFactory(new PropertyValueFactory("ngayDat"));
        colNgayDat.setPrefWidth(180);
        
        TableColumn colViTriGhe = new TableColumn("Số ghế");
        colViTriGhe.setCellValueFactory(new PropertyValueFactory("viTriGhe"));
        colViTriGhe.setPrefWidth(150);
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        colTrangThai.setPrefWidth(200);
        
        tableVeXe.getColumns().addAll(colTen, colSdt, colNgayDat, colViTriGhe, colTrangThai);
    }
    
    public void loadTableDataTicket(int id) throws SQLException {
        List<VeXe> ve = ticket.getVeTheoMa(id);
        this.tableVeXe.setItems(FXCollections.observableList(ve));
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
