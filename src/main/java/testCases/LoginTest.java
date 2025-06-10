package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClase;
			
public class LoginTest extends BaseClase {

	@Test
	public void verifyCorrectLogin() {
		logger.info("*** Starting test case verityCorrectLogin ***");
		
		LoginPage lp = new LoginPage(driver);
		
		lp.clearField();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();
		
	    wait.until(ExpectedConditions.urlToBe(p.getProperty("dashboardUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("dashboardUrl"), "Login failed: url mismatch.");

	}
	
}
