package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	@FindBy(id = "inputEmail")
	WebElement txtEmail;

	@FindBy(id = "inputChoosePassword")
	WebElement txtPassword;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement btnSignIn;
	
	@FindBy(xpath = "//*[@class='d-flex align-items-center nav-link dropdown-toggle dropdown-toggle-nocaret dropdown-toggle']")
	WebElement dropLogout;
	
	@FindBy(xpath = "//*[text()='Logout']")
	WebElement btnLogout;
	
	@FindBy(xpath= "//*[@role='alert']")
	WebElement alertToast;
	

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickSignIn() {
		btnSignIn.click();
	}
	
	public void clickLogout() {
		wait.until(ExpectedConditions.elementToBeClickable(dropLogout)).click();
		wait.until(ExpectedConditions.elementToBeClickable(btnLogout)).click();
	}
	
	public String getAlertMessage() {
		return (wait.until(ExpectedConditions.visibilityOf(alertToast)).getText());
	}

}
