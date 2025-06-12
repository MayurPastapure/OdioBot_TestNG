package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class HomeTest extends BaseClass {

	HomePage hp;

	@BeforeMethod
	public void setupHomePageObjects() {
		hp = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifyHomeCardsVisibility() {
		logger.info("*** Verifying that all 3 home cards are visible ***");

		boolean areVisible = hp.areAllCardsVisible();

		Assert.assertTrue(areVisible, "All 3 cards are not visible on the home page.");
	}

}
