package com.cs348.backendservice.repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseOperations {

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

    public static void updateRow(String url, String username, String password,
                                 String tableName, String setClause, String condition) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);

            String query = String.format("UPDATE %s SET %s WHERE %s;", tableName, setClause, condition);
            ps = connection.prepareStatement(query);

            int status = ps.executeUpdate();

            if (status != 0) {
                System.out.println("Record WAS UPDATED");
            } else {
                System.out.println("No matching record found to update.");
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

    public static List<Map<String, Object>> listTable(String url, String username, String password, String tableName) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> rows = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);

            String query = String.format("SELECT * FROM %s;", tableName);
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                rows.add(row);
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

        return rows;
    }

    public static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
