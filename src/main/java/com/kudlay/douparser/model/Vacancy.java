package com.kudlay.douparser.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "Vacancy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vacancy_id")
    private Long vacancyId;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    @Column(name = "description")
    private String description;

    @Column(name = "salary")
    private String salary;

    @Column(name = "city")
    private String city;

    @Column(name = "vacancy_url")
    private String url;

public Vacancy(String title, String company, String description, String salary, String city, String url) {
    this.title = title;
    this.company = company;
    this.description = description;
    this.salary = salary;
    this.city = city;
    this.url = url;
}

    public Long getVacancyId() {
        return vacancyId;
    }

    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public String getSalary() {
        return salary;
    }

    public String getCity() {
        return city;
    }
}
