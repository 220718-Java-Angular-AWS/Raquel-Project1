package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.User;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeLoginServlet extends HttpServlet {


    private ObjectMapper mapper;

    private UserService service;


    @Override
    public void init() throws ServletException {
        System.out.println("logIn servlet initializing...");
        this.service = new UserService();
        this.mapper = new ObjectMapper();
        System.out.println("logIn servlet initialized!");
    }
    @Override
    public void destroy() {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("USERNAME");
        String password = req.getParameter("PASSWORD");
        User user = service.logIn(username, password);
        String json = mapper.writeValueAsString(user);
        resp.getWriter().println(json);
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }}