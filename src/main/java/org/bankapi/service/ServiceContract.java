package org.bankapi.service;

import org.bankapi.exceptions.EntityNotFoundException;

import java.util.List;

public interface ServiceContract<T> {
    void save(T t);

    T findById(long id);

    List<T> findAll();

    void deleteById(long id) throws EntityNotFoundException;
}
