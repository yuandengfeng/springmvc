package tool.excelpoi;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.DateFormatConverter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/4/27.
 */
@Component
public class ExcelReportCreator {

//    private static String template = "/home/taxstatistics/template1/tax_statistics_template.xls";
//    private static String destDir = "/home/taxstatistics/template1/";
    private static String template = "E:\\gitcode\\autoemailreport\\area_info_day.xls";
    private static String destDir = "F:\\";

    public File createExcel(List<TaxStatistics> data, String fileName) {
        File xls = new File(destDir + "/" + fileName);
        try {
            HSSFWorkbook workbook = readExcelTemplate();  //获取excel模板
            addExcelData(workbook, data);  //创建excel表
            reCalculatingFormulas(workbook); //执行中间表所有公式
            copyCalculatedResultToSheet(workbook); //将中间表拷贝到结果表中
            writeExcel(workbook, xls);
        } catch (IOException ex) {
            xls = null;
        }
        return xls;
    }

    private void writeExcel(HSSFWorkbook workbook, File xls) throws IOException{
        workbook.setActiveSheet(0);
        workbook.write(new FileOutputStream(xls));
    }

    private void addExcelData(HSSFWorkbook workbook, List<TaxStatistics> data) throws IOException{
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat(DateFormatConverter.convert(Locale.CHINA, df)));

        HSSFSheet sheet = workbook.getSheetAt(2);
        int rowIndex = 1; //从第二行开始，跳过第一行

        if (data != null) {
            for (TaxStatistics taxStatistics : data) {
                HSSFDataFormat hssfDataFormat = workbook.createDataFormat();
                HSSFCellStyle contextstyle =workbook.createCellStyle();
                contextstyle.setDataFormat(hssfDataFormat.getBuiltinFormat("#,##0.0"));
                HSSFRow row = sheet.createRow(rowIndex); //从第二行开始添值
                row.createCell(0).setCellValue(taxStatistics.getServiceunits()); //单元格1
                HSSFCell cell = row.createCell(1);
                cell.setCellValue(taxStatistics.getDate()); //设置单元格值
                cell.setCellStyle(cellStyle); //设置单元格值显示格式
                row.createCell(2).setCellValue(taxStatistics.getTotaldevices());
                row.createCell(3).setCellValue(taxStatistics.getDealnumber());
                HSSFCell cell4 = row.createCell(4);
                cell4.setCellStyle(contextstyle);
                cell4.setCellValue(Double.parseDouble(taxStatistics.getPay().toString()));
                HSSFCell cell5 = row.createCell(5);
                cell5.setCellStyle(contextstyle);
                cell5.setCellValue(Double.parseDouble(taxStatistics.getBankcard().toString()));
                HSSFCell cell6 = row.createCell(6);
                cell6.setCellStyle(contextstyle);
                cell6.setCellValue(Double.parseDouble(taxStatistics.getWechat().toString()));
                HSSFCell cell7 = row.createCell(7);
                cell7.setCellStyle(contextstyle);
                cell7.setCellValue(Double.parseDouble(taxStatistics.getAlipay().toString()));
                HSSFCell cell8 = row.createCell(8);
                cell8.setCellStyle(contextstyle);
                cell8.setCellValue(Double.parseDouble(taxStatistics.getOtherincome().toString()));
                row.createCell(9).setCellFormula("WEEKNUM(B" + (rowIndex + 1) + ", 2)");
                row.createCell(10).setCellFormula("MONTH(B" + (rowIndex + 1) + ")");
                row.createCell(11).setCellFormula("INT((MONTH(B" + (rowIndex + 1) + ")-1)/3)+1");
                HSSFCell cell1 = row.createCell(12);
                cell1.setCellValue(DateUtil.getDayOfYesterdays());
                cell1.setCellStyle(cellStyle);
                row.createCell(13).setCellValue(taxStatistics.getSid());
                rowIndex++;
            }
        }
    }
   //依次执行表中的公式
    public HSSFWorkbook reCalculatingFormulas(HSSFWorkbook workbook){
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        Sheet sheet = workbook.getSheetAt(1); //第二个表格
        for (Row r : sheet) {
            for (Cell c : r) {
                if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    try {
                        evaluator.evaluateFormulaCell(c);  //依次执行每个单元个的公式
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return workbook;
    }

    public void copyCalculatedResultToSheet(HSSFWorkbook workbook){
        int rowIndex = 0;
        HSSFSheet sheet = workbook.getSheetAt(1);
        HSSFSheet sheet2 = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {   //将sheet表中数据拷贝到sheet2
            if (rowIndex < 2){
                Row row = rowIterator.next();
                rowIndex++;
            } else {
                Row row = rowIterator.next();
                HSSFRow newRow = sheet2.getRow(rowIndex);
//                newRow.setHeightInPoints(24);
                rowIndex++;
                Iterator<Cell> cellIterator = row.cellIterator();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    if (cellIndex < 2){
                        Cell cell = cellIterator.next();
                    } else {
                        Cell cell = cellIterator.next(); //Fetch CELL
                        String s = getCellValue((HSSFCell) cell);
                        HSSFCell newCell = newRow.createCell(cellIndex);
                        newCell.setCellValue(s);
                    }
                    cellIndex++;
                }
            }
        }
//        sheet2.setColumnWidth(0,50*256);
    }

    private HSSFWorkbook readExcelTemplate() throws IOException {
        InputStream is = new FileInputStream(template);
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        is.close();
        return workbook;
    }

    public String getCellValue(HSSFCell cell) {
        String value = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    // cell.getCellFormula();
                    try {
                        value = String.valueOf(cell.getNumericCellValue());
                    } catch (IllegalStateException e) {
                        value = String.valueOf(cell.getRichStringCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    value = String.valueOf(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = String.valueOf(cell.getRichStringCellValue());
                    break;
            }
        }
        return value;
    }


}
