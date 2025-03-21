package com.okky.excelexporter2.excel;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;

public interface ExcelMaker<T> {
    SXSSFWorkbook make(String sheetName, List<String> columnNames, List<T> datas);
}
