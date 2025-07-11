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

public class CRM_CustomersTest extends BaseClass {
	CRMPage crm;
	HomePage hp;
	LoginPage lp;
	AgentsPage ap;

	@BeforeMethod
	public void setupCRMUsersObjects() {
		crm = new CRMPage(driver);
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		ap = new AgentsPage(driver);
	}

	@Test(priority = 0)
	public void verifyPageIsCRM_CustomerPage() {
		logger.info("*** Verifying CRM_CustomersTest: verifyPageIsCRM_UsersPage ***");
		hp.openMoreOption();
		crm.openCRMPage();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, p.getProperty("CRMPageTitle"), "CRM_user page title does not match!");
	}

	@Test(priority = 1, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyBrokenLinksOfCRM_CustomerPage() {
		logger.info("*** Verifying CRM_CustomersTest: verifyBrokenLinksOfCRM_CustomerPage ***");
		int BrokenLinkCount = hp.checkBrokenLinks();
		Assert.assertEquals(BrokenLinkCount, 0, "Broken link is found on CRM_Customers page");
	}

//	@Test(priority = 2, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"} enabled = false)
//	public void verifyDownloadSampleCSV() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyDownloadSampleCSV ***");
//		crm.clickUploadCSV_BulkUsers();
//		crm.clickDownloadSampleCSV();
//		String actToast = crm.getToastMessage();
//		SoftAssert sa = new SoftAssert();
//		sa.assertEquals(actToast, p.getProperty("expDownloadStratMsg"),
//				"Download sample cvs toast message does not match!");
//		Thread.sleep(4000);
//		boolean exist = crm.isFileExist(p.getProperty("csvFileName"));
//		sa.assertTrue(exist, "File does not exist");
//		sa.assertAll();
//	}

//	@Test(priority = 3, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"} enabled = false)
//	public void verifyUploadBulkUserFile() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyUploadBulkUserFile ***");
//		crm.clickUploadCSV_BulkUsers();
//		crm.clickAndUploadBulkUserFile(p.getProperty("uploadFilePath"), p.getProperty("uploadFileName"));
//		String actToastMsg = crm.getToastMessage();
//		logger.info("File upload message: " + actToastMsg);
//		SoftAssert sa = new SoftAssert();
//		sa.assertEquals(actToastMsg, p.getProperty("expFileUploadMsg"), "File upload message does not match!");
//		sa.assertAll();
//	}

	@Test(priority = 4, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyNullCustomerName() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNullCustomerName ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.clickCreateCustomer_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullCustomerName"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullMsg, p.getProperty("expNullCustomerName"), "Null Customer name error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 5, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyNullMobileNumber() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNullMobileNumber ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.setCustomerName(p.getProperty("CustomerName"));
		crm.clickCreateCustomer_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullMobileNumber"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullMsg, p.getProperty("expNullMobileNumber"), "Null mobile error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 6, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyNullHandle() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNullHandle ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.setCustomerName(p.getProperty("CustomerName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.clickCreateCustomer_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullHandle"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullMsg, p.getProperty("expNullHandle"), "Null handle error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 7, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyNullEmail() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNullEmail ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.setCustomerName(p.getProperty("CustomerName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.clickCreateCustomer_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullEmail"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullMsg, p.getProperty("expNullEmail"), "Null email error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 8, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyNullDepartment() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNullDepartment ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.setCustomerName(p.getProperty("CustomerName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.setEmail(p.getProperty("email"));
		crm.clickCreateCustomer_Group();
		String actNullMsg = crm.getNullErrorMsgs(p.getProperty("expNullDepartment"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullMsg, p.getProperty("expNullDepartment"),
				"Null department error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 9, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyNewCustomerCreationWithValidData() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNewCustomerCreationWithValidData ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.setCustomerName(p.getProperty("CustomerName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.setEmail(p.getProperty("email"));
		crm.selectDepartment(p.getProperty("department"));
		crm.selectGroup(p.getProperty("group"));
		crm.clickOnAddInfo();
		crm.setKey(p.getProperty("key"));
		crm.setDescription(p.getProperty("description"));
		crm.clickCreateCustomer_Group();
		String actToastMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actToastMsg, p.getProperty("expCustomerCreateSuccessMsg"),
				"Customer creation toast message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 10, dependsOnMethods = {"verifyNewCustomerCreationWithValidData"})
	public void verifyNewCustomerCreationWithDuplicateHandle() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNewCustomerCreationWithDuplicateHandle ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.setCustomerName(p.getProperty("CustomerName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handle"));
		crm.setEmail(p.getProperty("email"));
		crm.selectDepartment(p.getProperty("department"));
		crm.selectGroup(p.getProperty("group"));
		crm.clickCreateCustomer_Group();
		String actToastMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actToastMsg, p.getProperty("expHandleExistMsg"),
				"Duplicate handle toast message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 11, dependsOnMethods = {"verifyNewCustomerCreationWithValidData"})
	public void verifyNewCustomerCreationWithDuplicateEmail() {
		logger.info("*** Verifying CRM_CustomersTest: verifyNewCustomerCreationWithDuplicateEmail ***");
		crm.clickOnAddCustomer_AddGroups();
		crm.setCustomerName(p.getProperty("CustomerName"));
		crm.setMobileNumber(p.getProperty("mobileNumber"));
		crm.setHandle(p.getProperty("handleNew"));
		crm.setEmail(p.getProperty("email"));
		crm.selectDepartment(p.getProperty("department"));
		crm.selectGroup(p.getProperty("group"));
		crm.clickCreateCustomer_Group();
		String actToastMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actToastMsg, p.getProperty("expEmailExistMsg"),
				"Duplicate email toast message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 12, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyTotalCustomerCountOnPagination() throws InterruptedException {
		logger.info("*** Verifying CRM_CustomersTest: verifyTotalCustomerCountOnPagination ***");
		int customerCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total Customer count: " + customerCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(customerCount, rowCount, "Customer pagination count is not match with Customer list count");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 13, dependsOnMethods = {"verifyNewCustomerCreationWithValidData"})
	public void verifySearchByCustomerName() throws InterruptedException {
		logger.info("*** Verifying CRM_CustomersTest: verifySearchByCustomerName ***");
		Boolean isAvailable = crm.searchByName(p.getProperty("CustomerNameSearch"));
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(isAvailable, "Customer name is not found in search result");
		lp.refreshPage();
		sa.assertAll();
	}

//	@Test(priority = 14) // Incomplete test case due to search customer has issue
//	public void verifyEditCustomerbyUpdatingName() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyEditCustomerbyUpdatingName ***");
//		crm.searchByName(p.getProperty("CustomerNameSearch"));
//		ap.clickActionOfSpecificAgent(p.getProperty("CustomerNameSearch"));
//		ap.clickEditAction();
//		crm.clickCreateUser_Group();
//		lp.refreshPage();
//	}

//	@Test(priority = 15) // Incomplete test case due to search customer has issue
//	public void verifyDeleteSpecificCustomer() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyDeleteSpecificCustomer ***");
//		crm.searchByName(p.getProperty("CustomerNameSearch"));
//		ap.clickActionOfSpecificAgent(p.getProperty("CustomerNameSearch"));
//		ap.clickDeleteAction();
//		ap.clickNoDeleteOnConfirm();
//		lp.refreshPage();
//	}

	@Test(priority = 16, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyNullConditionValueInFilter() throws InterruptedException {
		logger.info("*** Verifying CRM_CustomersTest: verifyNullConditionValueInFilter ***");
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.clickApplyFiler();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullConditionValueMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullConditionValueMsg"),
				"Null condition value error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 17, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
	public void verifyFilterBy_Name_Contains() throws InterruptedException {
		logger.info("*** Verifying CRM_CustomersTest: verifyFilterBy_Name_Contains ***");
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.selectFilterCondition(p.getProperty("condition_Contains"));
		crm.setConditionValue(p.getProperty("conditionValue"));
		crm.clickApplyFiler();
		int filterCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total user filter by Name_Contains: " + p.getProperty("conditionValue") + ":= " + filterCount);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(filterCount >= 1, "Data is not available as per filter name contains");
		lp.refreshPage();
		sa.assertAll();
	}

//	@Test(priority = 18, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
//	public void verifyFilterBy_Name_DoesNotContains() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyFilterBy_Name_DoesNotContains ***");
//		crm.selectFilterOption(p.getProperty("filterOption_Name"));
//		crm.selectFilterCondition(p.getProperty("condition_DoesNotContain"));
//		crm.setConditionValue(p.getProperty("conditionValue"));
//		crm.clickApplyFiler();
//		int filterCount = ap.getTotalAgentCountFromPagination();
//		logger.info(
//				"Total user filter by Name_DoesNotContain: " + p.getProperty("conditionValue") + ":= " + filterCount);
//		SoftAssert sa = new SoftAssert();
//		sa.assertTrue(filterCount >= 1, "Data is not available as per filter name does not contains");
//		lp.refreshPage();
//		sa.assertAll();
//	}

//	@Test(priority = 19, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
//	public void verifyFilterBy_Email_IsEqual() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyFilterBy_Email_IsEqual ***");
//		crm.selectFilterOption(p.getProperty("filterOption_Email"));
//		crm.selectFilterCondition(p.getProperty("condition_IsEquals"));
//		crm.setConditionValue(p.getProperty("condValue_Email"));
//		crm.clickApplyFiler();
//		int filterCount = ap.getTotalAgentCountFromPagination();
//		logger.info("Total user filter by Email_IsEqual: " + p.getProperty("condValue_Email") + ":= " + filterCount);
//		SoftAssert sa = new SoftAssert();
//		sa.assertTrue(filterCount >= 1, "Data is not available as per filter email is equals");
//		lp.refreshPage();
//		sa.assertAll();
//	}

//	@Test(priority = 20, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
//	public void verifyFilterBy_Email_IsNotEqual() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyFilterBy_Email_IsNotEqual ***");
//		crm.selectFilterOption(p.getProperty("filterOption_Email"));
//		crm.selectFilterCondition(p.getProperty("condition_IsNotEquals"));
//		crm.setConditionValue(p.getProperty("condValue_Email"));
//		crm.clickApplyFiler();
//		int filterCount = ap.getTotalAgentCountFromPagination();
//		logger.info("Total user filter by Email_IsNotEqual: " + p.getProperty("condValue_Email") + ":= " + filterCount);
//		SoftAssert sa = new SoftAssert();
//		sa.assertTrue(filterCount >= 1, "Data is not available as per filter email is not equals");
//		lp.refreshPage();
//		sa.assertAll();
//	}

//	@Test(priority = 21, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"})
//	public void verifyFilterBy_Mobile_EndsWith() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyFilterBy_Mobile_EndsWith ***");
//		crm.selectFilterOption(p.getProperty("filterOption_Mobile"));
//		crm.selectFilterCondition(p.getProperty("condition_EndsWith"));
//		crm.setConditionValue(p.getProperty("condValue_MobileEnd"));
//		crm.clickApplyFiler();
//		int filterCount = ap.getTotalAgentCountFromPagination();
//		logger.info(
//				"Total user filter by Mobile_EndsWith: " + p.getProperty("condValue_MobileEnd") + ":= " + filterCount);
//		lp.refreshPage();
//		Assert.assertTrue(filterCount >= 1, "Data is not available as per filter mobile number ends with");
//	}

//	@Test(priority = 22, dependsOnMethods = {"verifyPageIsCRM_CustomerPage"}) // Filter by mobile starts with is not work from BE
//	public void verifyFilterBy_Mobile_StartsWith() throws InterruptedException {
//		logger.info("*** Verifying CRM_CustomersTest: verifyFilterBy_Mobile_StartsWith ***");
//		crm.selectFilterOption(p.getProperty("filterOption_Mobile"));
//		crm.selectFilterCondition(p.getProperty("condition_StartsWith"));
//		crm.setConditionValue(p.getProperty("condValue_MobileStart"));
//		crm.clickApplyFiler();
//		int filterCount = ap.getTotalAgentCountFromPagination();
//		logger.info("Total user filter by Mobile_StartsWith: " + p.getProperty("condValue_MobileStart") + ":= "
//				+ filterCount);
//		SoftAssert sa = new SoftAssert();
//		sa.assertTrue(filterCount >= 1, "Data is not available as per filter mobile number starts with");
//		lp.refreshPage();
//		sa.assertAll();
//	}

}
