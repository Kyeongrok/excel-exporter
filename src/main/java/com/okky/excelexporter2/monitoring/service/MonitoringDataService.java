package com.okky.excelexporter2.monitoring.service;

import com.okky.excelexporter2.excel.ExcelMaker;
import com.okky.excelexporter2.monitoring.domain.MonitoringDataDto;
import com.okky.excelexporter2.monitoring.dto.ExcelRequest;
import com.okky.excelexporter2.monitoring.maker.MonitoringExcelMaker;
import com.okky.excelexporter2.monitoring.repository.MonitoringDataRepository;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;

public class MonitoringDataService {
    private final MonitoringDataRepository monitoringDataRepository;
    private final ExcelMaker excelMaker;

    public MonitoringDataService(MonitoringDataRepository monitoringDataRepository) {
        this.monitoringDataRepository = monitoringDataRepository;
        this.excelMaker = new MonitoringExcelMaker();
    }

    public SXSSFWorkbook excel(ExcelRequest request) {
        List<MonitoringDataDto> data = monitoringDataRepository.findAll();
        return excelMaker.make("monitoring", List.of("quantity", "spm"), data);
    }
}
