package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AgentsPage;
import pageObjects.CRMPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class CRM_GroupsTest extends BaseClass {
	CRMPage crm;
	HomePage hp;
	LoginPage lp;
	AgentsPage ap;

	@BeforeMethod
	public void setupCRMObjects() {
		crm = new CRMPage(driver);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		ap = new AgentsPage(driver);
	}

	@Test(priority = 1)
	public void verifyNullGroupName() {
		logger.info("*** Verifying test case: verifyNullGroupName ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullGroupName"));
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullGroupName"), "Group name error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = false)
	public void verifyGroupNameCreationWithValidDate() {
		logger.info("*** Verifying test case: verifyGroupNameCreationWithValidDate ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.setGroupName(p.getProperty("groupName"));
		crm.setWebsiteName(p.getProperty("websiteName"));
		crm.clickCreateUser_Group();
		String actMsg = crm.getToastMessage();
		softAssert.assertEquals(actMsg, p.getProperty("expgroupCreateSuccessMsg"), "Group creation error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = false) // Incomplete test case ###
	public void verifyDuplicateGroupNameCreation() {
		logger.info("*** Verifying test case: verifyDuplicateGroupNameCreation ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.setGroupName(p.getProperty("groupName"));
		crm.setWebsiteName(p.getProperty("websiteName"));
		crm.clickCreateUser_Group();
		String actMsg = crm.getToastMessage();
		softAssert.assertEquals(actMsg, p.getProperty(""), "Duplicate group error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 4)
	public void verifyTotalGroupCountOnPagination() throws InterruptedException {
		logger.info("*** Verify test case: verifyTotalUserCountOnPagination ***");
		crm.openGroupTab();
		int userCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total group count: " + userCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		softAssert.assertEquals(userCount, rowCount, "Group pagination count is not with group list count");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void verifySearchByGroupName() throws InterruptedException {
		logger.info("*** Verify test case: verifySearchByGroupName ***");
		crm.openGroupTab();
		Boolean isAvailable = crm.searchByName(p.getProperty("groupNameSearch"));
		softAssert.assertTrue(isAvailable, "Group name is not found in search result");
		lp.refreshPage();
		softAssert.assertAll();
	}

}
