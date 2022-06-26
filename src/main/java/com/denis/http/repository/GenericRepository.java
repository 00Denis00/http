package com.denis.http.repository;

import java.util.List;

public interface GenericRepository <T, ID>
{
    T findById(ID id);
    T save(T t);
    T update(T t);
    void delete(T t);
    List<T> findAll();
}
