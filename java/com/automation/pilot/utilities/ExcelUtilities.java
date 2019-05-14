package com.automation.pilot.utilities;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Excel Utilities to read and write into Excel
 * @ Author : gnana.kilambhi
 * 
 */

public class ExcelUtilities {

	public static String readExcelByKey(String filePath, String fileName, String sheetName, String keyName) {
		String resultValue = null;
		try {

			// Create an object of File class to open xlsx file

			File file = new File(filePath + "//" + fileName);
			String rowValue;

			// Create an object of FileInputStream class to read excel file

			FileInputStream inputStream = new FileInputStream(file);

			Workbook workBook = null;

			// Find the file extension by splitting file name in substring and getting only
			// extension name

			String fileExtensionName = fileName.substring(fileName.indexOf("."));

			// Check condition if the file is xlsx file

			if (fileExtensionName.equals(".xlsx")) {

				// If it is xlsx file then create object of XSSFWorkbook class

				workBook = new XSSFWorkbook(inputStream);

			}

			// Check condition if the file is xls file

			else if (fileExtensionName.equals(".xls")) {

				// If it is xls file then create object of XSSFWorkbook class

				workBook = new HSSFWorkbook(inputStream);

			}

			// Read sheet inside the workbook by its name

			Sheet sheet = workBook.getSheet(sheetName);

			// Find number of rows in excel file

			int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

			// Create a loop over all the rows of excel file to read it

			for (int i = 0; i < rowCount + 1; i++) {

				Row row = sheet.getRow(i);
				rowValue = row.getCell(0).getStringCellValue();
				if (rowValue.equals(keyName)) {
					resultValue = row.getCell(1).getStringCellValue();
				}
			}
			System.out.print(resultValue);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultValue;
	}
}
