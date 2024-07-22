package com.cs348.backendservice.repository;

import com.cs348.backendservice.constants.DatabaseConstants;
import com.cs348.backendservice.exceptions.UsernameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class CreateAccount {

    @Autowired
    private DatabaseConstants constant;

    public void insertRow(String username, String password, String email, String phone, String identity, boolean is_accessible) throws UsernameExistsException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);
            connection.setAutoCommit(false); // Start transaction

            // Insert new user
            String insertQuery = "INSERT INTO users (username, password, email, phone, identity, booking_num, is_accessible) " +
                    "VALUES (?, ?, ?, ?, ?, 0, ?);";
            ps = connection.prepareStatement(insertQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, identity);
            ps.setBoolean(6, is_accessible);
            ps.executeUpdate();


            connection.commit(); // Commit transaction

        } catch (ClassNotFoundException | SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new UsernameExistsException("username exists: " + username);
            }
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
        System.out.println("Account created with username: " + username);
    }

}
