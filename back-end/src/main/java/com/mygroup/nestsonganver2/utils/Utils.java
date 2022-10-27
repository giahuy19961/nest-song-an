/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygroup.nestsonganver2.utils;

import com.mygroup.nestsonganver2.dto.UserDTO;
import com.mygroup.nestsonganver2.entity.RoleEntity;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huy
 */
public class Utils {

    
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    
     public static Connection makeConnection() {
        Connection conn = null;
        try {
            String dbURL = "jdbc:sqlserver://nestsongan.cqtchuhryqsc.ap-southeast-1.rds.amazonaws.com;databaseName=NestSongAn";
            String user = "admin";
            String pass = "thisisadmin";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, user, pass);
            //System.out.println("Connect to DB successfully");
        } catch (Exception ex) {    

            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String hashPassWordMd5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
    
    public static String GetOTP(int length) {
        String number = "1234567890";
        Random ran = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int value = ran.nextInt(10);
            result.append(value);
        }
        return result.toString();
    }
//    expired=2022-10-0600:00:00|id=1|fullname=admin|role=admin
//    
//    public static void main(String[] args) {
//        UserDTO dto = new UserDTO() ;
//        dto.setId(1);
//        dto.setFullname("huyNguyen");
//        dto.setRole(new RoleEntity(1, "admin"));
//        String dtoToken = ConvertDTOtoToken(dto);
//        System.out.println(dtoToken);
//        String token = "expired=2022-10-06 00:00:00|id=1|fullname=admin|role=admin";
//        dto = convertTokentoDTO(token);
//        System.out.println(dto.getRole().getName());
//    }
//     

}
