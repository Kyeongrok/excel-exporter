package com.okky.excelexporter2.power.service;

import com.okky.excelexporter2.excel.ExcelMaker;
import com.okky.excelexporter2.monitoring.dto.ExcelRequest;
import com.okky.excelexporter2.power.domain.PowerDataDto;
import com.okky.excelexporter2.power.maker.PowerExcelMaker;
import com.okky.excelexporter2.power.repository.PowerDataRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

public class PowerDataService {
    private final PowerDataRepository powerDataRepository;
    private final ExcelMaker excelMaker;

    public PowerDataService(PowerDataRepository powerDataRepository) {
        this.powerDataRepository = powerDataRepository;
        this.excelMaker = new PowerExcelMaker();
    }

    public SXSSFWorkbook excel(ExcelRequest request) {
        List<PowerDataDto> data = powerDataRepository.findAll();
        return excelMaker.make("monitoring", List.of("quantity", "spm"), data);
    }
}
