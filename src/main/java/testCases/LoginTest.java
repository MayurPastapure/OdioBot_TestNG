package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.TestListener;

@Listeners(TestListener.class)
public class LoginTest extends BaseClass {

	LoginPage lp;

	@BeforeMethod
	public void setupLoginPageObjects() {
		lp = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyIncorrectCredLogin() {
		logger.info("*** Verifying test case verityInCorrectCredLogin ***");

		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPasswordWrong"));
		lp.clickLogin();

		String actLoginErrorMsg = lp.getErrorMessage();
		Assert.assertEquals(actLoginErrorMsg, p.getProperty("ExpLoginErrorMsg"));

	}

	@Test(priority = 2)
	public void verifyBlankEmailLogin() {
		logger.info("*** Verifying test case verityBlankEmailLogin ***");

		lp.setEmail("");
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();

		String actEmailReqMsg = lp.getEmailReqMsg();
		Assert.assertEquals(actEmailReqMsg, p.getProperty("EmailReq"));

	}

	@Test(priority = 3)
	public void verifyBlankPasswordLogin() {
		logger.info("*** Verifying test case verityBlankPasswordLogin ***");

		lp.refreshPage();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword("");
		lp.clickLogin();

		String actPasswordReqMsg = lp.getPasswordReqMsg();
		Assert.assertEquals(actPasswordReqMsg, p.getProperty("PasswordReq"));

	}

	@Test(priority = 4)
	public void verifyCorrectCredLogin() {
		logger.info("*** Verifying test case verityCorrectCredLogin ***");

		lp.refreshPage();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();

		wait.until(ExpectedConditions.urlToBe(p.getProperty("dashboardUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("dashboardUrl"), "Login failed: url mismatch.");

	}

}
