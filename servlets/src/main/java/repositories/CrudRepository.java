package repositories;

import models.User;

import java.util.List;

public interface CrudRepository<T>{
    List<T> findAll();
    T findById(Long id);
    void save(T entity);
}
