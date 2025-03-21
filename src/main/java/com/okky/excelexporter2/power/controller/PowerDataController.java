package com.okky.excelexporter2.power.controller;


import com.okky.excelexporter2.monitoring.dto.ExcelRequest;
import com.okky.excelexporter2.power.service.PowerDataService;
import com.okky.excelexporter2.utils.StreamMaker;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/power")
public class PowerDataController {

    private final PowerDataService powerDataService;


    @PostMapping("/excel")
    public ResponseEntity<InputStreamResource> downloadExcel() throws IOException {

        SXSSFWorkbook excel = powerDataService.excel(new ExcelRequest());
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
