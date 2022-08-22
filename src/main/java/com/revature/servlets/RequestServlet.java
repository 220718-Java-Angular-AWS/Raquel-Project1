package com.revature.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Request;
import com.revature.services.RequestService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class RequestServlet extends HttpServlet{

    RequestService service;
    ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        System.out.println("Request servlet initializing...");
        this.service = new RequestService();
        this.mapper = new ObjectMapper();
        System.out.println("Request servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("req_Id");
        String users = req.getParameter("user_Id");

        if(param == null&& users == null) {
            //Return all
            List<Request> reqList = service.getAllRequest();
            String json = mapper.writeValueAsString(reqList);
            resp.getWriter().println(json);
        } else if(param != null){
            //return the one the request wants
            Integer reqsId = Integer.parseInt(req.getParameter("req_Id"));

            Request request = service.getRequest(reqsId);
            String json = mapper.writeValueAsString(request);
            resp.getWriter().println(json);
        }else{
            Integer usersId = Integer.parseInt(req.getParameter("user_Id"));


            List<Request> listReq = service.getAllInUser((usersId));
            String json = mapper.writeValueAsString(listReq);
            resp.getWriter().println(json);
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder bob = new StringBuilder(); //bob da string builder
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            bob.append(buffer.readLine());
        }
        String json = bob.toString();


        Request newRequest = mapper.readValue(json, Request.class);
        service.saveRequest(newRequest);
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder bob = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()) {
            bob.append(buffer.readLine());
        }
        String json = bob.toString();


        Request newRequest = mapper.readValue(json, Request.class);
        service.updateRequest(newRequest);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("req_id");

        if(param == null) {
            //Return all

            resp.getWriter().println("Unable to delete user as the Request ID does not exist.");
        } else {
            //return the one the request wants
            Integer reqsIds = Integer.parseInt(req.getParameter("req_id"));

            service.deleteRequest(reqsIds);

            resp.getWriter().println("Deleted successfully!");
        }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}