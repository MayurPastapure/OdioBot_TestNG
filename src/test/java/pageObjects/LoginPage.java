package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name ='email']")
	WebElement inpEmail;

	@FindBy(xpath = "//input[@name ='password']")
	WebElement inpPassword;

	@FindBy(xpath = "//button[@type ='submit']")
	WebElement btnLogin;

	@FindBy(xpath = "//*[@role ='alert']")
	WebElement msgErrorMessage;
	
	@FindBy (xpath = "//*[@id=':r0:-helper-text']")
	WebElement msgEmailReq;
	
	@FindBy (xpath = "//*[@id=':r1:-helper-text']")
	WebElement msgPasswordReq;
	
	
	
	public void setEmail(String email) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpEmail));
		element.clear();
		element.sendKeys(email);
	}

	public void setPassword(String password) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpPassword));
		element.clear();
		element.sendKeys(password);
	}

	public void clickLogin() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
		element.click();
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
	
	public String getEmailReqMsg() {
		return(msgEmailReq.getText().trim());
	}
	
	public String getPasswordReqMsg() {
		return(msgPasswordReq.getText().trim());
	}
}
