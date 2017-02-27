package com.h2;

import java.sql.*;

/**
 * Created by yuandengfeng on 2017/2/21.
 */
public class Main2 {

    public static void main(String[] args) throws Exception {

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://192.168.0.251/home/kt/databaseh2/wenshu3", "sa", "sa");
        // add application code here
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("CREATE TABLE TEST_MEM(ID INT PRIMARY KEY,NAME VARCHAR(255));");
        stmt.executeUpdate("INSERT INTO TEST_MEM VALUES(1, 'Hello_Mem');");
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST_MEM");
        while(rs.next()) {
            System.out.println(rs.getInt("ID")+","+rs.getString("NAME"));
        }
        conn.close();


    }





}



