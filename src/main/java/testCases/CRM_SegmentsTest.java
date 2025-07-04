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

public class CRM_SegmentsTest extends BaseClass {
	CRMPage crm;
	HomePage hp;
	LoginPage lp;
	AgentsPage ap;

	@BeforeMethod
	public void setupCRMSegmentsObject() {
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
	public void verifyNullConditionValueOfFilterInSegment() {
		logger.info("*** Verify test case: verifyNullConditionValueOfFilterInSegment ***");
		crm.openSegmentsTab();
		crm.clickOnAddSegments();
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.clickApplyFiler();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullConditionValueMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullConditionValueMsg"),
				"Null condition value error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test(priority = 2)
	public void verifyTotalSegmentCountOnPagination() throws InterruptedException {
		logger.info("*** Verify test case: verifyTotalSegmentCountOnPagination ***");
		crm.openSegmentsTab();
		int userCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total segment count: " + userCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(userCount, rowCount, "Segment pagination count is not match with segment list count");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 3)
	public void verifyNewSegmentCreationByNameStartWith() {
		logger.info("*** Verify test case: verifyNewSegmentCreationByNameStartWith ***");
		crm.openSegmentsTab();
		crm.clickOnAddSegments();
		crm.selectFilterOption(p.getProperty("filterOption_Name"));
		crm.selectFilterCondition(p.getProperty("condition_StartsWith"));
		crm.setConditionValue(p.getProperty("segConditionValue"));
		crm.clickApplyFiler();
		crm.selectSegmentDepartment(p.getProperty("segDepartmentName"));
		crm.setSegmentName(p.getProperty("segmentName"));
		crm.clickApplySeg();
		String actToastMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actToastMsg, p.getProperty("expSegmentCreateSuccessMsg"),
				"Segment creation toast message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 4, dependsOnMethods = { "verifyNewSegmentCreationByNameStartWith" })
	public void verifyEditSegmentbyUpdatingName() {
		logger.info("*** Verify test case: verifyEditSegmentbyUpdatingName ***");
		crm.openSegmentsTab();
		crm.clickActionOfSpecificSegment(p.getProperty("segmentName"));
		ap.clickEditAction();
		crm.selectSegmentDepartment(p.getProperty("segDepartmentName"));
		crm.setSegmentName(p.getProperty("segmentUpdateName"));
		crm.clickUpdateSeg();
		String actMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expSegmentCreateSuccessMsg"),
				"Segment updation toast message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 5, dependsOnMethods = { "verifyEditSegmentbyUpdatingName" })
	public void verifyDeleteSegmentOfUpdatedName() {
		logger.info("*** Verify test case: verifyDeleteSegmentOfUpdatedName ***");
		crm.openSegmentsTab();
		crm.clickActionOfSpecificSegment(p.getProperty("segmentUpdateName"));
		ap.clickDeleteAction();
		ap.clickYesDeleteOnConfirm();
		String actMsg = crm.getToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expDeleteMsg"), "Segment delete toast message does not match!");
		sa.assertAll();
	}
	
	

}
