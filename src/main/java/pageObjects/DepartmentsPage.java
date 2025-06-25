package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DepartmentsPage extends BasePage {

	public DepartmentsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[text()='Departments']")
	WebElement txtDepartments;

	@FindBy(xpath = "//*[text()='New Department']")
	WebElement btnNewDepartment;

	@FindBy(xpath = "//*[@name='departmentName']")
	WebElement inpDepartmentName;

	@FindBy(name = "departmentDescription")
	WebElement inpDepartmentDescription;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement btnCreateDepartment;

	@FindBy(xpath = "//*[text()='Department Name is required']")
	WebElement msgNullDepartmentName;

	@FindBy(xpath = "//*[text()='Department Description is required']")
	WebElement msgNullDepartmentDescription;
	
	@FindBy(xpath = "//input[@placeholder='Search']")
	WebElement inpSearchDept;

	public void openDepartmentsPage() {
		txtDepartments.click();
		wait.until(ExpectedConditions.visibilityOf(btnNewDepartment));
	}

	public void openNewDepartment() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnNewDepartment));
		element.click();
	}

	public void setDepartmentName(String DepartmentName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpDepartmentName));
		element.sendKeys(DepartmentName);
	}

	public void setDepartmentDescription(String DepartmentDescription) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpDepartmentDescription));
		element.sendKeys(DepartmentDescription);
	}

	public void clickCreateDepartment() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnCreateDepartment));
		element.click();
	}

	public String getNullDepartmentNameErrorMsg() {
		return msgNullDepartmentName.getText().trim();
	}

	public String getNullDepartmentDescriptionErrorMsg() {
		return msgNullDepartmentDescription.getText().trim();
	}
	
	public boolean searchByDepartmentName(String SearchableDeptName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpSearchDept));
		element.sendKeys(SearchableDeptName);
		List<WebElement> rows = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//table[contains(@class,'MuiTable-root')]/tbody/tr")));
		for (WebElement row : rows) {
			if (row.getText().toLowerCase().contains(SearchableDeptName.toLowerCase())) {
				return true;
			}

		}
		return false;
	}

}
