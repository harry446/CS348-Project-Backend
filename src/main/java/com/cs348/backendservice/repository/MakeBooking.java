package com.cs348.backendservice.repository;
import com.cs348.backendservice.constants.DatabaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MakeBooking {

    @Autowired
    private DatabaseConstants constant;

    public void insertRow(int uid, int lid, int sid, String start_time, String end_time, double price) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);
            connection.setAutoCommit(false); // Start transaction

            // Insert booking
            String insertQuery = "INSERT INTO bookings (uid, lid, sid, start_time, end_time, price, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, 1);";
            ps = connection.prepareStatement(insertQuery);
            ps.setInt(1, uid);
            ps.setInt(2, lid);
            ps.setInt(3, sid);
            ps.setString(4, start_time);
            ps.setString(5, end_time);
            ps.setDouble(6, price);
            ps.executeUpdate();

            // Update user booking number
            String updateQuery = "UPDATE users SET booking_num = booking_num + 1 WHERE uid = ?";
            ps = connection.prepareStatement(updateQuery);
            ps.setInt(1, uid);
            ps.executeUpdate();

            connection.commit(); // Commit transaction
            System.out.println("Booking created with uid: " + uid);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) connection.rollback(); // Roll back transaction on error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (connection != null) try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public int checkAvailability(int lid, int sid, String start_time, String end_time) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);

            // update the like num
            String query = "SELECT COUNT(*) as c \n" +
                    "FROM bookings \n" +
                    "WHERE lid = ? AND sid = ? AND GREATEST(start_time, ?) <= LEAST(end_time, ?) AND status = 1;";
            ps = connection.prepareStatement(query);
            ps.setInt(1, lid);
            ps.setInt(2, sid);
            ps.setString(3, start_time);
            ps.setString(4, end_time);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("c");
                }
            }

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
