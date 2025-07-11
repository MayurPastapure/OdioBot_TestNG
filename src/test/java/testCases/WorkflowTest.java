package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.WorkflowPage;
import testBase.BaseClass;

public class WorkflowTest extends BaseClass {

	WorkflowPage wp;

	@BeforeMethod
	public void setupWorkflowObjects() {
		wp = new WorkflowPage(driver);
	}

	@Test(priority = 1)
	public void verifyPageIsMyWorkflowPage() {
		logger.info("*** Verifying WorkflowTest: verifyPageIsMyWorkflowPage ***");
		wp.openMyWorkflowPage();
		wait.until(ExpectedConditions.urlToBe(p.getProperty("workflowUrl")));
		Assert.assertEquals(driver.getCurrentUrl(), p.getProperty("workflowUrl"),
				"page navigation failed: url mismatch.");
	}

	@Test(priority = 2, dependsOnMethods = {"verifyPageIsMyWorkflowPage"})
	public void verifyNullChatBotName() {
		logger.info("*** Verifying WorkflowTest: NullChatBotName ***");
		wp.openAddWorkflow();
		wp.clickSubmit();
		String actNullChatBotNameError = wp.getChatbotErrorMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullChatBotNameError, p.getProperty("expNullChatBotName"),
				"Chatbot name required error message does not match!");
		wp.clickCloseWorkflow();
		sa.assertAll();
	}

	@Test(priority = 3, dependsOnMethods = {"verifyPageIsMyWorkflowPage"})
	public void verifyNullDepartmentId() {
		logger.info("*** Verifying WorkflowTest: NullDepartmentId ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.clickSubmit();
		String actNullDeptIdError = wp.getDepartmentIdErrorMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullDeptIdError, p.getProperty("expNullDepartmentId"),
				"Department name required error message does not match!");
		wp.clickCloseWorkflow();
		sa.assertAll();
	}

	@Test(priority = 4, dependsOnMethods = {"verifyPageIsMyWorkflowPage"})
	public void verifyNullProductType() {
		logger.info("*** Verifying WorkflowTest: NullProductType ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.selectDepartment(p.getProperty("Departments"));
		wp.clickSubmit();
		String actNullProductTypeError = wp.getProductTypeErrorMsg();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actNullProductTypeError, p.getProperty("expNullProductType"),
				"Product type required error message does not match!");
		wp.clickCloseWorkflow();
		sa.assertAll();
	}

	@Test(priority = 5, dependsOnMethods = {"verifyPageIsMyWorkflowPage"})
	public void verifyNewWorkflowCreation() {
		logger.info("*** Verifying WorkflowTest: newWorkflowCreation ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.selectDepartment(p.getProperty("Departments"));
		wp.selectProductType(p.getProperty("ProductType"));
		wp.setStartAt();
		wp.setEndAt();
		wp.clickSubmit();
		String actWorkflowCreateMsg = wp.getWorkflowToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actWorkflowCreateMsg, p.getProperty("expWorkflowCreateMsg"),
				"Workflow create message does not match!");
		wp.clickCancelEntryNodePopup();
		wp.clickWorkflowHome();
		sa.assertAll();
	}

	@Test(priority = 6, dependsOnMethods = { "verifyNewWorkflowCreation" })
	public void verifyDuplicateWorkflowCreation() {
		logger.info("*** Verifying WorkflowTest: DuplicateWorkflowCreation ***");
		wp.openAddWorkflow();
		wp.setChatbotName(p.getProperty("ChatbotName"));
		wp.selectDepartment(p.getProperty("Departments"));
		wp.selectProductType(p.getProperty("ProductType"));
		wp.setStartAt();
		wp.setEndAt();
		wp.clickSubmit();
		String actUniqueNameMsg = wp.getWorkflowToastMessage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actUniqueNameMsg, p.getProperty("expUniqueNameMsg"),
				"Duplicate workflow error message does not match!");
		wp.clickCloseWorkflow();
		sa.assertAll();
	}

	@Test(priority = 7, dependsOnMethods = { "verifyNewWorkflowCreation" })
	public void verifyResumeWorkflowWithoutNode() {
		logger.info("*** Verifying WorkflowTest: ResumeWorkflowWithoutNode ***");
		wp.clickResumeWorkflowByName(p.getProperty("workflowName"));
		String actResumeWorkflowErrorMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actResumeWorkflowErrorMsg, p.getProperty("expResumeWorkflowError"),
				"Resume workflow message does not match!");
	}

	@Test(priority = 8, dependsOnMethods = { "verifyNewWorkflowCreation" })
	public void verifyEditWorkflowOnlyName() {
		logger.info("*** Verifying WorkflowTest: EditWorkflowOnlyName ***");
		wp.clickEditWorkflowByName(p.getProperty("workflowName"));
		wp.setChatbotNameEdit(p.getProperty("updateWorkflowName"));
		wp.clickSubmitEdit();
		String actEditWorkflowMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actEditWorkflowMsg, p.getProperty("expEditWorkflowMsg"),
				"Edit workflow message does not match!");
	}

	@Test(priority = 9, dependsOnMethods = { "verifyEditWorkflowOnlyName" })
	public void verifyDeleteWorkflowByName() {
		logger.info("*** Verifying WorkflowTest: DeleteWorkflowByName ***");
		wp.clickDeleteWorkflowByName(p.getProperty("updateWorkflowName"));
		wp.clickYesDeleteConfirm();
		String actDeleteWorkflowMsg = wp.getWorkflowToastMessage();
		Assert.assertEquals(actDeleteWorkflowMsg, p.getProperty("expDeleteWorkflowMsg"),
				"Delete workflow message does not match!");
	}

}
