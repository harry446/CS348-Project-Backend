package com.cs348.backendservice.repository;

import com.cs348.backendservice.model.BookingHistoryResponse;
import com.cs348.backendservice.model.UpcomingBookingsResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UpcomingBookings {

    @PersistenceContext
    private EntityManager entityManager;

    public List<UpcomingBookingsResponse> upcomingBookings(int uid) {
        String sql = "WITH temp AS (" +
                "SELECT l.area, l.address, l.lot_name, s.parking_type, " +
                "b.create_time, b.start_time, b.end_time, b.price, " +
                "CASE WHEN (b.status=1 AND b.end_time < CURRENT_TIMESTAMP) THEN 'expired' " +
                "ELSE (CASE WHEN b.status=1 THEN 'booked' ELSE 'cancelled' END) END AS status, b.liked, b.bid " +
                "FROM bookings b " +
                "JOIN lots l ON b.lid = l.lid " +
                "JOIN spots s ON b.lid = s.lid AND b.sid = s.sid " +
                "WHERE b.uid = :uid AND b.start_time > CURRENT_TIMESTAMP AND b.status != 0) " +
                "SELECT * FROM temp ORDER BY start_time ASC";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("uid", uid);
        List<Object[]> result = query.getResultList();

        List<UpcomingBookingsResponse> responses = new ArrayList<>();
        for (Object[] row : result) {
            responses.add(new UpcomingBookingsResponse(
                    (String) row[0],
                    (String) row[1],
                    (String) row[2],
                    (String) row[3],
                    (Timestamp) row[4],
                    (Timestamp) row[5],
                    (Timestamp) row[6],
                    (Float) row[7],
                    (String) row[8],
                    (boolean) row[9],
                    (int) row[10]
            ));
        }
        return responses;
    }
}


