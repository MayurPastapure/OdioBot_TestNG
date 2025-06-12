package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
	
	@FindBy(xpath = "(//*[@formcontrolname='product_id'])[1]")
	WebElement drpProductType;
	
	

	public void openMyWorkflowPage() {
		odioIcon.click();
		txtWorkflow.click();
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOf(txtWorkflowTitle));
	}
	
	public void openAddWorkflow() {
		wait.until(ExpectedConditions.elementToBeClickable(btnAddWorkflow)).click();
	}
	
	public void setChatbotName(String ChatbotName) {
		wait.until(ExpectedConditions.visibilityOf(inpChatbotName)).sendKeys(ChatbotName);
		
	}

}
