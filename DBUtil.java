package com.mbprotech.controller;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/user_webapp", "Misto", "123456");
    }
}
