package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.DepartmentsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class DepartmentsTest extends BaseClass {
	DepartmentsPage dp;
	LoginPage lp;
	HomePage hp;

	@BeforeMethod
	public void setupDepartmentsObjectd() {
		dp = new DepartmentsPage(driver);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}

	@Test(priority = 0)
	public void verifyPageIsDepartmentsPage() {
		logger.info("*** Verifying DepartmentsTest: verifyPageIsDepartmentsPage ***");
		dp.openDepartmentsPage();
		wait.until(ExpectedConditions.titleContains(p.getProperty("DepartmentsPageTitle")));
		Assert.assertEquals(driver.getTitle(), p.getProperty("DepartmentsPageTitle"));
	}
	
	@Test(priority = 1, dependsOnMethods = {"verifyPageIsDepartmentsPage"})
	public void verifyBrokenLinkOfDepartmentsPage() {
		logger.info("*** Verifying DepartmentsTest: verifyBrokenLinkOfDepartmentsPage ***");
		int BrokenLinkCount = hp.checkBrokenLinks();
		Assert.assertEquals(BrokenLinkCount, 0, "Broken link is found on Departments page");
	}
	

	@Test(priority = 2, dependsOnMethods = {"verifyPageIsDepartmentsPage"})
	public void verifyNullDepartmentName() {
		logger.info("*** Verifying DepartmentsTest: verifyNullDepartmentName ***");
		dp.openNewDepartment();
		dp.clickCreateDepartment();
		String actErrorMsg = dp.getNullDepartmentNameErrorMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullDepartmentName"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}

	@Test(priority = 3, dependsOnMethods = {"verifyPageIsDepartmentsPage"})
	public void verifyNullDepartmentDescription() {
		logger.info("*** Verifying DepartmentsTest: verifyNullDepartmentDescription ***");
		dp.openNewDepartment();
		dp.setDepartmentName(p.getProperty("DepartmentName"));
		dp.clickCreateDepartment();
		String actErrorMsg = dp.getNullDepartmentDescriptionErrorMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actErrorMsg, p.getProperty("expNullDepartmentDescription"), "Error message does not match!");
		lp.refreshPage();
		sa.assertAll();
	}
	
	@Test(priority = 4, dependsOnMethods = {"verifyPageIsDepartmentsPage"})
	public void verifySearchByDepartmentName() {
		logger.info("*** Verifying DepartmentsTest: verifySearchByDepartmentName ***");
		Boolean isAvailable = dp.searchByDepartmentName(p.getProperty("DepartmentName"));
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(isAvailable, "Department name is not found in search result");
		lp.refreshPage();
		sa.assertAll();
	}

}
