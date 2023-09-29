package com.example.week_6;
import java.sql.*;

public class JDBCTest {
    public static void main(String[] args) throws SQLException {
        String username = "Iyke";
        String password = "Lawntennis1.";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/week6",username, password);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

        System.out.printf("%-5s%-18s%-18s%-18s%-12s\n","ID","NAME","EMAIL","PASSWORD","PHONE_NUMBER");

        while (resultSet.next()){
            int id = resultSet.getInt("customer_id");
            String name = resultSet.getString("customer_name");
            String email = resultSet.getString("email");
            String pass = resultSet.getString("password");
            String phone = resultSet.getString("phone");

            System.out.printf("%-5s%-18s%-18s%-18s%-12s\n",id,name,email,pass,phone);
        }
    }
}
