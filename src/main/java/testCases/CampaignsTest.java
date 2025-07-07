package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CampaignsPage;
import testBase.BaseClass;

public class CampaignsTest extends BaseClass {
	CampaignsPage campaign;

	@BeforeMethod
	public void setupCampaignsTestObject() {
		campaign = new CampaignsPage(driver);

	}
	
	@Test (priority = 1)
	public void verifyPageIsCampaignPage() {
		logger.info("*** Verifying test case: verifyPageIsCampaignPage ***");
		campaign.openCampaignsPage();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, p.getProperty("CampaignPageTitle"), "Campaign page title does not match!");
		}
	
	@Test (priority = 2)
	public void verifyNewCampaignCreationWithValidData() {
		logger.info("*** Verifying test case: verifyNewCampaignCreationWithValidData ***");
		campaign.openNewCampaignPopup();
		campaign.setCampaignName(p.getProperty("campaignName"));
		campaign.setDescription(p.getProperty("description"));
		campaign.selectWhatsAppTemplate(p.getProperty("whatappTemplateName"));
		campaign.selectStatus(p.getProperty("status"));
		campaign.selectDepartment(p.getProperty("campaignDepartmentName"));
		campaign.selectRadioButton_GroupsOrSegments(p.getProperty("groups/segments"));
		campaign.selectDropdownOptionOfGroupOrSegment(p.getProperty("group/segmentName"));
		campaign.clickCheckboxScheduleNow();
		//campaign.clickCreateCampaign();
	}

}
