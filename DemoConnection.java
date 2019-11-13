package com.example.demo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DemoConnection {
    static String url = "jdbc:oracle:thin:@localhost:1521:XE";
    static String username = " "; # The one you created while setting up
    static String password = " "; # your password
    public static void main(String[] args) throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection(url, username, password);

        String sql = "SELECT photo FROM pictures ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {

           FilePermission permission = new FilePermission("C:/Users/dc-user/Desktop/image/pic","write");
            File image = new File("C:/Users/dc-user/Desktop/image/pic/apple.png");
            FileOutputStream fos = new FileOutputStream(image);
            byte[] buffer = new byte[1];
            InputStream is = resultSet.getBinaryStream(1);
            while (is.read(buffer) > 0) {
                fos.write(buffer);
            }
            fos.close();
        }
        conn.close();
    }
}
