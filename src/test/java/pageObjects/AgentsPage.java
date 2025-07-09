package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AgentsPage extends BasePage {

	public AgentsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[text()='Agents']")
	WebElement txtAgents;

	@FindBy(xpath = "//*[text()='New Agent']")
	WebElement btnNewAgent;

	@FindBy(xpath = "//*[@name='agentName']")
	WebElement inpAgnetName;

	@FindBy(xpath = "//div[@role='combobox' and contains(@class, 'MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input css-184p1yy')]")
	WebElement drpDepartment;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	List<WebElement> drpDepartmentOptions;

	@FindBy(xpath = "//input[@name='agentEmail']")
	WebElement inpAgentEmail;

	@FindBy(xpath = "//input[@name='agentMobile']")
	WebElement inpAgnetMobileNo;

	@FindBy(xpath = "//input[@name='password']")
	WebElement inpPassword;

	@FindBy(xpath = "//input[@name='confirmPassword']")
	WebElement inpConfirmPassword;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnCreateAgent;

	@FindBy(xpath = "//input[@placeholder='Search Agent by Name']")
	WebElement inpSearchUser;

	@FindBy(xpath = "//p[text()='Agent Name must contain only letters and spaces']")
	WebElement msgNullAgentName;

	@FindBy(xpath = "//p[text()='Department Name is required']")
	WebElement msgNullDepartmentName;

	@FindBy(xpath = "//p[text()='Email is required']")
	WebElement msgNullAgentEmail;

	@FindBy(xpath = "//p[text()='Mobile number must be a 10-digit number']")
	WebElement msgNullAgentMobileNo;

	@FindBy(xpath = "//p[text()='Password must be at least 4 characters']")
	WebElement msgNullPassword;

	@FindBy(xpath = "//p[text()='Confirm Password is required']")
	WebElement msgNullConfirmPassword;

	@FindBy(xpath = "//*[@role='alert']")
	WebElement tostAlertMsg;

	@FindBy(xpath = "//p[@class='MuiTablePagination-displayedRows css-1qbj7le']")
	WebElement txtAgentCountOnPagi;

	@FindBy(xpath = "//button[@title='Go to next page']")
	WebElement btnNextPageButton;

	@FindBy(xpath = "//li[text()='Delete']")
	WebElement btnDelete;

	@FindBy(xpath = "//li[text()='Edit']")
	WebElement btnEdit;

	@FindBy(xpath = "//button[text()='Yes']")
	WebElement btnYesDelete;

	@FindBy(xpath = "//button[text()='No']")
	WebElement btnNoDelete;

	public void openAgentsPage() {
		txtAgents.click();
		wait.until(ExpectedConditions.visibilityOf(btnNewAgent));
	}

	public void openNewAgentCreatePopup() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnNewAgent));
		element.click();
	}

	public void setAgentName(String AgentName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpAgnetName));
		element.sendKeys(AgentName);
	}

	public void selectDepartmentName(String DepartmentName) {
		wait.until(ExpectedConditions.elementToBeClickable(drpDepartment)).click();
		List<WebElement> departmentList = wait.until(ExpectedConditions.visibilityOfAllElements(drpDepartmentOptions));
		for (WebElement department : departmentList) {
			if (department.getText().equalsIgnoreCase(DepartmentName)) {
				department.click();
				break;
			}
		}
	}

	public void setAgentEmail(String AgentEmail) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpAgentEmail));
		element.sendKeys(AgentEmail);
	}

	public void setAgentMobileNo(String AgentMobileNo) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpAgnetMobileNo));
		element.sendKeys(AgentMobileNo);
	}

	public void setPassword(String Password) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpPassword));
		element.sendKeys(Password);
	}

	public void setConfirmPassword(String ConfirmPassword) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpConfirmPassword));
		element.sendKeys(ConfirmPassword);
	}

	public void clickCreateAgentButton() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnCreateAgent));
		element.click();
	}

	public String getNullAgentNameMsg() {
		return msgNullAgentName.getText().trim();
	}

	public String getNullDepartmentNameMsg() {
		return msgNullDepartmentName.getText().trim();
	}

	public String getNullAgentEmailMsg() {
		return msgNullAgentEmail.getText().trim();
	}

	public String getNullAgentMobileNoMsg() {
		return msgNullAgentMobileNo.getText().trim();
	}

	public String getNullPasswordMsg() {
		return msgNullPassword.getText().trim();
	}

	public String getNullConfirmPasswordMsg() {
		return msgNullConfirmPassword.getText().trim();
	}

	public String getToastAlertMsg() {
		WebElement element = wait.until(ExpectedConditions.visibilityOf(tostAlertMsg));
		String message = element.getText().trim();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='toast-message']")));
		return message;
	}

	public boolean searchByUserName(String SearchableUserName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpSearchUser));
		element.sendKeys(SearchableUserName);
		List<WebElement> rows = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//table[contains(@class,'MuiTable-root')]/tbody/tr")));
		for (WebElement row : rows) {
			if (row.getText().toLowerCase().contains(SearchableUserName.toLowerCase())) {
				return true;
			}

		}
		return false;
	}

	public int getTotalAgentCountFromPagination() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", txtAgentCountOnPagi);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(txtAgentCountOnPagi));
		Thread.sleep(2000);
		String text = element.getText();
		String count = text.split("of")[1].trim();
		int totalCount = Integer.parseInt(count);
		return totalCount;
	}

	public int getTotalAgentCountFromList() throws InterruptedException {
		int totalRows = 0;

		while (true) {
			List<WebElement> rows = wait.until(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody/tr[td[not(@colspan)]]")));

		
			totalRows = totalRows + rows.size();

			String classAttr = btnNextPageButton.getAttribute("class");
			if (!btnNextPageButton.isEnabled() || classAttr.contains("Mui-disabled")) {
				break;
			}
			btnNextPageButton.click();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
		}
		return totalRows;
	}

	public void clickActionOfSpecificAgent(String AgentName) {
		String dynamicXpath = "//tr//td[text()='" + AgentName + "']//parent::tr//button[@type='button']";
		WebElement btnAction = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
		btnAction.click();
	}

	public void clickDeleteAction() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnDelete));
		element.click();
	}

	public void clickEditAction() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnEdit));
		element.click();
	}

	public void clickYesDeleteOnConfirm() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnYesDelete));
		element.click();
	}

	public void clickNoDeleteOnConfirm() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnNoDelete));
		element.click();
	}

}
