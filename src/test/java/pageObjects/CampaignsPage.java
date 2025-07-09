package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CampaignsPage extends BasePage {
	public CampaignsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[text()='Whatsapp AI']")
	WebElement txtWhatsappAI;

	@FindBy(xpath = "//div[text()='Campaigns']")
	WebElement txtCampaingns;

	@FindBy(xpath = "//button[text()='New Campaign']")
	WebElement btnNewCampaign;

	@FindBy(xpath = "//input[@name='campaignName']")
	WebElement inpCampaignName;

	@FindBy(xpath = "//input[@name='description']")
	WebElement inpDescription;

	@FindBy(id = "wa-template-select")
	WebElement drpWhatsAppTemplate;

	@FindBy(xpath = "//ul[@aria-labelledby='wa-template-select-label']//li")
	List<WebElement> drpWhatsAppTemplateOptions;

	@FindBy(id = "select-status")
	WebElement drpStatus;

	@FindBy(xpath = "//ul[@aria-labelledby='status-label']//li")
	List<WebElement> drpStatusOptions;

	@FindBy(id = "department-multi-select")
	WebElement drpDepartment;

	@FindBy(xpath = "//ul[@aria-labelledby='department-multi-select-label']//li")
	List<WebElement> drpDepartmentOptions;

	@FindBy(xpath = "//input[@name='checkedCSV']")
	WebElement chkUploadCSVFile;
	
	@FindBy (xpath = "//span[text()='Groups']//parent::label")
	WebElement chkGroups;
	
	@FindBy (xpath = "//span[text()='Segments']//parent::label")
	WebElement chkSegments;
	
	@FindBy (id = "group-select")
	WebElement drpGroups;
	
	@FindBy (id = "segment-select")
	WebElement drpSegments;

	@FindBy(xpath = "//ul[@role='listbox']//li")
	List<WebElement> drpGroupOrSegmentOptions;

	@FindBy(xpath = "//span[text()='Schedule Now']//parent::label")
	WebElement chkScheduleNow;

	@FindBy(xpath = "//span[text()='Schedule On']//parent::label")
	WebElement chkScheduleOn;

	@FindBy(xpath = "//input[@name='checked']")
	WebElement chkIsReccuring;

	@FindBy(xpath = "//input[@name='startDate']")
	WebElement inpStartDate;

	@FindBy(xpath = "//input[@name='endDate']")
	WebElement inpEndDate;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnCreateCampaign;

	public void openCampaignsPage() {
		txtWhatsappAI.click();
		WebElement element = wait.until(ExpectedConditions.visibilityOf(txtCampaingns));
		element.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody/tr[td[not(@colspan)]]")));
	}

	public void openNewCampaignPopup() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnNewCampaign));
		element.click();
		wait.until(ExpectedConditions.elementToBeClickable(inpCampaignName));
	}

	public void setCampaignName(String campaignName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpCampaignName));
		element.clear();
		element.click();
		if (!element.getAttribute("value").isEmpty()) {
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);
		}
		element.sendKeys(campaignName);
	}

	public void setDescription(String description) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inpDescription));
		element.sendKeys(description);
	}

	public void selectWhatsAppTemplate(String whatsAppTemplateName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(drpWhatsAppTemplate));
		element.click();
		List<WebElement> templates = wait.until(ExpectedConditions.visibilityOfAllElements(drpWhatsAppTemplateOptions));
		for (WebElement template : templates) {
			if (template.getText().trim().contains(whatsAppTemplateName)) {
				template.click();
				break;
			}
		}
	}

	public void selectStatus(String status) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(drpStatus));
		element.click();
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(drpStatusOptions));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(status)) {
				option.click();
				break;
			}
		}
	}

	public void selectDepartment(String departmentName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(drpDepartment));
		element.click();
		List<WebElement> departments = wait.until(ExpectedConditions.visibilityOfAllElements(drpDepartmentOptions));
		for (WebElement department : departments) {
			if (department.getText().trim().equalsIgnoreCase(departmentName)) {
				department.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='menu-department']")).click();
	}

	public void selectGroupRadioBtnAndGroupName(String groupName){
		WebElement btnRadio = wait.until(ExpectedConditions.elementToBeClickable(chkGroups));
		btnRadio.click();
		WebElement drpGrp = wait.until(ExpectedConditions.elementToBeClickable(drpGroups));
		drpGrp.click();
		List<WebElement> grps = wait.until(ExpectedConditions.visibilityOfAllElements(drpGroupOrSegmentOptions));
		for(WebElement grp : grps) {
			if(grp.getText().trim().equalsIgnoreCase(groupName)) {
				grp.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='menu-group']")).click();
		
	}

	public void selectSegmentsRadioBtnAndSegmentName(String segmentName){
		WebElement btnRadio = wait.until(ExpectedConditions.elementToBeClickable(chkSegments));
		btnRadio.click();
		WebElement drpSeg = wait.until(ExpectedConditions.elementToBeClickable(drpSegments));
		drpSeg.click();
		List<WebElement> segs = wait.until(ExpectedConditions.visibilityOfAllElements(drpGroupOrSegmentOptions));
		for(WebElement seg : segs) {
			if(seg.getText().trim().equalsIgnoreCase(segmentName)) {
				seg.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='menu-segment']")).click();
		
	}


	public void clickCheckboxScheduleNow() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(chkScheduleNow));
		element.click();
	}

	public void clickCheckboxScheduleOnAndSetDate(String startDate, String endDate) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(chkScheduleOn));
		element.click();
		WebElement startdate = wait.until(ExpectedConditions.elementToBeClickable(inpStartDate));
		startdate.sendKeys(startDate);
		WebElement enddate = wait.until(ExpectedConditions.elementToBeClickable(inpEndDate));
		enddate.sendKeys(endDate);
	}

	public void clickCreateCampaign() {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnCreateCampaign));
		element.click();
	}
	
	public void clickActionOfSpecificCampaign(String campaignName) {
		String dynamicXpath = "(//tr//td[text()='" + campaignName + "']//parent::tr//button[@type='button'])[3]";
		WebElement btnAction = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicXpath)));
		btnAction.click();
	}

}
