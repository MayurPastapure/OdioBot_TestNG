package BaseLibrary;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ExcelUtility.ExcelUtility;

public class BaseLibrary implements ExcelUtility{
	
	public static WebDriver driver;
	
	public void getLaunchUrl(String url) {
		
		String path = "C:\\Users\\Sachin Kumar\\AutomationOdioTestNG2\\Drivers\\chromedriver.exe";
		System.setProperty("WebDriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
	}
//	@AfterTest
//	public void Teardown()
//	{
//		driver.quit();
//	}
	@Override
	public String getReadData(String path, int sheetno, int colno, int rowno)
	{
		String value = " ";
		try {
			FileInputStream file = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheetAt(sheetno);
			value =  sheet.getRow(rowno).getCell(colno).getStringCellValue();
		}
		catch(Exception e)
		{
			System.out.println("Error in reading data from excel:"+e);
		}
		return value;
	}
	
}