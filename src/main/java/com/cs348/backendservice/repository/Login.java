package com.cs348.backendservice.repository;
import com.cs348.backendservice.constants.DatabaseConstants;
import com.cs348.backendservice.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class Login {
    @Autowired
    private DatabaseConstants constant;

    public String retrievePass(String username) throws UserNotFoundException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);

            // update the like num
            String query = "SELECT password FROM users WHERE username = ?;";
            ps = connection.prepareStatement(query);
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");  // Return the booking number
                } else {
                    throw new UserNotFoundException("Username not found: " + username);
                }
            }


//            connection.commit(); // Commit transaction

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

        return "";
    }

}
