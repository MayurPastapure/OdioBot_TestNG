package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CRMPage extends BasePage {

	public CRMPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(linkText = "CRM")
	WebElement txtCRM;

	@FindBy(xpath = "//button[text()='Add ']")
	WebElement btnAddUsers;

	@FindBy(xpath = "//input[@name='userName']")
	WebElement inpUserName;

	@FindBy(xpath = "//input[@name='userMobile']")
	WebElement inpUserMobile;

	@FindBy(xpath = "//input[@name='userHandle']")
	WebElement inpUserHandle;

	@FindBy(xpath = "//input[@name='userEmail']")
	WebElement inpUserEmail;

	@FindBy(xpath = "//label[text()='Select Department']")
	WebElement drpSelectDepartment;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	WebElement drpSelectDepartmentOption;

	@FindBy(xpath = "//label[text()='Select Group']")
	WebElement drpSelectGroup;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	WebElement drpSelectGroupOption;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnCreateUser;

	@FindBy(xpath = "//button[text()='+ Add Info']")
	WebElement btnAddInfo;

	@FindBy(xpath = "//input[@placeholder='Key']")
	WebElement inpKey;

	@FindBy(xpath = "//input[@placeholder='Description']")
	WebElement inpDescription;

	@FindBy(xpath = "//button[text()='Upload CSV (Bulk Users)']")
	WebElement btnUploadCSV;

	@FindBy(xpath = "//span[text()='Download Sample CSV']")
	WebElement btnDownloadSampleCSV;

	@FindBy(xpath = "//span[text()='Upload Bulk Users']")
	WebElement btnUploadBulkUser;
	
	@FindBy(xpath = "//h6[text()='Filter']")
	WebElement btnFilter;
	

	public void openCRMPage() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(txtCRM));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddUsers));
	}

}
