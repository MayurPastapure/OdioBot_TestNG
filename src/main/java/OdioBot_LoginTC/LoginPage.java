package OdioBot_LoginTC;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BaseLibrary.BaseLibrary;

public class LoginPage extends BaseLibrary{
	
	String path = "C:\\Users\\Sachin Kumar\\git\\OdioBot_TestNG\\Test-Data\\LoginDataAutomate.xlsx";
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@name=\"email\"]")
	private WebElement username;
	@FindBy(xpath="//*[@name=\"password\"]")
	private WebElement psswd;
	@FindBy(xpath="//*[text()='Login']")
	private WebElement login;
	  
	@FindBy(xpath="//*[text()='Workflow']")
	private WebElement wfclick;
	
	
	public void getTitle()
	{
		String title = driver.getTitle();
		System.out.println("Title of Odiobot :"+title);
	}
	public void getuserpass() throws InterruptedException
	{
		username.sendKeys(getReadData(path, 0, 0, 1));
		psswd.sendKeys(getReadData(path, 0, 1, 1));
		login.click();
		Thread.sleep(3000);
	}
	public void hoversidebar()
	{
		Actions act = new Actions(driver);
		  WebElement move = driver.findElement(By.xpath("//*[@class=\"MuiStack-root css-1uk2fxz\"]"));
		  act.moveToElement(move).build().perform();
		
	}
	public void getwfclick() throws InterruptedException
	{
		wfclick.click();
		Thread.sleep(5000);
		
	}

	public void getaddwfbtn()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement myWorkflow = driver.findElement(By.xpath("//*[text()='My Workflows']"));
		 wait.until(ExpectedConditions.visibilityOf(myWorkflow)).click();
//		 WebElement BtnAddWorkFlow = driver.findElement(By.xpath("//*[text()='+ Add Workflow']"));
//		 WebElement element = wait.until(ExpectedConditions.elementToBeClickable(BtnAddWorkFlow));
//		element.click();
//	
	}	 
}