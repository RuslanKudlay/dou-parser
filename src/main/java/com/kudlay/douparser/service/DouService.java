package com.kudlay.douparser.service;

import com.kudlay.douparser.model.Vacancy;
import com.kudlay.douparser.repositories.DouRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DouService {
    @Autowired
    private DouRepo vacancyRepo;

    public List<Vacancy> getVacancies() {
        return vacancyRepo.findAll();
    }

    public Vacancy saveVacancies(Vacancy vacancy) {
        return vacancyRepo.save(vacancy);
    }
}
