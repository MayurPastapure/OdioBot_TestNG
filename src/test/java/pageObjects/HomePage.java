package pageObjects;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
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

	@FindBy(xpath = "//*[@data-testid='MoreVertIcon']")
	WebElement iconMoreVertIcon;

	@FindBy(xpath = "//li[text()='Logout']")
	WebElement btnLogout;

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

	public int checkBrokenLinks() {
		int brokenLinkCount = 0;

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println("Total link found on page: " + allLinks.size());

		for (WebElement link : allLinks) {
			String url = link.getAttribute("href");
			//System.out.println("Present URL : " + url);
			if (url == null || url.isEmpty() || !url.startsWith("https")) {
				continue;
			}
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setRequestMethod("HEAD");
				connection.connect();
				int statusCode = connection.getResponseCode();

				if (statusCode >= 300) {
					System.out.println("Broken link: " + url + " - Status Code: " + statusCode);
					brokenLinkCount++;
				}
			} catch (Exception e) {
				System.out.println("Exception for URL: " + url + " - " + e.getMessage());
				brokenLinkCount++;
			}
		}
		System.out.println("Total broken links: " + brokenLinkCount);
		return brokenLinkCount;

	}

	public void openMoreOption() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(iconMoreVertIcon));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnLogout));
	}

	public void clickLogout() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
		element.click();
		wait.until(ExpectedConditions.urlToBe(p.getProperty("loginUrl")));
	}

}
