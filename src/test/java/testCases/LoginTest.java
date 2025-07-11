package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {

	LoginPage lp;

	@BeforeMethod
	public void setupLoginPageObjects() {
		lp = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyIncorrectCredLogin() {
		logger.info("*** Verifying LoginTest: verityInCorrectCredLogin ***");
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPasswordWrong"));
		lp.clickLogin();
		String actLoginErrorMsg = lp.getErrorMessage();
		Assert.assertEquals(actLoginErrorMsg, p.getProperty("ExpLoginErrorMsg"), "Login error message does not match!");
	}

	@Test(priority = 2)
	public void verifyBlankEmailLogin() {
		logger.info("*** Verifying LoginTest: verityBlankEmailLogin ***");
		lp.setEmail("");
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();
		String actEmailReqMsg = lp.getEmailReqMsg();
		Assert.assertEquals(actEmailReqMsg, p.getProperty("EmailReq"), "Email required error message does not match!");
	}

	@Test(priority = 3)
	public void verifyBlankPasswordLogin() {
		logger.info("*** Verifying LoginTest: verityBlankPasswordLogin ***");
		lp.refreshPage();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword("");
		lp.clickLogin();
		String actPasswordReqMsg = lp.getPasswordReqMsg();
		Assert.assertEquals(actPasswordReqMsg, p.getProperty("PasswordReq"), "Password required error message does not match!");

	}

	@Test(priority = 4)
	public void verifyCorrectCredLogin() {
		logger.info("*** Verifying LoginTest: verityCorrectCredLogin ***");
		lp.refreshPage();
		lp.setEmail(p.getProperty("adminEmail"));
		lp.setPassword(p.getProperty("adminPassword"));
		lp.clickLogin();
		wait.until(ExpectedConditions.urlToBe(p.getProperty("dashboardUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("dashboardUrl"), "Login failed: url mismatch.");

	}

}
