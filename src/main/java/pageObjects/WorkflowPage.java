package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WorkflowPage extends BasePage {

	public WorkflowPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@class='MuiStack-root css-1ialerq']")
	WebElement odioIcon;

	@FindBy(xpath = "//*[text()='Workflow']")
	WebElement txtWorkflow;

	@FindBy(xpath = "//*[@class='main-title clearfix']")
	WebElement txtWorkflowTitle;

	@FindBy(xpath = "//*[text()='+ Add Workflow']")
	WebElement btnAddWorkflow;

	@FindBy(xpath = "(//*[@placeholder='Enter a valid name'])[1]")
	WebElement inpChatbotName;

	@FindBy(xpath = "(//*[@placeholder='Enter a valid name'])[2]")
	WebElement inpChatbotNameEdit;

	@FindBy(xpath = "(//*[@formcontrolname='organization_department_id'])[1]")
	WebElement drpDepartments;

	@FindBy(xpath = "(//*[@formcontrolname='product_id'])[1]")
	WebElement drpProductType;

	@FindBy(xpath = "(//*[@formcontrolname='start_at'])[1]")
	WebElement calStartAt;

	@FindBy(xpath = "(//*[@formcontrolname='end_at'])[1]")
	WebElement calEndAt;

	@FindBy(xpath = "(//*[@id='submit'])[1]")
	WebElement btnSubmit;

	@FindBy(xpath = "(//*[@id='submit'])[2]")
	WebElement btnSubmitEdit;

	@FindBy(xpath = "//*[@class='toast-message ng-star-inserted']")
	WebElement msgToastWorkflowCreate;

	@FindBy(xpath = "//*[text()='Please Enter Chatbot Name']")
	WebElement msgNullChatBotName;

	@FindBy(xpath = "//*[text()='Please Select Department Id']")
	WebElement msgNullDeptId;

	@FindBy(xpath = "(//*[text()='Please Select Product type Id'])[1]")
	WebElement msgNullProductType;

	@FindBy(xpath = "(//*[@class='icon_cmn close_box ripplelink'])[1]")
	WebElement btnCloseWorkflow;

	@FindBy(xpath = "//*[@title='Home']")
	WebElement btnWorkflowHome;

	@FindBy(xpath = "//button[text()='Yes']")
	WebElement btnYesDeleteConfirm;

	public void openMyWorkflowPage() throws InterruptedException {
		odioIcon.click();
		Thread.sleep(5000);
		txtWorkflow.click();
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOf(txtWorkflowTitle));
	}

	public void openAddWorkflow() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddWorkflow)).click();
	}

	public void setChatbotName(String chatbotName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpChatbotName));
		element.clear();
		element.sendKeys(chatbotName);
	}

	public void setChatbotNameEdit(String chatbotName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpChatbotNameEdit));
		element.click();
		element.clear();
		if (!element.getAttribute("value").isEmpty()) {
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);
		}
		element.sendKeys(chatbotName);
	}

	public void selectDepartment(String Departments) {
		wait.until(ExpectedConditions.visibilityOf(drpDepartments));
		Select sel = new Select(drpDepartments);
		sel.selectByVisibleText(Departments);
	}

	public void selectProductType(String ProductType) {
		wait.until(ExpectedConditions.visibilityOf(drpDepartments));
		Select sel = new Select(drpProductType);
		sel.selectByVisibleText(ProductType);
	}

	public void setStartAt(String StartAt) {
		wait.until(ExpectedConditions.visibilityOf(calStartAt)).sendKeys(StartAt);
	}

	public void setEndAt(String EndAt) {
		wait.until(ExpectedConditions.visibilityOf(calEndAt)).sendKeys(EndAt);
	}

	public void clickSubmit() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit)).click();
	}

	public void clickSubmitEdit() {
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmitEdit)).click();
	}

	public String getWorkflowToastMessage() {
		return (wait.until(ExpectedConditions.visibilityOf(msgToastWorkflowCreate)).getText());
	}

	public String getChatbotErrorMsg() {
		return (msgNullChatBotName.getText());
	}

	public String getDepartmentIdErrorMsg() {
		return (msgNullDeptId.getText());
	}

	public String getProductTypeErrorMsg() {
		return (msgNullProductType.getText());
	}

	public void clickCloseWorkflow() {
		wait.until(ExpectedConditions.elementToBeClickable(btnCloseWorkflow)).click();
	}

	public void clickWorkflowHome() {
		wait.until(ExpectedConditions.elementToBeClickable(btnWorkflowHome)).click();
	}

	public void clickResumeWorkflowByName(String workflowName) {
		String dynamicXpath = "//li//span[text()='" + workflowName
				+ "']//ancestor::li//i[@title='Resume this workflow']";
		WebElement btnResumeWorkflow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicXpath)));
		wait.until(ExpectedConditions.elementToBeClickable(btnResumeWorkflow)).click();
	}

	public void clickEditWorkflowByName(String workflowName) {
		String dynamicXpath = "//li//span[@*='" + workflowName + "']//ancestor::li//i[@title='Edit this workflow']";
		WebElement btnEditWorkflow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicXpath)));
		wait.until(ExpectedConditions.elementToBeClickable(btnEditWorkflow)).click();
		wait.until(ExpectedConditions.visibilityOf(inpChatbotNameEdit));
	}

	public void clickDeleteWorkflowByName(String workflowName) {
		String dynamicXpath = "//li//span[@*='" + workflowName + "']//ancestor::li//i[@title='Delete this workflow']";
		WebElement btnDeleteWorkflow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dynamicXpath)));
		wait.until(ExpectedConditions.elementToBeClickable(btnDeleteWorkflow)).click();
	}

	public void clickYesDeleteConfirm() {
		wait.until(ExpectedConditions.elementToBeClickable(btnYesDeleteConfirm)).click();
	}

}
