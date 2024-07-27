package br.ufscar.dc.dsw.dao;

import java.util.List;

public interface GenericDAO <T>
{
    int insert(T t);

    T get(String uuid);
    
    List<T> getAll();
    
    int update(T t);

    int delete(T t);
}