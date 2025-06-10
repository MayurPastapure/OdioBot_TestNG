package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name ='email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@name ='password']")
	WebElement txtPassword;

	@FindBy(xpath = "//button[@type ='submit']")
	WebElement btnLogin;

	@FindBy(xpath = "//*[@role ='alert']")
	WebElement msgErrorMessage;

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public void clearField() {
		txtEmail.clear();
		txtPassword.clear();
	
	}
	public void refreshPage() {
		driver.navigate().refresh();
	}

	public String getErrorMessage() {
		try {
			return (msgErrorMessage.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
