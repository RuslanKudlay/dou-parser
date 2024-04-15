package com.kudlay.douparser.controller;

import com.kudlay.douparser.model.Vacancy;
import com.kudlay.douparser.service.DouParser;
import com.kudlay.douparser.service.DouService;
import com.kudlay.douparser.service.ExcelService;
import com.kudlay.douparser.service.ParamsService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/vacancies")
@ResponseBody
@CrossOrigin(origins = "http://localhost:5173/")
public class DouController {
    private final DouService douService;

    public DouController(DouService douService) {
        this.douService = douService;
    }

    @GetMapping("")
    public ResponseEntity<List<Vacancy>> getVacancies(@RequestParam(value = "category", required = false) String category,
                                                      @RequestParam(value = "experience", required = false) String experience,
                                                      @RequestParam(value = "city", required = false) String city,
                                                      @RequestParam(value = "keywords", required = false) String keywords,
                                                      @RequestParam(value = "status", required = false) String status) throws IOException {
        try{
            String params = ParamsService.parseParams(category, experience, city, keywords, status);
            DouParser douParser = new DouParser();
            List<Vacancy> vacancies = douParser.getVacancies(params);
            return ResponseEntity.ok(vacancies);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/save")
    public ResponseEntity<byte []> getExcelFile() throws IOException {
        try{
            List<Vacancy> vacancies = douService.getVacancies();
            byte[] createdFile = ExcelService.createExcelFile(vacancies);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "vacancy.xlsx");
            return ResponseEntity.ok().headers(headers).body(createdFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/my-list")
    public ResponseEntity<List<Vacancy>> getMyVacancies() throws IOException {
        try{
            List<Vacancy> vacancies = douService.getVacancies();
            return ResponseEntity.ok(vacancies);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Vacancy> createTransaction(@RequestBody Vacancy vacancy) throws IOException, ParseException {
        try{
            Vacancy savedVacancy = douService.saveVacancies(vacancy);
            return ResponseEntity.ok(savedVacancy);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
