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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
    @FXML private TextField noiDiField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            this.loadTableColumns();
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(ListTourAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.noiDiField.textProperty().addListener(e -> {
            try {
                this.loadTableData(this.noiDiField.getText());
            } catch (SQLException ex) {
                Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    public void loadTableColumns() {
        TableColumn colMaChuyen = new TableColumn("Mã chuyến");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        
        TableColumn colGiaVe = new TableColumn("Giá vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        
        TableColumn colNgayKhoiHanh = new TableColumn("Ngày Khởi Hành");
        colNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("ngayKhoiHanh"));
        colNgayKhoiHanh.setCellFactory(column -> {
            TableCell<ChuyenDi, Date> cell = new TableCell<ChuyenDi, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) 
                        setText(null);
                    else 
                        this.setText(format.format(item));
                }
            };

            return cell;
        });
        
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
            TableCell cellDat = new TableCell();
            cellDat.setGraphic(btn2);
            return cellDat;
        });
            
        TableColumn colBanVe = new TableColumn();
            colBanVe.setCellFactory(evt -> {
                Button btn3 = new Button("Bán vé");
                btn3.setOnAction(e -> {
                    try {
                        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                        TableCell cell = (TableCell) btn3.getParent();
                        ChuyenDi q = (ChuyenDi) cell.getTableRow().getItem();
                        try {   
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("SellTicket.fxml"));
                            Parent sellTicketView = loader.load();
                            Scene scene = new Scene(sellTicketView);
                            SellTicketController controller = loader.getController();
                            int id = q.getMaChuyenDi();
                            controller.loadSellTicket(id);
                            stage.setScene(scene);
                        } catch (SQLException ex) {
                            Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(MainStaffScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            TableCell cellBan = new TableCell();
            cellBan.setGraphic(btn3);
            return cellBan;
        });
        colBanVe.setPrefWidth(70);
        colDatVe.setPrefWidth(70);
        colXemVe.setPrefWidth(70);
        
        this.tableChuyenDi.getColumns().addAll(colMaChuyen ,colGiaVe, 
                colNgayKhoiHanh, colGioKhoiHanh, colDiemKhoiHanh, colDiemKetThuc,
                colSoGheTrong, colTrangThai,colXemVe, colDatVe, colBanVe);
    }
    
    private void loadTableData(String kw) throws SQLException {
        List<ChuyenDi> chuyendi = c.getChuyenDi(kw);
        
        this.tableChuyenDi.getItems().clear();
        this.tableChuyenDi.setItems(FXCollections.observableList(chuyendi));
    }
    
}