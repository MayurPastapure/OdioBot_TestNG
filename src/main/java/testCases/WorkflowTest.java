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

	@Test(priority = 1)
	public void verifyMyWorkflowsPage() throws InterruptedException {
		logger.info("*** Verifying test case: verifyMyWorkflowPage navigation ***");
		wp.openMyWorkflowPage();
		wait.until(ExpectedConditions.urlToBe(p.getProperty("workflowUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("workflowUrl"),
				"page navigation failed: url mismatch.");
	}

	@Test(priority = 2)
	public void verifyNullChatBotName() {
		logger.info("*** Verify test case: NullChatBotName ***");
		wp.openAddWorkflow();

		wp.clickSubmit();
		String actNullChatBotNameError = wp.getChatbotErrorMsg();
		Assert.assertEquals(actNullChatBotNameError, p.getProperty("expNullChatBotName"));
		wp.clickCloseWorkflow();
	}

	@Test(priority = 3)
	public void verifyNullDepartmentId() {
		logger.info("*** Verify test case: NullDepartmentId ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.clickSubmit();
		String actNullDeptIdError = wp.getDepartmentIdErrorMsg();
		Assert.assertEquals(actNullDeptIdError, p.getProperty("expNullDepartmentId"));
		wp.clickCloseWorkflow();
	}

	@Test(priority = 4)
	public void verifyNullProductType() {
		logger.info("*** Verify test case: NullProductType ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.selectDepartment(p.getProperty("Departments"));
		wp.clickSubmit();
		String actNullProductTypeError = wp.getProductTypeErrorMsg();
		Assert.assertEquals(actNullProductTypeError, p.getProperty("expNullProductType"));
		wp.clickCloseWorkflow();
	}

	@Test(priority = 5)
	public void verifyNewWorkflowCreation() {
		logger.info("*** Verify test case: newWorkflowCreation ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.selectDepartment(p.getProperty("Departments"));
		wp.selectProductType(p.getProperty("ProductType"));
		wp.setStartAt(p.getProperty("StartAt"));
		wp.setEndAt(p.getProperty("EndAt"));
		wp.clickSubmit();
		String actWorkflowCreateMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actWorkflowCreateMsg, p.getProperty("expWorkflowCreateMsg"));
		wp.clickWorkflowHome();
	}

	@Test(priority = 6)
	public void verifyDuplicateWorkflowCreation() {
		logger.info("*** Verify test case: DuplicateWorkflowCreation ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.selectDepartment(p.getProperty("Departments"));
		wp.selectProductType(p.getProperty("ProductType"));
		wp.setStartAt(p.getProperty("StartAt"));
		wp.setEndAt(p.getProperty("EndAt"));
		wp.clickSubmit();
		String actUniqueNameMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actUniqueNameMsg, p.getProperty("expUniqueNameMsg"));
		wp.clickCloseWorkflow();
	}

	@Test(priority = 7)
	public void verifyResumeWorkflowWithoutNode() {
		logger.info("*** Verify test case: ResumeWorkflowWithoutNode ***");
		wp.clickResumeWorkflowByName(p.getProperty("workflowName"));
		String actResumeWorkflowErrorMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actResumeWorkflowErrorMsg, p.getProperty("expResumeWorkflowError"));
	}

	@Test(priority = 8)
	public void verifyEditWorkflowOnlyName() {
		logger.info("*** Verify test case: EditWorkflowOnlyName ***");
		wp.clickEditWorkflowByName(p.getProperty("workflowName"));
		wp.setChatbotNameEdit(p.getProperty("updateWorkflowName"));
		wp.clickSubmitEdit();
		String actEditWorkflowMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actEditWorkflowMsg, p.getProperty("expEditWorkflowMsg"));
	}

	@Test(priority = 9)
	public void verifyDeleteWorkflowByName() {
		logger.info("*** Verify test case: DeleteWorkflowByName ***");
		wp.clickDeleteWorkflowByName(p.getProperty("updateWorkflowName"));
		wp.clickYesDeleteConfirm();
		String actDeleteWorkflowMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actDeleteWorkflowMsg, p.getProperty("expDeleteWorkflowMsg"));
	}

}
