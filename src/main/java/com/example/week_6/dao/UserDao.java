package com.example.week_6.dao;

import java.sql.*;
import com.example.week_6.model.*;

public class UserDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDao(Connection con) {
        this.con = con;
    }

    public User userLogin(String email, String password) {
        User user = null;
        try {
            query = "select * from users where email=? and password=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }

    public boolean isEmailRegistered(String email) {
        boolean emailExists = false;
        try {
            query = "select count(*) from users where email=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                emailExists = true;
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return emailExists;
    }

    public boolean insertUser(User user) {
        boolean result = false;
        try {
            query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return result;
    }
}
