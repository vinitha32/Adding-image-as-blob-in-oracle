package com.example.demo;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.FilePermission;
import java.sql.ResultSet;

public class DemoController {
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static String username = "test5";
    static String password = "password";

    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        String sql = "INSERT INTO pictures image VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "cat.jpg");
        FilePermission permission = new FilePermission("C:/Users/dc-user/Desktop/image", "read");
        File image = new File("C:/Users/dc-user/Desktop/image/apple.png");
        FileInputStream   fis = new FileInputStream(image);
        stmt.setBinaryStream(1,fis,(int) image.length());
        stmt.execute();
        conn.commit();
        fis.close();
        conn.close();
        System.out.println("success");
    }
}