package testCases;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClase;

public class LoginTest extends BaseClase {

	LoginPage lp = new LoginPage(driver);

	@Test (priority = 1)
	public void verifyCorrectCredLogin() {
		logger.info("*** Starting test case verityCorrectLogin ***");

		lp.clearField();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();

		wait.until(ExpectedConditions.urlToBe(p.getProperty("dashboardUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("dashboardUrl"), "Login failed: url mismatch.");

	}
	
	@Test (priority = 2)
	public void verifyIncorrectCredLogin() {
		
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPasswordWrong"));
		lp.clickLogin();
		
		
		
	}

}
