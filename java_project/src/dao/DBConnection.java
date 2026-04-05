package dao;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");  // 🔥 THIS LINE FIXES IT

        String url = "jdbc:mysql://localhost:3306/resource_monitoring";
        String user = "root";
        String password = "Root@1234";

        return DriverManager.getConnection(url, user, password);
    }
}
