package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {

    static DataFormatter formatter = new DataFormatter();
    public void getData() throws IOException {

        FileInputStream fis=new FileInputStream("D:\\QP Task\\API Automation Task\\src\\main\\resources\\TestData.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();
    }

    public Object[][] readTestDataFromExcel(String sheetName) throws IOException {

        // Open the Excel file
        ClassLoader classLoader = this.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);


       // XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("resources/Test.xlsx"));

        // Get the specific sheet
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Get the data range (adjust based on your sheet)
        int rowCount = sheet.getLastRowNum() ; // +1 to include header row
        int colCount = sheet.getRow(0).getLastCellNum(); // Assuming headers in first row

        // Create an object array to hold the data
        Object[][] data = new Object[rowCount][colCount];

        // Loop through rows and columns to read data
        for (int i = 1; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                XSSFCell cell = row.getCell(j);
                data[i-1][j]= formatter.formatCellValue(cell);
            }
        }

        // Close the workbook
        workbook.close();

        return data;
    }


}
