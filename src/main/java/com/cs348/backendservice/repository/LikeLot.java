package com.cs348.backendservice.repository;

import com.cs348.backendservice.constants.DatabaseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component

public class LikeLot {
    @Autowired
    private DatabaseConstants constant;

    public void like(int lid, int bid, int uid) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);

            // update the like num
            String updateLikeNumQuery = "UPDATE lots SET like_num = like_num+1 WHERE lid = ? " +
                    "AND lid IN (SELECT lid FROM Bookings WHERE uid = ? " +
                    "AND status = True AND end_time < CURRENT_TIMESTAMP AND liked = False)\n";
            ps = connection.prepareStatement(updateLikeNumQuery);
            ps.setInt(1, lid);
            ps.setInt(2, uid);
            ps.executeUpdate();

            // update booking liked status
            int status = ps.executeUpdate();
            String updateBookingQuery = "UPDATE bookings SET liked = True WHERE bid = ?;";
            ps = connection.prepareStatement(updateBookingQuery);
            ps.setInt(1, bid);
            ps.executeUpdate();

            connection.commit(); // Commit transaction
            System.out.println("like_num updated successfully for lid: " + lid);

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

    }
}
