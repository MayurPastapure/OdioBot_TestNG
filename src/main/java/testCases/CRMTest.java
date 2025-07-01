package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AgentsPage;
import pageObjects.CRMPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class CRMTest extends BaseClass {
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
	public void verifyPageIsCRM_UsersPage() {
		logger.info("*** Verifying test case: verifyPageIsCRM_UsersPage ***");
		hp.openMoreOption();
		crm.openCRMPage();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, p.getProperty("CRMPageTitle"), "Page title does not match!");
	}

	@Test(priority = 2, enabled = false)
	public void verifyDownloadSampleCSV() throws InterruptedException {
		logger.info("*** Verifying test case: verifyDownloadSampleCSV ***");
		crm.clickUploadCSV_BulkUsers();
		crm.clickDownloadSampleCSV();
		String actToast = crm.getToastMessage();
		softAssert.assertEquals(actToast, p.getProperty("expDownloadStratMsg"), "Toast message does not match!");
		Thread.sleep(4000);
		boolean exist = crm.isFileExist(p.getProperty("csvFileName"));
		softAssert.assertTrue(exist, "File does not exist");
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = false)
	public void verifyUploadBulkUserFile() throws InterruptedException {
		logger.info("*** Verifying test case: verifyUploadBulkUserFile ***");
		crm.clickUploadCSV_BulkUsers();
		crm.clickAndUploadBulkUserFile(p.getProperty("uploadFilePath"), p.getProperty("uploadFileName"));
		String actToastMsg = crm.getToastMessage();
		logger.info("File upload message: " + actToastMsg);
		softAssert.assertEquals(actToastMsg, p.getProperty("expFileUploadMsg"), "File upload message does not match!");
		softAssert.assertAll();
	}

	@Test(priority = 4)
	public void verifyNullUserName() {
		logger.info("*** Verifying test case: verifyNullUserName ***");
		crm.clickOnAddUsers_AddGroups();
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullUserName"));
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullUserName"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void verifyNullMobileNumber() {
		logger.info("*** Verifying test case: verifyNullMobileNumber ***");
		crm.clickOnAddUsers_AddGroups();
		crm.setUserName(p.getProperty("userName"));
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullMobileNumber"));
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullMobileNumber"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 6)
	public void verifyNullHandle() {
		logger.info("*** Verifying test case: verifyNullHandle ***");
		crm.clickOnAddUsers_AddGroups();
		crm.setUserName(p.getProperty("userName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullHandle"));
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullHandle"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 7)
	public void verifyNullEmail() {
		logger.info("*** Verifying test case: verifyNullEmail ***");
		crm.clickOnAddUsers_AddGroups();
		crm.setUserName(p.getProperty("userName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullEmail"));
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullEmail"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 8)
	public void verifyNullDepartment() {
		logger.info("*** Verifying test case: verifyNullDepartment ***");
		crm.clickOnAddUsers_AddGroups();
		crm.setUserName(p.getProperty("userName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.setEmail(p.getProperty("email"));
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullDepartment"));
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullDepartment"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 9, enabled = false)
	public void verifyNewUserCreationWithValidData() {
		logger.info("*** Verifying test case: verifyNewUserCreationWithValidData ***");
		crm.clickOnAddUsers_AddGroups();
		crm.setUserName(p.getProperty("userName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.setEmail(p.getProperty("email"));
		crm.selectDepartment(p.getProperty("department"));
		crm.selectGroup(p.getProperty("group"));
		crm.clickOnAddInfo();
		crm.setKey(p.getProperty("key"));
		crm.setDescription(p.getProperty("description"));
		crm.clickCreateUser_Group();
		String actToastMsg = crm.getToastMessage();
		softAssert.assertEquals(actToastMsg, p.getProperty("expUserCreateSuccessMsg"), "Toast message does not match!");
		softAssert.assertAll();
	}

	@Test(priority = 10)
	public void verifyNewUserCreationWithDuplicateHandle() {
		logger.info("*** Verifying test case: verifyNewUserCreationWithDuplicateHandle ***");
		crm.clickOnAddUsers_AddGroups();
		crm.setUserName(p.getProperty("userName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.setEmail(p.getProperty("email"));
		crm.selectDepartment(p.getProperty("department"));
		crm.selectGroup(p.getProperty("group"));
		crm.clickCreateUser_Group();
		String actToastMsg = crm.getToastMessage();
		softAssert.assertEquals(actToastMsg, p.getProperty("expHandleExistMsg"), "Toast message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 11)
	public void verifyNewUserCreationWithDuplicateEmail() {
		logger.info("*** Verifying test case: verifyNewUserCreationWithDuplicateEmail ***");
		crm.clickOnAddUsers_AddGroups();
		crm.setUserName(p.getProperty("userName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handleNew"));
		crm.setEmail(p.getProperty("email"));
		crm.selectDepartment(p.getProperty("department"));
		crm.selectGroup(p.getProperty("group"));
		crm.clickCreateUser_Group();
		String actToastMsg = crm.getToastMessage();
		softAssert.assertEquals(actToastMsg, p.getProperty("expEmailExistMsg"), "Toast message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 12)
	public void verifyTotalUserCountOnPagination() throws InterruptedException {
		logger.info("*** Verify test case: verifyTotalUserCountOnPagination ***");
		int userCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total user count: " + userCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		softAssert.assertEquals(userCount, rowCount, "User pagination count is not with agent list count");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 13)
	public void verifySearchByUserName() throws InterruptedException {
		logger.info("*** Verify test case: verifySearchByUserName ***");
		Boolean isAvailable = crm.searchByName(p.getProperty("userNameSearch"));
		softAssert.assertTrue(isAvailable, "User name is not found in search result");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 14, enabled = false) // Incomplete test case ###
	public void verifyEditUserbyUpdatingName() throws InterruptedException {
		logger.info("*** Verify test case: verifyEditUserbyUpdatingName ***");
		crm.searchByName(p.getProperty("userNameSearch"));
		ap.clickActionOfSpecificAgent(p.getProperty("userNameSearch"));
		ap.clickEditAction();
		crm.clickCreateUser_Group();
		lp.refreshPage();
	}

	@Test(priority = 15, enabled = false) // Incomplete test case ###
	public void verifyDeleteSpecificUser() throws InterruptedException {
		logger.info("*** Verify test case: verifyDeleteSpecificUser ***");
		crm.searchByName(p.getProperty("userNameSearch"));
		ap.clickActionOfSpecificAgent(p.getProperty("userNameSearch"));
		ap.clickDeleteAction();
		ap.clickNoDeleteOnConfirm();
		lp.refreshPage();
	}

	@Test(priority = 16) // In Complete test case ###
	public void verifyFilterByName() {
		logger.info("*** Verify test case: verifyFilterByName ***");
		crm.selectFilterOption(p.getProperty("filterOption"));
		crm.selectFilterCondition(p.getProperty("conditionOption"));
		crm.setConditionValue(p.getProperty("conditionValue"));
		crm.clickApplyFiler();

	}

	@Test(priority = 21)
	public void verifyNullGroupName() {
		logger.info("*** Verifying test case: verifyNullGroupName ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.clickCreateUser_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullGroupName"));
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullGroupName"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 22, enabled = false)
	public void verifyGroupNameCreationWithValidDate() {
		logger.info("*** Verifying test case: verifyGroupNameCreationWithValidDate ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.setGroupName(p.getProperty("groupName"));
		crm.setWebsiteName(p.getProperty("websiteName"));
		crm.clickCreateUser_Group();
		String actMsg = crm.getToastMessage();
		softAssert.assertEquals(actMsg, p.getProperty("expgroupCreateSuccessMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 23, enabled = false) // Incomplete test case ###
	public void verifyDuplicateGroupNameCreation() {
		logger.info("*** Verifying test case: verifyDuplicateGroupNameCreation ***");
		crm.openGroupTab();
		crm.clickOnAddUsers_AddGroups();
		crm.setGroupName(p.getProperty("groupName"));
		crm.setWebsiteName(p.getProperty("websiteName"));
		crm.clickCreateUser_Group();
		String actMsg = crm.getToastMessage();
		softAssert.assertEquals(actMsg, p.getProperty("expDuplicateGroupNameMsg"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 24)
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

	@Test(priority = 25)
	public void verifySearchByGroupName() throws InterruptedException {
		logger.info("*** Verify test case: verifySearchByGroupName ***");
		crm.openGroupTab();
		Boolean isAvailable = crm.searchByName(p.getProperty("groupNameSearch"));
		softAssert.assertTrue(isAvailable, "Group name is not found in search result");
		lp.refreshPage();
		softAssert.assertAll();
	}

}
