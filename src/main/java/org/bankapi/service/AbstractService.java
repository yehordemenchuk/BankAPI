package org.bankapi.service;

import lombok.Getter;
import org.bankapi.exceptions.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Getter
public abstract class AbstractService<T> implements ServiceContract<T> {
    private final JpaRepository<T, Long> repository;

    protected AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public void save(T t) {
        repository.save(t);
    }

    @Override
    public T findById(long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Entity not found");

        repository.deleteById(id);
    }
}
