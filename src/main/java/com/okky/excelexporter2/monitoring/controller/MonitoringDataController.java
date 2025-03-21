package com.okky.excelexporter2.monitoring.controller;

import com.okky.excelexporter2.monitoring.dto.ExcelRequest;
import com.okky.excelexporter2.monitoring.service.MonitoringDataService;
import com.okky.excelexporter2.utils.StreamMaker;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/monitoring")
public class MonitoringDataController {
    private final MonitoringDataService fixtureMonitoringDataService;

    @GetMapping("/excel")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {

        SXSSFWorkbook excel = fixtureMonitoringDataService.excel(new ExcelRequest());
        ByteArrayInputStream stream = StreamMaker.toStream(excel);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=monitoring.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(stream));

    }

}