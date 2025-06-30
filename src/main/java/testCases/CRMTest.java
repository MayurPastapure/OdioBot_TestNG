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

	@Test(priority = 23, enabled = false)
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
	public void verifySearchByGroupName() {
		logger.info("*** Verify test case: verifySearchByGroupName ***");
		crm.openGroupTab();
		Boolean isAvailable = crm.searchByName(p.getProperty("groupNameSearch"));
		softAssert.assertTrue(isAvailable, "Group name is not found in search result");
		lp.refreshPage();
		softAssert.assertAll();
	}

}
