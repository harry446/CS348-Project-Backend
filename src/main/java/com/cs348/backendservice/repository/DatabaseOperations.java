package com.cs348.backendservice.repository;
import java.sql.*;

public class DatabaseOperations {

    public static void connectionToDatabase(String url, String username, String password) {
        System.out.println("Connecting to database ...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver class is loaded
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Database connected!");
            }
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public static void insertRow(String url, String username, String password,
                                 String tableName, String name, String description,
                                 String note, double latitude, double longitude) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);

            String query = String.format("INSERT INTO %s (name, description, note, latitude, longitude) " +
                    " VALUES (?, ?, ?, ?, ?);", tableName);
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, note);
            ps.setDouble(4, latitude);
            ps.setDouble(5, longitude);

            int status = ps.executeUpdate();

            if (status != 0) {
                System.out.println("Database was Connected");
                System.out.println("Record WAS INSERTED");
                //System.out.println(getRowCount(connection, tableName));
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

    // Method to remove a row
    public static void removeRow(String url, String username, String password, String tableName, String name) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);

            String query = String.format("DELETE FROM %s WHERE name = ?;", tableName);
            ps = connection.prepareStatement(query);
            ps.setString(1, name);

            int status = ps.executeUpdate();

            if (status != 0) {
                System.out.println("Record WAS DELETED");
                //System.out.println(getRowCount(connection, tableName));
            } else {
                System.out.println("No matching record found to delete.");
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

    // Method to print the contents of the database
    public static void printDatabase(Connection connection, String tableName) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(String.format("SELECT * FROM %s;", tableName));

            System.out.println("********** Printing the content of database **********");
            int rowCount = getRowCount(connection, tableName);
            System.out.println("Number of rows in table " + tableName + ": " + rowCount);

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") +
                        ", Description: " + rs.getString("description") +
                        ", Note: " + rs.getString("note") +
                        ", Latitude: " + rs.getDouble("latitude") +
                        ", Longitude: " + rs.getDouble("longitude"));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }

    // Method to get the row count
    public static int getRowCount(Connection connection, String tableName) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowCount = 0;

        try {
            String query = String.format("SELECT COUNT(*) FROM %s;", tableName);
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                rowCount = rs.getInt(1);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return rowCount;
    }

    public static String fetchField(String url, String username, String password,
                                    String tableName, String fieldName, String condition) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);

            String query = String.format("SELECT %s FROM %s WHERE %s;", fieldName, tableName, condition);
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getString(fieldName);
            }

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void sortTable(String url, String username, String password,
                                 String tableName, String attribute) {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);

            String query = String.format("SELECT * FROM %s ORDER BY %s ASC;", tableName, attribute);
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
