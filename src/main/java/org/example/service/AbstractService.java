package org.example.service;

import java.util.List;

public interface AbstractService<T> {

    List<T> findAll();

    T findById(int id);

    T save(T t);

    T update(int pos, T t);

    boolean delete(int id);
}
