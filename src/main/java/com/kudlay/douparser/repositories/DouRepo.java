package com.kudlay.douparser.repositories;

import com.kudlay.douparser.model.Vacancy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DouRepo extends CrudRepository<Vacancy, Long> {
    List<Vacancy> findAll();
    Optional<Vacancy> findById(long id);
}
