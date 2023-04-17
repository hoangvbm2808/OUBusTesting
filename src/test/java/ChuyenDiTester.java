import com.myproject.conf.jdbcUtils;
import com.myproject.pojo.ChuyenDi;
import com.myproject.pojo.VeXe;
import com.myproject.services.ChuyenDiService;
import com.myproject.services.TicketService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
//import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Time;


public class ChuyenDiTester {
    private static Connection conn;
    private static ChuyenDiService cd;
    private static TicketService ticket;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        cd = new ChuyenDiService();
        ticket = new TicketService();
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Truyen null
    @Test
    public void checkTimKiem() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi(null, null);
        List<ChuyenDi> dscd = new ArrayList<>();
        try (Connection conn = jdbcUtils.getConn()) {
            String sql = "SELECT * FROM chuyendi";
            PreparedStatement stm = conn.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ChuyenDi c = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"),
                        rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"),
                        rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
                dscd.add(c);
            }
            Assertions.assertEquals(dscd.size(), ds.size());
        }
    }

    //Truyen du lieu co trong ds
    @Test
    public void checkTimKiem2() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("HCM", null);
        Assertions.assertEquals(1, ds.size());
    }

    //Truyen du lieu so
    @Test
    public void checkTimKiem3() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("1", null);
        Assertions.assertEquals(0, ds.size());
    }

    //Truyen ki tu dac biet
    @Test
    public void checkTimKiem4() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("@TPHCM", null);
        Assertions.assertEquals(0, ds.size());
    }

    @Test
    public void checkTimKiem5() throws SQLException {
        List<ChuyenDi> ds = cd.getChuyenDi("Thanh pho Ho Chi Minh", null);
        Assertions.assertEquals(0, ds.size());
    }

    @Test
    public void checkMaChuyenDi() throws Exception {
        Assertions.assertNull(cd.getChuyenDiByMaChuyenDi(-1));
    }

    @Test
    public void checkMaChuyenDi2() throws Exception {
        ChuyenDi c = cd.getChuyenDiByMaChuyenDi(27);
        Assertions.assertEquals(27, c.getMaChuyenDi());
    }

    @Test
    public void checkAddChuyenDi() throws ParseException {
        ChuyenDi c = new ChuyenDi(200, 1, 123000,
                Date.valueOf(LocalDate.now()), Time.valueOf("17:00" + ":00"),
                "An Giang", "Tien Giang", 20,
                0, "Chua khoi hanh");
        try {
            cd.addTour(c);
            int MaChuyen = c.getMaChuyenDi();
            String diemKH = c.getDiemKhoiHanh();
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE id=?");
            stm.setInt(1, MaChuyen);

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(MaChuyen, rs.getInt("id"));
            Assertions.assertEquals("An Giang", rs.getString("diemKhoiHanh"));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void checkAddChuyenDi2() {
        ChuyenDi c = new ChuyenDi(201, 2, 130000,
                Date.valueOf(LocalDate.now()), Time.valueOf("17:00" + ":00"),
                "Ben Tre", "Tien Giang", 20,
                0, "Chua khoi hanh");
        try {
            cd.addTour(c);
            int MaChuyen = c.getMaChuyenDi();

            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE id= ?");
            stm.setInt(1, MaChuyen);

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(MaChuyen, rs.getInt("id"));
            Assertions.assertEquals(Time.valueOf("17:00" + ":00"), rs.getString("gioKhoiHanh"));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void checkAddChuyenDi3() {
        List<ChuyenDi> ds = new ArrayList<>();
        ChuyenDi c = new ChuyenDi(145000, "Ha Tinh",
                "Hue", Date.valueOf(LocalDate.now()),
                Time.valueOf("12:30" + ":00"), 1);

        try {
            cd.addTour(c);
            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE diemKhoiHanh = ?");
            stm.setString(1, c.getDiemKhoiHanh());

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            while (rs.next()) {
                ChuyenDi chuyenDi = new ChuyenDi(rs.getInt("id"), rs.getInt("maXe"), rs.getInt("giaVe"),
                        rs.getDate("ngayKhoiHanh"), rs.getTime("gioKhoiHanh"),
                        rs.getString("diemKhoiHanh"), rs.getString("diemKetThuc"),
                        rs.getInt("soGheTrong"), rs.getInt("soGheDat"), rs.getString("trangThai"));
                ds.add(chuyenDi);
            }
            for (ChuyenDi chuyen : ds) {
                Assertions.assertEquals(Time.valueOf("12:30" + ":00"), chuyen.getGioKhoiHanh());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //    truyen du lieu xoa
    @Test
    public void checkDeleteChuyenDi() {
        int id = 23;
        try {
            boolean actual = cd.deleteTour(id);
            Assertions.assertTrue(actual);

            String sql = "SELECT * FROM chuyendi WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            Assertions.assertTrue(rs.next());

            Assertions.assertFalse(ticket.deleteListTicket(id));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void checkDeleteChuyenDi2() {
        int id = 47;
        try {
            boolean actual = cd.deleteTour(id);
            Assertions.assertTrue(actual);

            String sql = "SELECT * FROM chuyendi WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            Assertions.assertTrue(rs.next());
            Assertions.assertFalse(ticket.deleteListTicket(47));
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void checkUpdateChuyenDi() {
        boolean actual = false;
        try {
            actual = cd.updateTour(61, 100000, "Hue", "Sai Gon", Date.valueOf(LocalDate.now()), String.valueOf(Time.valueOf("17:00" + ":00")));
            Assertions.assertTrue(actual);

            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE id= ?");
            stm.setInt(1, 61);

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(61, rs.getInt("id"));
            Assertions.assertEquals(Time.valueOf("17:00" + ":00"), rs.getTime("gioKhoiHanh"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateTrangThai() {
        boolean actual = false;
        try {
            actual = cd.updateTrangThaiTour(20);
            Assertions.assertTrue(actual);

            PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyendi WHERE id= ?");
            stm.setInt(1, 20);

            ResultSet rs = stm.executeQuery();
            Assertions.assertNotNull(rs.next());
            Assertions.assertEquals(20, rs.getInt("id"));
            Assertions.assertEquals("Đã khởi hành", rs.getString("trangThai"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

