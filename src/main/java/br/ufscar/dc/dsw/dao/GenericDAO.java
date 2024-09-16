package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;

abstract public class GenericDAO <T>
{
    abstract void insert(T t);

    abstract T getById(int id);
    
    abstract List<T> getAll();
    
    abstract void update(T t);

    abstract void delete(T t);

    public GenericDAO()
    {
        try
        {   	
        	Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException
    {
        String url = "jdbc:mysql://localhost:3306/bikerent";

        return DriverManager.getConnection(url, "root", "root");
    }
}