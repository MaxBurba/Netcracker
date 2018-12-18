package output;

import analyzer.Analyzer;
import com.google.common.collect.Table;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class Output {

    private static final String FILE_NAME = "Table.xlsx";

    public void outputResults() {

        Analyzer analyzer = new Analyzer();
        XSSFWorkbook book = new XSSFWorkbook();

        Map<String, Table<String, Integer, Long>> map = analyzer.analyze();

        for(String key : map.keySet()){
            XSSFSheet sheet = book.createSheet(key);

            Table<String, Integer, Long> table = map.get(key);
            Set<String> sortNames = table.rowKeySet();
            Set<Integer> sizeKeys = table.columnKeySet();

            initFields(book, sortNames, sheet);

            int rowNum = 1;
            int colNum = 0;

            for(int sizeKey : sizeKeys){
                Row row = sheet.createRow(rowNum++);
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(sizeKey);

                for(String sortName : sortNames){
                    cell = row.createCell(colNum++);
                    cell.setCellValue(table.get(sortName, sizeKey));
                }
                colNum = 0;
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            book.write(outputStream);
            book.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private void initFields(XSSFWorkbook book, Set<String> sortNames, XSSFSheet sheet){
        int colNum = 0;
        Cell cell;
        Row row;

        XSSFCellStyle style = book.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);

        row = sheet.createRow(0);
        cell = row.createCell(colNum++);
        cell.setCellValue("Size");
        cell.setCellStyle(style);

        for (String sortName : sortNames) {
            sheet.setColumnWidth(colNum, 4700);
            //sheet.autoSizeColumn(colNum);

            cell = row.createCell(colNum++);
            cell.setCellValue(sortName);
            cell.setCellStyle(style);
        }
    }
}
