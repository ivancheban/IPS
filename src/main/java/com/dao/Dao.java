package com.dao;

import com.model.User;

import java.util.List;

public interface Dao<T> {
    public T create(T item) throws Exception; //save//signup //registration

    public T findByField(String value);

    public T update(T item);

    public boolean delete(int id);

    public List<T> findAll();


}
