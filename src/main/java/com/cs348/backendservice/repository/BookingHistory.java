package com.cs348.backendservice.repository;

import com.cs348.backendservice.model.BookingHistoryResponse;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingHistory {
    @PersistenceContext
    private EntityManager entityManager;

    public List<BookingHistoryResponse> bookingHistory(int uid) {
        String sql = "WITH temp AS (" +
                "SELECT l.area, l.address, l.lot_name, s.parking_type, " +
                "b.create_time, b.start_time, b.end_time, b.price, " +
                "CASE WHEN (b.status=1 AND b.end_time < CURRENT_TIMESTAMP) THEN 'expired' " +
                "ELSE (CASE WHEN b.status=1 THEN 'booked' ELSE 'cancelled' END) END AS status, b.liked, l.like_num, l.lid  " +
                "FROM bookings b " +
                "JOIN lots l ON b.lid = l.lid " +
                "JOIN spots s ON b.lid = s.lid AND b.sid = s.sid " +
                "WHERE b.uid = :uid) " +
                "SELECT * FROM temp ORDER BY start_time DESC";
        return getBookingHistory(sql, uid);
    }

    public List<BookingHistoryResponse> bookingHistory_priceDesc(int uid) {
        String sql = "WITH temp AS (" +
                "SELECT l.area, l.address, l.lot_name, s.parking_type, " +
                "b.create_time, b.start_time, b.end_time, b.price, " +
                "CASE WHEN (b.status=1 AND b.end_time < CURRENT_TIMESTAMP) THEN 'expired' " +
                "ELSE (CASE WHEN b.status=1 THEN 'booked' ELSE 'cancelled' END) END AS status, b.liked, l.like_num, l.lid  " +
                "FROM bookings b " +
                "JOIN lots l ON b.lid = l.lid " +
                "JOIN spots s ON b.lid = s.lid AND b.sid = s.sid " +
                "WHERE b.uid = :uid) " +
                "SELECT * FROM temp ORDER BY price DESC";
        return getBookingHistory(sql, uid);
    }

    public List<BookingHistoryResponse> bookingHistory_priceAsc(int uid) {
        String sql = "WITH temp AS (" +
                "SELECT l.area, l.address, l.lot_name, s.parking_type, " +
                "b.create_time, b.start_time, b.end_time, b.price, " +
                "CASE WHEN (b.status=1 AND b.end_time < CURRENT_TIMESTAMP) THEN 'expired' " +
                "ELSE (CASE WHEN b.status=1 THEN 'booked' ELSE 'cancelled' END) END AS status, b.liked, l.like_num, l.lid  " +
                "FROM bookings b " +
                "JOIN lots l ON b.lid = l.lid " +
                "JOIN spots s ON b.lid = s.lid AND b.sid = s.sid " +
                "WHERE b.uid = :uid) " +
                "SELECT * FROM temp ORDER BY price ASC";
        return getBookingHistory(sql, uid);
    }

    public List<BookingHistoryResponse> bookingHistory_likeNumDesc(int uid) {
        String sql = "WITH temp AS (" +
                "SELECT l.area, l.address, l.lot_name, s.parking_type, " +
                "b.create_time, b.start_time, b.end_time, b.price, " +
                "CASE WHEN (b.status=1 AND b.end_time < CURRENT_TIMESTAMP) THEN 'expired' " +
                "ELSE (CASE WHEN b.status=1 THEN 'booked' ELSE 'cancelled' END) END AS status, b.liked, l.like_num, l.lid " +
                "FROM bookings b " +
                "JOIN lots l ON b.lid = l.lid " +
                "JOIN spots s ON b.lid = s.lid AND b.sid = s.sid " +
                "WHERE b.uid = :uid) " +
                "SELECT * FROM temp ORDER BY like_num DESC";
        return getBookingHistory(sql, uid);
    }

    public List<BookingHistoryResponse> bookingHistory_likeNumAsc(int uid) {
        String sql = "WITH temp AS (" +
                "SELECT l.area, l.address, l.lot_name, s.parking_type, " +
                "b.create_time, b.start_time, b.end_time, b.price, " +
                "CASE WHEN (b.status=1 AND b.end_time < CURRENT_TIMESTAMP) THEN 'expired' " +
                "ELSE (CASE WHEN b.status=1 THEN 'booked' ELSE 'cancelled' END) END AS status, b.liked, l.like_num, l.lid " +
                "FROM bookings b " +
                "JOIN lots l ON b.lid = l.lid " +
                "JOIN spots s ON b.lid = s.lid AND b.sid = s.sid " +
                "WHERE b.uid = :uid) " +
                "SELECT * FROM temp ORDER BY like_num ASC";
        return getBookingHistory(sql, uid);
    }


    private List<BookingHistoryResponse> getBookingHistory(String s, int uid) {
        Query query = entityManager.createNativeQuery(s);
        query.setParameter("uid", uid);
        List<Object[]> result = query.getResultList();

        List<BookingHistoryResponse> responses = new ArrayList<>();
        for (Object[] row : result) {
            float price = 0.0f;
            if (row[7] != null) {
                price = (Float) row[7];
            }
            responses.add(new BookingHistoryResponse(
                    (String) row[0],
                    (String) row[1],
                    (String) row[2],
                    (String) row[3],
                    (Timestamp) row[4],
                    (Timestamp) row[5],
                    (Timestamp) row[6],
                    price,
                    (String) row[8],
                    (boolean) row[9],
                    (int) row[10],
                    (int) row[11]
            ));
        }
        return responses;
    }


}