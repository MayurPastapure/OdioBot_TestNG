package pageObject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KnowledgeBasePage {
	
	WebDriver driver;
	public KnowledgeBasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	@FindBy(xpath = "//*[@class='mb-1 text-dark font-14 carusal-data']")
	WebElement card;
	
	@FindBy(xpath = "//*[@class='logo-icon']")
	WebElement logoIcon;
	
	@FindBy(xpath = "//*[text()='Coaching' and @class='menu-title']")
	WebElement coachingDrop;
	
	@FindBy(xpath = "//*[text()='Knowledge Base']")
	WebElement knowledgeBase;
	
	@FindBy(id = "search")
	WebElement searchInput;
	
	@FindBy(xpath = "//*[text()='Create Folder']")
	WebElement createFolderBtn;
	
	@FindBy(xpath = "//*[@placeholder='Enter Folder Name']")
	WebElement folderNameInput;
	
	@FindBy(xpath = "//*[@class='MuiAutocomplete-root MuiAutocomplete-hasPopupIcon css-18col2x']")
	WebElement selectCOEdrop;
	
	@FindBy(xpath = "//*[@class='custom-create-folder-btn btn btn-primary']")
	WebElement submitBtn;
	
	@FindBy(xpath= "//*[@role='alert']")
	WebElement alertToast;
	
	
	public void dashboardCards() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(card));
	}
	
	public void openCreateFolderPage() {
		logoIcon.click();
		coachingDrop.click();
		wait.until(ExpectedConditions.elementToBeClickable(knowledgeBase)).click();
	
		Actions act = new Actions(driver);
		act.moveToElement(searchInput).perform();
		wait.until(ExpectedConditions.elementToBeClickable(createFolderBtn)).click();
	}
	
	public void createNewFolder() {
		folderNameInput.sendKeys("New sample folder");
		selectCOEdrop.click();
		List <WebElement> ceoList = driver.findElements(By.xpath("//*[@role='listbox']//li"));
		for (WebElement ceo : ceoList) {
			if(ceo.getText().equals("New York")) {
				ceo.click();
				break;
			}
		}
		folderNameInput.click();
		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
	}
	
	public String getSuccessMsg() {
		return(wait.until(ExpectedConditions.visibilityOf(alertToast)).getText());
	
	}
}
