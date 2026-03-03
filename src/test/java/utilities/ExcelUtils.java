package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class ExcelUtils {

    public static void updateExcel(String filePath, String sheetName, String testcaseName, String columnName, String value) {
        try {
            System.out.println("enter Excel sheet:");
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if(sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                workbook.close();
                fis.close();
                return;
            }

            // Find the row with the testcaseName in first column
            int testcaseRowNum = -1;
            for (Row row : sheet) {
                Cell firstCell = row.getCell(0);
                if (firstCell != null) {
                    firstCell.setCellType(CellType.STRING);
                    if (firstCell.getStringCellValue().equalsIgnoreCase(testcaseName)) {
                        testcaseRowNum = row.getRowNum();
                        break;
                    }
                }
            }

            if (testcaseRowNum == -1) {
                System.out.println("Testcase not found: " + testcaseName);
                workbook.close();
                fis.close();
                return;
            }

            // Find the column number from header row (row 0)
            Row headerRow = sheet.getRow(0);
            int colNum = -1;
            for (Cell cell : headerRow) {
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    colNum = cell.getColumnIndex();
                    break;
                }
            }

            if (colNum == -1) {
                System.out.println("Column not found: " + columnName);
                workbook.close();
                fis.close();
                return;
            }

            // Update the cell
            Row rowToUpdate = sheet.getRow(testcaseRowNum);
            if (rowToUpdate == null) rowToUpdate = sheet.createRow(testcaseRowNum);

            Cell cellToUpdate = rowToUpdate.getCell(colNum);
            if (cellToUpdate == null) cellToUpdate = rowToUpdate.createCell(colNum);

            cellToUpdate.setCellValue(value);

            // Save changes
            fis.close();
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            workbook.close();
            fos.close();

            System.out.println("Excel updated successfully for testcase: " + testcaseName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readExcel(String filePath, String sheetName, String testcaseName, String columnName) {
        String cellValue = null;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet not found: " + sheetName);
                workbook.close();
                fis.close();
                return null;
            }

            // Find row number for testcaseName (assumed in first column)
            int testcaseRowNum = -1;
            for (Row row : sheet) {
                Cell firstCell = row.getCell(0);
                if (firstCell != null) {
                    firstCell.setCellType(CellType.STRING);
                    if (firstCell.getStringCellValue().equalsIgnoreCase(testcaseName)) {
                        testcaseRowNum = row.getRowNum();
                        break;
                    }
                }
            }

            if (testcaseRowNum == -1) {
                System.out.println("Testcase not found: " + testcaseName);
                workbook.close();
                fis.close();
                return null;
            }

            // Find column number from header row (row 0)
            Row headerRow = sheet.getRow(0);
            int colNum = -1;

            for (Cell cell : headerRow) {
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                    colNum = cell.getColumnIndex();
                    break;
                }
            }

            if (colNum == -1) {
                System.out.println("Column not found: " + columnName);
                workbook.close();
                fis.close();
                return null;
            }

            // Get the cell value
            Row row = sheet.getRow(testcaseRowNum);
            Cell cell = row.getCell(colNum);

            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        cellValue = cell.getCellFormula();
                        break;
                    default:
                        cellValue = "";
                }
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cellValue;
    }
}
