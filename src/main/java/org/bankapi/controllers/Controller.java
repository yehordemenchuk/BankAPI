package org.bankapi.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface Controller<T> {
    @PostMapping("/save")
    void save(@RequestBody T t);

    @GetMapping("/{id}")
    T findById(@PathVariable long id);

    @GetMapping("/all")
    List<T> findAll();

    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable long id);
}
