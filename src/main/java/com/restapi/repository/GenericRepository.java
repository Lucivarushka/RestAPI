package com.restapi.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    void create(T value);

    void update(T value);

    void delete(T value);

    List<T> getAll();

    T getId(ID id);
}
