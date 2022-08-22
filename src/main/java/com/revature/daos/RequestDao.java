package com.revature.daos;



import com.revature.pojos.Request;

import com.revature.services.DatasourceService;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;



public class RequestDao implements DatasourceCRUD<Request> {

    Connection connection;

    public RequestDao() {
        connection = DatasourceService.getConnection();
    }

    @Override
    public void create(Request requests) {



        try {
            String sql = "INSERT INTO ERS.REQUEST (user_Id,req_comment,req_reason, req_amount,req_approved) VALUES( ?, ?, ?, ?,?);";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, requests.getUserId());
            pstmt.setString(2, requests.getComment());
            pstmt.setString(3, requests.getReason());
            pstmt.setDouble(4, requests.getAmount());
            pstmt.setString(5, requests.getApproved());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();



        }

    }


    @Override
    public Request read(int id) {



            Request req = new Request();
            try {
                String sql = "SELECT * FROM ERS.REQUEST WHERE req_Id = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1,id );
                ResultSet results = pstmt.executeQuery();


                if(results.next()) {
                    req.setReqId(results.getInt("req_Id"));
                    req.setUserId(results.getInt("user_Id"));
                    req.setComment(results.getString("req_comment"));
                    req.setReason(results.getString("req_reason"));
                    req.setAmount(results.getDouble("req_amount"));
                    req.setApproved(results.getString("req_approved"));


                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return req;

        }


    public List<Request> userOnly(Integer users){
        List<Request> reqList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM ERS.REQUEST WHERE user_Id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, users);
            ResultSet results = pstmt.executeQuery();



            while(results.next()) {
                Request req = new Request();
                req.setReqId(results.getInt("req_Id"));
                req.setUserId(results.getInt("user_Id"));
                req.setComment(results.getString("req_comment"));
                req.setReason(results.getString("req_reason"));
                req.setAmount(results.getDouble("req_amount"));
                req.setApproved(results.getString("req_approved"));

                reqList.add(req);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reqList;
    }
    @Override
    public List<Request> readAll() {



            List<Request> reqList = new LinkedList<>();
            try {
                String sql = "SELECT * FROM ERS.REQUEST";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet results = pstmt.executeQuery();



                while(results.next()) {
                    Request req = new Request();
                    req.setReqId(results.getInt("req_Id"));
                    req.setUserId(results.getInt("user_Id"));
                    req.setComment(results.getString("req_comment"));
                    req.setReason(results.getString("req_reason"));
                    req.setAmount(results.getDouble("req_amount"));
                    req.setApproved(results.getString("req_approved"));

                    reqList.add(req);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return reqList;
        }



    @Override
    public void update(Request request) {

        try {
            String sql = "UPDATE ERS.REQUEST SET user_Id = ?, req_comment = ?, req_reason = ?, req_amount = ?, req_approved = ? WHERE req_Id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, request.getUserId());
            pstmt.setString(2, request.getComment());
            pstmt.setString(3, request.getReason());
            pstmt.setDouble(4, request.getAmount());
            pstmt.setString(5, request.getApproved());
            pstmt.setInt(6, request.getReqId());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override


        public void delete(int reqID) {

            try{
                String sql = "DELETE FROM ERS.REQUEST WHERE req_Id = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, reqID);

                pstmt.executeUpdate();





            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

}



