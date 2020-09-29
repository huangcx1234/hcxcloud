package com.jiurong.hcx.common.util.excel;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author hcx
 * @date 2019/5/8
 * explain: EXCEL工具
 */
public class ExcelUtils {

    @SneakyThrows
    public static void exportExcel(ExcelData excelData, HttpServletResponse response) {
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(excelData.getName() + ".xlsx", "utf-8"));
        writeExcel(excelData, response.getOutputStream());
    }

    @SneakyThrows
    private static void writeExcel(ExcelData excelData, OutputStream out) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            excelData.getSheetDataList().forEach(sheetData -> {
                XSSFSheet sheet = wb.createSheet(sheetData.getName());
                writeSheet(wb, sheet, sheetData);
            });
            wb.write(out);
        } finally {
            out.close();
        }
    }

    /**
     * 表显示字段
     *
     * @param wb
     * @param sheet
     * @param sheetData
     * @return
     */
    private static void writeSheet(XSSFWorkbook wb, Sheet sheet, SheetData sheetData) {
        writeTitlesToSheet(wb, sheet, sheetData.getTitles());
        writeRowsToSheet(wb, sheet, sheetData.getRows(), 1);
        autoSizeColumns(sheet, sheetData.getTitles().size() + 1);
    }

    /**
     * 设置表头
     *
     * @param wb
     * @param sheet
     * @param titles
     * @return
     */
    private static void writeTitlesToSheet(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("宋体");
        titleFont.setBoldweight(Short.MAX_VALUE);
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(15);
        for (String title : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(title);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
    }

    /**
     * 设置内容
     *
     * @param wb
     * @param sheet
     * @param rows
     * @param rowIndex
     * @return
     */
    private static void writeRowsToSheet(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex;
        Font dataFont = wb.createFont();
        dataFont.setFontName("宋体");
        dataFont.setFontHeightInPoints((short) 10);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            dataRow.setHeightInPoints(15);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
    }

    /**
     * 自动调整列宽
     *
     * @param sheet
     * @param columnNumber
     */
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = sheet.getColumnWidth(i) + 100;
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    /**
     * 设置边框
     *
     * @param style
     * @param border
     * @param color
     */
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
}
