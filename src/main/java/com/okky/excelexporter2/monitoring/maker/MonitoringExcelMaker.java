package com.okky.excelexporter2.monitoring.maker;

import com.okky.excelexporter2.excel.ExcelWorkbookMaker;
import com.okky.excelexporter2.monitoring.domain.MonitoringDataDto;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

/**
 * Peak 등의 데이터를 포함하지 않습니다.
 */

@Component
@RequiredArgsConstructor
public class MonitoringExcelMaker extends ExcelWorkbookMaker<MonitoringDataDto> {

    @Override
    protected void addCellsCallback(Row row, MonitoringDataDto dto, int adjustedHour) {
        int columnCnt = 0;
        row.createCell(columnCnt++).setCellValue(dto.getFactoryName());
        row.createCell(columnCnt++).setCellValue(dto.getMachineName());
        row.createCell(columnCnt++).setCellValue(dto.getEquipmentName());
        row.createCell(columnCnt++).setCellValue(dto.getSpm());
    }
}
