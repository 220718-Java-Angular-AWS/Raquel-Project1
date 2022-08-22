package com.revature.daos;

import com.revature.pojos.Request;
import com.revature.pojos.User;

import java.sql.Connection;
import java.util.List;



public interface DatasourceCRUD<T> {


    //CRUD


    void create(T t);
    T read(int id);
    List<T> readAll();
    void update(T t);
    void delete(int id);


    void destroy();

    void init();
}
