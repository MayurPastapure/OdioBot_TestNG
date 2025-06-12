package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.WorkflowPage;
import testBase.BaseClass;

public class WorkflowTest extends BaseClass {
	
	WorkflowPage wp;
	
	@BeforeMethod
	public void setupWorkflowObjects() {
		wp = new WorkflowPage(driver);
	}
	
	@Test (priority = 1)
	public void verifyMyWorkflowsPage() {
		
		logger.info("*** Verifying test case verifyMyWorkflowPage navigation ***");
		wp.openMyWorkflowPage();
		wait.until(ExpectedConditions.urlToBe(p.getProperty("workflowUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("workflowUrl"), "page navigation failed: url mismatch.");
	}
	
	@Test (priority = 2)
	public void verifyNewWorkflowCreation() {
		logger.info("*** Verify test case newWorkflowCreation ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
	}
	

}
