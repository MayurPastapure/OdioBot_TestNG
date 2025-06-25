package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.DepartmentsPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class DepartmentsTest extends BaseClass {
	DepartmentsPage dp;
	LoginPage lp;

	@BeforeMethod
	public void setupDepartmentsObjectd() {
		dp = new DepartmentsPage(driver);
		lp = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyPageIsDepartmentsPage() {
		logger.info("*** Verifying test case: verifyPageIsDepartmentsPage ***");
		dp.openDepartmentsPage();
		wait.until(ExpectedConditions.titleContains(p.getProperty("DepartmentsPageTitle")));
		Assert.assertEquals(driver.getTitle(), p.getProperty("DepartmentsPageTitle"));
	}

	@Test(priority = 2)
	public void verifyNullDepartmentName() {
		logger.info("*** Verifying test case: verifyNullDepartmentName ***");
		dp.openNewDepartment();
		dp.clickCreateDepartment();
		String actErrorMsg = dp.getNullDepartmentNameErrorMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullDepartmentName"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void verifyNullDepartmentDescription() {
		logger.info("*** Verifying test case: verifyNullDepartmentDescription ***");
		dp.openNewDepartment();
		dp.setDepartmentName(p.getProperty("DepartmentName"));
		dp.clickCreateDepartment();
		String actErrorMsg = dp.getNullDepartmentDescriptionErrorMsg();
		softAssert.assertEquals(actErrorMsg, p.getProperty("expNullDepartmentDescription"), "Error message does not match!");
		lp.refreshPage();
		softAssert.assertAll();
	}
	
	@Test(priority = 4)
	public void verifySearchByDepartmentName() {
		logger.info("*** Verify test case: verifySearchByDepartmentName ***");
		Boolean isAvailable = dp.searchByDepartmentName(p.getProperty("DepartmentName"));
		softAssert.assertTrue(isAvailable, "Department name is not found in search result");
		lp.refreshPage();
		softAssert.assertAll();
	}

}
