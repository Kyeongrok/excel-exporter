package com.okky.excelexporter2.power.maker;

import com.okky.excelexporter2.excel.ExcelHeapSafeMaker;
import com.okky.excelexporter2.power.domain.PowerDataDto;
import org.apache.poi.ss.usermodel.Row;

public class PowerExcelMaker extends ExcelHeapSafeMaker<PowerDataDto> {
    @Override
    protected void addCellsCallback(Row row, PowerDataDto dto, int adjustedHour) {
        int columnCnt = 0;
        row.createCell(columnCnt++).setCellValue(dto.getVoltageL1L2());
        row.createCell(columnCnt++).setCellValue(dto.getVoltageL2L3());
        row.createCell(columnCnt++).setCellValue(dto.getVoltageL3L1());
    }
}
