package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@class='consult-section']")
	List<WebElement> homeCards;

	@FindBy(xpath = "//*[@class='home-head']")
	WebElement headTitle;

	public boolean areAllCardsVisible() {
		wait.until(ExpectedConditions.visibilityOfAllElements(headTitle));

		if (homeCards.size() != 3)
			return false;

		for (WebElement card : homeCards) {
			if (!card.isDisplayed()) {
				return false;
			}
		}
		return true;
	}

}
