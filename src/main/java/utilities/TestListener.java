package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testBase.BaseClass;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		// Get the driver from BaseClass
		WebDriver driver = BaseClass.driver;

		// Get the test method name
		String testName = result.getMethod().getMethodName();

		// Capture screenshot
		ScreenshotUtil.captureScreenshot(driver, testName);
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test suite started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test suite finished: " + context.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
}
