package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AgentsPage;
import pageObjects.CRMPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class CRM_UsersTest extends BaseClass {
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
		Assert.assertEquals(actTitle, p.getProperty("CRMPageTitle"), "CRM_user page title does not match!");
	}

	@Test(priority = 2, enabled = false)
	public void verifyDownloadSampleCSV() throws InterruptedException {
		logger.info("*** Verifying test case: verifyDownloadSampleCSV ***");
		crm.clickUploadCSV_BulkUsers();
		crm.clickDownloadSampleCSV();
		String actToast = crm.getToastMessage();
		softAssert.assertEquals(actToast, p.getProperty("expDownloadStratMsg"),
				"Download sample cvs toast message does not match!");
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
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullUserName"),
				"Null user name error message does not match!");
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
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullMobileNumber"),
				"Null mobile error message does not match!");
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
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullHandle"),
				"Null handle error message does not match!");
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
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullEmail"), "Null email error message does not match!");
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
		softAssert.assertEquals(actNullMsg, p.getProperty("expNullDepartment"),
				"Null department error message does not match!");
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
		softAssert.assertEquals(actToastMsg, p.getProperty("expUserCreateSuccessMsg"),
				"User creation toast message does not match!");
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
		softAssert.assertEquals(actToastMsg, p.getProperty("expHandleExistMsg"),
				"Duplicate handle toast message does not match!");
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
		softAssert.assertEquals(actToastMsg, p.getProperty("expEmailExistMsg"),
				"Duplicate email toast message does not match!");
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

	@Test(priority = 16)
	public void verifyNullConditionValueInFilter() throws InterruptedException {
		logger.info("*** Verify test case: verifyNullConditionValueInFilter ***");
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.clickApplyFiler();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullConditionValueMsg"));
		softAssert.assertEquals(actMsg, p.getProperty("expNullConditionValueMsg"),
				"Null condition value error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 17)
	public void verifyFilterBy_Name_Contains() throws InterruptedException {
		logger.info("*** Verify test case: verifyFilterBy_Name_Contains ***");
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.selectFilterCondition(p.getProperty("condition_Contains"));
		crm.setConditionValue(p.getProperty("conditionValue"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total user filter by Name_Contains: " + p.getProperty("conditionValue") + ":= " + filterCount);
		softAssert.assertTrue(filterCount >= 1, "Data is not available as per filter name contains");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 18)
	public void verifyFilterBy_Name_DoesNotContains() throws InterruptedException {
		logger.info("*** Verify test case: verifyFilterBy_Name_DoesNotContains ***");
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.selectFilterCondition(p.getProperty("condition_DoesNotContain"));
		crm.setConditionValue(p.getProperty("conditionValue"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info(
				"Total user filter by Name_DoesNotContain: " + p.getProperty("conditionValue") + ":= " + filterCount);
		softAssert.assertTrue(filterCount >= 1, "Data is not available as per filter name does not contains");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 19)
	public void verifyFilterBy_Email_IsEqual() throws InterruptedException {
		logger.info("*** Verify test case: verifyFilterBy_Email_IsEqual ***");
		crm.selectFilterOption(p.getProperty("filterOption_Email"));
		crm.selectFilterCondition(p.getProperty("condition_IsEquals"));
		crm.setConditionValue(p.getProperty("condValue_Email"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total user filter by Email_IsEqual: " + p.getProperty("condValue_Email") + ":= " + filterCount);
		softAssert.assertTrue(filterCount >= 1, "Data is not available as per filter email is equals");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 20)
	public void verifyFilterBy_Email_IsNotEqual() throws InterruptedException {
		logger.info("*** Verify test case: verifyFilterBy_Email_IsNotEqual ***");
		crm.selectFilterOption(p.getProperty("filterOption_Email"));
		crm.selectFilterCondition(p.getProperty("condition_IsNotEquals"));
		crm.setConditionValue(p.getProperty("condValue_Email"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total user filter by Email_IsNotEqual: " + p.getProperty("condValue_Email") + ":= " + filterCount);
		softAssert.assertTrue(filterCount >= 1, "Data is not available as per filter email is not equals");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 21)
	public void verifyFilterBy_Mobile_EndsWith() throws InterruptedException {
		logger.info("*** Verify test case: verifyFilterBy_Mobile_EndsWith ***");
		crm.selectFilterOption(p.getProperty("filterOption_Mobile"));
		crm.selectFilterCondition(p.getProperty("condition_EndsWith"));
		crm.setConditionValue(p.getProperty("condValue_MobileEnd"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info(
				"Total user filter by Mobile_EndsWith: " + p.getProperty("condValue_MobileEnd") + ":= " + filterCount);
		lp.refreshPage();
		Assert.assertTrue(filterCount >= 1, "Data is not available as per filter mobile number ends with");
	}

	@Test(priority = 22, enabled = false) // Filter by mobile starts with is not work from BE
	public void verifyFilterBy_Mobile_StartsWith() throws InterruptedException  {
		logger.info("*** Verify test case: verifyFilterBy_Mobile_StartsWith ***");
		crm.selectFilterOption(p.getProperty("filterOption_Mobile"));
		crm.selectFilterCondition(p.getProperty("condition_StartsWith"));
		crm.setConditionValue(p.getProperty("condValue_MobileStart"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total user filter by Mobile_StartsWith: " + p.getProperty("condValue_MobileStart") + ":= "
				+ filterCount);
		softAssert.assertTrue(filterCount >= 1, "Data is not available as per filter mobile number starts with");
		lp.refreshPage();
		softAssert.assertAll();
	}

}
