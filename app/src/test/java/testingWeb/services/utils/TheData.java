package testingWeb.services.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TheData {
    
    public static String[][] getExcelData(String fileName, String sheetName) {
        String[][] data = new String[0][0];
        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row != null ? row.getLastCellNum() : 0;
            Cell cell;
            data = new String[(noOfRows > 0 ? (noOfRows-1) : 0)][noOfCols];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for(int i = 1; i < noOfRows; i++) {
                for(int j = 0; j < noOfCols; j++) {
                    row = sh.getRow(i);
                    cell= row.getCell(j);
                    String cellData = "";
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    Date dateValue = cell.getDateCellValue();
                                    cellData = dateFormat.format(dateValue);
                                } else {
                                    cellData = Double.toString(cell.getNumericCellValue());
                                }
                                break;
                            case STRING:
                                cellData = cell.getStringCellValue();
                                break;
                            case BOOLEAN:
                                cellData = String.valueOf(cell.getBooleanCellValue());
                                break;
                        }
                    }
                    data[i-1][j] = cellData;
                }
            }
        }
        catch (Exception e) {
            System.out.println("The exception is: " +e.getMessage());
        }
        return removeRowsWithEmptyFirstColumn(data);
    }

    private static String[][] removeRowsWithEmptyFirstColumn(String[][] matriz) {
        int numRowsToKeep = 0;
        for (String[] row : matriz) {
            if (row != null && row.length > 0 && row[0] != null && !row[0].isEmpty()) {
                numRowsToKeep++;
            }
        }
        String[][] newMatriz = new String[numRowsToKeep][];
        int newRow = 0;
        for (String[] row : matriz) {
            if (row != null && row.length > 0 && row[0] != null && !row[0].isEmpty()) {
                newMatriz[newRow++] = row;
            }
        }
        return newMatriz;
    }

}