package com.myproject.oubus;

import com.myproject.conf.Utils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.services.ChuyenDiService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

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
    
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    
    @FXML private Text maChuyenDi;
    
    StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter
                = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }

        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadTableColumns();
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(ListTourAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    private void loadTableColumns() {
        TableColumn colMaChuyen = new TableColumn("Mã chuyến");
        colMaChuyen.setCellValueFactory(new PropertyValueFactory("maChuyenDi"));
        
        TableColumn colMaXe = new TableColumn("Mã xe");
        colMaXe.setCellValueFactory(new PropertyValueFactory("maXe"));
        
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
        
        TableColumn colSoGheDat = new TableColumn("Ghế đặt");
        colSoGheDat.setCellValueFactory(new PropertyValueFactory("soGheDat"));
        
        TableColumn colTrangThai = new TableColumn("Trạng thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        
        this.tableChuyenDi.getColumns().addAll(colMaChuyen, colMaXe ,colGiaVe, 
                colNgayKhoiHanh, colGioKhoiHanh, colDiemKhoiHanh, colDiemKetThuc,
                colSoGheTrong, colSoGheDat, colTrangThai);
    }
    
    private void loadTableData(String kw) throws SQLException {
        List<ChuyenDi> chuyendi = c.getChuyenDi(kw);
        
        this.tableChuyenDi.getItems().clear();
        this.tableChuyenDi.setItems(FXCollections.observableList(chuyendi));
    }
    
    
//    quay ve trang GD Nguoiquanly
    public void actionQuayVe(ActionEvent event) throws IOException {
         FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("MainAdminScreen.fxml"));
                                Scene scene = new Scene(fxmloader.load());
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                                Button btn = (Button) event.getSource();
                                Stage stagelogin = (Stage) btn.getScene().getWindow();
                                stagelogin.close();
    }
    
    //them chuyen di
    public void add(ActionEvent e) throws SQLException {
        Time time = Time.valueOf(tgKhoiHanhField.getText() + ":00");
        System.out.print("------------ " + String.valueOf(time));
        ChuyenDi tour = new ChuyenDi(Integer.parseInt(giaVeField.getText()), 
                noiDiField.getText(),noiDenField.getText(), Date.valueOf(ngayKhoiHanhField.getValue()),
                time, Integer.parseInt(maXeField.getText()));
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
            loadTableData(null);
//            tableChuyenDi.ad;
            maXeField.setText("");
            noiDiField.setText("");
            noiDenField.setText("");
            ngayKhoiHanhField.setValue(null);
            tgKhoiHanhField.setText("");
            giaVeField.setText("");
            maChuyenDi.setText("");
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Add failed" + ex.getMessage());
            alert.show();
        }
        
    }
    
    
    
    //xoa chuyen di
    public void delete(ActionEvent e) throws SQLException {
        ChuyenDi t = tableChuyenDi.getSelectionModel().getSelectedItem();
//        Time time = Time.valueOf(tgKhoiHanhField.getText() + ":00");
        if (c.deleteTour(t.getMaChuyenDi()) == true) {
            Utils.getBox("Delete successful", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            maXeField.setText("");
            noiDiField.setText("");
            noiDenField.setText("");
            ngayKhoiHanhField.setValue(null);
            tgKhoiHanhField.setText("");
            giaVeField.setText("");
            maChuyenDi.setText("");
        } else
            Utils.getBox("Delete failed", Alert.AlertType.ERROR).show();
    }
    
    
    public void getTour(MouseEvent event) {
        ChuyenDi t = tableChuyenDi.getSelectionModel().getSelectedItem();
        maXeField.setText(String.valueOf(t.getMaXe()));
        noiDiField.setText(t.getDiemKhoiHanh());
        noiDenField.setText(t.getDiemKetThuc());
        ngayKhoiHanhField.setValue(LocalDate.parse(String.valueOf(t.getNgayKhoiHanh())));
        tgKhoiHanhField.setText(String.valueOf(t.getGioKhoiHanh()));
        giaVeField.setText(String.valueOf(t.getGiaVe()));
        maChuyenDi.setText(String.valueOf(t.getMaChuyenDi()));
        
    }
    
    
    //cap nhat chuyen di
    public void update(ActionEvent e) throws SQLException {
        int giaVe = Integer.parseInt(giaVeField.getText());
        String noiDi = noiDiField.getText();
        String noiDen = noiDenField.getText();
        Date ngayKH = Date.valueOf(ngayKhoiHanhField.getValue());
        String tg = tgKhoiHanhField.getText();
        int maXe = Integer.parseInt(maXeField.getText());
        int maChuyen = Integer.parseInt(this.maChuyenDi.getText());
        if (c.updateTour(maChuyen, giaVe, noiDi, noiDen, ngayKH, tg)==true) {
            Utils.getBox("Update successful", Alert.AlertType.INFORMATION).show();
            this.loadTableData(null);
            maXeField.setText("");
            noiDiField.setText("");
            noiDenField.setText("");
            ngayKhoiHanhField.setValue(null);
            tgKhoiHanhField.setText("");
            giaVeField.setText("");
            maChuyenDi.setText("");
        } else
            Utils.getBox("Update failed", Alert.AlertType.ERROR).show();
    }
    
    
}