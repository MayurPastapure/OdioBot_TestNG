package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	public void setupCRMGroupsObjects() {
		crm = new CRMPage(driver);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		ap = new AgentsPage(driver);
	}
	
	@Test(priority = 0)
	public void verifyPageIsCRM_UsersPage() {
		logger.info("*** Verifying test case: verifyPageIsCRM_UsersPage ***");
		hp.openMoreOption();
		crm.openCRMPage();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, p.getProperty("CRMPageTitle"), "CRM_user page title does not match!");
	}

	@Test(priority = 1)
	public void verifyNullGroupName() {
		logger.info("*** Verifying test case: verifyNullGroupName ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullGroupName"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullMsg, p.getProperty("expNullGroupName"), "Group name error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test(priority = 2)
	public void verifyTotalGroupCountOnPagination() throws InterruptedException {
		logger.info("*** Verify test case: verifyTotalUserCountOnPagination ***");
		crm.openGroupTab();
		int userCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total group count: " + userCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(userCount, rowCount, "Group pagination count is not with group list count");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 3)
	public void verifyGroupNameCreationWithValidDate() {
		logger.info("*** Verifying test case: verifyGroupNameCreationWithValidDate ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.setGroupName(p.getProperty("groupName"));
		crm.setWebsiteName(p.getProperty("websiteName"));
		crm.clickCreateUser_Group();
		String actMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expgroupCreateSuccessMsg"), "Group creation error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

//	@Test(priority = 4, dependsOnMethods = {"verifyGroupNameCreationWithValidDate"}) // Error message is not received on UI (FE issue)
//	public void verifyDuplicateGroupNameCreation() {
//		logger.info("*** Verifying test case: verifyDuplicateGroupNameCreation ***");
//		crm.openGroupTab();
//		crm.clickOnAddUsers_AddGroups();
//		crm.setGroupName(p.getProperty("groupName"));
//		crm.setWebsiteName(p.getProperty("websiteName"));
//		crm.clickCreateUser_Group();
//		String actMsg = crm.getToastMessage();
//		SoftAssert sa = new SoftAssert();
//		sa.assertEquals(actMsg, p.getProperty("expDuplicateGroupNameMsg"), "Duplicate group error message does not match!");
//		lp.refreshPage();
//		sa.assertAll();
//	}


	@Test(priority = 5, dependsOnMethods = {"verifyGroupNameCreationWithValidDate"})
	public void verifySearchByGroupName() throws InterruptedException {
		logger.info("*** Verify test case: verifySearchByGroupName ***");
		lp.refreshPage();
		crm.openGroupTab();
		Boolean isAvailable = crm.searchByName(p.getProperty("groupName"));
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(isAvailable, "Group name is not found in search result");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test(priority = 6, dependsOnMethods = {"verifyGroupNameCreationWithValidDate"})
	public void verifyFilterBy_Name_IsEquals() throws InterruptedException {
		logger.info("*** Verify test case: verifyFilterBy_Name_IsEquals ***");
		crm.openGroupTab();
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.selectFilterCondition(p.getProperty("condition_IsEquals"));
		crm.setConditionValue(p.getProperty("groupName"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total group filter by Name_IsEquals: " + p.getProperty("groupName") + ":= " + filterCount);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(filterCount >= 1, "Data is not available as per filter name IsEquals");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test(priority = 7, dependsOnMethods = {"verifyGroupNameCreationWithValidDate"})
	public void verifyFilterBy_CompanyWebsite_IsEquals() throws InterruptedException {
		logger.info("*** Verify test case: verifyFilterBy_CompanyWebsite_IsEquals ***");
		crm.openGroupTab();
		crm.selectFilterOption(p.getProperty("filterOption_CompanyWebsite"));
		crm.selectFilterCondition(p.getProperty("condition_IsEquals"));
		crm.setConditionValue(p.getProperty("websiteName"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total group filter by Website_IsEquals: " + p.getProperty("websiteName") + ":= " + filterCount);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(filterCount >= 1, "Data is not available as per filter website IsEquals");
		lp.refreshPage();
		sa.assertAll();
	}
	
//	@Test(priority = 8, dependsOnMethods = {"verifyGroupNameCreationWithValidDate"}) // BE API gives 500 error while update (BE issue)
//	public void verifyEditGroupbyUpdatingName() {
//		logger.info("*** Verify test case: verifyEditGroupbyUpdatingName ***");
//		crm.openGroupTab();
//		ap.clickActionOfSpecificAgent(p.getProperty("groupName"));
//		ap.clickEditAction();
//		crm.setGroupName(p.getProperty("UpdatedGroupName"));
//		crm.clickCreateUser_Group();
//		String actMsg = crm.getToastMessage();
//		SoftAssert sa = new SoftAssert();
//		sa.assertEquals(actMsg, p.getProperty("expGroupCreateSuccessMsg"),
//				"Group updation toast message does not match!");
//		lp.refreshPage();
//		sa.assertAll();
//	}
	
	@Test(priority = 9, dependsOnMethods = { "verifyGroupNameCreationWithValidDate" })
	public void verifyDeleteGroupOfUpdatedName() {
		logger.info("*** Verify test case: verifyDeleteGroupOfUpdatedName ***");
		lp.refreshPage();
		crm.openGroupTab();
		ap.clickActionOfSpecificAgent(p.getProperty("groupName"));
		ap.clickDeleteAction();
		ap.clickYesDeleteOnConfirm();
		String actMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expDeleteMsg"), "Group delete toast message does not match!");
		sa.assertAll();
	}

}
