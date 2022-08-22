package com.revature.services;



import com.revature.daos.RequestDao;
import com.revature.pojos.Request;

import java.util.List;

public class RequestService {
    private RequestDao dao;

    public RequestService() {
        this.dao = new RequestDao();
    }

    public void saveRequest(Request request) {
        dao.create(request);
    }

    public Request getRequest(int id) {
        return dao.read(id);
    }
    public List<Request> getAllInUser(Integer users) {
        return dao.userOnly(users);
    }
    public List<Request> getAllRequest() {
        return dao.readAll();
    }

    public void updateRequest(Request request) {
        dao.update(request);
    }

    public void deleteRequest(int id) {
        dao.delete(id);

    }
}
