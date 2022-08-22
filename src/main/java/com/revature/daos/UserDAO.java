package com.revature.daos;


import com.revature.pojos.User;
import com.revature.services.DatasourceService;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO implements DatasourceCRUD<User> {

    Connection connection;

    public UserDAO() {
        this.connection = DatasourceService.getConnection();
    }

    public UserDAO(String s, String sa, String sa1) {
    }


    @Override
    public void create(User user) {
        try {
            String sql = "INSERT INTO ERS.USERS ( FIRSTNAME, LASTNAME, USER_USERNAME, USER_PASSWORD) VALUES (?, ?, ?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, user.getFIRSTNAME());
            pstmt.setString(2, user.getLASTNAME());
            pstmt.setString(3, user.getUSERNAME());
            pstmt.setString(4, user.getPASSWORD());


            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                Integer key = keys.getInt("USER_ID");
                user.setUSER_ID(key);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(int id) {
        User user = new User();
        try {
            String sql = "SELECT * FROM ERS.USERS WHERE USER_ID = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet results = pstmt.executeQuery();


            if (results.next()) {
                user.setUSER_ID(results.getInt("USER_ID"));
                user.setFIRSTNAME(results.getString("FIRSTNAME"));
                user.setLASTNAME(results.getString("LASTNAME"));
                user.setPASSWORD(results.getString("USER_USERNAME"));
                user.setUSERNAME(results.getString("USER_PASSWORD"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    public User logInUser(String USERNAME, String PASSWORD) {
        User userLogin = new User();
        try {
            System.out.println("loggin in");
            String sql = "SELECT * FROM ERS.USERS WHERE USER_USERNAME= ? AND USER_PASSWORD = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,USERNAME);
            pstmt.setString(2,PASSWORD);
            ResultSet results = pstmt.executeQuery();


            if(results.next()) {
                userLogin.setUSER_ID(results.getInt("USER_ID"));
                userLogin.setFIRSTNAME(results.getString("FIRSTNAME"));
                userLogin.setLASTNAME(results.getString("LASTNAME"));
                userLogin.setUSERNAME(results.getString("USER_USERNAME"));
                userLogin.setPASSWORD(results.getString("USER_PASSWORD"));
               // userLogin.setAdmin(results.getBoolean("is_Admin"));



            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userLogin;

    }




    @Override
    public List<User> readAll() {
        List<User> userList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM ERS.USERS";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();


            while (results.next()) {
                User user = new User();

                user.setFIRSTNAME(results.getString("FIRSTNAME"));
                user.setLASTNAME(results.getString("LASTNAME"));
                user.setUSERNAME(results.getString("USER_USERNAME"));
                user.setPASSWORD(results.getString("USER_PASSWORD"));
                user.setUSER_ID(results.getInt("USER_ID"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void update(User user) {

        try {
            String sql = "UPDATE ERS.USERS SET FIRSTNAME = ?, LASTNAME = ?, USER_USERNAME = ?, USER_PASSWORD = ?, WHERE USER_ID = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getFIRSTNAME());
            pstmt.setString(1, user.getLASTNAME());
            pstmt.setString(3, user.getUSERNAME());
            pstmt.setString(3, user.getPASSWORD());
            pstmt.setInt(4, user.getUSER_ID());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String sql = "DELETE FROM ERS.USERS WHERE USER_ID = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void destroy() {
        
    }

    @Override
    public void init() {

    }


    public User getByUserId(int userid, Connection con) {
        return null;
    }



}