package pageObjects;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
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

	@FindBy(xpath = "(//*[@id= 'demo-simple-select'])[1]")
	WebElement drpSelectDepartment;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	List<WebElement> drpSelectDepartmentOptions;

	@FindBy(xpath = "(//*[@id= 'demo-simple-select'])[2]")
	WebElement drpSelectGroup;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	List<WebElement> drpSelectGroupOptions;

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

	@FindBy(xpath = "//div[@class='SnackbarItem-message']")
	WebElement toastMessage;

	@FindBy(xpath = "//input[@placeholder='Search Name...']")
	WebElement inpSearch;

	@FindBy(xpath = "//p[contains(@class, 'MuiFormHelperText-root')]")
	List<WebElement> nullMessages;

	public void openCRMPage() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(txtCRM));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddUsers));
	}

	public void clickUploadCSV_BulkUsers() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnUploadCSV));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnUploadBulkUser));
	}

	public void clickAndUploadBulkUserFile(String filePath, String fileName) {
		wait.until(ExpectedConditions.elementToBeClickable(btnUploadBulkUser));
		WebElement fileInput = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
		fileInput.sendKeys(filePath + fileName);
	}

	public void clickDownloadSampleCSV() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnDownloadSampleCSV));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(toastMessage));
	}

	public boolean isFileExist(String fileName) {
		File file = new File(System.getProperty("user.dir") + "\\downloadFiles\\" + fileName + ".csv");
		Boolean exist = file.exists();
		return exist;
	}

	public boolean searchByName(String searchingName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpSearch));
		element.sendKeys(searchingName);
		List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//table[contains(@class,'MuiTable-root')]/tbody/tr[td[not(@colspan)]]")));
		for (WebElement row : rows) {
			if (row.getText().toLowerCase().contains(searchingName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public String getToastMessage() {
		WebElement element = wait.until(ExpectedConditions.visibilityOf(toastMessage));
		String text = element.getText().trim().replaceAll("\\n", " ");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='toast-message']")));
		return text;
	}

	public void clickOnAddUser() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnAddUsers));
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(inpUserName));
	}

	public void setUserName(String userName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpUserName));
		element.sendKeys(userName);
	}

	public void setMobileNumber(String mobileNumber) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpUserMobile));
		element.sendKeys(mobileNumber);
	}

	public void setHandle(String handle) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpUserHandle));
		element.sendKeys(handle);
	}

	public void setEmail(String email) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpUserEmail));
		element.sendKeys(email);
	}

	public void selectDepartment(String departmentName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(drpSelectDepartment));
		element.click();
		List<WebElement> depts = wait.until(ExpectedConditions.visibilityOfAllElements(drpSelectDepartmentOptions));
		for (WebElement dept : depts) {
			if (dept.getText().equalsIgnoreCase(departmentName)) {
				dept.click();
				break;
			}
		}
	}

	public void selectGroup(String groupName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(drpSelectGroup));
		element.click();
		List<WebElement> groups = wait.until(ExpectedConditions.visibilityOfAllElements(drpSelectGroupOptions));
		for (WebElement group : groups) {
			if (group.getText().equalsIgnoreCase(groupName)) {
				group.click();
				break;
			}
		}
		driver.findElement(By.xpath("//*[@id= 'menu-channel']")).click();
	}

	public void clickOnAddInfo() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnAddInfo));
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(inpKey));
	}

	public void setKey(String keyName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpKey));
		element.sendKeys(keyName);
	}

	public void setDescription(String description) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpDescription));
		element.sendKeys(description);
	}

	public void clickCreateUser() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnCreateUser));
		element.click();
	}

	public String getNullErrorMsgs(String expNullMsg) {
		for (WebElement nullMsg : nullMessages) {
			if (nullMsg.getText().equals(expNullMsg)) {
				return nullMsg.getText();
			}
		}
		return null;
	}

}
