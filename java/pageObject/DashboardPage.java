package pageObject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//*[@class='bx bx-filter-alt']")
	WebElement filterSwitch;
	
	@FindBy(xpath = "(//*[@name='role'])[1]")
	WebElement momentBktDrop;
	
	@FindBy (xpath ="//*[@name='role' and @placeholder ='Select COE']")
	WebElement coeDrop;
	
	@FindBy (xpath = "//*[@name='selectDate']")
	WebElement selectDateDrop;
	
	@FindBy (xpath = "//*[@type='submit']")
	WebElement applyBtn;
	
	@FindBy (xpath = "//*[text()='Reset']")
	WebElement resetBtn;
	
	@FindBy (xpath = "//*[@class='badge rounded-pill bg-warning bg-gradient text-dark active-filter-on-table']")
	WebElement applyDateFilterEle;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	public void clickFilterSwitch() {
		wait.until(ExpectedConditions.elementToBeClickable(filterSwitch)).click();
	}
	
	public void selectMomentBktDrop (String MomentBktName) {
		wait.until(ExpectedConditions.visibilityOf(momentBktDrop));
		Select selMBkt = new Select(momentBktDrop);
		selMBkt.selectByVisibleText(MomentBktName);
	}
	
	public void selectCOEDrop (String coeName) {
		wait.until(ExpectedConditions.visibilityOf(coeDrop));
		Select selCOE = new Select(coeDrop);
		selCOE.selectByVisibleText(coeName);
	}
	
	public void selectDate (String dateOptionName) {
		wait.until(ExpectedConditions.visibilityOf(selectDateDrop));
		Select selDate = new Select(selectDateDrop);
		selDate.selectByVisibleText(dateOptionName);
	}
	
	public void clickApplyBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(applyBtn)).click();
		
	}
	
	public void clickResetBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(resetBtn)).click();
	}
	
	public String getDateFilter() {
		return wait.until(ExpectedConditions.visibilityOf(applyDateFilterEle)).getText();
	}
	

}
