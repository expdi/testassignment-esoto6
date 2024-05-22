package com.edwinsoto.repository;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    List<T> findAll();

    Optional<T> findById(int id);

    T create(T t);

    T update(T t);

//    int[] update(List<T> t);

    int delete(int id);
}
