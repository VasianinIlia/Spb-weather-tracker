package com.VasianinIlia;

import java.sql.*;

public class MySqlQuery {
    private static final String url = "jdbc:mysql://localhost:3306/app";
    private static final String user = "root";
    private static final String password = "Starwars2";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void selectAll(){
        String query = "select * from weather";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()){
                int id = rs.getInt(1);
                float temperature = rs.getFloat(2);
                String date = rs.getString(3);
                String time = rs.getString(4);
                System.out.println("id:" + id + ", Temperature: " + temperature +
                        "C, Date: " + date + ", Time: " + time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{con.close();} catch (SQLException se){se.printStackTrace();}
            try{stmt.close();} catch (SQLException se){se.printStackTrace();}
            try{rs.close();} catch (SQLException se){se.printStackTrace();}
        }
    }

    public static void insert(String temperature, String date, String time){
        String query = "insert into app.weather(temperature, date, time) " +
                "values(" + temperature + ", '" + date + "', '" + time + "')";
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            }
        catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try{con.close();} catch (SQLException se){se.printStackTrace();}
            try{stmt.close();} catch (SQLException se){se.printStackTrace();}
            try{rs.close();} catch (SQLException se){se.printStackTrace();}
        }
    }

}
