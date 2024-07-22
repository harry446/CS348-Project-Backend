package com.cs348.backendservice.repository;

import com.cs348.backendservice.constants.DatabaseConstants;
import com.cs348.backendservice.exceptions.UserNotFoundException;
import com.cs348.backendservice.model.LotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Lot {
    @Autowired
    private DatabaseConstants constant;

    public List<LotResponse> getLotInfo() {
        Connection connection = null;
        PreparedStatement ps = null;
        List<LotResponse> res = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(constant.url, constant.username, constant.password);

            // update the like num
            String query = "SELECT l.lid, l.lot_name, ANY_VALUE(s.latitude) AS latitude, ANY_VALUE(s.longitude) AS longitude " +
                    "FROM lots l, spots s " +
                    "Where l.lid = s.lid " +
                    "GROUP BY l.lid, l.lot_name;";
            ps = connection.prepareStatement(query);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LotResponse r = new LotResponse(rs.getInt("lid"), rs.getString("lot_name"), rs.getFloat("latitude"), rs.getFloat("longitude"));
                    res.add(r);
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
        return res;
    }
}
