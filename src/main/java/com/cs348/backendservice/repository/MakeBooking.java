package com.cs348.backendservice.repository;
import java.sql.*;

public class MakeBooking {
public static String url;
public static String username;
public static String password;

    public static void insertRow(int bid, int uid, int lid, int sid, String create_time,
                                 String start_time, String end_time, float price, boolean status) {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);

            String query = "INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            ps = connection.prepareStatement(query);
            ps.setInt(1, bid);
            ps.setInt(2, uid);
            ps.setInt(3, lid);
            ps.setInt(4, sid);
            ps.setString(5, create_time);
            ps.setString(6, start_time);
            ps.setString(7, end_time);
            ps.setFloat(8, price);
            ps.setBoolean(9, status);

            int resultStatus = ps.executeUpdate();

            if (resultStatus != 0) {
                System.out.println("Database was Connected");
                System.out.println("Record WAS INSERTED");

                String condition = "uid = " + uid;
                String bookingNumStr = DatabaseOperations.fetchField(url, username, password, "users", "booking_num", condition);
                if (bookingNumStr != null) {
                    int bookingNum = Integer.parseInt(bookingNumStr);
                    bookingNum += 1;
                    String setClause = "booking_num = " + bookingNum;
                    DatabaseOperations.updateRow(url, username, password, "users", setClause, condition);
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
    }


}
