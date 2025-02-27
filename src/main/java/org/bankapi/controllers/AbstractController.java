package org.bankapi.controllers;

import org.bankapi.service.ServiceContract;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractController<T> implements Controller<T> {
    private ServiceContract<T> service;

    protected AbstractController(ServiceContract<T> service) {
        this.service = service;
    }


    @Override
    public void save(@RequestBody T t) {
        service.save(t);
    }

    @Override
    public T findById(@PathVariable long id) {
        return service.findById(id);
    }

    @Override
    public List<T> findAll() {
        return service.findAll();
    }

    @Override
    public void deleteById(long id) {
        service.deleteById(id);
    }
}
