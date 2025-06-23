package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AgentsPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class AgentsTest extends BaseClass {

	AgentsPage ap;
	LoginPage lp;

	@BeforeMethod
	public void setupAgentsObjects() {
		ap = new AgentsPage(driver);
		lp = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyPageIsAgentsPage() {
		logger.info("*** Verifying test case: verifyPageIsAgentsPage ***");
		ap.openAgentsPage();
		wait.until(ExpectedConditions.titleContains(p.getProperty("AgentsPageTitle")));
		Assert.assertEquals(driver.getTitle(), p.getProperty("AgentsPageTitle"));
	}

	@Test(priority = 2)
	public void verifyNullAgentName() {
		logger.info("*** Verifying test case: verifyNullAgentName ***");
		ap.openNewAgentCreatePopup();
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentNameMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullAgentNameMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void verifyNullAgentEmail() {
		logger.info("*** Verify test case: verifyNullAgentEmail ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentEmailMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullAgentEmailMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 4)
	public void verifyNullAgentMobileNumber() {
		logger.info("*** Verify test case: verifyNullAgentMobileNumber ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentMobileNoMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullMobileNoMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void verifyNullPassword() {
		logger.info("*** Verify test case: verifyNullPassword ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullPasswordMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullPasswordMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 6)
	public void verifyNullConfirmPassword() {
		logger.info("*** Verify test case: verifyNullConfirmPassword ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.setPassword(p.getProperty("Password"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullConfirmPasswordMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullConfirmPasswordMsg"),
				"Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}
	
	@Test(priority = 7)
	public void verifyNewAgentCreationWithValidData() {
		logger.info("*** Verify test case: verifyNewAgentCreationWithValidData ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.setPassword(p.getProperty("Password"));
		ap.setConfirmPassword(p.getProperty("ConfirmPassword"));
		ap.clickCreateAgentButton();
		String actToastMsg = ap.getToastAlertMsg();
		softAssert.assertEquals(actToastMsg, "Success", "Error message does not match!");
		softAssert.assertAll();
	}
	
	@Test(priority = 8)
	public void verifyDuplicateAgentCreationWithSameEmail() {
		logger.info("*** Verify test case: verifyDuplicateAgentCreationWithSameEmail ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.setPassword(p.getProperty("Password"));
		ap.setConfirmPassword(p.getProperty("ConfirmPassword"));
		ap.clickCreateAgentButton();
		String actToastMsg = ap.getToastAlertMsg();
		softAssert.assertEquals(actToastMsg, p.getProperty("expDuplicateEmailMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}
	
	@Test(priority=9)
	public void verifySearchByUserName() {
		logger.info("*** Verify test case: verifySearchByUserName ***");
		Boolean isAvailable = ap.searchByUserName(p.getProperty("AgentName"));
		Assert.assertTrue(isAvailable, "Agent name is not found in search result");
	}

}
