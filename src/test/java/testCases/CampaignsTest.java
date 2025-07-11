package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AgentsPage;
import pageObjects.CRMPage;
import pageObjects.CampaignsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class CampaignsTest extends BaseClass {
	CampaignsPage campaign;
	AgentsPage ap;
	CRMPage crm;
	LoginPage lp;
	HomePage hp;

	@BeforeMethod
	public void setupCampaignsTestObject() {
		campaign = new CampaignsPage(driver);
		ap = new AgentsPage(driver);
		crm = new CRMPage(driver);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}
	
	@Test (priority = 1)
	public void verifyPageIsCampaignPage() {
		logger.info("*** Verifying CampaignsTest: verifyPageIsCampaignPage ***");
		campaign.openCampaignsPage();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, p.getProperty("CampaignPageTitle"), "Campaign page title does not match!");
		}
	
	@Test (priority = 2, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNullCampaignName() {
		logger.info("*** Verifying CampaignsTest: verifyNullCampaignName ***");
		campaign.openNewCampaignPopup();
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullCampaignNameMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullCampaignNameMsg"), "Campaign name required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 3, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNullDescription() {
		logger.info("*** Verifying CampaignsTest: verifyNullDescription ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.clickCreateCampaign();
		String actMsg = crm.getNullErrorMsgs(p.getProperty("expNullDescriptionMsg"));
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expNullDescriptionMsg"), "Description required error message not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test (priority = 4, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNullWhatsAppTemplate() {
		logger.info("*** Verifying CampaignsTest: verifyNullWhatsAppTemplate ***");
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
	
	@Test (priority = 5, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNullStatus() {
		logger.info("*** Verifying CampaignsTest: verifyNullStatus ***");
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
	
	@Test (priority = 6, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNullDepartment() {
		logger.info("*** Verifying CampaignsTest: verifyNullDepartment ***");
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
	
	@Test (priority = 7, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNullGroup_Segment() {
		logger.info("*** Verifying CampaignsTest: verifyNullGroup_Segment ***");
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
	
	@Test (priority = 8, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNullSchedule() {
		logger.info("*** Verifying CampaignsTest: verifyNullSchedule ***");
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
	
	@Test (priority = 9, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyNewCampaignCreationWithValidData() {
		logger.info("*** Verifying CampaignsTest: verifyNewCampaignCreationWithValidData ***");
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
//		logger.info("*** Verifying CampaignsTest: verifyDuplicateCampaignCreationWithSameName ***");
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
		logger.info("*** Verifying CampaignsTest: verifyEditCampaignByUpdatingName ***");
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
		logger.info("*** Verifying CampaignsTest: verifyDeleteCampaign ***");
		campaign.clickActionOfSpecificCampaign(p.getProperty("campaignNameUpdate"));
		ap.clickDeleteAction();
		ap.clickYesDeleteOnConfirm();
		String actMsg = ap.getToastAlertMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actMsg, p.getProperty("expCampaignDeleteMsg"), "Campaign delete message is not match!");
		sa.assertAll();
	}
	
	@Test(priority = 12, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyTotalCampaignCountOnPagination() throws InterruptedException {
		logger.info("*** Verifying CampaignsTest: verifyTotalCampaignCountOnPagination ***");
		int campaignCount = ap.getTotalAgentCountFromPagination();
		logger.info("Total campaign count: " + campaignCount);
		int rowCount = ap.getTotalAgentCountFromList();
		logger.info("Total row count: " + rowCount);
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(campaignCount, rowCount, "Campaign pagination count is not match with campaign list count");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test(priority = 13, dependsOnMethods = {"verifyPageIsCampaignPage"})
	public void verifyBrokenLinksOnCampaignPage() {
		logger.info("*** Verifying CampaignsTest: verifyBrokenLinksOnCampaignPage ***");
		int BrokenlinkCount = hp.checkBrokenLinks();
		Assert.assertEquals(BrokenlinkCount, 0, "Broken links found on Campaign page");
	}
	

}
