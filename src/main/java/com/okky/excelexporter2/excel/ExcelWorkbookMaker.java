package com.okky.excelexporter2.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


import java.io.IOException;
import java.util.List;

@Slf4j
public abstract class ExcelWorkbookMaker<T> implements ExcelMaker<T> {

    private final SXSSFWorkbook workbook = new SXSSFWorkbook();
    protected SXSSFSheet sheet;

    @Override
    public SXSSFWorkbook make(String sheetName, List<String> columnNames, List<T> datas) {
        return make(sheetName, columnNames, datas, 9);
    }

    public SXSSFWorkbook make(String sheetName, List<String> columnNames, List<T> datas, int adjustedHour) {
        int rowCnt = 0;
        this.sheet = workbook.createSheet(sheetName);
        sheet.setRandomAccessWindowSize(100);

        Row headerRow = sheet.createRow(rowCnt++);
        for (int i = 0; i < columnNames.size(); i++) {
            headerRow.createCell(i).setCellValue(columnNames.get(i));
        }

        int cnt = 0;
        for (T dto : datas) {
            Row row = sheet.createRow(rowCnt++);
            addCellsCallback(row, dto, adjustedHour);
            if (++cnt % 1000 == 0) {
                log.info("[EXCEL_DOWNLOAD] {} rows are made", cnt);
                try {
                    sheet.flushRows();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("엑셀 파일 생성 중 오류가 발생했습니다.");
                }
            }
        }

        return workbook;
    }

    protected abstract void addCellsCallback(Row row, T dto, int adjustedHour);
}
