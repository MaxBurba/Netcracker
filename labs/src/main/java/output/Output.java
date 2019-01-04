package output;

import analyzer.Analyzer;
import com.google.common.collect.Table;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xssf.usermodel.*;

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
            initChart(sheet, sizeKeys.size(), sortNames.size());
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

    private void initChart(XSSFSheet sheet, int rowNum, int colNum){

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, rowNum + 2, colNum + 1, rowNum * 3);

        XSSFChart chart = drawing.createChart(anchor);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("Size");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("Sort Duration");
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, rowNum, 0, 0));

        for(int i = 1; i <= colNum; i++){
            XDDFNumericalDataSource<Double> ys = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, rowNum, i, i));
            XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(xs, ys);

            XSSFRow row = sheet.getRow(0);
            String sortName = row.getCell(i).getStringCellValue();

            series.setTitle(sortName, null);
            series.setSmooth(false);
            series.setMarkerSize((short) 6);
        }
        //chart.displayBlanksAs(DisplayBlanks.GAP);
        chart.plot(data);
    }
}
