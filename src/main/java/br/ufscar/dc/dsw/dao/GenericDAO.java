package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;

public interface GenericDAO <T>
{
    int insert(T t);

    T get(int id);
    
    List<T> getAll();
    
    int update(T t);

    int delete(T t);
}