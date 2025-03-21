package com.okky.excelexporter2.utils;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class StreamMaker {

    public static ByteArrayInputStream toStream(SXSSFWorkbook workbook) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        assert workbook != null;
        workbook.write(outputStream);
        outputStream.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
