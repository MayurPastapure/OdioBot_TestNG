package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AgentsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class AgentsTest extends BaseClass {

	AgentsPage ap;
	LoginPage lp;
	HomePage hp;

	@BeforeMethod
	public void setupAgentsObjects() {
		ap = new AgentsPage(driver);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}

	@Test(priority = 0)
	public void verifyPageIsAgentsPage() {
		logger.info("*** Verifying test case: verifyPageIsAgentsPage ***");
		ap.openAgentsPage();
		wait.until(ExpectedConditions.titleContains(p.getProperty("AgentsPageTitle")));
		Assert.assertEquals(driver.getTitle(), p.getProperty("AgentsPageTitle"));
	}
	
	@Test(priority = 1)
	public void verifyBrokenLinksOfAgentsPage() {
		logger.info("*** Verifying test case: verifyBrokenLinksOfAgentsPage ***");
		int BrokenLinkCount = hp.checkBrokenLinks();
		Assert.assertEquals(BrokenLinkCount, 0, "Broken link is found on Agent page");
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
	public void verifyNullDepartmentName() {
		logger.info("*** Verify test case: verifyNullDepartmentName ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullDepartmentNameMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullDepartmentName"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 4)
	public void verifyNullAgentEmail() {
		logger.info("*** Verify test case: verifyNullAgentEmail ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentEmailMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullAgentEmailMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void verifyNullAgentMobileNumber() {
		logger.info("*** Verify test case: verifyNullAgentMobileNumber ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentMobileNoMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullMobileNoMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 6)
	public void verifyNullPassword() {
		logger.info("*** Verify test case: verifyNullPassword ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullPasswordMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullPasswordMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 7)
	public void verifyNullConfirmPassword() {
		logger.info("*** Verify test case: verifyNullConfirmPassword ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
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

	@Test(priority = 8, enabled = false)
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

	@Test(priority = 9, enabled = false)
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

	@Test(priority = 10)
	public void verifySearchByAgentName() {
		logger.info("*** Verify test case: verifySearchByAgentName ***");
		Boolean isAvailable = ap.searchByUserName(p.getProperty("AgentName"));
		softAssert.assertTrue(isAvailable, "Agent name is not found in search result");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 11)
	public void verifyTotalAgentCountOnPagination() throws InterruptedException {
		logger.info("*** Verify test case: verifyTotalAgentCountOnPagination ***");
		int agentCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total agent count: " + agentCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		softAssert.assertEquals(agentCount, rowCount, "Agent pagination count is not with agent list count");
		lp.refreshPage();
		softAssert.assertAll();
	}

//	@Test(priority = 12) // Incomplete test case
//	public void verifyEditAgentbyUpdatingName() {
//		logger.info("*** Verify test case: verifyEditAgentbyUpdatingName ***");
//		ap.searchByUserName(p.getProperty("AgentName"));
//		ap.clickActionOfSpecificAgent(p.getProperty("AgentName"));
//		ap.clickEditAction();
//		ap.clickCreateAgentButton();
//		lp.refreshPage();
//	}

//	@Test(priority = 13) // Incomplete test case
//	public void verifyDeleteSpecificAgent() {
//		logger.info("*** Verify test case: verifyDeleteSpecificAgent ***");
//		ap.searchByUserName(p.getProperty("AgentName"));
//		ap.clickActionOfSpecificAgent(p.getProperty("AgentName"));
//		ap.clickDeleteAction();
//		ap.clickNoDeleteOnConfirm();
//		lp.refreshPage();
//	}

}
