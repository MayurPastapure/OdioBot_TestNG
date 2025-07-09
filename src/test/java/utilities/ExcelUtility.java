
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ExcelUtility {

	String path;
	WebDriver driver;
	public FileInputStream fin;
	public XSSFWorkbook xworkbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileOutputStream fout;
	String data = null;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fin = new FileInputStream(path);
		xworkbook = new XSSFWorkbook(fin);
		sheet = xworkbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		xworkbook.close();
		fin.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fin = new FileInputStream(path);
		xworkbook = new XSSFWorkbook(fin);
		sheet = xworkbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		xworkbook.close();
		fin.close();
		return cellCount;
	}

	public String getData(String sheetName, int rowNum, int cellNum) throws IOException {
		fin = new FileInputStream(path);
		xworkbook = new XSSFWorkbook(fin);
		sheet = xworkbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		cell.getCellType();

		switch (cell.getCellType()) {

		case STRING:
			data = cell.getStringCellValue();
			break;

		case NUMERIC:
			data = String.valueOf(cell.getNumericCellValue());
			break;

		case BOOLEAN:
			data = String.valueOf(cell.getBooleanCellValue());
			break;

		case FORMULA:
			data = cell.getCellFormula().toString();
			break;

		case BLANK:
			data = "";
			break;

		default:
			System.out.println("Invalid date...");
			break;

		}
		xworkbook.close();
		fin.close();
		return data;

	}

	public void setData(String sheetName, int rowNum, int cellNum, String data) throws IOException {
		File xlfile = new File(path);
		if (!xlfile.exists()) {
			
			fout = new FileOutputStream(path);
			xworkbook = new XSSFWorkbook();
			xworkbook.write(fout);
		}
		fin = new FileInputStream(path);
		xworkbook = new XSSFWorkbook(fin);

		if (xworkbook.getSheetIndex(sheetName) == -1) {
			xworkbook.createSheet(sheetName);
		}
		sheet = xworkbook.getSheet(sheetName);

		if (sheet.getRow(rowNum) == null) {
			sheet.createRow(rowNum);
		}
		row = sheet.getRow(rowNum);

		cell = row.createCell(cellNum);
		cell.setCellValue(data);

		fout = new FileOutputStream(path);
		xworkbook.write(fout);

		xworkbook.close();
		fin.close();
		fout.close();

	}

}
