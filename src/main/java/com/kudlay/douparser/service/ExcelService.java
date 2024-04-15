package com.kudlay.douparser.service;

import com.kudlay.douparser.model.Vacancy;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelService {
    static public byte[] createExcelFile(List<Vacancy> vacancies) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Vacancy");

        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Title", "Company", "Description", "City", "Salary", "URL"};
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (Vacancy vacancy : vacancies) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(vacancy.getVacancyId());
            row.createCell(1).setCellValue(vacancy.getTitle());
            row.createCell(2).setCellValue(vacancy.getCompany());
            row.createCell(3).setCellValue(vacancy.getDescription());
            row.createCell(4).setCellValue(vacancy.getCity());
            row.createCell(5).setCellValue(vacancy.getSalary());
            row.createCell(6).setCellValue(vacancy.getUrl());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
