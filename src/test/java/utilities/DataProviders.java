package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public Object[][] getData() throws IOException {
		String path = "./testData//loginData.xlsx";
		ExcelUtility xlutil = new ExcelUtility(path);
		int totalRows = xlutil.getRowCount("loginCred");
		int totalCols = xlutil.getCellCount("loginCred", 1);

		String[][] logindata = new String[totalRows][totalCols];
		for (int i = 1; i <= totalRows; i++) {

			for (int j = 0; j < totalCols; j++) {
				logindata[i - 1][j] = xlutil.getData("loginCred", i, j);
			}
		}

		return logindata;
	}
}
