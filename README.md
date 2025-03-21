# 엑셀 다운로드 기능

## 요구사항은
1. '엑셀 파일을 다운로드 하는 API'입니다. 
2. Heap메모리가 꽉 차지 않도록 관리 해야 합니다.
3. Monitoring이라는 Domain과 Power라는 Domain이 있고  
4. 각 도메인은 요구되는 엑셀파일의 모양이 다릅니다.
   - Monitoring의 경우 컬럼이 quantity, spm이 들어갑니다.
   - Power의 경우 컬럼이 voltageL1L2, voltageL2L3, voltageL3L1 이 들어갑니다.
5. 달라지는 부분은 Column과 Row의 생김새가 달라집니다.

6. 다국어를 지원 합니다. 언어는 Controller를 통해 입력 받습니다. ex)ko, en, jp 등

# ERD
```mermaid
classDiagram
    class MonitoringDataService {
        +excel()
    }

    class ExcelMaker {
        <<interface>>
        +make()
    }

    class ExcelHeapSafeMaker {
        <<abstract>>
        #addCellsCallback()
        +make()
    }

    class MonitoringExcelMaker {
        #addCellsCallback()
    }

    class PowerExcelMaker {
        #addCellsCallback()
    }

    MonitoringDataService --> ExcelMaker : uses
    ExcelMaker <|-- ExcelHeapSafeMaker : implements
    ExcelHeapSafeMaker <|-- MonitoringExcelMaker : extends
    ExcelHeapSafeMaker <|-- PowerExcelMaker : extends
```

## 핵심 로직

###
```java
public interface ExcelMaker<T> {
    SXSSFWorkbook make(String sheetName, List<String> columnNames, List<T> datas);
}

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

```

### MonitoringExcelMaker
바뀌는 부분 Row에 해당하는 구현체
```java
@Component
@RequiredArgsConstructor
public class MonitoringExcelMaker extends ExcelWorkbookMaker<MonitoringDataDto> {

    @Override
    protected void addCellsCallback(Row row, MonitoringDataDto dto, int adjustedHour) {
        int columnCnt = 0;
        row.createCell(columnCnt++).setCellValue(dto.getQty());
        row.createCell(columnCnt++).setCellValue(dto.getSpm());
    }
}

```

## 사용한 라이브러리
- SpringBoot
- POI

## 실행 방법
- gradle 빌드 후 ExcelExporter2Application 으로 실행

## 호출
GET http://localhost:8080/api/v1/monitoring/excel  
GET http://localhost:8080/api/v1/power/excel  

