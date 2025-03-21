package com.okky.excelexporter2.power.maker;

import com.okky.excelexporter2.excel.ExcelWorkbookMaker;
import com.okky.excelexporter2.power.domain.PowerDataDto;
import org.apache.poi.ss.usermodel.Row;

public class PowerExcelMaker extends ExcelWorkbookMaker<PowerDataDto> {
    @Override
    protected void addCellsCallback(Row row, PowerDataDto dto, int adjustedHour) {

    }
}
