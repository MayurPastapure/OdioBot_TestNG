package testCases;

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
	}
	
}
