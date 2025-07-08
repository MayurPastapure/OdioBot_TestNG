package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AgentsPage;
import pageObjects.CRMPage;
import pageObjects.CampaignsPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class CampaignsTest extends BaseClass {
	CampaignsPage campaign;
	AgentsPage ap;
	CRMPage crm;
	LoginPage lp;

	@BeforeMethod
	public void setupCampaignsTestObject() {
		campaign = new CampaignsPage(driver);
		ap = new AgentsPage(driver);
		crm = new CRMPage(driver);
		lp = new LoginPage(driver);
	}
	
	@Test (priority = 1)
	public void verifyPageIsCampaignPage() {
		logger.info("*** Verifying test case: verifyPageIsCampaignPage ***");
		campaign.openCampaignsPage();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, p.getProperty("CampaignPageTitle"), "Campaign page title does not match!");
		}
	
	@Test (priority = 2)
	public void verifyNullCampaignName() {
		logger.info("*** Verifying test case: verifyNullCampaignName ***");
		campaign.openNewCampaignPopup();
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullCampaignNameMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullCampaignNameMsg"), "Campaign name required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 3)
	public void verifyNullDescription() {
		logger.info("*** Verifying test case: verifyNullDescription ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullDescriptionMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullDescriptionMsg"), "Description required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 4)
	public void verifyNullWhatsAppTemplate() {
		logger.info("*** Verifying test case: verifyNullWhatsAppTemplate ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.setDescription(p.getProperty("description"));
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullTemplateMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullTemplateMsg"), "WhatsApp template required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 5)
	public void verifyNullStatus() {
		logger.info("*** Verifying test case: verifyNullStatus ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.setDescription(p.getProperty("description"));
		campaign.selectWhatsAppTemplate(p.getProperty("whatappTemplateName"));
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullStatusMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullStatusMsg"), "Status required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 6)
	public void verifyNullDepartment() {
		logger.info("*** Verifying test case: verifyNullDepartment ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.setDescription(p.getProperty("description"));
		campaign.selectWhatsAppTemplate(p.getProperty("whatappTemplateName"));
		campaign.selectStatus(p.getProperty("status"));
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullDepartmentMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullDepartmentMsg"), "Department required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 7)
	public void verifyNullGroup_Segment() {
		logger.info("*** Verifying test case: verifyNullGroup_Segment ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.setDescription(p.getProperty("description"));
		campaign.selectWhatsAppTemplate(p.getProperty("whatappTemplateName"));
		campaign.selectStatus(p.getProperty("status"));
		campaign.selectDepartment(p.getProperty("campaignDepartmentName"));
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullGroup_SegmentMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullGroup_SegmentMsg"), "Group_Segment required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 8)
	public void verifyNullSchedule() {
		logger.info("*** Verifying test case: verifyNullSchedule ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.setDescription(p.getProperty("description"));
		campaign.selectWhatsAppTemplate(p.getProperty("whatappTemplateName"));
		campaign.selectStatus(p.getProperty("status"));
		campaign.selectDepartment(p.getProperty("campaignDepartmentName"));
		campaign.selectGroupRadioBtnAndGroupName(p.getProperty("groupName"));
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullScheduleMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullScheduleMsg"), "Schedule required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 9)
	public void verifyNewCampaignCreationWithValidData() {
		logger.info("*** Verifying test case: verifyNewCampaignCreationWithValidData ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.setDescription(p.getProperty("description"));
		campaign.selectWhatsAppTemplate(p.getProperty("whatappTemplateName"));
		campaign.selectStatus(p.getProperty("status"));
		campaign.selectDepartment(p.getProperty("campaignDepartmentName"));
		campaign.selectGroupRadioBtnAndGroupName(p.getProperty("groupName"));
		campaign.clickCheckboxScheduleNow();
		campaign.clickCreateCampaign();
		String actMsg = ap.getToastAlertMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expCampaignCreateMsg"), "Campaign create message is not match!");
		sa.assertAll();
	}
	
//	@Test (priority = , dependsOnMethods = {"verifyNewCampaignCreationWithValidData"}) //# Duplicate campaign message is not shown on UI
//	public void verifyDuplicateCampaignCreationWithSameName() {
//		logger.info("*** Verifying test case: verifyDuplicateCampaignCreationWithSameName ***");
//		campaign.openNewCampaignPopup();
//		campaign.setCampaignName(p.getProperty("campaignName"));
//		campaign.setDescription(p.getProperty("description"));
//		campaign.selectWhatsAppTemplate(p.getProperty("whatappTemplateName"));
//		campaign.selectStatus(p.getProperty("status"));
//		campaign.selectDepartment(p.getProperty("campaignDepartmentName"));
//		campaign.selectGroupRadioBtnAndGroupName(p.getProperty("groupName"));
//		campaign.clickCheckboxScheduleNow();
//		campaign.clickCreateCampaign();
//		String actMsg = ap.getToastAlertMsg();
//		SoftAssert sa = new SoftAssert();
//		sa.assertEquals(actMsg, p.getProperty(""), "Duplicate Campaign create message is not match!");
//		sa.assertAll();
//	}
	
	@Test (priority = 10, dependsOnMethods= {"verifyNewCampaignCreationWithValidData"})
	public void verifyEditCampaignByUpdatingName() {
		logger.info("*** Verifying test case: verifyEditCampaignByUpdatingName ***");
		campaign.clickActionOfSpecificCampaign(p.getProperty("campaignName"));
		ap.clickEditAction();
		campaign.setCampaignName(p.getProperty("campaignNameUpdate"));
		campaign.clickCheckboxScheduleNow();
		campaign.clickCreateCampaign();
		String actMsg = ap.getToastAlertMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expCampaignUpdateMsg"), "Campaign update message is not match!");
		sa.assertAll();
	}
	
	@Test (priority = 11, dependsOnMethods = {"verifyEditCampaignByUpdatingName"})
	public void verifyDeleteCampaign() {
		logger.info("*** Verifying test case: verifyDeleteCampaign ***");
		campaign.clickActionOfSpecificCampaign(p.getProperty("campaignNameUpdate"));
		ap.clickDeleteAction();
		ap.clickYesDeleteOnConfirm();
		String actMsg = ap.getToastAlertMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expCampaignDeleteMsg"), "Campaign delete message is not match!");
		sa.assertAll();
	}
	
	@Test(priority = 12)
	public void verifyTotalCampaignCountOnPagination() throws InterruptedException {
		logger.info("*** Verify test case: verifyTotalCampaignCountOnPagination ***");
		int campaignCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total campaign count: " + campaignCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(campaignCount, rowCount, "Campaign pagination count is not match with campaign list count");
		lp.refreshPage();
		sa.assertAll();
	}
	

}
