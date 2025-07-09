package stepDefination;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.DashboardPage;
import pageObject.KnowledgeBasePage;
import pageObject.LoginPage;
import utilities.ReadConfig;

public class StepDef extends BaseClass {
	
  SoftAssert softAssert = new SoftAssert();
	
	
	@Before
	public void setup() throws IOException{
		
		log = LogManager.getLogger("StepDef");
		log.info("Setup executed :...");
		
		readConfig = new ReadConfig(driver);
		String browser = readConfig.getBrowser();
		switch(browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
			
		case "firefox":
			driver = new FirefoxDriver();
			break;
			
		case "edge":
			driver = new EdgeDriver();
			break;
			
		default:
			driver = null;
			break;	
		}
		
	}

	@Given("User launch Chrome browser") 
	public void user_launch_chrome_browser() throws IOException {
		
		driver.manage().window().maximize();
		loginPg = new LoginPage(driver);
		knowBasePg = new KnowledgeBasePage(driver);
		dashPage = new DashboardPage(driver);
		
		log.info("User launch Chrome browser: executed :...");
		
	}

	@When("User open url {string}")
	public void user_open_url(String url) {
		driver.get(url);
		log.info("User open url: executed :...");
	}

	@When("User enter email as {string} and password as {string}")
	public void user_enter_email_as_and_password_as(String email, String password) {
		loginPg.setEmail(email);
		loginPg.setPassword(password);
		log.info("User enter emai and password : executed :...");

	}

	@When("Click on Login")
	public void click_on_login() {
		loginPg.clickSignIn();
		log.info("Click on login: executed :...");

	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		log.info("Expected page title : executed :...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs(expectedTitle));
		String actualTitle = driver.getTitle();

		softAssert.assertEquals(actualTitle, expectedTitle);
		softAssert.assertAll();

	}

	@When("User click on logout")
	public void user_click_on_logout() {
		log.info("User click on logout: executed :...");
		loginPg.clickLogout();

	}
	
	@Then("Error toast message display as {string}")
	public void error_toast_message_display(String expectedToastMessage) {
	  log.info("Login error message will displayed: executed:...");
	  String actualToastMessage = loginPg.getAlertMessage().trim().replaceAll("\\s+", " ");
	  System.out.println("Toast message: "+ actualToastMessage);
	  softAssert.assertEquals(actualToastMessage, expectedToastMessage);
	  softAssert.assertAll();
	  
	}

	@Then("Browser close")
	public void browser_close() {
		log.info("Browser close : executed :...");
	}
	
	@After
	public void tearDown(Scenario sc) throws IOException {
		
		if(sc.isFailed()== true) {
		TakesScreenshot scrnShot = (TakesScreenshot) driver;
		File source = scrnShot.getScreenshotAs(OutputType.FILE);
		String timeStamp =new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
		String screenshotName = "Screenshot_" + timeStamp + ".png";
		
		File desti = new File("C:\\Users\\Mayur\\eclipse-workspace\\OdioBDD\\Screenshots\\"+ screenshotName);
		FileHandler.copy(source, desti);
		}
		log.info("Screenshot : executed :...");
		
		driver.quit();
	}

// ***** Create new Folder *****
	
	@Then("User on dashboard page")
	public void user_on_dashboard_page() {
		log.info("User on dashboard page : executed :...");
		knowBasePg.dashboardCards();
	    
	}

	@When("User open Knowledge base page and click on Create folder")
	public void user_open_knowledge_base_page_and_click_on_create_folder() {
		log.info("User open knowledge base page and click on create folder: executed :...");
		knowBasePg.openCreateFolderPage();
	   
	}

	@When("User create new folder")
	public void user_create_new_folder() {
		log.info("User create new folder : executed :...");
		knowBasePg.createNewFolder();
	 
	}

	@Then("New folder will create")
	public void new_folder_will_create() {
		log.info("New folder will create : executed :...");
		String actualSuccessMsg = knowBasePg.getSuccessMsg().trim().replaceAll("\\s+", " ");
		String ExpectedSuccessMsg = "Success Folder created successfully";
		softAssert.assertEquals(actualSuccessMsg, ExpectedSuccessMsg);
		softAssert.assertAll();
		
	}
	
// ***** Apply dashboard filter *****
	
	@When("User click on filter")
	public void user_click_on_filter() {
		log.info("User click on filter : executed ...");
		dashPage.clickFilterSwitch();
	
	}

	@When("User select filter options and apply")
	public void user_select_filter_options_and_apply() {
		log.info("User select filter options and apply : executed ...");
		dashPage.selectMomentBktDrop("NexusBucket");
		dashPage.selectCOEDrop("New York");
		dashPage.selectDate(" Last Year ");
		dashPage.clickApplyBtn();
	
	}

	@Then("Data will appears on dashboard as per filter")
	public void data_will_appears_on_dashboard_as_per_filter() {
		log.info("Data will appears on dashboard as per filter : executed ...");
		String expectedDateFilterText = "Last Year";
		String actualDateFilterText = dashPage.getDateFilter();
		
		System.out.println("Filter selected date option : "+ actualDateFilterText);
		softAssert.assertEquals(actualDateFilterText, expectedDateFilterText);
		softAssert.assertAll();
		
	}
	
	
// *****  *****

}
