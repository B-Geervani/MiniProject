package com.ExcelTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class CustomData {
	public XSSFWorkbook wb;

	@DataProvider(name = "exceldata")
	public Object[][] excelData() {
		
		File f1 = new File(System.getProperty("user.dir") + "//TestData//Data.xlsx");
		FileInputStream fs;
		Object arr[][] = null;
		try {
			fs = new FileInputStream(f1);
			wb = new XSSFWorkbook(fs);
		
			int rows = wb.getSheet("userdata").getPhysicalNumberOfRows();
			System.out.println("Number of rows: " + rows);

			int cells = wb.getSheet("userdata").getRow(0).getPhysicalNumberOfCells();
			System.out.println("Number of columns: " + cells);

			arr = new Object[rows - 1][cells];
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < cells; j++) {
					arr[i - 1][j] = wb.getSheet("userdata").getRow(i).getCell(j).getStringCellValue();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
