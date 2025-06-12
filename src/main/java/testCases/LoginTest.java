package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClase;

public class LoginTest extends BaseClase {

	LoginPage lp;

	@BeforeMethod
	public void setupPageObjects() {
		lp = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyIncorrectCredLogin() {
		logger.info("*** Starting test case verify InCorrectCredLogin ***");

		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPasswordWrong"));
		lp.clickLogin();

		String actLoginErrorMsg = lp.getErrorMessage();
		Assert.assertEquals(actLoginErrorMsg, p.getProperty("ExpLoginErrorMsg"));

	}

	@Test(priority = 2)
	public void verifyBlankEmailLogin() {
		logger.info("*** Starting test case verify BlankEmailLogin ***");

		lp.setEmail("");
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();

		String actEmailReqMsg = lp.getEmailReqMsg();
		Assert.assertEquals(actEmailReqMsg, p.getProperty("EmailReq"));

	}

	@Test(priority = 3)
	public void verifyBlankPasswordLogin() {
		logger.info("*** Starting test case verify BlankPasswordLogin ***");

		lp.refreshPage();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword("");
		lp.clickLogin();

		String actPasswordReqMsg = lp.getPasswordReqMsg();
		Assert.assertEquals(actPasswordReqMsg, p.getProperty("PasswordReq"));

	}

	@Test(priority = 4)
	public void verifyCorrectCredLogin() {
		logger.info("*** Starting test case verify CorrectCredLogin ***");

		lp.refreshPage();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();

		wait.until(ExpectedConditions.urlToBe(p.getProperty("dashboardUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("dashboardUrl"), "Login failed: url mismatch.");

	}

}
