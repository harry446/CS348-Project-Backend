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


    public List<AvailableSpotResponse.ParkingLot> getAvailable(int uid, String location, String start, String end, float duration, String type) throws UserNotFoundException {

        String sql = "SELECT l.lid, l.like_num, s.sid, s.price, s.parkingType" +
                "FROM lots l, spots s, users u" +
                "WHERE s.lid = l.lid AND l.area = ?1 AND u.uid = ?2" +
                    "s.sid NOT IN (SELECT sid FROM bookings WHERE GREATEST(start_time, ?3) <= LEAST(end_time, ?4) AND status = 1) AND " +
                    "(s.parking_type != ‘accessible’ OR u.isAccessible = TRUE) AND " +
                    "(s.parking_type != ‘permit’ OR EXISTS (SELECT * FROM permit_holders p WHERE p.uid = ?5 AND p.expiry_date >= ?6)) AND " +
                    "(s.max_stay >= ?7) AND " +
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
        List<AvailableSpotResponse.ParkingLot> lots = new ArrayList<>();

        for (Object[] k : result) {
            if (lots.isEmpty()) {
                List<AvailableSpotResponse.ParkingLot.ParkingSpot> s = new ArrayList<>();
                AvailableSpotResponse.ParkingLot l = new AvailableSpotResponse.ParkingLot((int) k[0], (int) k[1], s);
                lots.add(l);
            }

            if (lots.get(lots.size()-1).getLid() != (int) k[0]) {       // new lot number
                List<AvailableSpotResponse.ParkingLot.ParkingSpot> s = new ArrayList<>();
                AvailableSpotResponse.ParkingLot l = new AvailableSpotResponse.ParkingLot((int) k[0], (int) k[1], s);
                lots.add(l);
            }

            AvailableSpotResponse.ParkingLot.ParkingSpot s = new AvailableSpotResponse.ParkingLot.ParkingSpot((int) k[2], (float) k[3], (String) k[4], true);
            lots.get(lots.size()-1).spots.add(s);
        }

//        result.forEach(row -> {
//            String lid = (String) row[0];
//            String likeNum = (String) row[1];
//            String sid = (String) row[2];
//            String price = (String) row[3];
//            String parkingType = (String) row[4];
//            boolean isAvailable = (boolean) row[5];
//
//            AvailableSpotResponse.ParkingLot.ParkingSpot spot = new AvailableSpotResponse.ParkingLot.ParkingSpot(sid, price, parkingType, isAvailable);
//            List<AvailableSpotResponse.ParkingLot.ParkingSpot> spots = new ArrayList<>();
//            spots.add(spot);
//
//            AvailableSpotResponse.ParkingLot lot = new AvailableSpotResponse.ParkingLot(lid, likeNum, spots);
//            lots.add(lot);
//        });

        return lots;
//        Connection connection = null;
//        PreparedStatement ps = null;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);
//            connection.setAutoCommit(false); // Start transaction
//
//            String query = "SELECT l.lid, l.like_num, s.sid, s.price, s.parkingType\n" +
//                    "FROM lots l, spots s\n" +
//                    "WHERE s.lid = l.lid AND l.area = ?\n" +
//                    "   s.sid NOT IN (SELECT sid FROM bookings \t\t\n" +
//                    "WHERE GREATEST(start_time, ?) <= LEAST(end_time, ?) AND status = 1) AND\n" +
//                    "\t   (s.parking_type != ‘accessible’ OR u.isAccessible = True) AND\n" +
//                    "\t   (s.parking_type != ‘permit’ OR \n" +
//                    "EXISTS (SELECT * FROM permit_holders p\n" +
//                    "\t\t\tWHERE p.uid = ? AND \n" +
//                    "p.expiry_date >= ?)) AND\n" +
//                    "\t   (s.max_stay >= ?) AND \n" +
//                    "\t   (s.parking_type != ?)\n";
//            ps = connection.prepareStatement(query);
//            ps.setString(1, location);
//            ps.setString(2, start);
//            ps.setString(3, end);
//            ps.setInt(4, uid);
//            ps.setString(5, end);
//            ps.setFloat(6, duration);
//            ps.setString(7, type);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getInt("booking_num");  // Return the booking number
//                } else {
//                    throw new UserNotFoundException("User not found for UID: " + uid);
//                }
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            try {
//                if (connection != null) connection.rollback(); // Roll back transaction on error
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        } finally {
//            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
//            if (connection != null) try { connection.close(); } catch (SQLException e) { e.printStackTrace(); }
//        }
//
//        return -1;
    }


}
