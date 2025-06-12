package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
		inpEmail.clear();
		inpEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		inpPassword.clear();
		inpPassword.sendKeys(password);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public void clearField() {
		inpEmail.clear();
		inpPassword.clear();
	
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
		return(msgEmailReq.getText());
	}
	
	public String getPasswordReqMsg() {
		return(msgPasswordReq.getText());
	}
}
