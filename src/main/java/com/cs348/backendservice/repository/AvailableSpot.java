package com.cs348.backendservice.repository;

import com.cs348.backendservice.constants.DatabaseConstants;
import com.cs348.backendservice.exceptions.UserNotFoundException;
import com.cs348.backendservice.model.AvailableSpotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AvailableSpot {
    @Autowired
    private DatabaseConstants constant;
    @PersistenceContext
    private EntityManager entityManager;

    public void trivialUpdate(int uid) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);
//            connection.setAutoCommit(false); // Start transaction

            String query = "UPDATE users SET booking_num = (SELECT COUNT(*) FROM bookings WHERE uid=? AND end_time>CURRENT_TIMESTAMP AND status = 1) WHERE uid=?;";
            ps = connection.prepareStatement(query);
            ps.setInt(1, uid);
            ps.setInt(2, uid);

            ps.executeUpdate();

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
    public int getCurBookingCount(int uid) throws UserNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);
            connection.setAutoCommit(false); // Start transaction

            String query = "SELECT booking_num FROM users WHERE uid = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, uid);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("booking_num");  // Return the booking number
                } else {
                    throw new UserNotFoundException("User not found for UID: " + uid);
                }
            }

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

        return -1;
    }


    public List<Object[]> getAvailable(int uid, String location, String start, String end, float duration, String type) {

        String sql = "SELECT l.lid, l.like_num, s.sid, s.price, s.parking_type, l.lot_name " +
                "FROM lots l, spots s, users u " +
                "WHERE s.lid = l.lid AND l.lot_name = ?1 AND u.uid = ?2 AND " +
                    "(s.sid, l.lid) NOT IN (SELECT sid, lid FROM bookings WHERE GREATEST(start_time, ?3) < LEAST(end_time, ?4) AND status = 1) AND " +
                    "(s.parking_type != 'accessible' OR u.is_accessible = TRUE) AND " +
                    "(s.parking_type != 'permit' OR EXISTS (SELECT * FROM permit_holders p WHERE p.uid = ?5 AND p.expiry_date >= ?6)) AND " +
                    "(IFNULL(s.max_stay, 168) >= ?7) AND " +
                    "(s.parking_type != ?8) " +
                "ORDER BY l.lid ASC;";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, location);
        query.setParameter(2, uid);
        query.setParameter(3, start);
        query.setParameter(4, end);
        query.setParameter(5, uid);
        query.setParameter(6, end);
        query.setParameter(7, duration);
        query.setParameter(8, type);

        List<Object[]> result = query.getResultList();
        return result;
    }

    public List<Object[]> getAll(String location) {

        String sql = "SELECT l.lid, s.sid, s.price, s.parking_type " +
                "FROM lots l, spots s " +
                "WHERE s.lid = l.lid AND l.lot_name = ?1 " +
                "ORDER BY l.lid ASC;";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, location);

        List<Object[]> result = query.getResultList();
        return result;
    }


}
