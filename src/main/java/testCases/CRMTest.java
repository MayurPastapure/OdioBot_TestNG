package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CRMPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class CRMTest extends BaseClass {
	CRMPage crm;
	HomePage hp;

	@BeforeMethod
	public void setupCRMObjects() {
		crm = new CRMPage(driver);
		hp = new HomePage(driver);	
	}
	
	@Test(priority = 1)
	public void verifyPageIsCRMPage() {
		logger.info("*** Verifying test case: verifyPageIsCRMPage ***" );
		hp.openMoreOption();
		crm.openCRMPage();
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, p.getProperty("CRMPageTitle"), "Page title does not match!");
	}
	
	@Test(priority = 2)
	public void verifyNullUserName() {
		
	}
}
