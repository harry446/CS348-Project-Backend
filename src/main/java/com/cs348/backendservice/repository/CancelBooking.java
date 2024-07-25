package com.cs348.backendservice.repository;

import com.cs348.backendservice.constants.DatabaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class CancelBooking {

    @Autowired
    private DatabaseConstants constant;

    public void cancelBooking(int uid, int bid) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);
            connection.setAutoCommit(false); // Start transaction

            // Update the booking status to 0 (cancelled)
            String updateBookingStatusQuery = "UPDATE bookings SET status = 0 WHERE uid = ? AND bid = ?";
            ps = connection.prepareStatement(updateBookingStatusQuery);
            ps.setInt(1, uid);
            ps.setInt(2, bid);
            ps.executeUpdate();

            System.out.println("update book_num-1 for user " + uid);
            // Decrement the booking_num in users table
            String decrementBookingNumQuery = "UPDATE users SET booking_num = booking_num - 1 WHERE uid = ?";
            ps = connection.prepareStatement(decrementBookingNumQuery);
            ps.setInt(1, uid);
            ps.executeUpdate();

            connection.commit(); // Commit transaction
            System.out.println("Booking cancelled successfully for uid: " + uid + ", bid: " + bid + ".");

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

}
