package com.automation.pilot.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.automation.pilot.utilities.TestBaseClass;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
public class TestDataRead extends TestBaseClass {

	static String currentDir = System.getProperty("user.dir");
	private final static String propertyFilePath = currentDir + "//Data//TestData.properties";
	static Properties properties = new Properties();
	static String value;

	public static String getDataFromTestDataProperty(String keyName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties.load(reader);
			value = properties.getProperty(keyName);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.assertFalse(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		return value;
	}

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
	
	public static String readDataFromXML(String filePath,String fileName,String outerTag,String innerTag)
	{
		File xmlFile = new File(filePath+"\\"+fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		String resultValue = null;
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
			NodeList nodeList = doc.getElementsByTagName(outerTag);
			for(int i=0;i<nodeList.getLength();i++) 
			{
				Node nNode = nodeList.item(i);
				if(nNode.getNodeType()==Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode;
					resultValue = eElement.getAttribute(innerTag);
				}
			}
		}
	catch (Exception e)
		{
			e.printStackTrace();
			Assertion.assertFalse(true,e.getMessage());
		}
		return resultValue;
	}
}
