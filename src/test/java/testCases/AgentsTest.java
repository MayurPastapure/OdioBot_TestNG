package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
		logger.info("*** Verifying AgentsTest: verifyPageIsAgentsPage ***");
		ap.openAgentsPage();
		wait.until(ExpectedConditions.titleContains(p.getProperty("AgentsPageTitle")));
		Assert.assertEquals(driver.getTitle(), p.getProperty("AgentsPageTitle"));
	}

	@Test(priority = 1, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyBrokenLinksOfAgentsPage() {
		logger.info("*** Verifying AgentsTest: verifyBrokenLinksOfAgentsPage ***");
		int BrokenLinkCount = hp.checkBrokenLinks();
		Assert.assertEquals(BrokenLinkCount, 0, "Broken link is found on Agent page");
	}

	@Test(priority = 2, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyNullAgentName() {
		logger.info("*** Verifying AgentsTest: verifyNullAgentName ***");
		ap.openNewAgentCreatePopup();
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentNameMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullAgentNameMsg"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 3, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyNullDepartmentName() {
		logger.info("*** Verifying AgentsTest: verifyNullDepartmentName ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullDepartmentNameMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullDepartmentName"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 4, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyNullAgentEmail() {
		logger.info("*** Verifying AgentsTest: verifyNullAgentEmail ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentEmailMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullAgentEmailMsg"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 5, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyNullAgentMobileNumber() {
		logger.info("*** Verifying AgentsTest: verifyNullAgentMobileNumber ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullAgentMobileNoMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullMobileNoMsg"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 6, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyNullPassword() {
		logger.info("*** Verifying AgentsTest: verifyNullPassword ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullPasswordMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullPasswordMsg"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 7, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyNullConfirmPassword() {
		logger.info("*** Verifying AgentsTest: verifyNullConfirmPassword ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.setPassword(p.getProperty("Password"));
		ap.clickCreateAgentButton();
		String actErrorMsg = ap.getNullConfirmPasswordMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullConfirmPasswordMsg"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 8, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyNewAgentCreationWithValidData() {
		logger.info("*** Verifying AgentsTest: verifyNewAgentCreationWithValidData ***");
		ap.openNewAgentCreatePopup();
		ap.setAgentName(p.getProperty("AgentName"));
		ap.selectDepartmentName(p.getProperty("DepartmentName"));
		ap.setAgentEmail(p.getProperty("AgentEmail"));
		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
		ap.setPassword(p.getProperty("Password"));
		ap.setConfirmPassword(p.getProperty("ConfirmPassword"));
		ap.clickCreateAgentButton();
		String actToastMsg = ap.getToastAlertMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actToastMsg, "Success", "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

//	@Test(priority = 9, dependsOnMethods = {"verifyNewAgentCreationWithValidData"})
//	public void verifyDuplicateAgentCreationWithSameEmail() {
//		logger.info("*** Verifying AgentsTest: verifyDuplicateAgentCreationWithSameEmail ***");
//		ap.openNewAgentCreatePopup();
//		ap.setAgentName(p.getProperty("AgentName"));
//		ap.selectDepartmentName(p.getProperty("DepartmentName"));
//		ap.setAgentEmail(p.getProperty("AgentEmail"));
//		ap.setAgentMobileNo(p.getProperty("AgentMobileNo"));
//		ap.setPassword(p.getProperty("Password"));
//		ap.setConfirmPassword(p.getProperty("ConfirmPassword"));
//		ap.clickCreateAgentButton();
//		String actToastMsg = ap.getToastAlertMsg();
//		SoftAssert sa = new SoftAssert();
//		sa.assertEquals(actToastMsg, p.getProperty("expDuplicateEmailMsg"), "Error message does not match!");
//		lp.refreshPage();
//		sa.assertAll();
//	}

//	@Test(priority = 10, dependsOnMethods = {"verifyNewAgentCreationWithValidData"}) // Search functionality is not work
//	public void verifySearchByAgentName() {
//		logger.info("*** Verifying AgentsTest: verifySearchByAgentName ***");
//		Boolean isAvailable = ap.searchByUserName(p.getProperty("AgentName"));
//		SoftAssert sa = new SoftAssert();
//		sa.assertTrue(isAvailable, "Agent name is not found in search result");
//		lp.refreshPage();
//		sa.assertAll();
//	}

	@Test(priority = 11, dependsOnMethods = {"verifyPageIsAgentsPage"})
	public void verifyTotalAgentCountOnPagination() throws InterruptedException {
		logger.info("*** Verifying AgentsTest: verifyTotalAgentCountOnPagination ***");
		int agentCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total agent count: " + agentCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(agentCount, rowCount, "Agent pagination count is not with agent list count");
		lp.refreshPage();
		sa.assertAll();
	}

//	@Test(priority = 12, dependsOnMethods = {"verifyPageIsAgentsPage"}) // Incomplete test case due to search functionality is not work
//	public void verifyEditAgentbyUpdatingName() {
//		logger.info("*** Verifying AgentsTest: verifyEditAgentbyUpdatingName ***");
//		ap.searchByUserName(p.getProperty("AgentName"));
//		ap.clickActionOfSpecificAgent(p.getProperty("AgentName"));
//		ap.clickEditAction();
//		ap.clickCreateAgentButton();
//		lp.refreshPage();
//	}

//	@Test(priority = 13, dependsOnMethods = {"verifyPageIsAgentsPage"}) // Incomplete test case due to search functionality is not work
//	public void verifyDeleteSpecificAgent() {
//		logger.info("*** Verifying AgentsTest: verifyDeleteSpecificAgent ***");
//		ap.searchByUserName(p.getProperty("AgentName"));
//		ap.clickActionOfSpecificAgent(p.getProperty("AgentName"));
//		ap.clickDeleteAction();
//		ap.clickNoDeleteOnConfirm();
//		lp.refreshPage();
//	}

}
