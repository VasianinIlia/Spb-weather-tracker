package com.VasianinIlia;

import java.sql.*;

public class MySqlQuery {
    private static final String url = "jdbc:mysql://localhost:3306/app";
    private static final String user = "root";
    private static final String password = "Starwars2";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static String selectAll(){
        String query = "select * from weather";
        String info = "";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){

                info = "id:" + rs.getString(1) + ", Temperature: " + rs.getString(2) +
                        "C, Date: " + rs.getString(3) + ", Time: " + rs.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }
}
