package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.revature.pojos.User;
import com.revature.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	UserService service;
    ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        System.out.println("User servlet initializing...");
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        System.out.println("User servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("USER_ID");
        String email = req.getParameter("USERNAME");
        String password = req.getParameter("PASSWORD");
        if(param == null && email == null && password ==null) {
            //Return all
            List<User> userList = service.getAllUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        } else if (param != null){
            //return the one the request wants
            Integer userId = Integer.parseInt(req.getParameter("USER_ID"));


            User user = service.getUser(userId);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }else {


            User user = service.checkUserLogin(email, password);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            builder.append(buffer.readLine());
        }
        String json = builder.toString();


        User newUser = mapper.readValue(json, User.class);
        service.saveUser(newUser);

        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
