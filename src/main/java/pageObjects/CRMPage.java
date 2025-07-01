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
	WebElement btnAddUsers_Groups;

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
	WebElement btnCreateUser_Group;

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

	@FindBy(xpath = "//div[contains(@class ,'MuiPaper-root')]//li")
	List<WebElement> drpFilterOptions;

	@FindBy(xpath = "//div[text()='Is equals']")
	WebElement drpFilterCondition;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	List<WebElement> drpFilterConditionOptions;

	@FindBy(xpath = "//input[@placeholder='Value']")
	WebElement inpFilterConditionValue;

	@FindBy(xpath = "//input[@placeholder='Value']")
	WebElement btnAddCondition;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnApplyFilter;

	@FindBy(xpath = "//div[@class='SnackbarItem-message']")
	WebElement toastMessage;

	@FindBy(xpath = "//input[@placeholder='Search Name...']")
	WebElement inpSearch;

	@FindBy(xpath = "//p[contains(@class, 'MuiFormHelperText-root')]")
	List<WebElement> nullMessages;

	@FindBy(xpath = "//button[text()='users']")
	WebElement txtUsersTab;

	@FindBy(xpath = "//button[text()='groups']")
	WebElement txtGroupsTab;

	@FindBy(xpath = "//button[text()='segments']")
	WebElement txtSegmentsTab;

	@FindBy(xpath = "//input[@name='groupName']")
	WebElement inpGroupName;

	@FindBy(xpath = "//input[@name='websiteName']")
	WebElement inpWebsite;

	public void openCRMPage() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(txtCRM));
		element.click();
		wait.until(ExpectedConditions.visibilityOf(btnAddUsers_Groups));
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

	public boolean searchByName(String searchingName) throws InterruptedException {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpSearch));
		element.sendKeys(searchingName);
		List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//table[contains(@class,'MuiTable-root')]/tbody/tr[td[not(@colspan)]]")));
		Thread.sleep(2000);
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

	public void clickOnAddUsers_AddGroups() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnAddUsers_Groups));
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(btnCreateUser_Group));
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

	public void clickCreateUser_Group() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnCreateUser_Group));
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

	public void openGroupTab() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(txtGroupsTab));
		element.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//table[contains(@class,'MuiTable-root')]/tbody/tr[td[not(@colspan)]]")));
	}

	public void setGroupName(String groupName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpGroupName));
		element.sendKeys(groupName);
	}

	public void setWebsiteName(String websiteName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpWebsite));
		element.sendKeys(websiteName);
	}

	public void selectFilterOption(String filterOption) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnFilter));
		element.click();
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(drpFilterOptions));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(filterOption)) {
				option.click();
				break;
			}
		}
	}

	public void selectFilterCondition(String conditionOption) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(drpFilterCondition));
		element.click();
		List<WebElement> conditionOpts = wait
				.until(ExpectedConditions.visibilityOfAllElements(drpFilterConditionOptions));
		for (WebElement conditionOpt : conditionOpts) {
			if (conditionOpt.getText().trim().equalsIgnoreCase(conditionOption)) {
				conditionOpt.click();
				break;
			}
		}
	}

	public void setConditionValue(String conditionValue) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpFilterConditionValue));
		element.sendKeys(conditionValue);
	}

	public void clickApplyFiler() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnApplyFilter));
		element.click();
	}

}
