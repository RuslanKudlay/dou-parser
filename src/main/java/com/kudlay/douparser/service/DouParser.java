package com.kudlay.douparser.service;

import com.kudlay.douparser.model.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DouParser {

    private Document getDocument(String url) throws IOException {
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 OPR/108.0.0.0";
        return Jsoup.connect(url).userAgent(userAgent).timeout(5000).get();
    }

    public Vacancy getVacancy(Element vacancyElem) throws IOException {
        Element titleElem = vacancyElem.selectFirst(".title>a");
        Element companyElem = vacancyElem.selectFirst(".title>strong>a");
        Element cityElem = vacancyElem.selectFirst(".title>span.cities");
        Element salaryElem = vacancyElem.selectFirst(".title>span.salary");
        Element descriptionElem = vacancyElem.selectFirst(".sh-info");

        String title = "";
        String url = "";
        String company = "";
        String city = "";
        String salary = "";
        String description = "";

        if(titleElem != null) {title = titleElem.text(); url=titleElem.attr("href");}
        if(companyElem != null) {company = companyElem.text();}
        if(cityElem != null) {city = cityElem.text();}
        if(salaryElem != null) {salary = salaryElem.text();}
        if(descriptionElem != null) {description = descriptionElem.text();}

        return new Vacancy(title, company, description, salary, city, url);
    }

    public List<Vacancy> getVacancies(String params) throws IOException {
        String url = getUrl(params);
        List<Vacancy> vacancies = new ArrayList<>();
        Document document = getDocument(url);
        Elements elements = document.select("ul.lt>li.l-vacancy");
        for (Element element : elements) {
            vacancies.add(getVacancy(element));
        }
        return vacancies;
    }

    private String getUrl(String params){
        String url = "https://jobs.dou.ua/vacancies/";
        if(!params.isEmpty()){
            url += "?" + params;
        }
        return url;
    }

}
