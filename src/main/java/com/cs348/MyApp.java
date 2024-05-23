package src.main.java.com.cs348;
import java.sql.*;

public class MyApp {
    public static void main(String[] args) {
        System.out.println("hello world");

        String url = "jdbc:mysql://localhost:3306/sonoo";
        String username = "root";
        String password = "Kw14101106";

        System.out.println("Connecting database ...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        hello();
    }

    public static void hello() {
        System.out.println("Hello");
    }
}
